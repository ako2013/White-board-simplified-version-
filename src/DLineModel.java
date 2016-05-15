import java.awt.Point;

public class DLineModel extends DShapeModel {
	private Point p1, p2;
	
	public DLineModel() { 
        super(); 
        // Set p1 being the starting point and p2 being the opposite dialog point to represent the line
        p1 = new Point(shapeBound.x, shapeBound.y); 
        p2 = new Point(shapeBound.x + shapeBound.width, shapeBound.y + shapeBound.height); 
	}
	
	public void setPoints(Point p1, Point p2) {
		this.p1 = new Point(p1); 
	    this.p2 = new Point(p2);
	    super.setPoints(p1, p2);
	}
	
	public Point getP1() {
		return p1; 
	} 
	public Point getP2() {
		return p2; 
	} 
}
