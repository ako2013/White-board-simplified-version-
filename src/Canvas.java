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
		this.setSize(DEFAULT_LENGTH, DEFAULT_WIDTH);
		setBackground(Color.WHITE);
	}
	
	public void addShape(DShapeModel d)
	{
		if (d instanceof DRectModel)
		{
			list.add(new DRect(d));
		}
		if (d instanceof DOvalModel)
		{
			list.add(new DOval(d));
		}
	}
	
	public void paintcomponent(Graphics g)
	{
		for(DShape d: list)
		{
			d.draw(g);
		}
	}
}
