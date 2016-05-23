import java.net.SocketException;

public class Client extends Thread
{
	private Canvas c;
	private String hostIP;
	private int port;
	private BoardSocket line;
	
	public Client(Canvas c) throws SocketException
	{
		this.c = c;
		this.hostIP = "127.0.0.1";
		line = new BoardSocket();
	}
	
	public Client(Canvas c, String hostIP, int port) throws SocketException
	{
		this.c = c;
		this.hostIP = hostIP;
		this.port = port;
		line = new BoardSocket(port);
	}
	
	public void run()
	{
		while(true)
		{
			String msg = line.receive();
		}
	}

}
