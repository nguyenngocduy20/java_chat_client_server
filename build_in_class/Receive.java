/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package build_in_class;
import java.util.*;
import java.io.*;
import java.net.*;
import java.security.NoSuchAlgorithmException;
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
    public List<People> friendList = new ArrayList();
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
    public ServerSocket dsoc;
    public Socket server2client;
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
            dsoc = new ServerSocket(this.yPort, 5, this.yIP); // connect to server
        } catch (IOException ex)
        {
            Logger.getLogger(Receive.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void run()
    {
        try
        {
            server2client = dsoc.accept();
            receiveFromServer = new BufferedReader(new InputStreamReader(server2client.getInputStream()));
        } catch (IOException ex)
        {
            Logger.getLogger(Receive.class.getName()).log(Level.SEVERE, null, ex);
        }
        
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
                    if(this.previous_cmd.equals("LIST"))
                    {
                        People p = new People();
                        p.username = this.content.substring(0, this.content.indexOf("|"));
                        this.content = this.content.substring(p.username.length() + 1, this.content.length());
                        if(this.content.charAt(0) == 0)
                            p.status = false;
                        else
                            p.status = true;
                        
                        this.content = this.content.substring(2, this.content.length());
                        p.ip = InetAddress.getByName(this.content.substring(0, this.content.indexOf("|")));
                        this.content = this.content.substring(p.ip.toString().length(), this.content.length());
                        p.port = Integer.parseInt(this.content);
                        this.friendList.add(p);
                    }
                }
                
                if(this.flag.equals("EACK"))
                {
                    acked = false;
                    if(this.previous_cmd.equals("LIST"))
                    {
                        People p = new People();
                        p.username = this.content.substring(0, this.content.indexOf("|"));
                        this.content = this.content.substring(p.username.length() + 1, this.content.length());
                        if(this.content.charAt(0) == 0)
                            p.status = false;
                        else
                            p.status = true;
                        
                        this.content = this.content.substring(2, this.content.length());
                        p.ip = InetAddress.getByName(this.content.substring(0, this.content.indexOf("|")));
                        this.content = this.content.substring(p.ip.toString().length(), this.content.length());
                        p.port = Integer.parseInt(this.content);
                        this.friendList.add(p);
                    }
                    this.previous_cmd = "";
                    
                    File currentDirectory = new File(new File(".").getAbsolutePath());
                    String p;
                    for(int i = 0; i < this.friendList.size(); i++)
                    {
                        p = currentDirectory.getCanonicalPath();
                        File file = new File(p + "/temp_" + friendList.get(i).username + ".db");
                        FileWriter temp = new FileWriter(file);
                        BufferedWriter b = new BufferedWriter(temp);
                        b.write("<html>\n</html>");
                    }
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
        String from = text.substring(0, text.indexOf(" "));
        text = text.substring(from.length() + 1, text.length());
        String to = text.substring(0, text.indexOf(" "));
        text = text.substring(to.length() + 1, text.length());
        
        try
        {
            text = MyCrypto.symDecryptMessage(MyCrypto.keyToString(MyCrypto.secretKeyGen_S("DuyDuyBuDai255" + this.yNickname)), text);
        } catch (NoSuchAlgorithmException ex)
        {
            Logger.getLogger(Receive.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedEncodingException ex)
        {
            Logger.getLogger(Receive.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        System.out.println("MESS content: " + text);
        String pic = "<img src=\"C:\\Users\\nguye\\Pictures\\11212762_776134922485724_4283480414057853955_n.jpg\" alt=\"avt\" style=\"width:24px;height:24px;\">";
        String s = "<span style=\"color:red;font-weight:bold\">&emsp <span style=\"color:blue;font-weight:bold\">" + this.fNickname.toUpperCase() + "</span> &emsp[" + this.get_date() + "]</span>: ";
        s = pic + s;

        if(text.charAt(0) == '\n')
            s = s + text.substring(1, text.length());
        else
            s = s + text;
        
        File currentDirectory = new File(new File(".").getAbsolutePath());
        String p;
        File html = null;
        RandomAccessFile b = null;
        try
        {
            p = currentDirectory.getCanonicalPath();
            html = new File(p + "/temp.db");
            b = new RandomAccessFile(html, "rw");
            b.seek(html.length() - 7);
            b.write(("<br>" + s + "</br></html>").getBytes(), 0, ("<br>" + s + "</br></html>").length());
            b.close();
            this.t.setPage(html.toURI().toURL());
        }
        catch (IOException ex)
        {
        }
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
