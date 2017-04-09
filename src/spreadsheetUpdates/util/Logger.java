package spreadsheetUpdates.util;

/**
 * Logger Class
 * @author Abhijeet Kulkarni
 *
 */
public class Logger{

  public static enum DebugLevel {
	  CONSTRUCTOR,
	  UPDATES,
	  SUM,
	  DEFAULT,
	  CYCLE_DETECTION
  };
  
  private static DebugLevel debugLevel;

  /**
   * Sets debug level according to input from user.
   * @param levelIn
   */
  public static void setDebugValue (int levelIn) {
    switch (levelIn) 
    {
    	case 1:
    		debugLevel = DebugLevel.CONSTRUCTOR;
    		break;
    	
    	case 2:
    		debugLevel = DebugLevel.UPDATES;
    		break;
    		
    	case 3:
    		debugLevel = DebugLevel.SUM;
    		break;
    		
    	case 4: 
		   	debugLevel = DebugLevel.CYCLE_DETECTION; 
		   	break;
  		
    	case 0: 
		   	debugLevel = DebugLevel.DEFAULT; 
		   	break;
    }
  }

  /**
   * Setter for debugLevel
   * @param levelIn
   */
  public static void setDebugValue (DebugLevel levelIn) {
	  debugLevel = levelIn;
  }

  /**
   * Writes message to standard out if the debugLevel matches with levelIn.
   * @param message
   * @param levelIn
   */
  public static void writeMessage (String  message, DebugLevel levelIn ) {
	  if (levelIn == debugLevel)
		  System.out.println(message);
  }

  /**
	*  @return String
	*/
  public String toString() {
	  return "Debug Level is " + debugLevel;
  }
}
