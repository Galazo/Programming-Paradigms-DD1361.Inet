/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author luisgm
 */
public class Server  {

    private static ServerFrame frame;
    private static Map<String, String> logged;
    private Map<String, Account> accounts;

    /**
     * Create a ServerSocket object. The ServerSocket constructor requires a
     * port number as an argument. For example:
     * <code>ServerSocket serverSocket = new ServerSocket(1234);</code> then
     * Notify the Servers GUI Put theserver into a waiting state. The server
     * waits indefinitely (‘blocks’) for a client to connect. It does this by
     * calling method accept of class ServerSocket, which returns a Socket
     * object when a connection is made. For example:
     * <code> Socket clientSocket = serverSocket.accept();</code> then Notify
     * the Servers Gui. Create a handler thread and pass referenser      
     * <code> new Thread(new ServerHandler(clientSocket, frame, 
     * logged, accounts)).start();</code>
     *
     * @author Luis Galaz
     * @author Karl fridlund
     * @param port
     */
    public void startServer(int port) {
        boolean listening = true;
        ServerSocket serverSocket = null;

        try {
            //Create a ServerSocket object
            //Skapa ett Serversocket-objekt
            //Crear un objeto ServerSocket
            serverSocket = new ServerSocket(port);

            //Notify Server Status
            //Meddela Server Status
            //Notificar estado del servidor
            String msj1 = "Mode: on ";
            frame.notifyStatus("2" + " , " + msj1);

            //Start bank accounts
            //Starta bankkonton
            //Iniciar cuentas de banco
            startBankAccounts();
            //The server's main loop, listen for incoming connections and handle request
            //Serverns huvudslinga, lyssna efter inkommande anslutningar och handtag förfrågan
            // Bucle principal del servidor, escucha las conexiones entrantes y manejar petición
            while (listening) {
                //wait for a client connection request
                //Blocks the current thread until a client connects
                //Returns a connected Socket of the accepted connection.
                Socket clientSocket = serverSocket.accept();
                //notifyServerStatus
                String msj = "listening on port: ";
                msj += String.valueOf(port);
                frame.notifyStatus("2" + " , " + msj);
                //Create a handler thread and pass referenser 
                new Thread(new ServerHandler(clientSocket, frame, logged, accounts)
                ).start();

            }

        } catch (IOException ex) {
            //notify Server Status
            String msj1 = "Unable to attach to port!";
            frame.notifyStatus("2" + " , " + msj1);
            System.out.println("Unable to attach to port!");
            System.exit(1);
        }

    }

    /**
     * Crea e inicia las cuentas bancarias y los codigos de seguridad. Skapar
     * och startar bankkonton och säkerhetskoder. Creates and starts bank
     * accounts and security securityCodes.
     */
    private void startBankAccounts() {
        //security securityCodes
        int[] securityCodes = new int[50];
        int i = 0;
        for (int counter = 1; counter < 100; counter = counter + 2) {
            securityCodes[i] = counter;
            i++;
        }
        //create Bank accounts
        accounts = new HashMap<>();
        Account cuentas[] = {
            new AccountImpl("0000", "000", 10000, securityCodes),
            new AccountImpl("1111", "111", 10000, securityCodes),
            new AccountImpl("2222", "222", 10000, securityCodes),
            new AccountImpl("3333", "333", 10000, securityCodes),
            new AccountImpl("4444", "444", 10000, securityCodes),
            new AccountImpl("5555", "555", 10000, securityCodes)
        };
        //stores bank accounts
        for (Account cuenta : cuentas) {
            accounts.put(cuenta.getName(), cuenta);
        }

    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        /* Set the Nimbus look and feel */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ServerFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }//End Nimbus Design 

        logged = new HashMap<>();// to save the user state
        frame = new ServerFrame();//Server Gui

    }
}
