import java.beans.XMLEncoder;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Server extends Thread
{
	private Canvas c;
	private String hostIP;
	private int port;
	private ServerSocket s;
	private List<ObjectOutputStream> outputs;
	
	public Server(Canvas c) throws UnknownHostException, IOException
	{
		this.c = c;
		this.hostIP = "127.0.0.1";
		s = new ServerSocket(port);
		outputs = new ArrayList<>();
	}
	
	public Server(Canvas c, String hostIP, int port) throws UnknownHostException, IOException
	{
		this.c = c;
		this.hostIP = hostIP;
		this.port = port;
		s = new ServerSocket(port);
		outputs = new ArrayList<>();
	}
	
    public void run() 
    {
        try 
        {
            while (true) {
                Socket toClient = null;
                // this blocks, waiting for a Socket to the client
                toClient = s.accept();
                System.out.println("server: got client");
                // Get an output stream to the client, and add it to
                // the list of outputs
                // (our server only uses the output stream of the connection)
                addOutput(new ObjectOutputStream(toClient.getOutputStream()));
            }
        } catch (IOException ex) {
            ex.printStackTrace(); 
        }
    }

 // Sends a message to all of the outgoing streams.
    // Writing rarely blocks, so doing this on the swing thread is ok,
    // although could fork off a worker to do it.
    public synchronized void send() 
    {
    	List<DShape> shapes = c.getShapes();
    	DShapeModel[] list = new DShapeModel[shapes.size()];
    	for(int i = 0; i < list.length; i ++)
    	{
    		list[i] = shapes.get(i).getModel();
    	}
        // Convert the message object into an xml string.
        OutputStream memStream = new ByteArrayOutputStream();
        XMLEncoder encoder = new XMLEncoder(memStream);
        encoder.writeObject(list);
        encoder.close();
        String xmlString = memStream.toString();
        // Now write that xml string to all the clients.
        Iterator<ObjectOutputStream> it = outputs.iterator();
        while (it.hasNext()) {
            ObjectOutputStream out = it.next();
            try {
                out.writeObject(xmlString);
                out.flush();
            }
            catch (Exception ex) {
                ex.printStackTrace();
                it.remove();
                // Cute use of iterator and exceptions --
                // drop that socket from list if have probs with it
            }
        }
    }
    
    // Adds an object stream to the list of outputs
    // (this and sendToOutputs() are synchronzied to avoid conflicts)
    public synchronized void addOutput(ObjectOutputStream out) {
        outputs.add(out);
    }
}