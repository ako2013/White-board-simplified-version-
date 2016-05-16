import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;


public class StringTableModel extends AbstractTableModel
{
	public static final int IMG_COL = 0;

	  public String[] m_colNames = { "Variable Dimension" };

	  public Class[] m_colTypes = { String.class };

	  public StringTableModel() {
	    super();

	  }

	  public int getColumnCount() {
	    return m_colNames.length;
	  }

	  public int getRowCount() {
	    return 1;
	  }

	  public String getColumnName(int col) {
	    return "" + col;
	  }

	  public Object getValueAt(int row, int col) 
	  {
	    return "";
	  }
	}
	
	
	

