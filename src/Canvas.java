import java.util.LinkedList;

import javax.swing.JPanel;

public class Canvas extends JPanel
{
	private final int DEFAULT_LENGTH = 400; 
	private final int DEFAULT_WIDTH =	400;	
	
	private LinkedList<DShape> list;
	
	public Canvas()
	{
		setSize(DEFAULT_LENGTH, DEFAULT_WIDTH);
	}
	
	public void addRect()
	{
		DRect r = new DRect();
		list.add(r); 
	}
	
	public void addOval()
	{
		list.add(new DOval());
	}
}
