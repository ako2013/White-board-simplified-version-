import java.awt.Graphics;
import java.awt.Rectangle;

public class DRect extends DShape {
	
	public DRect(DShapeModel dShapeModel) {
		super(dShapeModel);
	}

	public void draw(Graphics g) {
		// Drawing the rectangle shape. Data from the model
		g.setColor(dShapeModel.getColor());
		Rectangle shapeBounds = dShapeModel.getBounds();
		g.fillRect(shapeBounds.x, shapeBounds.y, shapeBounds.width, shapeBounds.height);
	}
}
