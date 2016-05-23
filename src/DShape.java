import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;

public abstract class DShape implements ModelListener {
	protected DShapeModel dShapeModel;
   protected ArrayList<Point> knobs; 
   /*
   * Constructor of the DShape
   * @param dShapeModel takes in object of DShapeModel
   */ 
	public DShape(DShapeModel dShapeModel) 
	{
		this.dShapeModel = dShapeModel;
	}
   /*
   * This method set the color of the selected DShape
   * @param color takes in the color for changes
   */
	public void setColor(Color color) 
	{
		dShapeModel.setColor(color);
	}
   /*
   * This method get the color of the selected DShape
   * @return the color
   */
	public Color getColor() 
	{
		return dShapeModel.getColor();
	}
	/*
   * This method get the Rectangle bound of the shape
   * @return the bounds of the shape
   */
	public Rectangle getBounds() 
	{
		return dShapeModel.getBounds();
	}
	@Override
	public void modelChanged(DShapeModel shapeModel)
	{
		// TODO Auto-generated method       	
	}
   /*
   * This method moves the position of a Shape 
   * @param dx takes in the changes in x coordinates
   * @param dy takes in the changes in y coordinates
   */
	public void moveShape(int dx, int dy) 
	{
		dShapeModel.moveShape(dx, dy); 
	} 
   /*
   * This method changes the size of a Shape
   * @param point takes in the position of the knob selected
   * @param dx takes in the changes in x coordinates
   * @param dy takes in the changes in y coordinates
   */
   public void resizeShape(int point,int dx, int dy)
   {
       dShapeModel.resizeShape(point,dx,dy);
   }
  /*
   * This method check if the Point is in bound of the Shape
   * @param pt takes in the coordinates point
   * @return true if the point is within the Shape
   */
	public boolean isInBoundOfPoint(Point pt)
	{ 
        Rectangle bounds = getBounds(); 
        
        if(bounds.contains(pt))
        {
            return true; 
        }
        return false; 
    }
  /*
   * This method create an ArrayList to store the knobs
   * 
   * @return the list of knobs created
   */
    public ArrayList<Point> getKnob()
    {
        try{
        knobs = new ArrayList<Point>();
        Rectangle r = dShapeModel.getBounds();
        
        Point p1 = new Point(r.x,r.y);
        Point p2 = new Point(r.x+r.width-9,r.y);
        Point p3 = new Point(r.x,r.y+r.height-9);
        Point p4 = new Point(r.x+r.width-9,r.y+r.height-9);
      
        knobs.add(p1);
        knobs.add(p2);
        knobs.add(p3);
        knobs.add(p4);
        }catch(Exception e){
        }
        
        return knobs;
    }
    
    public void resizeLine(int point,int dx, int dy)
    {
        //cooridnates of the shape
        int x = dShapeModel.getBounds().x;
        int y = dShapeModel.getBounds().y;
        int w = dShapeModel.getBounds().width;
        int h = dShapeModel.getBounds().height;
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
        Point p1 = new Point(x, y);
        Point p2 = new Point(x + w, y + h);

        dShapeModel.setPoints(p1, p2);
    }
    
   //abstract the draw method
	abstract public void draw(Graphics g);
   //abstract for getting the model
	abstract public DShapeModel getModel(); 
		
}
