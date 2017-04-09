package spreadsheetUpdates.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;

/**
 * Implements FileProcessorI iterface to perform operations on input and output files. 
 * @author Abhijeet Kulkarni
 *
 */
public class FileProcessor implements FileProcessorI {
	
    private BufferedReader bufferedReader;
    private BufferedWriter bufferedWriter;
    
    /**
     * Constructor for input files.
     * @param bufferedReaderIn
     */
    public FileProcessor(BufferedReader bufferedReaderIn){
    	Logger.writeMessage("In FileProcessor, BufferedReader constructor", Logger.DebugLevel.CONSTRUCTOR);
    	bufferedReader = bufferedReaderIn;
    	bufferedWriter = null;
    }
    
    /**
     * Constructor for output files.
     * @param bufferedWriterIn
     */
    public FileProcessor(BufferedWriter bufferedWriterIn){
    	Logger.writeMessage("In FileProcessor, BufferedWriter constructor", Logger.DebugLevel.CONSTRUCTOR);
    	bufferedWriter = bufferedWriterIn;
    	bufferedReader = null;
    }
    
    /**
     * Reads line from input file.
     * @return String
     */
    public String readLineFromFile(){
    	try {
    		return bufferedReader.readLine();
    	} catch (IOException exception) {
    		System.err.println("Error while reading from while.");
    		exception.printStackTrace();
    		System.exit(1);
    	} finally {
    		
    	}
    	return null;
    }
    
    /**
     * Close output file.
     */
    public void closeWriterFile() {
    	try {
    		bufferedWriter.close();
    	} catch (IOException exception) {
    		System.err.println("Error while closing file");
    		exception.printStackTrace();
    		System.exit(1);
    	}
    }

    /**
     * Close input file.
     */
	public void closeReaderFile() {
		try {
			bufferedReader.close();
		} catch (IOException exception) {
			System.err.println("Error while closing file.");
			exception.printStackTrace();
			System.exit(1);
		}
	}
    
	/**
	 * Writes line to output file.
	 * @param outStringIn
	 */
	public void writeLineToFile(String outStringIn) {
		try {
			bufferedWriter.write(outStringIn);
		} catch (IOException exception) {
			System.err.println("Error while writing to file.");
			exception.printStackTrace();
			System.exit(1);
		}
	}
}
