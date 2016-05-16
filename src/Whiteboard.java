import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Enumeration;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.event.TableColumnModelListener;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;


public class Whiteboard extends JFrame
{
	final int FRAME_WIDTH = 1000; 
	final int FRAME_HEIGHT = 1000; 
	private Canvas drawPane;
	private Box vert, horz1, horz2, horz3, horz4;
	private JButton b1, b2, b3, b4, b5, b6, b7, b8;
	private JTable table;
	private JTextField t1;
	private	JSplitPane utilPane;
	private JPanel buttonPane, infoPane;
	private JFrame board;

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
		JSplitPane  utilPane = new JSplitPane();
		JPanel buttonPane = new JPanel();
		JPanel infoPane = new JPanel();
	}
	
	private void createBoxes()
	{
		//Make boxes
		vert = Box.createVerticalBox();
		horz1 = Box.createHorizontalBox();
		horz2 = Box.createHorizontalBox();
		horz3 = Box.createHorizontalBox();		
		horz4 = Box.createHorizontalBox();	
		
		//Box Code and addition to buttonPane
		vert.add(horz1);
		vert.add(horz2);
		vert.add(horz3);
		vert.add(horz4);
		horz1.add(b1);
		horz1.add(b2);
		horz1.add(b3);
		horz1.add(b4);
		horz2.add(b5);
		horz3.add(t1);
		horz4.add(b6);
		horz4.add(b7);
		horz4.add(b8);		
	}
	
	private void createTable()
	{
		String[] columnNames = {"X", "Y", "Width", "Height"};
		Object[][] data = {{"","","",""}};
		table = new JTable(data , columnNames);
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
				drawPane.recolorShape(selectedColor);	
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
	}
	
	private void setUtilPane() 
	{
		
		utilPane = new JSplitPane();
		buttonPane = new JPanel();
		infoPane = new JPanel();
		
		//infoPane Code
		infoPane.add(table);
		infoPane.setLayout(new FlowLayout());
		
		
		
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
	
}
