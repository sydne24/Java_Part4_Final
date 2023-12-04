package com.nwtcstudent.java.textadventure;

/**
 * Provides informational and contextual messages related to the game's storytelling.
 */
public class GameInfo {
	
	// ### Fields ###
	
	// The name of the game
	private static String name = "GAME TITLE";
	
	// Player reference
	static Player player = Player.getInstance();
	
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
		
		String message = "\nYou find yourself in a vacant room...\nYou notice three doors to the NORTH, WEST, and EAST.";
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
		
		String message = String.format("Thank you for playing %s, %s!", name, player.getName());
		return message;
	}
	
	public static String getHelpMessage() {
		
		String message = "\n(Type HELP for a list of commands) \n";
		return message;
	}
	
	public static String getAvailableCommands() {
		
		String message = "\n------------------------------ HELP ------------------------------"
				+ "\nCommands can be entered into the console with any mix of letter casing."
				+ "\nTo interact with a specific object, write a suitable command followed by the object to interact with it."
				+ "\nFor example, 'LOOK NORTH' to target a door, followed by 'OPEN DOOR' to open it."
				+ "\n\nObject Commands:"
				+ "\nLOOK/INSPECT [OBJECT]: Describe the given object"
				+ "\nUSE/INTERACT [OBJECT]: Use the given object"
				+ "\nMOVE [NORTH/EAST/SOUTH/WEST]: Focus on any object contained within the selected portion of a room"
				+ "\n\nOther Commands:"
				+ "\nLOOK AROUND: Describe the environment around you"
				+ "\nEXIT: Exit the game"
				+ "\n\nThere are many additional alternative commands you can use, such as EAT [FOOD], to specify how you would like to interact."
				+ "\n------------------------------------------------------------------";
				
				
//				"// Display available options or prompt for user input\r\n"
//				+ "        System.out.println(\"Available options:\");\r\n"
//				+ "        System.out.println(\"1. Look around\");\r\n"
//				+ "        System.out.println(\"2. Use item\");\r\n"
//				+ "        System.out.println(\"3. Move to another room\");\r\n"
//				+ "        System.out.println(\"4. Quit\");
				
		return message;
	}
	
	
	// ### Properties ###
	
	public String getName() {
		
		return name;
	}
}
