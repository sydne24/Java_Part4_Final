package com.nwtcstudent.java.textadventure;
import java.util.Scanner;
import org.apache.logging.log4j.Level;

/**
 * The Controller class initializes and controls the flow of the game
 */
public class Controller {
	
	private static GameLogger logger;
	static Scanner myScan = new Scanner(System.in);

	public Controller() {
		
		logger = GameLogger.getInstance();
    	logger.log("Game Started", Level.INFO);
    	
    	//TODO: instantiate player and inventory
    	
    	String input = "";
    	System.out.println("'A Long Way From Home' is a text-based puzzle adventure game made to test your wits and challenge your mind. \n"
    			+ "Please note that the game can be closed at any time by typing 'exit'.");
    	
    	//start input loop?
    	
    	//TODO: 
    	
	}
	
	//msc methods
	//input parser - for now just returns input in lowercase format
	public static String GetInput() {
		boolean matchFound = false;
		
		//while (!matchFound) {
			String input = myScan.nextLine();
			
	    	if (input.toString().toLowerCase() == "exit") {
	    		EndGame();
	    	}
	    	

		//}
    	return input.toString().toLowerCase();
	}
	
    public static void EndGame() {
        System.out.println("Thanks for playing!");
        System.exit(0);
    }
}
