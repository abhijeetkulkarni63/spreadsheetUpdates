package spreadsheetUpdates.observer;

/**
 * Interface for implementing subject
 * @author Abhijeet Kulkarni
 *
 */
public interface SubjectI {
	/**
	 * Registers observer to subject
	 * @param listener
	 */
	public void registerListener(ListenerI listener);
	
	/**
	 * Removes observer from list of observers
	 * @param listener
	 */
	public void unregisterListener(ListenerI listener);
	
	/**
	 * Notify all observer about change in state
	 */
	public void notifyListners();
}