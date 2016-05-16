import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.LinkedList;
import java.awt.event.MouseMotionListener;


import javax.swing.JComponent;
import javax.swing.JPanel;

public class Canvas extends JPanel
{
	private final int DEFAULT_LENGTH = 400; 
	private final int DEFAULT_WIDTH =	400;	
	private LinkedList<DShape> shapeList;
    private Whiteboard whiteboard; 
    private DShape selectedShape; 
    private int lastX, lastY; 
    private Point movingPoint; 
    private Point anchorPoint; 
    private boolean isAShapeSelected;
    
	public Canvas(Whiteboard whiteBoard)
	{
		this.whiteboard = whiteBoard;
		shapeList = new LinkedList<>();
		setSize(DEFAULT_LENGTH, DEFAULT_WIDTH);
		this.setBackground(Color.WHITE);
		setCanvasOnClickListener();
      dragShape();
	}
	
	// Add a new shape to the linked list
	public void addShape(DShapeModel dShapeModel)
	{
		DShape shape = null; 
		
		// Create shape based on the model
        if(dShapeModel instanceof DRectModel) 
        {
            shape = new DRect(dShapeModel); 
        }
        else if (dShapeModel instanceof DOvalModel) 
        {
            shape = new DOval(dShapeModel); 
        }
        else if (dShapeModel instanceof DLineModel) 
        {
            shape = new DLine(dShapeModel); 
        }
        else if(dShapeModel instanceof DTextModel) 
        { 
            shape = new DText(dShapeModel); 
        } 
		
        shapeList.add(shape); 
		
	}
	
	// Remove the currently selected shape
	public void removeShape() 
	{ 
		if (isAShapeSelected) 
		{
			shapeList.remove(selectedShape); 
			repaint();
		}
    } 
	
	// Re-color the currently selected shape
	public void recolorShape(Color color) 
	{
		if (isAShapeSelected) 
		{
			selectedShape.setColor(color);
			repaint();
		}
	}
	
	public void selectObjectForClick(Point clickedPoint) {
		 lastX = clickedPoint.x; 
	    lastY = clickedPoint.y; 
	    movingPoint = null; 
	    
	    // Check to see if a shape is clicked on and set selectedShape
	    if(movingPoint == null) 
	    {
	    	selectedShape = null;
	    	for(DShape shape : shapeList) 
	    	{
	    		if(shape.isInBoundOfPoint(clickedPoint)) 
	    		{
	    			isAShapeSelected = true;
	    			selectedShape = shape;
	    			System.out.println("CLICKING ON A SHAPE NOW");
	    		}
	    	}
	    }
	    repaint();
	} 
	
	// Return true if a valid shade is selected, false otherwise
	public boolean isAShapeSelected() 
	{
		isAShapeSelected = false;
		if (selectedShape != null) 
		{
			isAShapeSelected = true;
		} 
		
		return isAShapeSelected;
	}
	
	// Return the currently selected shape
	public DShape getSelectedShape() 
	{ 
        return selectedShape; 
    } 
	
	// Mouse listens for click on the canvas
   
   int x =0;
   int y =0;
	private void setCanvasOnClickListener()
	{
		addMouseListener(new MouseAdapter() 
		{ 
            public void mousePressed(MouseEvent e) 
            {
            	selectObjectForClick(e.getPoint());
               Point p = e.getPoint();
               x = p.x;
               y = p.y;
               System.out.println("Point origin: "+x+","+y);
            }
        });
	}
   // drag shape
   public void dragShape()
   {

      addMouseMotionListener(new MouseAdapter() 
		{ 
            @Override
            public void mouseDragged(MouseEvent e)
            {
               try{
               Point p = e.getPoint();
               int dx = p.x-x;
               int dy = p.y-y;
               //System.out.println("Point move: "+p.x+","+p.y);
               //System.out.println("Moved: "+dx+","+dy);
               if(isAShapeSelected){
                  //System.out.println("DRAGGING");
                  selectedShape.moveShape(dx,dy);
                  repaint();
               }
               x += dx;
               y += dy;
               }catch (Exception e2){
                  System.out.println("");
               }
            }
      });
   }
	
	// Move selected shape to the front
	public void moveSelectedShapeToFront() 
	{
		if (isAShapeSelected) {
			shapeList.remove(selectedShape);
			shapeList.add(selectedShape);
			repaint();
		}
	}
	
	// Move selected shape to the back
	public void moveSelectedShapeToBack() 
	{
		if (isAShapeSelected) 
		{
			shapeList.remove(selectedShape);
			shapeList.add(0, selectedShape);
			repaint();
		}
	}
	
	
	@Override
	protected void paintComponent(Graphics g) 
	{
		super.paintComponent(g);
		for(DShape d: shapeList)
		{
			d.draw(g);
		}
	}
}
