/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;

/**
 *
 * @author luisgm
 */
class ServerHandler implements Runnable {

    private final ServerFrame frame;

    private final Socket clientSocket;
    private BufferedInputStream in;
    private BufferedOutputStream out;
    private final boolean listening = true;
    private Map<String, Account> accounts = new HashMap<>();
    private Map<String, String> logged = new HashMap<>();
    private Account user;
    private String language = "";
    private String NewWel= "";
    DefaultListModel formatoLista;

    //thread constructor
    public ServerHandler(Socket clientSocket, ServerFrame frame, Map<String, String> logged) {
        this.clientSocket = clientSocket;
        this.frame = frame;
        this.logged = logged;
        init();
    }

    //thread constructor
    ServerHandler(Socket clientSocket, ServerFrame frame, Map<String, String> logged, Map<String, Account> accounts) {
        this.clientSocket = clientSocket;
        this.frame = frame;
        this.logged = logged;
        this.accounts = accounts;
        init();
    }

    //Communicate with the client via the socket
    @Override
    public void run() {

        String msg = null;

        String transTyp = "";
        connect();//Get input/output streams from client socket
        OUTER:
        //lyssan inkommande medelande
        while (listening) {
            try {
                //anropa metod för att hämta strängen med meddelande
                msg = ReadMsgFromClient();
                //dela Strängen i olika meddelande
                String[] msgList = msg.split(",");
                transTyp = msgList[0];

                switch (transTyp) {
                    case "1"://Transaction type 1 : Connection
                        language = msgList[1];
                        sendReply("Connected");
                        break;
                    case "2"://Transaction type 2 : Login
                        if (accounts.get(msgList[1]).getPass().equals(msgList[2])
                                && !logged.containsKey(msgList[1])) {
                            //Save the user state
                            logged.put(msgList[1], msgList[1]);
                            //Get the clients account
                            user = accounts.get(msgList[1]);

                            //Notify the clients state to server
                            statusUser(msgList[1] + " " + "status online");
                            //set language
                            switch (language) {
                                case "Sv"://Swedish
                                    sendReply("Valid" + "," + "Välkommen");
                                    break;
                                case "Es"://Spanish
                                    sendReply("Valid" + "," + "Bienvenido");
                                    break;
                                case "En"://English
                                    sendReply("Valid" + "," + "Welcome");
                                    break;
                                case "Close"://close communication
                                    in.close();
                                    out.close();
                                    break OUTER;
                            }
                        } else {
                            System.out.println("Ogiltig opertation!!");
                            sendReply("NoValid" + "," + language);
                        }
                        break;
                    case "3"://Transaction type 3: Withdrawal
                        //if the client is logged and the security code is valid
                        String mensaje = "";
                        if (logged.containsKey(user.getName())
                                && user.validateWithdrawal(msgList[2])) {

                            Float wdrw = Float.valueOf(msgList[1]);
                            if (wdrw <= user.getBalance()) {
                                //Parses string to integer and calls withdrawal
                                mensaje = user.withdraw(wdrw);
                                //calls get balance and get the new balance
                                String msg1 = String.valueOf(user.getBalance());
                                //notify transaction to the server Gui
                                frame.notifyStatus("2" + " , " + mensaje);
                                //Calls a new random tipsES
                                String msg2 = tips();
                                //Calls sendReply and pass the new msg to the client
                                sendReply("Valid" + "," + msg2 + "," + "$" + msg1);
                            } else {
                                sendReply("NoValid" + "," + language);
                            }
                        }
                        break;
                    case "4"://Transaction type 4: logout
                        statusUser(user.getName() + " " + "offline");
                        logged.remove(user.getName());
                        in.close();
                        out.close();

                        break OUTER;

                    case "5"://Transaction type 5: close connection 
                        frame.notifyStatus("2" + " , " + "Connection Aborted");
                        logged.remove(user.getName());
                        in.close();
                        out.close();

                        break OUTER;
                    case"6":
                        sendReply(frame.welcomeMsg.getText());
                }
            } catch (RejectedException | IOException ex) {
                Logger.getLogger(ServerHandler.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    /**
     * Get an input stream of the socket connection for reading data Get an
     * output stream of the connection for writing data.
     */
    private void connect() {
        try {
            in = new BufferedInputStream(clientSocket.getInputStream());
            out = new BufferedOutputStream(clientSocket.getOutputStream());
        } catch (IOException e) {
            System.out.println(e.toString());
        }
    }

    /**
     * Read the message from the clien.
     */
    private String ReadMsgFromClient() throws IOException {

        String msgFromTheClient = null;
        //array to store the message (Destination buffer)
        byte[] fromClient = new byte[10];
        int bytesRead = 0;
        int n;
        //loop to read bytes
        //read(Destination buffer, offset att which to start storing, maximun byte to read)
        while ((n = in.read(fromClient, bytesRead, 2)) != -1) {
            bytesRead += n;//new start

            if (bytesRead == 10) {//max bytes to read 
                break;
            }
            if (in.available() == 0) { //no more bytes to read
                break;
            }
        }
        msgFromTheClient = new String(fromClient);
        //displays with extra bytes to explain
        System.out.println("Number of bytes : " + msgFromTheClient + " "
                + msgFromTheClient.length());
        //Cleaning the extra bytes
        msgFromTheClient = msgFromTheClient.substring(0, bytesRead);
        //displays without extra bytes to explain
        System.out.println("Number of bytes : " + msgFromTheClient + " "
                + msgFromTheClient.length());

        return msgFromTheClient;
    }

    /**
     * reply to the client.
     */
    private void sendReply(String result) {
        try {
            System.out.println(result);
            // convert to byte array
            byte[] toClient = result.getBytes();
            System.out.println(toClient);
            // send message
            out.write(toClient, 0, toClient.length);
            // ensure that message is sent
            out.flush();
        } catch (IOException e) {
            System.out.println(e.toString());
        }
    }

    /**
     * Notify the server Gui.
     */
    private void statusUser(String mensaje) {
        // 1 is the message type 
        String status = "1" + " , " + " " + mensaje;
        frame.notifyStatus(status);
    }

    /**
     * New random tipsES to clients.
     */
    private String tips() {
        Random randomno = new Random();
        String news = "";

        //set language
        switch (language) {
            case "Sv"://Swedish
                String[] tipsSV = {"Nu kan företagare ta betalt med mobilen", "Skanna OCR med mobilkameran", "Behöver du hjälp? Ring Starthjälpen på 0771-606502", "Fyll på snabbsaldot utan att logga in"};
                news = tipsSV[randomno.nextInt(tipsSV.length)];
                break;
            case "Es"://Spanish
                String[] tipsES = {"Ahora los dueños de negocios pueden cobrar con el teléfono celular", "Scan OCR con cámara móvil", "¿Necesitas ayuda? Llamar a casa Ayuda en 0771-606502", "Llenar el equilibrio rápido sin necesidad de acceder"};
                news = tipsES[randomno.nextInt(tipsES.length)];
                break;
            case "En"://English
                String[] tips = {"Now business owners can charge with the cell phone", "Scan OCR with mobile camera", "Need help? Call Home Help on 0771-606502", "Fill the rapid balance without logging in"};
                news = tips[randomno.nextInt(tips.length)];
                break;
        }
        return news;

    }

    public void init() {
        frame.changeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String msg = frame.welcomeMsg.getText();
                System.out.println(msg);
                sendReply(msg);
                //String status = "1"+","+msg;
                //frame.notifyStatus(status);

            }

        });

    }
}
