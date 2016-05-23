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
		// TODO Auto-generated method       	
	}
	public void moveShape(int dx, int dy) 
	{
		dShapeModel.moveShape(dx, dy); 
	} 
   public void resizeShape(int point,int dx, int dy)
   {
       //cooridnates of the shape
       int x = dShapeModel.getBounds().x;
       int y = dShapeModel.getBounds().y;
       int w = dShapeModel.getBounds().width;
       int h = dShapeModel.getBounds().height;
       
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
       dShapeModel.setBounds(x,y,w,h);
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

	abstract public void draw(Graphics g);
	abstract public DShapeModel getModel(); 
		
}
