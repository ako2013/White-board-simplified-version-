import javax.swing.JPanel;

public class Canvas extends JPanel
{
	private final int DEFAULT_LENGTH = 400; 
	private final int DEFAULT_WIDTH =	400;	
	
	public Canvas()
	{
		setSize(DEFAULT_LENGTH, DEFAULT_WIDTH);
	}
}
