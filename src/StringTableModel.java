import java.awt.Rectangle;
import java.util.ArrayList;

import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;


public class StringTableModel extends AbstractTableModel implements ModelListener
{
    private String[] columnNames = {"X", "Y", "Width", "Height"}; 
    private ArrayList<DShapeModel> shapeModels; 

    public StringTableModel() {
    	super();
        shapeModels = new ArrayList<DShapeModel>(); 
    }

    public int getRowCount() {
    	return shapeModels.size();
    }
    
    public String[] getColumnNames() {
		return columnNames;
	}
    
    // Returns length of column array
    @Override
    public int getColumnCount() {
    	return columnNames.length;
    }
    
    // Set column names
    @Override
    public String getColumnName(int index) {
        return columnNames[index];
    }
    
    public int getRowForSpecificModel(DShapeModel model) { 
        return shapeModels.indexOf(model); 
    } 
    
    @Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		// cells cannot be edited
		return false;
	}
    
    // Add a shape model to the array list
    public void addAShapeModel(DShapeModel shapeModel) { 
        shapeModels.add(0, shapeModel); 
        fireTableDataChanged(); 
    }
    
    // Remove a shape model from the array list
    public void removeAShapeModel(DShapeModel shapeModel) { 
        shapeModels.remove(shapeModel); 
        fireTableDataChanged(); 
    } 
    
    // Move selected shape to the back
    public void moveShapeToBack(DShapeModel shapeModel) { 
        if(!shapeModels.isEmpty()) {
        	shapeModels.remove(shapeModel);
        	shapeModels.add(shapeModel); 
        }
        fireTableDataChanged(); 
    } 
    
    // Move selected shape to the front
    public void moveShapeToFront(DShapeModel shapeModel) { 
        if(!shapeModels.isEmpty()) {
        	shapeModels.remove(shapeModel);
        	shapeModels.add(0, shapeModel); 
        }
        fireTableDataChanged(); 
    } 
    
    // Return value of specific row and column of the table
    public Object getValueAt(int row, int col) {
        Rectangle shapeBounds = shapeModels.get(row).getBounds(); 
        
        switch (col) {
        	case 0:
        		return shapeBounds.x;
        	case 1:
        		return shapeBounds.y;
        	case 2:
        		return shapeBounds.width;
        	case 3:
        		return shapeBounds.height;
        	default:
        		return null;
        }
	}

	@Override
	public void modelChanged(DShapeModel shapeModel) {
        int index = shapeModels.indexOf(shapeModel); 
        fireTableRowsUpdated(index, index); 
	}
}
	
	
	

