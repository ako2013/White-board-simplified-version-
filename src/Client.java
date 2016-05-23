import java.beans.XMLDecoder;
import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;

public class Client extends Thread
{
	private Canvas c;
	private String hostIP;
	private int port;
	private Socket s;
	
	public Client(Canvas c) throws UnknownHostException, IOException
	{
		this.c = c;
		this.hostIP = "127.0.0.1";
		this.port = 39587;
		s = new Socket(hostIP, port);
	}
	
	public Client(Canvas c, String hostIP, int port) throws UnknownHostException, IOException
	{
		this.c = c;
		this.hostIP = hostIP;
		this.port = port;
		s = new Socket(hostIP, port);
	}
	
	public void run()
	{
		while(true)
		{
			try
			{
				ObjectInputStream in = new ObjectInputStream(s.getInputStream());
				while (true) 
				{
					String xmlString = (String) in.readObject();
					XMLDecoder xmlIn = new XMLDecoder(new BufferedInputStream( new FileInputStream (xmlString)));
			   		DShapeModel[] list = (DShapeModel[]) xmlIn.readObject();
			   		xmlIn.close();
			   		for(DShapeModel d: list)
			   		{
			   			c.addShape(d);
			   		}
			   		c.repaint();
				}
			}
			catch (Exception e)
			{
				
			}    

            
		}
	}

}
