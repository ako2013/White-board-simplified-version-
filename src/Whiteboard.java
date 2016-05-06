import java.awt.BorderLayout;

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
		
		
		//Add canvas and buttons to the frame
		board.add(buttonPane);
		board.add(drawPane);
		
		//Generic Swing things
		board.setSize(FRAME_WIDTH, FRAME_HEIGHT);
		board.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		board.setVisible(true);
	}
	
	
	
}
