import build_in_class.*;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.RenderingHints;
import java.io.*;
import java.util.*;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import java.awt.image.BufferedImage;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author nguye
 */
public class configuration extends javax.swing.JFrame {

    /**
     * Creates new form configuration
     */
    public configuration() {
        initComponents();
        own_thrd = Thread.currentThread();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lbl_username = new javax.swing.JLabel();
        lbl_ip = new javax.swing.JLabel();
        lbl_port = new javax.swing.JLabel();
        txt_username = new javax.swing.JTextField();
        txt_ip = new javax.swing.JTextField();
        txt_port = new javax.swing.JTextField();
        lbl_hint = new javax.swing.JLabel();
        btn_ok = new javax.swing.JButton();
        btn_cancel = new javax.swing.JButton();
        lbl_avatar = new javax.swing.JLabel();
        lbl_yIP = new javax.swing.JLabel();
        lbl_yPort = new javax.swing.JLabel();
        txt_password = new javax.swing.JPasswordField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Configuration");
        setName("frm_configuration"); // NOI18N
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        lbl_username.setText("Username:");

        lbl_ip.setText("Server's IP:");

        lbl_port.setText("Server's Port:");

        lbl_hint.setText("You have to enter server's IP and Port to begin:");

        btn_ok.setText("OK");
        btn_ok.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_okActionPerformed(evt);
            }
        });

        btn_cancel.setText("Cancel");
        btn_cancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_cancelActionPerformed(evt);
            }
        });

        lbl_avatar.setText("Password:");

        lbl_yIP.setText("IP:");

        lbl_yPort.setText("Port:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lbl_yIP)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lbl_yPort)
                        .addGap(111, 111, 111))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(btn_ok, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(33, 33, 33)
                                .addComponent(btn_cancel))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addComponent(lbl_hint)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lbl_port)
                                    .addComponent(lbl_ip))
                                .addGap(18, 36, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txt_ip, javax.swing.GroupLayout.DEFAULT_SIZE, 244, Short.MAX_VALUE)
                                    .addComponent(txt_port)))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lbl_username)
                                    .addComponent(lbl_avatar))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 51, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txt_username, javax.swing.GroupLayout.DEFAULT_SIZE, 247, Short.MAX_VALUE)
                                    .addComponent(txt_password))))
                        .addGap(28, 28, 28))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbl_yIP)
                    .addComponent(lbl_yPort))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbl_username)
                    .addComponent(txt_username, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbl_avatar)
                    .addComponent(txt_password, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(lbl_hint)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_ip, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbl_ip))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_port, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbl_port))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_ok)
                    .addComponent(btn_cancel))
                .addGap(0, 21, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btn_okActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_okActionPerformed
        // TODO add your handling code here:
        
        JOptionPane p1 = new JOptionPane();
        //p1.showOptionDialog(null, "Waiting for your friend to respond!!!", "Information", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, new Object[]{}, null);
        boolean flag = false;
        // check that connection was opened? if connection were opened, flag = true, else flag = false
        send.threadName = "send HELO";
        send.flag = "HELO";
        send.yIP = this.yIP;
        send.yPort = this.yPort;
        send.content = this.txt_username.getText();
        this.yUsername = send.content;
        try
        {
            send.IpDest = recv.fIP = InetAddress.getByName(this.txt_ip.getText());
            send.PortDes = recv.fPort = Integer.parseInt(this.txt_port.getText()) + 1;
            send.InitConnection();
            recv.InitConnection();
        } catch (UnknownHostException ex)
        {
            Logger.getLogger(configuration.class.getName()).log(Level.SEVERE, null, ex);
        }
        send.run();
        
        
        
        System.out.print("Waiting for acknowledge");
        int count = 0;
        while(!recv.acked)
        {
            System.out.print(".");
            if(count++ > 1000)
            {
                System.out.println("\nServer not respond");
                JOptionPane.showMessageDialog(null, "Server not respond", "ERROR", JOptionPane.ERROR_MESSAGE);
                break;
            }
        }
        
        if(recv.acked)
        {
            System.out.println("\nServer suggest crypto: " + recv.cryp_algo.toUpperCase());

            recv.acked = false;
            // send ack to server, ack for crypto
            send.flag = "ACK";
            send.threadName = "send ACK";
            send.content = "";
            send.run();

            // send USER to server
            send.threadName = "send USER";
            send.flag = "USER";
            send.content = " " + this.yUsername + " " + MyCrypto.digestMessage(this.txt_password.getText(), "MD5");
            send.run();

            System.out.print("Waiting for acknowledge");
            count  = 0;
            while(!recv.acked)
            {
                System.out.print(".");
                if(count++ > 1000)
                {
                    System.out.println("\nServer not respond");
                    JOptionPane.showMessageDialog(null, "Server not respond", "ERROR", JOptionPane.ERROR_MESSAGE);
                    break;
                }
            }
            
            if(recv.acked)
            {
                try
                {
                    this.fIP = InetAddress.getByName(this.txt_ip.getText());
                    this.fPort = Integer.parseInt(this.txt_port.getText());
                } catch (UnknownHostException ex) {
                    Logger.getLogger(configuration.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        this.dispose();
    }//GEN-LAST:event_btn_okActionPerformed

    private void btn_cancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_cancelActionPerformed
        // TODO add your handling code here:
        this.dispose();
        System.exit(0);
    }//GEN-LAST:event_btn_cancelActionPerformed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        // TODO add your handling code here:
        if(this.yIP != null)
        {
            this.lbl_yIP.setText("IP: " + this.yIP.toString());
            this.lbl_yPort.setText("Port: " + this.yPort);
        }
        
        Object[] options = {"Not yet", "Yes"};
        int n = (int) JOptionPane.showInputDialog(null, "Have you had your account?", "Prompt", JOptionPane.YES_NO_OPTION, null, options, options[1]);
        if(n == 0) // not yet
        {
            String user = JOptionPane.showInputDialog(null, "Enter username to create: ");
            if(user != null)
            {
                JPanel panel = new JPanel();
                JLabel label = new JLabel("Enter password for user:");
                JPasswordField pass = new JPasswordField(100);
                panel.add(label);
                panel.add(pass);
                options = new String[]{"OK", "Cancel"};
                int option = JOptionPane.showOptionDialog(null, panel, "The title",
                                         JOptionPane.NO_OPTION, JOptionPane.PLAIN_MESSAGE,
                                         null, options, options[1]);
                if(option == 0) // pressing OK button
                {
                    String password = new String(pass.getPassword());
                    System.out.println("Your password is: " + new String(password));
                    
                    // get server information
                    JPanel myPanel = new JPanel();
                    JTextField ip = new JTextField(100);
                    JTextField port = new JTextField(100);
                    myPanel.add(ip);
                    myPanel.add(port);
                    JOptionPane.showMessageDialog(null, myPanel);
                    
                    send.threadName = "send NEW";
                    try
                    {
                        send.IpDest = InetAddress.getByName(ip.getText());
                        send.PortDes = Integer.parseInt(port.getText());
                        send.flag = "NEW";
                        send.content = " " + user + " " + MyCrypto.digestMessage(password, "MD5");
                        send.run();
                    } catch (UnknownHostException ex)
                    {
                        Logger.getLogger(configuration.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        }
        
    }//GEN-LAST:event_formWindowOpened

    public static File FileChooser()
    {
        File chosenFile = null;
        boolean flag = false;
        JFileChooser chooser = new JFileChooser();
        
        while(flag == false)
        {
            int choice = chooser.showOpenDialog(null);

            if (choice != JFileChooser.APPROVE_OPTION)
                return null;

            chosenFile = chooser.getSelectedFile();

            String filePath = chosenFile.getAbsolutePath();
            if(!(filePath.endsWith(".jpg") || filePath.endsWith(".png") || filePath.endsWith("bmp")))
            {
                JOptionPane.showMessageDialog(null, "File chosen must be image file.");
            }
            else
                flag = true;
        }
        return chosenFile;
    }
    
    /**
     * @param args the command line arguments
     */
    public static void configuration() {
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
            java.util.logging.Logger.getLogger(configuration.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(configuration.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(configuration.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(configuration.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new configuration().setVisible(true);
            }
        });
    }
    
    public String yUsername;
    public String yPassword;
    public String cryp_algo;
    public InetAddress yIP;
    public int yPort;
    public InetAddress fIP;
    public int fPort;
    public boolean isClosed = false;
    public Receive recv;
    public Send send;
    public Thread own_thrd;
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_cancel;
    private javax.swing.JButton btn_ok;
    private javax.swing.JLabel lbl_avatar;
    private javax.swing.JLabel lbl_hint;
    private javax.swing.JLabel lbl_ip;
    private javax.swing.JLabel lbl_port;
    private javax.swing.JLabel lbl_username;
    private javax.swing.JLabel lbl_yIP;
    private javax.swing.JLabel lbl_yPort;
    public javax.swing.JTextField txt_ip;
    private javax.swing.JPasswordField txt_password;
    public javax.swing.JTextField txt_port;
    public javax.swing.JTextField txt_username;
    // End of variables declaration//GEN-END:variables
}

class ImageImplement extends JPanel
{
    private Image img; 
    public ImageImplement(Image img)
    { 
        this.img = img; Dimension size = new Dimension(img.getWidth(null), img.getHeight(null)); 
        setPreferredSize(size); 
        setMinimumSize(size); 
        setMaximumSize(size); 
        setSize(size); 
        setLayout(null); 
    } 
    public void paintComponent(Graphics g) 
    { 
        g.drawImage(img, 122, 69, 132, 78, null); 
    } 
}


