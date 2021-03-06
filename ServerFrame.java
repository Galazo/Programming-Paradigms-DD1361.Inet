/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import java.text.DateFormat;
import java.util.Date;
import javax.swing.DefaultListModel;

/**
 *
 * @author luisgm
 */
public class ServerFrame extends javax.swing.JFrame{

    private Server server;

    DefaultListModel modeloLista;

    /**
     * Creates new form NewJFrame
     */
    public ServerFrame() {
        this.setVisible(true);
        initComponents();
        modeloLista = new DefaultListModel();
        statusList.setModel(new DefaultListModel());
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        ipAdressTxtFl = new javax.swing.JTextField();
        ipLabel = new javax.swing.JLabel();
        portTxtF = new javax.swing.JTextField();
        portLabel = new javax.swing.JLabel();
        startButton = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        statusLb = new javax.swing.JLabel();
        welcomeMsg = new javax.swing.JTextField();
        changeButton = new javax.swing.JButton();
        WelcomeLb = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        statusList = new javax.swing.JList();
        jSeparator2 = new javax.swing.JSeparator();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("MyServer By luisgm@kth.se");
        setBounds(new java.awt.Rectangle(600, 600, 0, 0));
        setMinimumSize(new java.awt.Dimension(624, 424));
        setName("JVentanaServer"); // NOI18N
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setMaximumSize(new java.awt.Dimension(624, 424));
        jPanel2.setPreferredSize(new java.awt.Dimension(624, 424));
        jPanel2.setSize(new java.awt.Dimension(624, 424));

        ipAdressTxtFl.setText("localhost");

        ipLabel.setText("Ip Adress:");

        portTxtF.setText("8080");

        portLabel.setText("Port:");

        startButton.setText("Start");
        startButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                startButtonActionPerformed(evt);
            }
        });

        statusLb.setText("Server Status:");

        welcomeMsg.setText("Welcome !");

        changeButton.setText("Change");
        changeButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                changeButtonActionPerformed(evt);
            }
        });

        WelcomeLb.setText("Welcome message: ");

        jScrollPane4.setViewportView(statusList);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(statusLb)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(ipLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(ipAdressTxtFl, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(35, 35, 35)
                        .addComponent(portLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(portTxtF, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(startButton, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(WelcomeLb)
                        .addGap(2, 2, 2)
                        .addComponent(welcomeMsg, javax.swing.GroupLayout.PREFERRED_SIZE, 278, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(changeButton, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 570, Short.MAX_VALUE)
                    .addComponent(jSeparator1)
                    .addComponent(jSeparator2))
                .addContainerGap(28, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(welcomeMsg, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(changeButton)
                    .addComponent(WelcomeLb))
                .addGap(13, 13, 13)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(statusLb)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 33, Short.MAX_VALUE)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ipLabel)
                    .addComponent(ipAdressTxtFl, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(portTxtF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(portLabel)
                    .addComponent(startButton))
                .addGap(33, 33, 33))
        );

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        pack();
    }// </editor-fold>                        

    private void changeButtonActionPerformed(java.awt.event.ActionEvent evt) {                                             
       /* new Thread(new Runnable() {
         @Override
         public void run() {

         String msg = welcomeMsg.getText();//no usado
         }
         }).start();*/
    }                                            

    private void startButtonActionPerformed(java.awt.event.ActionEvent evt) {                                            
        //Start up the Server
        new Thread(new Runnable() {
            @Override
            public void run() {

                startButton.setEnabled(true);//visible
                String ip = ipAdressTxtFl.getText();//no usado
                int port = Integer.parseInt(portTxtF.getText());//parsa el string
                server = new Server();//nuevo server
                server.startServer(port);//Pass the port and start the server

            }
        }).start();
    }                                           

    public void notifyStatus(String status) {
        String mensaje = "";
        System.out.println("Notifying...");
        //New Event Date 
        DateFormat formatoFecha = DateFormat.getDateTimeInstance();
        String fecha = formatoFecha.format(new Date());
        String[] msgStatus = status.split(" , ");

        if (msgStatus[0].equals("1")) {
            mensaje = fecha + " New User " + msgStatus[1];
        } else {
            //Status message
            mensaje = fecha + " Server status: " + msgStatus[1];
        }

        //Get info and add new status
        modeloLista = (DefaultListModel) statusList.getModel();
        modeloLista.addElement(mensaje);
    }

    /**
     * @param args the command line arguments
     */
   public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ServerFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ServerFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ServerFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ServerFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
       java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new ServerFrame().setVisible(true);
            }
        });
    }


    // Variables declaration - do not modify                     
    private javax.swing.JLabel WelcomeLb;
    public javax.swing.JButton changeButton;
    private javax.swing.JTextField ipAdressTxtFl;
    private javax.swing.JLabel ipLabel;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JLabel portLabel;
    private javax.swing.JTextField portTxtF;
    private javax.swing.JButton startButton;
    private javax.swing.JLabel statusLb;
    public javax.swing.JList statusList;
    public javax.swing.JTextField welcomeMsg;
    // End of variables declaration                   

}

