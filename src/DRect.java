import java.awt.Color;
import java.awt.Graphics;

public class DRect extends DShape {
	private DShapeModel dShapeModel;
	
	public DRect() {
		dShapeModel = new DShapeModel();
	}

	public void setX(int x) {
		dShapeModel.setX(x);
	}

	public void setY(int y) {
		dShapeModel.setY(y);
	}

	public void setWidth(int width) {
		dShapeModel.setWidth(width);
	}

	public void setHeight(int height) {
		dShapeModel.setHeight(height);
	}

	public int getX() {
		return dShapeModel.getX();
	}

	public int getY() {
		return dShapeModel.getY();
	}

	public int getWidth() {
		return dShapeModel.getWidth();
	}

	public int getHeight() {
		return dShapeModel.getHeight();
	}

	public void setParams(int x, int y, int width, int height) {
		dShapeModel.setParams(x, y, width, height);
	}

	public void setColor(Color color) {
		dShapeModel.setColor(color);
	}

	public Color getColor() {
		return dShapeModel.getColor();
	}

	public void draw(Graphics g) {
		dShapeModel.draw(g);
	}
}
