import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.List;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JTextField;



public class Whiteboard extends JFrame
{
	final int FRAME_WIDTH = 1000; 
	final int FRAME_HEIGHT = 1000; 
	private Canvas drawPane;
	private Box vert, horz1, horz2, horz3, horz4, horz5, horz6;
	private JButton b1, b2, b3, b4, b5, b6, b7, b8, b9, b10, b11, b12, b13;
	private JTable table;
	private JTextField t1;
	private	JSplitPane utilPane;
	private JPanel buttonPane, infoPane;
	private JFrame board;
	private JScrollPane scrollpane;
	private StringTableModel tableModel;
	
	public static void main(String[] args)
	{
		// Create a white board
        Whiteboard whiteboard = new Whiteboard(); 		
	}
	
	
	public Whiteboard() 
	{
		// Set up the Whiteboard with different components
		setCanvasAndPanel();
		setButtons();
		createTable();
		createBoxes();		
		setUtilPane();
		fillCanvas();
	}	
	
	private void setCanvasAndPanel()
	{
		//Size of Window
		final int FRAME_WIDTH = 1000; 
		final int FRAME_HEIGHT = 1000; 
		
		//Set the Canvas and panel
		drawPane = new Canvas(this);
		//New Whiteboard Frame
		board = new JFrame("Whiteboard");
		board.setLayout(new BorderLayout());

		//Set the Canvas and panel
		utilPane = new JSplitPane();
		buttonPane = new JPanel();
		infoPane = new JPanel();
	}
	
	private void createBoxes()
	{
		//Make boxes
		vert = Box.createVerticalBox();
		horz1 = Box.createHorizontalBox();
		horz2 = Box.createHorizontalBox();
		horz3 = Box.createHorizontalBox();		
		horz4 = Box.createHorizontalBox();	
		horz5 = Box.createHorizontalBox();
		horz6 = Box.createHorizontalBox();
		
		//Box Code and addition to buttonPane
		vert.add(horz1);
		vert.add(horz2);
		vert.add(horz3);
		vert.add(horz4);
		vert.add(horz5);
		horz1.add(b1);
		horz1.add(b2);
		horz1.add(b3);
		horz1.add(b4);
		horz2.add(b5);
		horz3.add(t1);
		horz4.add(b6);
		horz4.add(b7);
		horz4.add(b8);	
		horz5.add(b9);
		horz5.add(b10);
		horz5.add(b11);
		horz6.add(b12);
		horz6.add(b13);
	}
	
	private void createTable()
	{	
		tableModel = new StringTableModel();
		table = new JTable(tableModel);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS); 
        scrollpane = new JScrollPane(table); 
        scrollpane.setPreferredSize(new Dimension(380, 400));
	}
	
	private void setButtons() 
	{
		//ButtonCode
		b1 = new JButton("Add Rect");
		b2 = new JButton("Add Oval");
		b3 = new JButton("Add Line");
		b4 = new JButton("Add Text");
		b5 = new JButton("Set Color");
		b6 = new JButton("Move to Front");
		b7 = new JButton("Move to Back");
		b8 = new JButton("Remove");
		b9 = new JButton("Save");
		b10 = new JButton("Load");
		b11 = new JButton("Save Image");
		b12 = new JButton("Server Start");
		b13 = new JButton("Client Start");
		t1 = new JTextField();
		
		// Add rectangle
		b1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DRectModel d = new DRectModel(100, 100, 100, 100, Color.GRAY);
				drawPane.addShape(d);
				drawPane.repaint();
			}
		});
		
		// Add oval
		b2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DOvalModel d = new DOvalModel(300, 300, 100, 100, Color.GRAY);
				drawPane.addShape(d);
				drawPane.repaint();
			}
		});
		
		// Add line
		b3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Point p1 = new Point(100, 90);
				Point p2 = new Point(50, 40);
				DLineModel d = new DLineModel();
				d.setPoints(p1, p2);
				drawPane.addShape(d);
				drawPane.repaint();
			}
		});
		
		// Add text
		b4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DTextModel d = new DTextModel();
				d.setBounds(0, 0, 50, 50);
				drawPane.addShape(d);
				drawPane.repaint();
			}
		});
		
		//Set color
		b5.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
                Color selectedColor = JColorChooser.showDialog(Whiteboard.this, "Select A Color", drawPane.getSelectedShape().getColor()); 
                
                if (selectedColor != null) {
                	drawPane.recolorShape(selectedColor);	
                }
			}
		});
		
		// Add shape to front
		b6.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
                drawPane.moveSelectedShapeToFront();
			}
		});
		
		// Add shape to back
		b7.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				drawPane.moveSelectedShapeToBack();
			}
		});
				
		
		// Remove a shape
		b8.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (drawPane.isAShapeSelected()) {
					drawPane.removeShape();	
					repaint();
				}				
			}
		});
		
		//Save a whiteBoard
		b9.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				DShapeModel[] list = null;
				String result = JOptionPane.showInputDialog("File Name", null);
				if (result != null) {
					File f = new File(result);
					save(f);
				}
			}
		});
		
		//Load a WhiteBoard
		b10.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) 
			{		
				String result = JOptionPane.showInputDialog("File Name", null);
				if (result != null) {
					File f = new File(result);
					open(f);
				}
			}
		});
		
		//Save an Image
		b11.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) 
			{		
				String result = JOptionPane.showInputDialog("File Name", null);
				if (result != null) {
					File f = new File(result);
					saveImage(f);
				}
			}
		});
	}
	
	private void setUtilPane() 
	{
		
		utilPane = new JSplitPane();
		buttonPane = new JPanel();
		infoPane = new JPanel();
		
		//infoPane Code
		infoPane.add(scrollpane);
		
		
		//utilPane Code
		utilPane.setOrientation(JSplitPane.VERTICAL_SPLIT);
		utilPane.setDividerSize(5);
		utilPane.setTopComponent(buttonPane);
		utilPane.setBottomComponent(infoPane);
		
		//buttonPane Code
		buttonPane.add(vert);
		for (Component comp : horz1.getComponents()) 
		{
			JComponent c = (JComponent) comp;
			c.setAlignmentX(Box.LEFT_ALIGNMENT);
		}
		for (Component comp : horz2.getComponents()) 
		{
			JComponent c = (JComponent) comp;
			c.setAlignmentX(Box.LEFT_ALIGNMENT);
		}
		for (Component comp : horz3.getComponents()) 
		{
			JComponent c = (JComponent) comp;
			c.setAlignmentX(Box.LEFT_ALIGNMENT);
		}
		for (Component comp : horz4.getComponents()) 
		{
			JComponent c = (JComponent) comp;
			c.setAlignmentX(Box.LEFT_ALIGNMENT);
		}
		
		//Add canvas and buttonPane to the frame
		board.add(utilPane, BorderLayout.LINE_START);
		board.add(drawPane, BorderLayout.CENTER);
				
	}
	
	private void fillCanvas() 
	{
		//Add canvas and buttonPane to the frame
		board.add(utilPane, BorderLayout.LINE_START);
		board.add(drawPane, BorderLayout.CENTER);
		
		//Generic Swing things
		board.setSize(FRAME_WIDTH, FRAME_HEIGHT);
		board.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		board.setVisible(true);
	}
	
	// Update the selected shape
    public void updateTableSelect(DShape selectedShape) { 
        table.clearSelection(); 
        if(selectedShape != null) { 
            int index = tableModel.getRowForSpecificModel(selectedShape
            		.getModel()); 
            table.setRowSelectionInterval(index, index); 
        } 
    } 
    
    // A new shape added. Invoke table model's method to add shape to its shape list
	public void addShapeToTable(DShape shape) {
		tableModel.addAShapeModel(shape.getModel());
		updateTableSelect(shape); 
	} 
    
    // Shape has moved to back. Invoke table model's method to re-order the shape list
    public void shapeMovedBack(DShape shape) { 
        tableModel.moveShapeToBack(shape.getModel()); 
        updateTableSelect(shape); 
    } 
     
    // Shape has moved to front. Invoke table model's method to re-order the shape list
    public void shapeMovedFront(DShape shape) { 
        tableModel.moveShapeToFront(shape.getModel()); 
        updateTableSelect(shape); 
    }
    
    // Shape has been removed. Invoke table model's method to remove from shape list
    public void shapeRemoved(DShape shape) { 
        tableModel.removeAShapeModel(shape.getModel()); 
        updateTableSelect(null); 
    } 
    
    //Opens a file by opening an array of things and then instantiating those
    public void open(File file)
    {
    	DShapeModel[] list = null;
    	try 
    	{
    		XMLDecoder xmlIn = new XMLDecoder(new BufferedInputStream( new FileInputStream (file)));
    		list = (DShapeModel[]) xmlIn.readObject();
    		xmlIn.close();
    	}
    	catch (Exception e)
    	{
    		System.out.println(e.getMessage());
    	}
    	for(DShapeModel m: list)
    	{
    		drawPane.addShape(m);
    	}
    	
    }
    
    //Saves a file by turning things into an xml file
    public void save(File file)
    {
    	List<DShape> shapes = drawPane.getShapes();
    	DShapeModel[] list = new DShapeModel[shapes.size()];
    	for(int i = 0; i < list.length; i ++)
    	{
    		list[i] = shapes.get(i).getModel();
    	}
    	try
    	{
    		XMLEncoder xmlOut = new XMLEncoder(new BufferedOutputStream(new FileOutputStream(file)));
    		xmlOut.writeObject(list);
    		xmlOut.close();
    	}
    	catch (Exception e)
    	{
    		System.out.println(e.getMessage());
    	}
    }
     
    public void saveImage(File file)
    {
    	BufferedImage image = (BufferedImage) createImage(drawPane.getWidth(), drawPane.getHeight());
    	Graphics g = image.getGraphics();
    	paintAll(drawPane.getGraphics());
    	g.dispose();
    	try 
    	{
			javax.imageio.ImageIO.write(image, "PNG", file);
		} 
    	catch (Exception e) 
    	{
			System.out.println(e.getMessage());
		}
    	
    }
    
  
}
