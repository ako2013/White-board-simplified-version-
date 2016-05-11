import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class DRectModel extends DShapeModel {
	
	private Rectangle rectangle;
	
	public DRectModel() {
		super();
	}
	
	public void setX(int x) {
		super.setX(x);
	}

	public void setY(int y) {
		super.setY(y);
	}

	public void setWidth(int width) {
		super.setWidth(width);
	}

	public void setHeight(int height) {
		super.setHeight(height);
	}

	public int getX() {
		return super.getX();
	}

	public int getY() {
		return super.getY();
	}

	public int getWidth() {
		return super.getWidth();
	}

	@Override
	public int getHeight() {
		return super.getHeight();
	}

	@Override
	public void setParams(int x, int y, int width, int height) {
		super.setParams(x, y, width, height);
		rectangle.setBounds(x, y, width, height);
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
		((Graphics2D) g).draw(rectangle);;
	}
}