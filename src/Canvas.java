import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.awt.event.MouseMotionListener;
import javax.swing.JComponent;
import javax.swing.JPanel;

/**
 * The Canvas class that stores a running list of all the objects within the Canvas
 * The canvas also provides operations to those objects
 */
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
	private boolean isAKnobSelected = false;
	private LinkedList<DShape> pointList;
	private DShape selectedKnob; 
 
	/**
	 * Constructor for the Canvas
	 * @param whiteBoard The frame to add the canvas to
	 */
	public Canvas(Whiteboard whiteBoard)
	{
		this.whiteboard = whiteBoard;
		shapeList = new LinkedList<>();
		setSize(DEFAULT_LENGTH, DEFAULT_WIDTH);
		this.setBackground(Color.WHITE);
		setCanvasOnClickListener();
      dragShape();
	}
	
	/**
	 * Adds a shape to the canvas
	 * @param dShapeModel the shape to add
	 */
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
            DText textShape = (DText) shape; 
            whiteboard.updateFont(textShape.getText(), textShape.getFont()); 
        } 
        // Add to the table list
        whiteboard.addShapeToTable(shape); 
        // Add to shape list
        shapeList.add(shape); 
        if(whiteboard.isServer())
		{
			whiteboard.getServer().send();
		}
		
	}
	
	/**
	 * Allows the selected shape to be recolored
	 * @param color the color to change to
	 */
	public void recolorShape(Color color) 
	{
		if (isAShapeSelected) 
		{
			selectedShape.setColor(color);
			repaint();
			if(whiteboard.isServer())
			{
				whiteboard.getServer().send();
			}
		}
	}
	
	/**
	 * Selects an object
	 * @param clickedPoint The point that was clicked
	 */
	public void selectObjectForClick(Point clickedPoint) {
		if(!whiteboard.isClient())
		{
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
						isAKnobSelected = false;
					}
				}
				selectedKnob = null;
				if(pointList != null){
					for(DShape point : pointList)
					{
						if(point.isInBoundOfPoint(clickedPoint))
						{  
							isAKnobSelected = true;
							selectedKnob = point;
							isAShapeSelected = false;
						}
					}
				}
			}
			repaint();
	    }
	   
	}
	
	/**
	 * Checks whether a shape is slected
	 * @return Return true if a valid shade is selected, false otherwise
	 */
	public boolean isAShapeSelected() 
	{
		isAShapeSelected = false;
		if (selectedShape != null) 
		{
			isAShapeSelected = true;
		} 
		
		return isAShapeSelected;
	}
	
	/**
	 * gets the currently selected shape
	 * @return The shape currently selected
	 */
	public DShape getSelectedShape() 
	{ 
        return selectedShape; 
   } 
	
	// Mouse listens for click on the canvas
   private int x =0;
   private int y =0;
   
   /**
    * Sets the canvas mouse listener
    */
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
               
               try{
                     if(isAShapeSelected == true){
                        pointList = new LinkedList<>();
                        ArrayList<Point> point = selectedShape.getKnob();               
                        DRectModel d;
                        for(int i = 0; i <= 3;i++)
                        {
                           Point p2 = point.get(i);
                           d = new DRectModel(p2.x, p2.y, 9, 9, Color.BLACK);
                           DShape shape = new DRect(d);
                           pointList.add(shape);
                        }
                     } 
               }catch(Exception execp){
               }  
                    
            }
        });
	}   
	/**
	 * Allows a shape to be moved on the canvas
	 */
   int point = -1;
   private void dragShape()
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
                  //if a shape is clicked on
                  //drag it
                  if(isAShapeSelected == true && isAKnobSelected == false){
                     selectedShape.moveShape(dx,dy);
                     for(int i = 0;i <4;i++)
                     {
                        DShape shape = pointList.get(i);
                        shape.moveShape(dx,dy);
                        pointList.set(i,shape);
                     }
                     whiteboard.updateTableSelect(selectedShape);
                     repaint();
                     if(whiteboard.isServer())
         			{
         				whiteboard.getServer().send();
         			}
                  }
                  //if a knob is clicked on
                  //resize the shape
                  if(isAKnobSelected == true && isAShapeSelected == false)
                  {
                     for(int i = 0; i <4;i++)
                     {
                        if(selectedKnob == pointList.get(i)){
                           point = i;
                           break;
                        }
                     }
                     
                   
                     if (selectedShape instanceof DLine) {
                         selectedShape.resizeLine(point+1,dx,dy);
                     } else {
                    	 selectedShape.resizeShape(point+1,dx,dy);
                     }
                     createKnob(selectedShape);
                     whiteboard.updateTableSelect(selectedShape);
                     repaint();
                     if(whiteboard.isServer())
     				 {
     					whiteboard.getServer().send();
     				 }
                  }
                  x += dx;
                  y += dy;
               }catch (Exception e2){
               }
            }
      });
   }
   private void createKnob(DShape shape)
   {
      pointList = new LinkedList<>();
      ArrayList<Point> point = selectedShape.getKnob();               
      DRectModel d;
      for(int i = 0; i <= 3;i++)
      {
         Point p2 = point.get(i);
         d = new DRectModel(p2.x, p2.y, 9, 9, Color.BLACK);
         DShape shape2 = new DRect(d);
         pointList.add(shape2);
      }  
   }
	
	/**
	 * Moves the selected shape to the front of the canvas
	 */
	public void moveSelectedShapeToFront() 
	{
		if (isAShapeSelected) {
			shapeList.remove(selectedShape);
			shapeList.add(selectedShape);
	        whiteboard.shapeMovedFront(selectedShape); 
			repaint();
			if(whiteboard.isServer())
			{
				whiteboard.getServer().send();
			}
		}
	}
	
	/**
	 * Moves the selected shape to the back of the canvas
	 */
	public void moveSelectedShapeToBack() 
	{
		if (isAShapeSelected) 
		{
			shapeList.remove(selectedShape);
			shapeList.add(0, selectedShape);
	        whiteboard.shapeMovedBack(selectedShape); 
			repaint();
			{
				if(whiteboard.isServer())
				{
					whiteboard.getServer().send();
				}
			}
		}
	}	
	
	/**
	 *  Remove the currently selected shape
	 */
	public void removeShape() 
	{ 
		if (isAShapeSelected) 
		{
			shapeList.remove(selectedShape); 
	        whiteboard.shapeRemoved(selectedShape); 
			repaint();
			if(whiteboard.isServer())
			{
				whiteboard.getServer().send();
			}
		}
    } 
	
	/**
	 * Change text of currently selected shape 
	 * @param text the text to change to
	 */
	public void setText(String text) {
		((DText) selectedShape).setText(text);
		repaint();
		if(whiteboard.isServer())
		{
			whiteboard.getServer().send();
		}
	}
	
	/**
    * paint the shapes
    */
	@Override
	protected void paintComponent(Graphics g) 
	{
		super.paintComponent(g);
      
		for(DShape d: shapeList)
		{
			d.draw(g);
  		}
      try
         {
            for(int i = 0; i < 4; i++){
                DShape d2 = pointList.get(i);
                d2.draw(g);
            }
         }catch(Exception e){
         }

	}
	
	/**
	 * Gets the list of shapes of the canvas
	 * @return The Canvas's list of shapes
	 */
	public List<DShape> getShapes()
	{
		return shapeList;
	}
	
	/**
	 * Resets the canvas
	 */	
	public void resetShapes()
	{
		for(DShape d: shapeList)
		{
			whiteboard.shapeRemoved(d);
		}
		shapeList = new LinkedList<>();
	}
}
