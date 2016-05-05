import java.awt.Color;
import java.awt.Graphics;

public class DShapeModel {
	private int x,y, width, height;
	private Color color;
	
	public DShapeModel() {
		this.x = 0;
		this.y = 0;
		this.width = 0;
		this.height = 0;
		this.color = Color.GRAY;
	}

	public void setX(int x) {
		this.x = x;
	}

	public void setY(int y) {
		this.y = y;	
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int getX() {
		return this.x;
	}

	public int getY() {
		return this.y;
	}

	public int getWidth() {
		return this.width;
	}

	public int getHeight() {
		return this.height;
	}

	public void setParams(int x, int y, int width, int height) {
		this.x = x;
		this. y = y;
		this.width = width;
		this.height = height;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public Color getColor() {
		return this.color;
	}

	public void draw(Graphics g) {
		// called by subclass
	}	

}
