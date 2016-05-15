import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class DOval extends DShape {
	
	public DOval(DShapeModel dShapeModel)
	{
		super(dShapeModel);
	}

	public void draw(Graphics g)
	{
		g.setColor(dShapeModel.getColor());
		Rectangle shapeBounds = dShapeModel.getBounds();
		g.fillOval(shapeBounds.x, shapeBounds.y, shapeBounds.width, shapeBounds.height);
	}

	@Override
	public DShapeModel getModel() 
	{
		return dShapeModel;
	}
	
	
}
