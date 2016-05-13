import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.JTextField;


public class Whiteboard extends JFrame
{
	final int FRAME_WIDTH = 1000; 
	final int FRAME_HEIGHT = 1000; 
	
	public static void main(String[] args)
	{
		//Size of Window
		final int FRAME_WIDTH = 1000; 
		final int FRAME_HEIGHT = 1000; 
				
		//New Whiteboard Frame
		JFrame board = new JFrame("Whiteboard");
		board.setLayout(new BorderLayout());
		
		//Set the Canvas and panel
		Canvas drawPane = new Canvas();
		JSplitPane  utilPane = new JSplitPane();
		JPanel buttonPane = new JPanel();
		JPanel infoPane = new JPanel();
		
		//Make boxes
		Box vert = Box.createVerticalBox();
		Box horz1 = Box.createHorizontalBox();
		Box horz2 = Box.createHorizontalBox();
		Box horz3 = Box.createHorizontalBox();		
		Box horz4 = Box.createHorizontalBox();	
		
		//ButtonCode
		JButton b1 = new JButton("Add Rect");
		JButton b2 = new JButton("Add Oval");
		JButton b3 = new JButton("Add Line");
		JButton b4 = new JButton("Add Text");
		JButton b5 = new JButton("Set Color");
		JButton b6 = new JButton("Move to Front");
		JButton b7 = new JButton("Move to Back");
		JButton b8 = new JButton("Remove");
		JTextField t1 = new JTextField();
		
		
		b1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DRectModel d = new DRectModel(100, 100, 100, 100, Color.ORANGE);
				DShape rect = new DRect(d);
				drawPane.addRect(rect);
				drawPane.repaint();
			}
		});
		
		b2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DOvalModel d = new DOvalModel(300, 300, 100, 100, Color.ORANGE);
				DShape oval = new DOval(d);
				drawPane.addOval(oval);
				drawPane.repaint();
			}
		});
		
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
		
		//Generic Swing things
		board.setSize(FRAME_WIDTH, FRAME_HEIGHT);
		board.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		board.setVisible(true);
	}
	
	
	
}
