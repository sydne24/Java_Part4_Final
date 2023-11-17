package com.nwtcstudent.java.textadventure;
import org.apache.logging.log4j.*;

// Immutable/Singleton class for logging all application events
public final class GameLogger {
	
	// 1.5 / 1.6 / 2.3 -- proper use of static and final keywords, use of the Singleton pattern
	// The GameLogger object
	private static final GameLogger instance = new GameLogger();
	
	// Logger object
	private static final Logger logger = LogManager.getFormatterLogger(GameLogger.class.getName());
	
	
	// 2.3 -- use of the singleton pattern
	// Get the GameLogger as an object
	public static GameLogger getInstance() {
		
		return instance;
	}
	
	// Log a message
	public void log(String message, Level level) {
		
		logger.log(level, message);
	}
	
	// 4.a / 4.9 -- Override of .toString(), proper use of @Override notation
	@Override
	public String toString() {
		
		return this.getClass().getName();
	}
}
