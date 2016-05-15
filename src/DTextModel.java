
public class DTextModel extends DShapeModel {
	// Default text and font
	private String default_text = "Hello"; 
    private String default_font = "Dialog"; 
    private String text; 
    private String font; 
     
    public DTextModel() { 
        super(); 
        text = default_text; 
        font = default_font; 
    } 
    
    public String getText() { 
        return text; 
    } 
    
    public String getFont() { 
        return font; 
    } 
    public void setText(String text) { 
        this.text = text;
    } 
    public void setFont(String font) { 
        this.font = font; 
    } 
    
    

}
