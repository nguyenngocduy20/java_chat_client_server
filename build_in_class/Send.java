/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package build_in_class;
import java.util.*;
import java.io.*;
import java.net.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author nguye
 */
public class Send implements Runnable
{
    public String threadName;
    public String flag; // hello: gui hello den peer
                        // ack: xac nhan da nhan duoc
                        // mess: tin nhan
                        // file: command gui file
                        // star: signal bat dau nhan file
                        // pubk: public key
                        // eof: het file
                        // send: gui file
    public String content;  // hello: <nickname> <ip> <port_send>
                            // ack: <ip> <port_send>
                            // mess: <from> <to> <content>
                            // file: <file_name>
                            // star: <file_name>
                            // eof: <file_name>
                            // pubk: <pem>
                            // send: <path_to_file>
    public InetAddress IpDest;
    public int PortDes;
    public int yPort; // port for sending
    public InetAddress yIP; // your IP
    public Socket dsoc;
    DataOutputStream send2Server;
    
    public Send(String name)
    {
        System.out.println("Init Thread Send");
        this.threadName = name;
        System.out.println("Thread name: " + this.threadName);
    }
    
    public Send(String name, String flag)
    {
        System.out.println("Init Thread Send");
        this.threadName = name;
        this.flag = flag.toUpperCase();
        System.out.println("Thread name: " + this.threadName);
    }
    
    public void InitConnection()
    {
        
        try
        {
            dsoc = new Socket(IpDest, PortDes, yIP, yPort); // connect to server
            send2Server = new DataOutputStream(dsoc.getOutputStream());
        } catch (IOException ex)
        {
            Logger.getLogger(Send.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void run()
    {
        System.out.println("Your IP: " + this.yIP.toString() + "\tYour port: " + this.yPort);
        System.out.println("Init DatagramSocket");
        System.out.println("Datagram Init Successful");
        
        
        if(flag.equals("HELO"))
        {
            try
            {
                byte[] sendData = this.flag.getBytes();
                System.out.println("Packet: " + new String(sendData));
                System.out.println("Dest IP: " + IpDest.toString() + "\nDest Port: " + PortDes);
                send2Server.write(sendData);
                System.out.println("Packet sent!");
                System.out.println("DatagramSocket Closed");
            } catch (IOException ex)
            {
                Logger.getLogger(Send.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
        
        
        if(flag.equals("ACK"))
        {
            System.out.println("Packet ACK");
            try
            {
                this.content = " " + this.content;
                byte[] sendData = (this.flag + this.content).getBytes();
                System.out.println("Packet: " + new String(sendData));
                System.out.println("Dest IP: " + IpDest.toString() + "\nDest Port: " + PortDes);
                send2Server.write(sendData);
                System.out.println("Packet sent!");
                dsoc.close();
                System.out.println("DatagramSocket Closed");
            } catch (IOException ex)
            {
                Logger.getLogger(Send.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        
        if(flag.equals("USER"))
        {
            System.out.println("Packet USER");
            try
            {
                this.content = " " + this.content;
                byte[] sendData = (this.flag + this.content).getBytes();
                System.out.println("Packet: " + new String(sendData));
                System.out.println("Dest IP: " + IpDest.toString() + "\nDest Port: " + PortDes);
                send2Server.write(sendData);
                System.out.println("Packet sent!");
                dsoc.close();
                System.out.println("DatagramSocket Closed");
            } catch (IOException ex)
            {
                Logger.getLogger(Send.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        
        if(flag.equals("NEW"))
        {
            System.out.println("Packet NEW");
            try
            {
                this.content = " " + this.content;
                byte[] sendData = (this.flag + this.content).getBytes();
                System.out.println("Packet: " + new String(sendData));
                send2Server.write(sendData);
                System.out.println("Packet sent!");
                dsoc.close();
                System.out.println("DatagramSocket Closed");
            } catch (IOException ex)
            {
                Logger.getLogger(Send.class.getName()).log(Level.SEVERE, null, ex);                
            }
        }
        
        
        if(flag.equals("CHAT"))
        {
            
            System.out.println("Packet CHAT");
            try
            {
                this.content = " " + this.content;
                byte[] sendData = (this.flag + this.content).getBytes();
                System.out.println("Packet: " + new String(sendData));
                send2Server.write(sendData);
                System.out.println("Packet sent!");
                dsoc.close();
                System.out.println("DatagramSocket Closed");
            } catch (IOException ex)
            {
                Logger.getLogger(Send.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        
        if(flag.equals("MESS"))
        {
            System.out.println("Packet MESS");
            try
            {
                byte[] sendData = (flag + " " + content).getBytes();
                System.out.println("Packet: " + new String(sendData));
                send2Server.write(sendData);
                System.out.println("Packet sent!");
                dsoc.close();
                System.out.println("DatagramSocket Closed");
            } catch (IOException ex)
            {
                Logger.getLogger(Send.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        
        if(flag.equals("LIST"))
        {
            
            System.out.println("Packet LIST");
            try
            {
                byte[] sendData = flag.getBytes();
                System.out.println("Packet: " + new String(sendData));
                send2Server.write(sendData);
                System.out.println("Packet sent!");
                dsoc.close();
                System.out.println("DatagramSocket Closed");
            } catch (IOException ex)
            {
                Logger.getLogger(Send.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        
        if(flag.equals("ADDR"))
        {
            System.out.println("Packet ADDR");
            try
            {
                String temp = this.yIP.toString();
                temp = temp.substring(1).replace('.', ','); 
                
                int i = 1;
                while(i*256 < this.yPort)
                {
                    i = i + 1;
                }
                i = i -1;
                temp = temp + "," + i + "," + (this.yPort - i*256);
                
                byte[] sendData = (flag + " " + temp).getBytes();
                System.out.println("Packet: " + new String(sendData));
                send2Server.write(sendData);
                System.out.println("Packet sent!");
                dsoc.close();
                System.out.println("DatagramSocket Closed");
            } catch (IOException ex)
            {
                Logger.getLogger(Send.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
        
        // QUIT
        if(this.flag.equals("QUIT"))
        {
            System.out.println("Packet QUIT");
            try
            {
                byte[] sendData = flag.getBytes();
                System.out.println("Packet: " + new String(sendData));
                send2Server.write(sendData);
                System.out.println("Packet sent!");
                dsoc.close();
                System.out.println("DatagramSocket Closed");
            } catch (IOException ex)
            {
                Logger.getLogger(Send.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        // ROCO
        if(this.flag.equals("ROCO"))
        {
            System.out.println("Packet ROCO");
            try
            {
                byte[] sendData = flag.getBytes();
                System.out.println("Packet: " + new String(sendData));
                send2Server.write(sendData);
                System.out.println("Packet sent!");
                dsoc.close();
                System.out.println("DatagramSocket Closed");
            } catch (IOException ex)
            {
                Logger.getLogger(Send.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        // MOD <username> <old_password_md5> <new_password_md5>
        if(this.flag.equals("MOD"))
        {
            
            System.out.println("Packet ROCO");
            try
            {
                byte[] sendData = (flag + this.content).getBytes();
                System.out.println("Packet: " + new String(sendData));
                send2Server.write(sendData);
                System.out.println("Packet sent!");
                dsoc.close();
                System.out.println("DatagramSocket Closed");
            } catch (IOException ex)
            {
                Logger.getLogger(Send.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        /*
        if(flag.equals("file"))
        {
            System.out.println("Packet FILE");
            try
            {
                byte[] sendData = (flag + " " + content).getBytes();
                System.out.println("Packet: " + new String(sendData));
                System.out.println("Dest IP: " + IpDest.toString() + "\nDest Port: " + PortDes);
                send2Server.write(sendData);
                System.out.println("Packet sent!");
                dsoc.close();
                System.out.println("DatagramSocket Closed");
            } catch (IOException ex)
            {
                Logger.getLogger(Send.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if(flag.equals("star"))
        {
            System.out.println("Packet STAR");
            try
            {
                byte[] sendData = (flag + " " + content).getBytes();
                System.out.println("Packet: " + new String(sendData));
                System.out.println("Dest IP: " + IpDest.toString() + "\nDest Port: " + PortDes);
                DatagramPacket sendPacket = new DatagramPacket(sendData, (flag + " " + content).length(), IpDest, PortDes);
                dsoc.send(sendPacket);
                System.out.println("Packet sent!");
                dsoc.close();
                System.out.println("DatagramSocket Closed");
                
                Receive r = new Receive("Receive FILE");
                r.content = content; // content = file_name
                r.flag = "";
                r.run();
                
            } catch (IOException ex)
            {
                Logger.getLogger(Send.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if(flag.equals("send"))
        {
            System.out.println("Packet SEND");
            File file = new File(content); // content = file_path
            byte[] result = new byte[16374];
            InputStream input = null;
            
            try
            {
                long totalBytesRead = 0;
                input = new BufferedInputStream(new FileInputStream(file));
                long file_length = file.length();
                while (totalBytesRead < file_length) 
                {
                    int bytesRead = input.read(result, (int) totalBytesRead, 16384);
                    if(bytesRead > 0)
                    {
                        totalBytesRead = totalBytesRead + bytesRead;
                        DatagramPacket sendPacket = new DatagramPacket(result, result.length, IpDest, PortDes);
                        dsoc.send(sendPacket);
                        System.out.println("Send file, part from " + (totalBytesRead - bytesRead) + " to " + totalBytesRead);
                    }
                }
                
                System.out.println("All file sent!");
                dsoc.close();
                System.out.println("DatagramSocket Closed");
                
                input.close();
                Send s = new Send("send EOF", "oef");
                s.content = content.substring(content.lastIndexOf('\\'), content.length());
                s.run();
            } catch (FileNotFoundException ex)
            {
                Logger.getLogger(Send.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(Send.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
        if(flag.equals("eof"))
        {
            System.out.println("Packet EOF");
            try
            {
                byte[] sendData = (flag + " " + content).getBytes();
                System.out.println("Packet: " + new String(sendData));
                System.out.println("Dest IP: " + IpDest.toString() + "\nDest Port: " + PortDes);
                DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IpDest, PortDes);
                dsoc.send(sendPacket);
                System.out.println("Packet sent!");
                dsoc.close();
                System.out.println("DatagramSocket Closed");
            } catch (IOException ex)
            {
                Logger.getLogger(Send.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        */
        
    }
    
}
