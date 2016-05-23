import java.awt.Graphics;
import java.awt.Rectangle;

public class DRect extends DShape {
  /*
   * Contructor to create Rectangle Shape 
   * inheriting from DShape
   */
	public DRect(DShapeModel dShapeModel) {
		super(dShapeModel);
	}
  /*
   * Method to draw shape
   */
	public void draw(Graphics g) {
		// Drawing the rectangle shape. Data from the model
		g.setColor(dShapeModel.getColor());
		Rectangle shapeBounds = dShapeModel.getBounds();
		g.fillRect(shapeBounds.x, shapeBounds.y, shapeBounds.width, shapeBounds.height);
	}
   /*
   * Method to get the DShapeModel for DRect
   */
	@Override
	public DShapeModel getModel() {
		return dShapeModel;
	}
}
