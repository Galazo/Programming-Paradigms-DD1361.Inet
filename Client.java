package client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author luisgm
 */
public class Client {

    private DataInputStream in;
    private DataOutputStream out;
    private Socket clientSocket;
   
    /**
     * 1. Establish a connection to the server. We create a Socket object,
     * supplying its constructor with the following two arguments: • the
     * server’s IP address (of type InetAddress); • the appropriate port number
     * for the service. For example:
     * <code>Socket clientSocket = new Socket(ip, port); </code>
     *
     * 2. Set up input and output streams. These are set up in exactly the same
     * way as the server streams were set up (by calling methods getInputStream
     * and getOutputStream).
     *
     * @param ip
     * @param port
     */
    public void accessServer(String ip, int port) {

        try {
            //Open socket connection to server with hostname and port
            clientSocket = new Socket(ip, port);
            //Open the input/output streams from the client socket
            out = new DataOutputStream(clientSocket.getOutputStream());
            in = new DataInputStream(clientSocket.getInputStream());
            //ClientHandler c = new ClientHandler(clientSocket, frameC);
            System.out.println("Connected to The Server...");
        } catch (IOException ex) {
            System.err.println("Couldn't get I/O for "
                    + "the connection to: " + port + "");
            System.exit(1);
        }
    }

    /**
     *
     * @param trans
     * @return 
     */
    public String MakeTransac(TransactionInterface trans) {
        String transReply = "";
        String transToServer = "";

        switch (trans.getType()) {
            case "1": //informs connection and language
                transToServer = trans.getType() + "," + trans.getLanguage();
                break;
            case "2":// sends type login, username and password
                transToServer = trans.getType() + "," + trans.getUser() + "," + trans.getPss();
                break;
            case "3"://sends type withdrawal , amount and security code
                transToServer = trans.getType() + "," + trans.getAmount() + "," + trans.getSCode();
                break;
            case "6":
                transToServer= trans.getType();
        }

        try {
            byte[] toServer = transToServer.getBytes();// convert to byte array
            if (toServer.length > 10) { //max 10 bytes
                System.out.println("illegal operation!!");
            } else {
                out.write(toServer, 0, toServer.length);// send message
                out.flush();// ensure that message is sent

                //Receive  message from server
                byte[] fromServer = new byte[100];// allocate buffer
                int n = in.read(fromServer, 0, 100);//read servers message
                transReply = new String(fromServer);//create a new string
                transReply = transReply.substring(0, n);//cleaning extra bytes
            }
        } catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
        return transReply;

    }

    public void Disconnect(String msg) {

        try {
            String closeConnectionMsg = msg;
            //convert to byte array
            byte[] toServer = closeConnectionMsg.getBytes();
            // send message
            out.write(toServer, 0, toServer.length);
            // ensure that message is sent
            out.flush();

            out.close();// close output stream
            in.close();// close input stream
            clientSocket.close();// close connection

        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }

}
