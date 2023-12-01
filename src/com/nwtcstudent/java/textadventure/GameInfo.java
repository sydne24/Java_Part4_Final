package com.nwtcstudent.java.textadventure;

/**
 * Provides informational and contextual messages related to the game's storytelling.
 */
public class GameInfo {
	
	// ### Fields ###
	
	// The name of the game
	private static String name = "GAME TITLE";
	
	// ### Methods ###
	
	/**
	 * @return a stylized header for the game.
	 */
	public static String getHeaderTitle() {
		
		String message = String.format(" _____________________________\n"
				+ "\n"
				+ "|         %s          |\n"
				+ " _____________________________\n", name);
		
		return message;
	}
	
	/**
	 * @return an introductory message to start the actual game.
	 */
	public static String getIntroMessage() {
		
		String message = "You find yourself in a vacant room...\nYou notice three doors to the NORTH, WEST, and EAST.";
		return message;
	}
	
	/**
	 * @return the ending message for when the player completes the game.
	 */
	public static String getEndMessage() {
		
		String message = "You walk into the field and continue your adventure...";
		return message;
	}
	
	/**
	 * @return the exit message for when the game exits.
	 */
	public static String getExitMessage() {
		
		String message = String.format("Thank you for playing %s!", name);
		return message;
	}
	
	public static String getHelpMessage() {
		
		String message = "\n(Type HELP for a list of commands) \n";
		return message;
	}
	
	
	// ### Properties ###
	
	public String getName() {
		
		return name;
	}
}
