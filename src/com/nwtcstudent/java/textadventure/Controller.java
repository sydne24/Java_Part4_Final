package com.nwtcstudent.java.textadventure;

import java.sql.SQLException;
import java.util.HashMap;

import java.util.Scanner;
import org.apache.logging.log4j.Level;

/**
 * The Controller class initializes and controls the flow of the game
 */
public class Controller {
	
	// ### Fields ###
	
	// Game logger
	private GameLogger logger;
	
	// Database
	private GameDB db;
	
	// Lists for all the items, rooms, and doors within the game
	private static HashMap<Integer, Item> itemList;
	private static HashMap<Integer, Room> roomList;
	private static HashMap<Integer, Door> doorList;
	
	// Game states
	private boolean gameOver = false;
	private static Room currentRoom;
	private static Item currentItem;
	
	// Storytelling/Messages
	GameInfo story;
	
	// Moved scanner instantiation to the controller's constructor for consistency
	static Scanner myScan;

	
	// ### Constructor ###
	
	/**
	 * Sets up the controller object.
	 * @throws SQLException
	 */
	public Controller() throws SQLException {
		
		// Start up the logger
		logger = GameLogger.getInstance();
    	logger.log("Game Started", Level.INFO);
    	
    	// Create an instance of the database
    	db = new GameDB();
    	
    	// Initialize all the items, rooms, and doors for the game
    	initializeItems();
    	initializeRooms();
    	initializeDoors();
    	
    	// Initialize the story teller
    	story = new GameInfo();
    	
    	// Create the input scanner
    	myScan = new Scanner(System.in);
    	
    	// Introductory messages
    	System.out.println(story.getHeaderTitle());
    	System.out.println(story.getIntroMessage());
    	System.out.println(story.getHelpMessage());
    	
    	// Primary game loop. Handle the inputs in the verb-noun parser and have them call methods in the Controller class or a separate interaction class.
//    	while (!gameOver) {
//    		
//    		
//    	}
    	
    	
	}
	
	
	// ### Methods ###
	
	// Game Logic Methods
	
    public static void endGame() {
        System.out.println("Thanks for playing!");
        System.exit(0);
    }
    
    
    // Database-Accessing Methods
    
    /**
     * Initializes the list of items.
     * @throws SQLException
     */
 	public void initializeItems() throws SQLException {
 		
 		itemList = db.getItems();
 	}
 	
 	/**
 	 * Initializes the list of rooms.
 	 * @throws SQLException
 	 */
 	public void initializeRooms() throws SQLException {
 		
 		roomList = db.getRooms();
 	}
 	
 	/**
 	 * Initializes the list of doors.
 	 * @throws SQLException
 	 */
 	public void initializeDoors() throws SQLException {
 		
 		doorList = db.getDoors(roomList);
 		db.setRoomFeatures(itemList, roomList, doorList);
 	}
 	
	
	// ### Properties ###
	
 	/**
 	 * @return all items available in the game.
 	 */
	public static HashMap<Integer, Item> getItems() {
		
		return itemList;
	}
	
	/**
	 * @return all rooms available in the game.
	 */
	public static HashMap<Integer, Room> getRooms() {
		
		return roomList;
	}
	
	/**
	 * @return the room the player is currently in.
	 */
	public static Room getCurrentRoom() {
		
		return currentRoom;
	}
	/**
	 * Sets the room the player is currently in.
	 * @param room the room to set.
	 */
	public static void setCurrentRoom(Room room) {
		
		currentRoom = room;
    	//TODO: instantiate player and inventory
    	
    	String input = "";
    	
    	//start input loop?
    	
    	//TODO: 
    	
	}
}
