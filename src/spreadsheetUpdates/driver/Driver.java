package spreadsheetUpdates.driver;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import spreadsheetUpdates.observer.SpreadSheetReader;
import spreadsheetUpdates.util.FileProcessor;
import spreadsheetUpdates.util.FileProcessorI;
import spreadsheetUpdates.util.Logger;

/**
 * Driver class. Entry point for program.
 * @author Abhijeet Kulkarni
 *
 */
public class Driver {

	public static void main(String[] args) {
		Driver driver = new Driver();
		try {
			driver.validateArgs(args);
			Logger.setDebugValue(Integer.parseInt(args[2]));
			FileProcessorI inputFileProcessor = new FileProcessor(new BufferedReader(new FileReader(args[0])));
			FileProcessorI outputFileProcessor = new FileProcessor(new BufferedWriter(new FileWriter(args[1])));
			SpreadSheetReader reader = new SpreadSheetReader();
			reader.readSpreadSheet(inputFileProcessor, outputFileProcessor);
			inputFileProcessor.closeReaderFile();
			outputFileProcessor.closeWriterFile();
		} catch(IOException exception) {
			System.err.println("Error while opening files.");
			exception.printStackTrace();
			System.exit(1);
		} catch(NumberFormatException exception) {
			System.err.println("Invalid Number of Arguments");
			System.exit(1);
		}
	}
	
	/**
	 * Validates command line arguments.
	 * @param args
	 */
	private void validateArgs(String[] args) {
		if(3 == args.length) {
			File inputFile = new File(args[0]);
			if(inputFile.isFile() && inputFile.canRead()) {
				try {
					if(0 == inputFile.length())
						throw new IOException();
					new FileInputStream(inputFile);
				} catch(IOException exception) {
					System.err.println("Error while opening file.");
					exception.printStackTrace();
					System.exit(1);
				}
			}
			File outputFile = new File(args[1]);
			if(outputFile.isFile() && outputFile.canWrite()) {
				try{
					new FileOutputStream(outputFile);
				} catch(IOException exception) {
					System.err.println("Error while opening file.");
					exception.printStackTrace();
					System.exit(1);
				}
			}
		} else {
			System.err.println("Invalid Number of Arguments.");
			System.exit(1);
		}
	}
}
