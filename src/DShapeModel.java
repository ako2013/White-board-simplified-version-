import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class DShapeModel {
	private Rectangle shapeBound;
	private Color color;
	
	public DShapeModel() {
		shapeBound = new Rectangle(0, 0, 0, 0);
		this.color = Color.gray;
	}
	
	public DShapeModel(int x, int y, int width, int height, Color color) {
		this.shapeBound = new Rectangle(x, y, width, height);
		this.color = color;
	}

	public void setBounds(int x, int y, int width, int height) {
		this.shapeBound = new Rectangle(x, y, width, height);
	}

	public Rectangle getBounds() {
		return shapeBound;
	}
	public void setColor(Color color) {
		this.color = color;
	}

	public Color getColor() {
		return this.color;
	}
}
