package spreadsheetUpdates.observer;

import java.util.HashMap;
import java.util.Map;

import spreadsheetUpdates.util.Logger;

/**
 * Class to maintain all cells in the spreadsheet
 * @author Abhijeet Kulkarni
 *
 */
public class SpreadSheet {
	
	private Map<String, Cell>spreadSheet;
	
	/**
	 * SpreadSheet constructor
	 */
	public SpreadSheet() {
		Logger.writeMessage("In SpreadSheet constructor", Logger.DebugLevel.CONSTRUCTOR);
		spreadSheet = new HashMap<String, Cell>();
		initializeSpreadSheet();
	}
	
	/**
	 * Initializes all cells.
	 */
	private void initializeSpreadSheet() {
		for(char ch = 'a'; ch <= 'z'; ch++)
			for(int i = 1; i <= 26; i++) {
				Cell cell = new Cell(String.valueOf(ch)+Integer.toString(i));
				spreadSheet.put(cell.getCellId(), cell);
			}
	}
	
	/**
	 * @return returns spreadsheet map
	 */
	public Map<String, Cell> getSpreadSheet() {
		return spreadSheet;
	}
}
