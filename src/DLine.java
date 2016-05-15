import java.awt.Graphics;

public class DLine extends DShape {
	public DLine(DShapeModel model) { 
		super(model); 
	} 
	
	@Override
	public void draw(Graphics g) {
		DLineModel dLineModel = (DLineModel) getModel();
		g.setColor(getColor());
		g.drawLine(dLineModel.getP1().x, dLineModel.getP1().y, dLineModel.getP2().x, dLineModel.getP2().y);
	}

	@Override
	public DShapeModel getModel() {
		return dShapeModel;
	}
}
