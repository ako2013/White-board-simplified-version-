import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class BoardSocket extends DatagramSocket
{
   protected static final int CAP = 80; // receive's buffer capacity
   DatagramPacket packet; 
   
   public BoardSocket(int port) throws SocketException 
   {
      super(port);
   }
   
   public BoardSocket() throws SocketException 
   {
      this(39587);
   }
   
   public String receive() 
   {
      byte[] buffer = new byte[CAP];
      try 
      {
         packet = new DatagramPacket(buffer, CAP);
         super.receive(packet);
      } 
      catch(IOException e) 
      {
         System.err.println(e.getMessage());
      }
      return new String(buffer);
   }
   
   public void send(InetAddress hostIP, int port, String msg) 
   {
      try 
      {
         byte[] buffer = msg.getBytes();
         DatagramPacket packet = 
            new DatagramPacket(buffer, buffer.length, hostIP, port);
         super.send(packet);
      } 
      catch(IOException e) 
      {
         System.err.println(e.getMessage());
      }
   }
   
   public void send(String host, int port, String msg) 
   {
      try 
      {
         InetAddress hostIP = InetAddress.getByName(host);
         send(hostIP, port, msg);
      } 
      catch(IOException e) 
      {
         System.err.println(e.getMessage());
      }
   }
}