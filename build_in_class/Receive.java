/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package build_in_class;
import java.util.*;
import java.io.*;
import java.net.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.concurrent.TimeoutException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JEditorPane;

/**
 *
 * @author nguye
 */
public class Receive implements Runnable
{
    public String threadName;
    public String flag;
    public String fNickname;
    public String yNickname;
    public int yPort; // port nhan mac dinh
    public InetAddress yIP; // ip
    public InetAddress fIP;
    public int fPort;
    public InetAddress sIP;
    public int sPort; // port gui
    public String content; // chua noi dung goi tin
    public String previous_cmd;
    public boolean acked;
    public boolean isRunnable = true;
    public JEditorPane t;
    public StringBuilder doc;
    public Socket dsoc;
    BufferedReader receiveFromServer;
    public String cryp_algo;
    
    public Receive()
    {
        System.out.println("Init RECEIVE");
        System.out.println("Your IP: " + yIP + "\tYour port: " + yPort);
        this.yNickname = "anonymous";
        this.fNickname = "anonymous_friend";
            
    }

    public Receive(String name)
    {
        System.out.println("Init RECEIVE");
        this.threadName = name;
        System.out.println("Thread name: " + this.threadName +"\tYour IP: " + yIP + "\tYour port: " + yPort);
        this.yNickname = "anonymous";
        this.fNickname = "anonymous_friend";
    }
    
    public void InitConnection()
    {
        try
        {
            dsoc = new Socket(this.fIP, this.fPort, this.yIP, this.yPort); // connect to server
            receiveFromServer = new BufferedReader(new InputStreamReader(dsoc.getInputStream()));
        } catch (IOException ex)
        {
            Logger.getLogger(Receive.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void run()
    {
        while(true)
        {
            try 
            {
                System.out.println("Init DatagramSocket");
                //System.out.println("Thread name: " + this.threadName +"\tYour IP: " + IPAddress.toString() + "\tYour port: " + yPort);
                System.out.println("Init RECEIVE datagramSocket successful");
                                
                // nhan goi tini
                if((this.content = receiveFromServer.readLine()) == null)
                    continue;
                this.sIP = this.fIP;
                this.sPort = this.fPort;
                System.out.println("Received packet from " + sIP + "\tPort: " + sPort);
                System.out.println("Content [" + this.content.length() + "]: " + this.content);
                parsePacket(this.content);
                
                // client don't receive HELO
                /*
                if(this.flag.equals("HELO"))
                {
                    this.fNickname = this.content.substring(0, this.content.indexOf(" "));
                    Send s = new Send("send ACK", "ack");
                    s.yIP = this.yIP;
                    s.yPort = this.yPort - 1;
                    s.IpDest = this.sIP;
                    s.PortDes = this.sPort + 1; // recv_port = send_port + 1;
                    s.run();
                }
                */
                
                if(this.flag.equals("ACK"))
                {
                    // content of packet was saved to this.content
                    acked = true;
                }
                
                if(this.flag.equals("EACK"))
                {
                    acked = false;
                }
                
                /*
                if(this.flag.equals(""))
                {
                    ds.receive(packet);
                    File currentDirectory = new File(new File(".").getAbsolutePath());
                    String p = currentDirectory.getCanonicalPath();
                    p = p + "\\ReceivedFiles";
                    File f = new File(p);
                    if(!f.exists())
                        f.mkdir();
                    File file = new File(p + this.content);
                    OutputStream output = null;
                    long totalBytesRecv = 0;
                    output = new BufferedOutputStream(new FileOutputStream(file));
                    
                    while(!parsePacket(packet).equals("eof"))
                    {
                        output.write(packet.getData());
                        totalBytesRecv = totalBytesRecv + packet.getLength();
                        ds.receive(packet);
                    }
                    
                    System.out.println("Received file " + this.content + "(" + totalBytesRecv + ")");
                }
                */
                
                if(this.flag.equals("MESS"))
                {
                    parsePacket(this.content);
                    UpdateChatText(this.content);
                }
                
                if(this.flag.equals("CRYP"))
                {
                    // this.content = method used by server
                    acked = true;
                    cryp_algo = this.content;
                }
            } catch (SocketTimeoutException ex)
            {
                if(!isRunnable)
                    return;
                Logger.getLogger(Receive.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(Receive.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
    }
    
    public String parsePacket(String packet)
    {
        String s = new String(packet);
        this.flag = s.substring(0, s.indexOf(" "));
        this.sIP = this.fIP;
        this.sPort = this.fPort;
        if(this.flag.equals("HELO") || this.flag.equals("ACK") || this.flag.equals("EACK"))
        {
            this.content = s.substring(this.flag.length() + 1, s.length());
            //this.content = this.content.substring(0, this.content.indexOf(" "));
            System.out.println("Flag [" + this.flag.length() + "]: <" + this.flag.toUpperCase() + ">");
        }
        
        if(this.flag.equals("MESS") || this.flag.equals("CHAT"))
        {
            this.content = s.substring(this.flag.length() + 1, s.length());
            System.out.println("Flag [" + this.flag.length() + "]: <" + this.flag.toUpperCase() + ">");
        }
        
        if(this.flag.equals("CRYP"))
        {
            this.content = s.substring(this.flag.length() + 1, s.length());
            System.out.println("Flag [" + this.flag.length() + "]: <" + this.flag.toUpperCase() + ">");
            System.out.println("Crypto using: " + this.content.toUpperCase());
        }
        
        if(this.flag.length() > 4 && !this.flag.equals("ACK"))
        {
            this.flag = "";
            System.out.println("Flag [" + this.flag.length() + "]: <" + this.flag.toUpperCase() + ">");
        }
        
        return this.flag;
    }
    
    public void UpdateChatText(String text)
    {
        FileReader in = null;
        BufferedReader b = null;
        File currentDirectory = new File(new File(".").getAbsolutePath());
        String p = null; 
        //StringBuilder doc = null;
        try 
        {
            p = currentDirectory.getCanonicalPath();
            in = new FileReader(p + "\\temp" + this.yNickname + ".db");
            b = new BufferedReader(in);
            doc = new StringBuilder(b.readLine());
            String temp = b.readLine();
            if(temp != null)
            {
                doc = new StringBuilder(temp);
                temp = b.readLine();
                while(temp != null)
                    doc.append(temp);
            }
        } catch (FileNotFoundException ex) 
        {
            Logger.getLogger(Receive.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Receive.class.getName()).log(Level.SEVERE, null, ex);
        } finally 
        {
            try 
            {
                in.close();
            } catch (IOException ex) 
            {
                Logger.getLogger(Receive.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        System.out.println("MESS content: " + text);
        String pic = "<img src=\"C:\\Users\\nguye\\Pictures\\11212762_776134922485724_4283480414057853955_n.jpg\" alt=\"avt\" style=\"width:24px;height:24px;\">";
        String s = "<span style=\"color:red;font-weight:bold\">&emsp <span style=\"color:blue;font-weight:bold\">" + this.fNickname.toUpperCase() + "</span> &emsp[" + this.get_date() + "]</span>: ";
        s = pic + s;

        if(text.charAt(0) == '\n')
            s = s + text.substring(1, text.length());
        else
            s = s + text;
        doc.append("\n<br>" + s + "</br>");
        s = doc.toString();
        s = "<html>" + s + "\n</html>";
        System.out.println(s);
        t.setText(s);
        write_temp();
    }
    
    public String get_date()
    {
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date date = new Date();
        System.out.println("Event at: " + dateFormat.format(date)); //06/08/2014 15:59:48
        return dateFormat.format(date);
    }
    
    public void write_temp()
    {
        FileWriter temp = null;
        BufferedWriter b = null;
        File currentDirectory = new File(new File(".").getAbsolutePath());
        String p = null;
        try
        {      
            p = currentDirectory.getCanonicalPath();
            temp = new FileWriter(p + "\\temp" + this.yNickname + ".db");
            b = new BufferedWriter(temp);
            b.write(doc.toString());
            b.close();
        } catch (IOException ex)
        {
            Logger.getLogger(Receive.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
