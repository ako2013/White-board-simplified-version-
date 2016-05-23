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
   public void changeShape(int dx,int dy)
   {
      System.out.println("IN HERE");
     //dShapeModel.setBounds(dShapeModel.getBounds().x,dShapeModel.getBounds().y,dShapeModel.getBounds().width+dx,
     //dShapeModel.getBounds().height+dy);
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
        Point p1 = new Point(r.x-9,r.y-9);
        Point p2 = new Point(r.x+r.width,r.y-9);
        Point p3 = new Point(r.x-9,r.y+r.height);
        Point p4 = new Point(r.x+r.width,r.y+r.height);
      
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
