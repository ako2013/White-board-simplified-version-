import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Shape;

public class DText extends DShape {
	private double startingSize = 1.0;
    private Font computedFont; 
    private boolean computeAgain;
    private boolean firstChange;
    
	public DText(DShapeModel dShapeModel) 
	{
		super(dShapeModel);
        computedFont = null; 
        computeAgain = true;
        firstChange = true;
	}

	@Override
	public void draw(Graphics g)
	{
		// Clip to set the font so it doesn't go out of bound
		Shape clip = g.getClip(); 
        g.setClip(clip.getBounds().createIntersection(getBounds())); 
        Font font = computeFont(g); 
        DTextModel model = (DTextModel) getModel();
        g.setColor(getColor()); 
        g.setFont(font); 
        g.drawString(model.getText(), getBounds().x, getBounds().y 
        + getBounds().height); 
        g.setClip(clip); 
	}

	@Override
	public DShapeModel getModel()
	{
		return dShapeModel;
	}
	
	public String getText()
	{
        DTextModel model = (DTextModel) getModel();
		return model.getText();
	}
	
	public void setText(String text)
	{
        DTextModel model = (DTextModel) getModel();
		model.setText(text);
	}
	
	public String getFont() 
	{
		DTextModel model = (DTextModel) getModel();
		return model.getFont();
	}
	
	public Font computeFont(Graphics g)
	{
	
		double theSize = startingSize;

		// Computing the font and re-computing for resize later
		if (computeAgain && !firstChange) {
		
			double size = startingSize;
			double lastSize = size;
			
            theSize = theSize + 1;

			while (true) {
				computedFont = new Font(getFont(), Font.PLAIN, (int) size); 
	            if (computedFont.getLineMetrics(getText(), ((Graphics2D) g).getFontRenderContext()).getHeight() > getModel().getBounds().getHeight()) {
	            	break;
	            }
	            lastSize = size;
	            size = (size * 1.1) + 1;
	            theSize = (theSize * 1.1) + 1;
	        }
			computedFont = new Font(getFont(), Font.PLAIN, (int) theSize);
		} else {
			computedFont = new Font(getFont(), Font.PLAIN, 15);
			firstChange = false;
			
		}

		return computedFont;
	} 
}
