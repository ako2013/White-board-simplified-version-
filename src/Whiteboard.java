import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

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
		JPanel buttonPane = new JPanel(); 
		
		//ButtonCode
		JButton b1 = new JButton("Rect");
		JButton b2 = new JButton("Oval");
		JButton b3 = new JButton("Line");
		JButton b4 = new JButton("Text");
		
		b1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DRectModel d = new DRectModel(100, 100, 100, 100, Color.BLACK);
				DShape rect = new DRect(d);
				drawPane.addRect(rect);
				drawPane.repaint();
			}
		});
		
		//Add buttons to buttonPane
		buttonPane.add(b1);
		buttonPane.add(b2);
		buttonPane.add(b3);
		buttonPane.add(b4);
		
		
		//Add canvas and buttonPane to the frame
		buttonPane.setPreferredSize(new Dimension(500, 500));
		drawPane.setPreferredSize(new Dimension(500, 500)); 
		board.add(buttonPane, BorderLayout.WEST);
		board.add(drawPane, BorderLayout.EAST);
		
		//Generic Swing things
		board.setSize(FRAME_WIDTH, FRAME_HEIGHT);
		board.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		board.setVisible(true);
	}
	
	
	
}
