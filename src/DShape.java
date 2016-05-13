import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public abstract class DShape {
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

	abstract public void draw(Graphics g);
		
}
