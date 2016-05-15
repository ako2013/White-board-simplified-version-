import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.LinkedList;

import javax.swing.JComponent;
import javax.swing.JPanel;

public class Canvas extends JPanel
{
	private final int DEFAULT_LENGTH = 400; 
	private final int DEFAULT_WIDTH =	400;	
	private LinkedList<DShape> list;
    private Whiteboard whiteboard; 
    private DShape selectedShape; 
    
	public Canvas(Whiteboard whiteBoard)
	{
		this.whiteboard = whiteBoard;
		list = new LinkedList<>();
		setSize(DEFAULT_LENGTH, DEFAULT_WIDTH);
		this.setBackground(Color.WHITE);
	}
	
	public void addShape(DShapeModel dShapeModel)
	{
		DShape shape = null; 
		
		// Create shape based on the model
        if(dShapeModel instanceof DRectModel) 
        {
            shape = new DRect(dShapeModel); 
        }
        else if (dShapeModel instanceof DOvalModel) 
        {
            shape = new DOval(dShapeModel); 
        }
        else if (dShapeModel instanceof DLineModel) 
        {
            shape = new DLine(dShapeModel); 
        }
        else if(dShapeModel instanceof DTextModel) 
        { 
            shape = new DText(dShapeModel); 
        } 
		
		list.add(shape); 
		
	}
	
	 @Override
	protected void paintComponent(Graphics g) 
	 {
		// TODO Auto-generated method stub
		super.paintComponent(g);
		for(DShape d: list)
		{
			d.draw(g);
		}
	}
}
