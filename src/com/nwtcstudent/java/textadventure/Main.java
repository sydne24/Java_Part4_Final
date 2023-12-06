package com.nwtcstudent.java.textadventure;
import java.sql.SQLException;

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
		logger.log("Logging started by " + logger.toString() + ".", Level.INFO);
		// 6-1 Proper use of Try-Catch blocks
		// 6-2 Use of catch, multi-catch, and finally clauses
		// Exception handling block
		try {
			// Run the game
			Controller controller = new Controller();
		}
		catch (SQLException e) {
			
			handleError(e);
			
			System.out.println("An error has occured and the program had to close");
		}
		catch (Exception e) {
			handleError(e);
			
			System.out.println("An error has occured and the program had to close");
		}
		finally {
			// Exit the game
			logger.log("Game Exit", Level.INFO);
		}
	}
	
	// 1.3 Use of overloaded methods and/or constructors
	/**
	 * Handle any database-related error that was thrown
	 * @param e the database exception
	 */
	private static void handleError(SQLException e) {
		String errorMessage = "A DATABASE error has occurred and the application had to exit!" +
				"\n\nCause of Error: " + e.getMessage() +
				"\n\nLocation:\n\t" + e.getStackTrace()[0] +
				"\n\nFull Stack Trace:";
		
		for (StackTraceElement line : e.getStackTrace()) {
			errorMessage = errorMessage += "\n\t" + line;
		}
		
		logger.log(errorMessage, Level.FATAL);
	}

	// 1.3 Use of overloaded methods and/or constructors
	/**
	 * Handle any generic error that was thrown
	 * @param e the generic exception
	 */
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

