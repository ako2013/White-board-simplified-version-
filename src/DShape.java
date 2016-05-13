import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public abstract class DShape {
	protected DShapeModel dShapeModel;
	protected Canvas canvas;
	
	public DShape(DShapeModel dShapeModel, Canvas Canvas) {
		this.dShapeModel = dShapeModel;
		this.canvas = canvas;
		
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

	abstract public void draw(Graphics g);
		
}
