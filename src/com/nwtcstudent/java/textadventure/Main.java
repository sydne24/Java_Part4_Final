package com.nwtcstudent.java.textadventure;
import org.apache.logging.log4j.Level;


/**
 * The Main class is the first to run on program startup, and performs any non-game-related tasks before creating the game controller
 */
public class Main {
	
	// ### Fields ###
	
	//Instantiate Logging Object to be used by class
	// 1.1 - Proper use of visibility modifiers
	private static GameLogger logger = GameLogger.getInstance();

	
	// ### Methods ###
	
	/**
	 * Initializes the game and handles any fatal errors faced within the game.
	 * @param args arguments to be passed in when the application begins
	 */
	public static void main(String[] args) {
		// Logging
		logger.log("Logging started.", Level.INFO);
		// 6-1: try catch block
		// Exception handling block
		try {
			// Run the game
			Controller controller = new Controller();
		}
		catch (Exception e) {
			handleError(e);
		}
		finally {
			// Exit the game
			logger.log("Game Exit", Level.INFO);
		}
	}

	// Create a separate method to handle errors
	private static void handleError(Exception e) {
		String errorMessage = "An error has occurred and the application had to exit!" +
				"\n\nCause of Error: " + e.getMessage() +
				"\n\nLocation:\n\t" + e.getStackTrace()[0] +
				"\n\nFull Stack Trace:";
		
		for (StackTraceElement line : e.getStackTrace()) {
			errorMessage = errorMessage += "\n\t" + line;
		}
		
		logger.log(errorMessage, Level.FATAL);
	}
}

