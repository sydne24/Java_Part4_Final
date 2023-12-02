package com.nwtcstudent.java.textadventure;

import java.sql.SQLException;
import java.util.HashMap;

import java.util.Scanner;
import org.apache.logging.log4j.Level;

import java.time.LocalDateTime;  
import java.time.format.DateTimeFormatter;  

/**
 * The Controller class initializes and controls the flow of the game
 */
public class Controller {
	
	// ### Fields ###
	
	// Game logger
	private static GameLogger logger;
	
	//Player
	private static Player player;
	
	// Database
	private GameDB db;
	
	// Lists for all the items, rooms, and doors within the game
	private static HashMap<Integer, Item> itemList;
	private static HashMap<Integer, Room> roomList;
	private static HashMap<Integer, Door> doorList;
    private static boolean gameOver = false;
	
	// Game states
	private static Room currentRoom;
	private static Item currentItem;
	
	// Moved scanner instantiation to the controller's constructor for consistency
	public static Scanner myScan;

	
	// ### Constructor ###
	
	/**
	 * Sets up the controller object.
	 * @throws SQLException
	 */
	public Controller() throws SQLException {
		
		// Start up the logger
		logger = GameLogger.getInstance();
    	logger.log("Game Started", Level.INFO);
    	
    	//Initialize player
    	player = Player.getInstance();
    	
    	// Create an instance of the database
    	db = new GameDB();
    	
    	// Initialize all the items, rooms, and doors for the game
    	initializeItems();
    	initializeRooms();
    	initializeDoors();
    	currentRoom = roomList.values().iterator().next();
    	
    	// Create the input scanner
    	myScan = new Scanner(System.in);
    	
    	// Introductory messages
    	System.out.println(GameInfo.getHeaderTitle());
    	
    	//Player information
    	System.out.println("Enter your name: ");
    	String input = myScan.nextLine();
    	player.setName(input);
    	System.out.println("Thank you for playing " + player.getName());
    	System.out.println(GameInfo.getIntroMessage());
    	System.out.println(GameInfo.getHelpMessage());
    	
    	
    	// Primary game loop.
    	  // Start the game loop
        while (!gameOver) {
            displayCurrentState();
        }

        // Close the scanner when the game is finished
        myScan.close();
    }

    private void displayCurrentState() {
        // Display current room description
        System.out.println(currentRoom.getDescription());

        // Display available options or prompt for user input
        System.out.println("Available options:");
        System.out.println("1. Look around");
        System.out.println("2. Use item");
        System.out.println("3. Move to another room");
        System.out.println("4. Quit");
    }

    private void lookAround() {
        // Implement look around logic
        System.out.println("You look around the room. What do you see?");
    }

    private void useItem() {
        // Implement use item logic
        System.out.println("You try to use an item. What do you want to use?");
    }

    private void move() {
        // Implement move logic
        System.out.println("You try to move to another room. Where do you want to go?");
    }
	
	
	// ### Methods ###
	
	// Game Logic Methods
	
    public static void endGame() {
        gameOver = true;
    	
        System.out.println(GameInfo.getExitMessage());

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
