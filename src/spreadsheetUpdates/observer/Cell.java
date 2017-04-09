package spreadsheetUpdates.observer;

import java.util.ArrayList;
import java.util.List;

import spreadsheetUpdates.util.Logger;

/**
 * Class cell acts as Observe and Subject simultaneously.
 * @author dell1
 *
 */
public class Cell implements ListenerI, SubjectI{
	private String cellId;
	private int value;
	private Object operandOne;
	private Object operandTwo;
	
	private List<ListenerI> observers;
	
	/**
	 * Cell constructor
	 * @param cellIndicesIn
	 */
	public Cell(String cellIndicesIn) {
		Logger.writeMessage("In Cell constructor", Logger.DebugLevel.CONSTRUCTOR);
		cellId = cellIndicesIn;
		value = 0;
		observers = new ArrayList<ListenerI>();
	}
	
	/**
	 * @return returns first operand
	 */
	public Object getOperandOne() {
		return operandOne;
	}

	/**
	 * 
	 * @return list of observers
	 */
	public List<ListenerI> getObservers() {
		return observers;
	}

	/**
	 * Sets first operand
	 * @param operandOneIn
	 */
	public void setOperandOne(Object operandOneIn) {
		operandOne = operandOneIn;
	}

	/**
	 * 
	 * @return second operand
	 */
	public Object getOperandTwo() {
		return operandTwo;
	}

	/**
	 * sets second operand
	 * @param operandTwoIn
	 */
	public void setOperandTwo(Object operandTwoIn) {
		operandTwo = operandTwoIn;
	}
	
	/**
	 * 
	 * @return cell identifier (Row Number and Column Number)
	 */
	public String getCellId() {
		return cellId;
	}
	
	/**
	 * 
	 * @return value store in cell
	 */
	public int getValue() {
		return value;
	}

	/**
	 * Updates state of the observer and also notifies its observers.
	 */
	@Override
	public void update() {
		int tempOperandOne;
		try {
			tempOperandOne = (Integer)operandOne;
		} catch(ClassCastException exception) {
			Cell tempOperandOneCell = (Cell)operandOne;
			tempOperandOne = tempOperandOneCell.getValue();
		}
		int tempOperandTwo;
		try {
			tempOperandTwo = (Integer)operandTwo;
		} catch(ClassCastException exception) {
			Cell tempOperandTwoCell = (Cell)operandTwo;
			tempOperandTwo = tempOperandTwoCell.getValue();
		}
		value = tempOperandOne + tempOperandTwo;
		Logger.writeMessage("Cell: [ CellId: "+ cellId + " Value: "+value+" ]", Logger.DebugLevel.UPDATES);
		notifyListners();
	}

	/**
	 * Register observer to subject
	 */
	@Override
	public void registerListener(ListenerI listener) {
		observers.add(listener);
	}

	/**
	 * Remove observer from list of observers
	 */
	@Override
	public void unregisterListener(ListenerI listener) {
		int index = observers.indexOf(listener);
		if(0 <= index)
			observers.remove(index);
	}

	/**
	 * Notifies observer that state of the subject has changed.
	 */
	@Override
	public void notifyListners() {
		for(ListenerI listener : observers) {
			listener.update();
		}
	}
}
