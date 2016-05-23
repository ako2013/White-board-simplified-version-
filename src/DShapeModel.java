import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;

public class DShapeModel {
	protected Rectangle shapeBound;
	private Color color;
    protected ArrayList<ModelListener> listeners; 
    private int index;
    
	public DShapeModel() 
	{
		shapeBound = new Rectangle(0, 0, 0, 0);
		this.color = Color.gray;
	}
	
	public DShapeModel(int x, int y, int width, int height, Color color) 
	{
		this.shapeBound = new Rectangle(x, y, width, height);
		this.color = color;
	}
	
	public void setIndex(int index)
	{
		this.index = index;
	}
	
	public int getIndex()
	{
		return this.index;
	}

	public void setBounds(int x, int y, int width, int height) 
	{
		this.shapeBound = new Rectangle(x, y, width, height);
	}
	
	public void setBounds(Rectangle bounds) 
	{ 
		this.shapeBound = new Rectangle(bounds);
	} 
	
	public Rectangle getBounds()
	{
		return shapeBound;
	}
	
	public void setPoints(Point p1, Point p2) { 
		int x, y;
		
		// We set our starting rectangle shape to be the upper left point
		if (p1.x < p2.x) {
			x = p1.x;
		} else {
			x = p2.x;
		}
		if (p1.y < p2.y) {
			y = p1.y;
		} else {
			y = p2.y;
		}
		
		// Change in x is the width
		int width = Math.abs(p1.x - p2.x); 
		// Change in y is the height
		int height = Math.abs(p1.y - p2.y); 
		
		// Set rectangle bounds for the 2 points (line)
		setBounds(new Rectangle(x, y, width, height));
	}
	   
	public void setColor(Color color) 
	{
		this.color = color;
	}

	public Color getColor()
	{
		return this.color;
	}
	
	 public void addListener(ModelListener listener) 
	 { 
		 listeners.add(listener); 
	 }
	 
	 public boolean removeListener(ModelListener listener)
	 { 
		 return listeners.remove(listener); 
	 } 
	 
	 public void notifyListeners()
	 { 
       try{
		 for(ModelListener listener : listeners)
		 {
			 listener.modelChanged(this); 
		 }
       }catch(Exception e){
         //System.out.println("");
       }
	 } 
	 
	 // Change shape position based on moved position
	 public void moveShape(int xDirection, int yDirection)
	 {
       if (this instanceof DLineModel) {
         Point p1 = new Point(shapeBound.x + xDirection, shapeBound.y + yDirection);
         Point p2 = new Point(shapeBound.x + shapeBound.width + xDirection, shapeBound.y + shapeBound.height + yDirection);
         this.setPoints(p1, p2);
       } else {
       
		   shapeBound.x += xDirection; 
		   shapeBound.y += yDirection; 
       }
		 
		 // Send notification to the shape that a model has changed
	     notifyListeners(); 
	 }   
	 
}
