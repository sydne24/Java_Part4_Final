package com.nwtcstudent.java.textadventure;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Provides informational and contextual messages related to the game's storytelling.
 */
public class GameInfo {
	
	// ### Fields ###
	
	// The name of the game
	private static String name = "GAME TITLE";
	
	// Player reference
	static Player player = Player.getInstance();
	
	// DateTime objects
	// 5.1 - Use of dates and times
	DateTimeFormatter tf = DateTimeFormatter.ofPattern("HH:mm:ss");
	static LocalTime localTime = LocalTime.now();
		
	static long now = System.currentTimeMillis();
	// 5.2 - Implementation of a custom calendar
    static Calendar christmas = Calendar.getInstance();
		
	static LocalTime earlyMorning1 = LocalTime.of(0, 0), earlyMorning2 = LocalTime.of(6, 0);
	static LocalTime morning1 = LocalTime.of(6, 1), morning2 = LocalTime.of(11, 59);
	static LocalTime noon1 = LocalTime.of(12, 0), noon2 = LocalTime.of(12, 59);
	static LocalTime afternoon1 = LocalTime.of(13, 0), afternoon2 = LocalTime.of(17, 59);
	static LocalTime evening1 = LocalTime.of(18, 0), evening2 = LocalTime.of(23, 59);
		
	//Resource bundle and locale
	static Locale enLoc = new Locale("en");
	static Locale spLoc = new Locale("sp");
		
	static ResourceBundle en = ResourceBundle.getBundle("Game", enLoc);
	static ResourceBundle sp = ResourceBundle.getBundle("Game", spLoc);

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
	 * @return a welcome message to the player.
	 */
	public static String getPlayerMessage() {
		
		String message1 = "";
		String message2 = "";
		
		// Return a message to the user based on their current system time
		if ((localTime.isAfter(earlyMorning1) && localTime.isBefore(earlyMorning2)) || localTime.equals(earlyMorning1) || localTime.equals(earlyMorning2))
		{
			// 5.3 - Proper use of String localization
			message1 = (en.getString("earlyMorning") + " " + player.getName());
			message2 = (sp.getString("earlyMorning") + " " + player.getName());
		}
		else if ((localTime.isAfter(morning1) && localTime.isBefore(morning2)) || localTime.equals(morning1) || localTime.equals(morning2))
		{
			message1 = (en.getString("morning") + " " + player.getName());
			message2 = (sp.getString("morning") + " " + player.getName());
		}
		else if ((localTime.isAfter(noon1) && localTime.isBefore(noon2)) || localTime.equals(noon1) || localTime.equals(noon2))
		{
			message1 = (en.getString("noon") + " " + player.getName());
			message2 = (sp.getString("noon") + " " + player.getName());
		}
		else if ((localTime.isAfter(afternoon1) && localTime.isBefore(afternoon2)) || localTime.equals(afternoon1) || localTime.equals(afternoon2))
		{
			message1 = (en.getString("afternoon") + " " + player.getName());
			message2 = (sp.getString("afternoon") + " " + player.getName());
		}
		else if ((localTime.isAfter(evening1) && localTime.isBefore(evening2)) || localTime.equals(evening1) || localTime.equals(evening2))
		{
			message1 = (en.getString("evening") + " " + player.getName());
			message2 = (sp.getString("evening") + " " + player.getName());
		}
		
		return "\n" + message1 + "\n" + message2;
	}
	
	public static String getChristmasMessage() {
		String message = "";
		
		christmas.set(Calendar.MONTH, Calendar.DECEMBER);
	    christmas.set(Calendar.DAY_OF_MONTH, 25);
	    
	    // 5.4 - At least one date calculation
	    long untilChristmas = christmas.getTimeInMillis() - now;
	    
	    SimpleDateFormat df = new SimpleDateFormat("dd");
	    Date date = new Date(untilChristmas);
	    
	    message = df.format(date);
	    
		return "\n" + message + " days until Christmas!";
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
		
		String message = "\nA feeling of indescribable peace washes over you as the famous pop singer, Rick Astley, appears before you and begins singing you a song... THE END! Thanks for playing!!!";
		return message;
	}
	
	/**
	 * @return the exit message for when the game exits.
	 */
	public static String getExitMessage() {
		
		String message = String.format("\nThank you for playing %s, %s!", name, player.getName());
		return message;
	}
	
	public static String getHelpMessage() {
		
		String message = "\n(Type HELP for a list of commands)";
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
				+ "\n------------------------------------------------------------------\n";
				
				
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
