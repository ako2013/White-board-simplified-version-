
public class DTextModel extends DShapeModel {
	 // Default text and font
	 private String default_text = "Hello"; 
    private String default_font = "Dialog"; 
    private String text; 
    private String font; 
  /*
   * Contructor for DTextModel
   * text is set to default: Hello
   * font is set to default: Dialog
   */
    public DTextModel()
    { 
        super(); 
        text = default_text; 
        font = default_font; 
    } 
   /*
    * This method gets the text using in an instance
    *
    * @return a String
    */
    public String getText() 
    { 
        return text; 
    } 
   /*
    * Method to get the Font using in an instance 
    * 
    * @return the Font
    */
    public String getFont()
    { 
        return font; 
    } 
   /*
    * Method to set the text 
    * @param text takes in a String to set the text
    */
    public void setText(String text) 
    { 
        this.text = text;
    } 
   /*
    * Method to set the Fotn of an instance of DText 
    * @param font takes in a String name for the Font
    */
    public void setFont(String font) 
    { 
        this.font = font; 
    } 
}
