package com.nwtcstudent.java.textadventure;

import org.apache.logging.log4j.Level;

/**
 * The Controller class initializes and controls the flow of the game
 */
public class Controller {
	
	GameLogger logger;

	public Controller() {
		
		logger = GameLogger.getInstance();
    	logger.log("Game Started", Level.INFO);
    	
	}
}
