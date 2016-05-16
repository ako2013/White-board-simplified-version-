import java.awt.Component;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.table.TableCellRenderer;


public class VariableRowHeightRenderer extends JLabel implements TableCellRenderer 
{
	public VariableRowHeightRenderer() 
	{
		super();
	    setOpaque(true);
	    setHorizontalAlignment(JLabel.CENTER);
	}
	
	 public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
		      boolean hasFocus, int row, int column) 
	 {
		 if (isSelected) 
		 {
			 setBackground(UIManager.getColor("Table.selectionBackground"));
		 }

		 if (hasFocus) 
		 {
			 setBorder(UIManager.getBorder("Table.focusCellHighlightBorder"));
			 if (table.isCellEditable(row, column)) 
			 {
		        super.setForeground(UIManager.getColor("Table.focusCellForeground"));
		        super.setBackground(UIManager.getColor("Table.focusCellBackground"));
			 }
		 } 
		 else 
		 {
			 setBorder(BorderFactory.createEmptyBorder(1, 1, 1, 1));
		 }
		 setText((String) (value));
		 table.setRowHeight(row, getPreferredSize().height + row * 10);
		 return this;
	 }
}
