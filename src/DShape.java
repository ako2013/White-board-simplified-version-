import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public abstract class DShape implements ModelListener {
	protected DShapeModel dShapeModel;
	
	public DShape(DShapeModel dShapeModel) {
		this.dShapeModel = dShapeModel;
		
	}
	public void setColor(Color color) {
		dShapeModel.setColor(color);
	}

	public Color getColor() {
		return dShapeModel.getColor();
	}
	
	public Rectangle getBounds() {
		return dShapeModel.getBounds();
	}
	
	@Override
	public void modelChanged(DShapeModel shapeModel) {
		// TODO Auto-generated method stub
		
	}
	 public void moveShape(int dx, int dy) { 
	        dShapeModel.moveShape(dx, dy); 
	    } 

	abstract public void draw(Graphics g);
	abstract public DShapeModel getModel(); 
		
}
