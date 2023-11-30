package com.nwtcstudent.java.textadventure;

/**
 * Provides informational and contextual messages related to the game's storytelling.
 */
public class GameInfo {
	
	/**
	 * @return a stylized header for the game.
	 */
	public String getHeaderTitle() {
		
		String message = " _____________________________\n"
				+ "\n"
				+ "|         GAME TITLE          |\n"
				+ " _____________________________\n";
		
		return message;
	}
	
	/**
	 * @return an introductory message to start the actual game.
	 */
	public String getIntroMessage() {
		
		String message = "You find yourself in a vacant room...\nYou notice three doors to the NORTH, WEST, and EAST.";
		return message;
	}
	
	/**
	 * @return the ending message for when the player completes the game.
	 */
	public String getEndMessage() {
		
		return null;
	}
	
	public String getHelpMessage() {
		
		String message = "\n(Type HELP for a list of commands) \n";
		return message;
	}
}
