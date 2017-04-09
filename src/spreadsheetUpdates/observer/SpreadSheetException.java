package spreadsheetUpdates.observer;

import spreadsheetUpdates.util.Logger;

public class SpreadSheetException extends Exception {
	private String message;
	
	public SpreadSheetException(String messageIn) {
		Logger.writeMessage("In SpreadSheetException Constructor", Logger.DebugLevel.CONSTRUCTOR);
		message = messageIn;
	}

	@Override
	public String toString() {
		return message;
	}
}
