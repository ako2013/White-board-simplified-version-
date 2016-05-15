import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;

public abstract class DShape implements ModelListener {
	protected DShapeModel dShapeModel;
    protected ArrayList<Point> knobs; 
    
	public DShape(DShapeModel dShapeModel) 
	{
		this.dShapeModel = dShapeModel;
		
	}
	public void setColor(Color color) 
	{
		dShapeModel.setColor(color);
	}

	public Color getColor() 
	{
		return dShapeModel.getColor();
	}
	
	public Rectangle getBounds() 
	{
		return dShapeModel.getBounds();
	}
	
	@Override
	public void modelChanged(DShapeModel shapeModel)
	{
		// TODO Auto-generated method stub
		
	}
	public void moveShape(int dx, int dy) 
	{
		dShapeModel.moveShape(dx, dy); 
	} 
	
	public boolean isInBoundOfPoint(Point pt)
	{ 
        Rectangle bounds = getBounds(); 
        
        if(bounds.contains(pt))
        {
            return true; 
        }
        return false; 
    } 

	abstract public void draw(Graphics g);
	abstract public DShapeModel getModel(); 
		
}
