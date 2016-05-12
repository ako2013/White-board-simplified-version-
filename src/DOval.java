import java.awt.Color;
import java.awt.Graphics;

public class DOval extends DShape {
	
	public DOval(DShapeModel dShapeModel) {
		super(dShapeModel);
	}

	public void draw(Graphics g) {
		g.setColor(dShapeModel.getColor());
		
	}
	
	
}
