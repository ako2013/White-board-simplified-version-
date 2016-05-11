import java.awt.Color;
import java.awt.Graphics;

public class DOvalModel extends DShapeModel {
	
	public DOvalModel() {
		super();
	}

	@Override
	public void setX(int x) {
		super.setX(x);
	}

	@Override
	public void setY(int y) {
		super.setY(y);
	}

	@Override
	public void setWidth(int width) {
		super.setWidth(width);
	}

	@Override
	public void setHeight(int height) {
		super.setHeight(height);
	}

	@Override
	public int getX() {
		return super.getX();
	}

	@Override
	public int getY() {
		return  super.getY();
	}

	@Override
	public int getWidth() {
		return  super.getWidth();
	}

	@Override
	public int getHeight() {
		return  super.getHeight();
	}

	@Override
	public void setParams(int x, int y, int width, int height) {
		super.setParams(x, y, width, height);
	}

	@Override
	public void setColor(Color color) {
		super.setColor(color);
	}

	@Override
	public Color getColor() {
		return super.getColor();
	}

	@Override
	public void draw(Graphics g) {
		g.setColor(super.getColor());
		g.fillOval(super.getX(), super.getY(), super.getWidth(), super.getHeight());
	}	
}