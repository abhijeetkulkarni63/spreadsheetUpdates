package spreadsheetUpdates.observer;

/**
 * Interface to implement observer
 * @author Abhijeet Kulkarni
 *
 */
public interface ListenerI {
	
	/**
	 * Subject invokes update method to notify observer that state of the subject is changed.
	 */
	public void update();
}
