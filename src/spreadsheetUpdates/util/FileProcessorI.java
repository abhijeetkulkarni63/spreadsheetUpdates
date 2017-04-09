package spreadsheetUpdates.util;

/**
 * Interface for file processing
 * @author Abhijeet Kulkarni
 *
 */
public interface FileProcessorI {
	/**
	 * Returns line read from file
	 * @return
	 */
	public String readLineFromFile();
	
	/**
	 * Close output file.
	 */
	public void closeWriterFile();
	
	/**
	 * Close input file.
	 */
	public void closeReaderFile();
	
	/**
	 * Writes string to output file.
	 * @param outStringIn
	 */
	public void writeLineToFile(String outStringIn);
}
