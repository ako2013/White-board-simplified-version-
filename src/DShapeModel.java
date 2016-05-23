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
   /*
   * Construtor for DShapeModel - Default
   * set the bound using Rectangle
   */ 
	public DShapeModel() 
	{
		shapeBound = new Rectangle(0, 0, 0, 0);
		this.color = Color.gray;
	}
	/*
   * Constructor for DShapeModel
   * @param x takes in the x coordinate
   * @param y takes in the y coordinate
   * @param width takes in the width of the shape
   * @param height takes in the height of the shape
   * @param color takes in the color of the shape
   */
	public DShapeModel(int x, int y, int width, int height, Color color) 
	{
		this.shapeBound = new Rectangle(x, y, width, height);
		this.color = color;
	}
	/*
   * This method set the index for the object
   */
	public void setIndex(int index)
	{
		this.index = index;
	}
	/*
   * This method get the index of the object
   * @return the index number as an integer
   */
	public int getIndex()
	{
		return this.index;
	}
  /*
   * This method set the bounds to draw a Shape using coordinates and pixels 
   * @param x takes in the x coordinate
   * @param y takes in the y coordinate
   * @param width takes in the width of the shape
   * @param height takes in the height of the shape
   */
	public void setBounds(int x, int y, int width, int height) 
	{
		this.shapeBound = new Rectangle(x, y, width, height);
	}
  /*
   * This method set bounds using a Rectangle bounds
   * @param bounds takes in a Rectangle
  */
	public void setBounds(Rectangle bounds) 
	{ 
		this.shapeBound = new Rectangle(bounds);
	} 
  /*
   * This method get the bounds of the shape
   * @return the bound of Shape
   *
  */
	public Rectangle getBounds()
	{
		return shapeBound;
	}
  /*
   * This method set the points on the shape
   * @param p1 takes in frist Point
   * @param p2 takes in second Point
  */
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
  /*
   * This method set the color of an instance of Shape
  */
	public void setColor(Color color) 
	{
		this.color = color;
	}
  /*
   * This method get the color of an instance of Shapes
  */
	public Color getColor()
	{
		return this.color;
	}
  /*
   * This method implements listener for the object
  */
	 public void addListener(ModelListener listener) 
	 { 
		 listeners.add(listener); 
	 }
  /*
   * This method remove the implementation of listener for the object
  */
	 public boolean removeListener(ModelListener listener)
	 { 
		 return listeners.remove(listener); 
	 } 
	/*
   * This method will prompt the notifications when listeners are used
  */
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
  /*
   * Method to move an instace of Shape
   * @param xDirection takes in the x coordinates
   * @param yDirection takes in the y cooridnates
  */
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
    public void resizeShape(int point, int dx, int dy)
    {
      //cooridnates of the shape
       int x = shapeBound.x;
       int y = shapeBound.y;
       int w = shapeBound.width;
       int h = shapeBound.height;
       //resize based on knob position
       //
       // p1_______p2
       // |        |
       // |        |
       // |        |
       // p3_______p4
       //
       if(point == 1){
         x += dx;
         y += dy;
         h += dy * (-1);
         w += dx * (-1);
       }
       else if(point == 2){
         y += dy;
         w += dx;
         h += dy*(-1);
       }
       else if(point == 3){
         h += dy;
         x += dx;
         w += dx*(-1);
       
       }
       else if(point == 4){
         h += dy;
         w += dx;
       }
       if(this instanceof DLineModel)
       {
          Point p1 = new Point(x,y);
          Point p2 = new Point(x+w,y+h);
          this.setPoints(p1,p2);
       }
       else
         shapeBound.setBounds(x,y,w,h);
    }  
	 
}
