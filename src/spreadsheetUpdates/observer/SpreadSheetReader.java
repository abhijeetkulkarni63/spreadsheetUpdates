package spreadsheetUpdates.observer;

import spreadsheetUpdates.util.FileProcessorI;
import spreadsheetUpdates.util.Logger;

import java.util.Map;

/**
 * Class to implement Spread Sheet reading functionalities
 * @author Abhijeet Kulkarni
 *
 */
public class SpreadSheetReader {
	
	/**
	 * Reads the spread sheet using inputFileProcessor, checks for cyclic dependencies and writes sum of all cells to output file. 
	 * @param inputFileProcessorIn
	 * @param outputFileProcessorIn
	 */
	public void readSpreadSheet(FileProcessorI inputFileProcessorIn, FileProcessorI outputFileProcessorIn) {
		SpreadSheet sheet = new SpreadSheet();
    	String line = null;
    	while(null != (line = inputFileProcessorIn.readLineFromFile())) {
    		String[] result = line.split("=");
    		String[] operands = result[1].split("\\+");
    		Cell resultCell = sheet.getSpreadSheet().get(result[0]);
    		int operandOneValue = 0;
    		Cell operandOneCell = null;
    		try {
    			operandOneValue = Integer.parseInt(operands[0]);
    			if(operandOneValue < 10)
    				throw new SpreadSheetException("Operand Should be greater than 10");
    			try {
        			Cell oldOperand = (Cell)resultCell.getOperandOne();
        			if(null != oldOperand)
        				oldOperand.unregisterListener(resultCell);
        		} catch(ClassCastException castException) {
        			
        		}
    			resultCell.setOperandOne(operandOneValue);
    		} catch(NumberFormatException exception) {
    			operandOneCell = sheet.getSpreadSheet().get(operands[0]);
        		try {
        			Cell oldOperand = (Cell)resultCell.getOperandOne();
        			if(null != oldOperand)
        				oldOperand.unregisterListener(resultCell);
        		} catch(ClassCastException castException) {
        			
        		}
        		resultCell.setOperandOne(operandOneCell);
    		} catch(SpreadSheetException exception) {
    			System.err.println(exception.toString());
    		}
    		int operandTwoValue = 0;
    		Cell operandTwoCell = null;
    		try {
    			operandTwoValue = Integer.parseInt(operands[1]);
    			if(operandTwoValue < 10)
    				throw new SpreadSheetException("Operand Should be greater than 10");
    			try {
        			Cell oldOperand = (Cell)resultCell.getOperandTwo();
        			if(null != oldOperand)
        				oldOperand.unregisterListener(resultCell);
        		} catch(ClassCastException castException) {
        			
        		}
    			resultCell.setOperandTwo(operandTwoValue);
    		} catch(NumberFormatException exception) {
    			operandTwoCell = sheet.getSpreadSheet().get(operands[1]);
        		try {
        			Cell oldOperand = (Cell)resultCell.getOperandTwo();
        			if(null != oldOperand)
        				oldOperand.unregisterListener(resultCell);
        		} catch(ClassCastException castException) {
        			
        		}
        		resultCell.setOperandTwo(operandTwoCell);
    		} catch(SpreadSheetException exception) {
    			System.err.println(exception.toString());
    		}
			try {
				if(null != operandOneCell) {
					checkCycle(operandOneCell, resultCell);
					operandOneCell.registerListener(resultCell);
				}
				if(null != operandTwoCell) {
					checkCycle(operandTwoCell, resultCell);
					operandTwoCell.registerListener(resultCell);
				}
				resultCell.update();
			} catch (SpreadSheetException exception) {
				Logger.writeMessage("Cycle detected for expression "+result[0]+"="+operands[0]+"+"+operands[1], Logger.DebugLevel.CYCLE_DETECTION);
				outputFileProcessorIn.writeLineToFile("Cycle detected for expression "+result[0]+"="+operands[0]+"+"+operands[1]+"\n");
			}
    	}
    	writeSumOfCellValues(sheet,outputFileProcessorIn);
	}
	
	/**
	 * Computes sum of all cell values and writes to output file.
	 * @param sheet
	 */
	private void writeSumOfCellValues(SpreadSheet sheet, FileProcessorI outputFileProcessorIn) {
		Map<String, Cell> cells = sheet.getSpreadSheet();
		int sum = 0;
		for(Cell cell : cells.values()) {
			if(null != cell)
				sum = sum + cell.getValue();
		}
		Logger.writeMessage("Sum: " + sum, Logger.DebugLevel.SUM);
		outputFileProcessorIn.writeLineToFile("The sum of all cell values is: " + sum);
	}

	/**
	 * Detects any cycles found in spreadsheet.
	 * @param operandCell
	 * @param resultCell
	 * @throws Exception
	 */
	private void checkCycle(Cell operandCell, Cell resultCell) throws SpreadSheetException{
		for(ListenerI rCell : resultCell.getObservers()) {
			if(rCell.equals(operandCell) || operandCell.equals(resultCell)) {
				throw new SpreadSheetException("Cycle detected");
			} else if(!rCell.equals(operandCell)) {
				checkCycle(operandCell, (Cell)rCell);
			}
		}
	}
}
