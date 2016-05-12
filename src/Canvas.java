import java.awt.Color;
import java.awt.Graphics;
import java.util.LinkedList;

import javax.swing.JPanel;

public class Canvas extends JPanel
{
	private final int DEFAULT_LENGTH = 400; 
	private final int DEFAULT_WIDTH =	400;	
	
	private LinkedList<DShape> list;
	
	public Canvas()
	{
		list = new LinkedList<>();
		setSize(DEFAULT_LENGTH, DEFAULT_WIDTH);
		this.setBackground(Color.WHITE);
	}
	
	public void addRect(DShape d)
	{
		//DRect r = new DRect(d);
		list.add(d); 
		System.out.println("Ya Da");
	}
	
	public void addOval()
	{
		//list.add(new DOval());
	}
	
	 @Override
	protected void paintComponent(Graphics g) 
	 {
		// TODO Auto-generated method stub
		super.paintComponent(g);
		System.out.println("Na gawa");
		for(DShape d: list)
		{
			d.draw(g);
		}
	}
}
