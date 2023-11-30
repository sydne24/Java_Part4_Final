package com.nwtcstudent.java.textadventure;
import org.apache.logging.log4j.*;

/**
 * Static class to be used for all logging operations.
 **/
public final class GameLogger {
	
	// ### Fields ###
	
	// 1.5 / 1.6 / 2.3 -- proper use of static and final keywords, use of the Singleton pattern
	// The GameLogger object
	private static final GameLogger instance = new GameLogger();
	
	// Logger object
	private static final Logger logger = LogManager.getFormatterLogger(GameLogger.class.getName());
	
	
	// ### Methods ###
	
	/**
	 * Log a message.
	 * @param message the message to output.
	 * @param level the level of the message.
	 */
	public void log(String message, Level level) {
		
		logger.log(level, message);
	}
	
	// 4.a / 4.9 -- Override of .toString(), proper use of @Override notation
	@Override
	public String toString() {
		
		return this.getClass().getName();
	}
	
	
	// ### Properties ###
	
	// 2.3 -- use of the singleton pattern
	/**
	 * Get the GameLogger as an object
	 * @return the logger object.
	 */
	public static GameLogger getInstance() {
		
		return instance;
	}
}
