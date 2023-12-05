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
	
	// Scanner for user input
	public static Scanner myScan;

	// ### Constructor ###
	
	/**
	 * Sets up the controller object.
	 * @throws SQLException
	 */
	public Controller() throws SQLException {
		
		// Sets up all the initial variables for starting the game
		setup();
    	
    	// ### Introduction ###
    	
    	// Game title header
    	System.out.println(GameInfo.getHeaderTitle());
    	
    	// Player information entry
    	System.out.println("Enter your name: ");
    	String input = myScan.nextLine();
    	player.setName(input);
    	
    	// Introductory messages
    	System.out.println(GameInfo.getPlayerMessage());
    	System.out.println(GameInfo.getChristmasMessage());
    	System.out.println(GameInfo.getIntroMessage());
    	System.out.println(GameInfo.getHelpMessage());
    	
    	
    	// Start the primary game loop.
        while (!gameOver) {
        	
        	// Get and act on user input
        	// nextLine is required to pass entire input string to the Parser
            input = myScan.nextLine();
        	Parser.parseInput(input);
            
        }

        // Close the scanner when the game is finished
        myScan.close();
    }
	
	private void setup() throws SQLException {
		
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
		
		// Set the current room to room 0 (starting room)
		Player.setCurrentRoom(roomList.get(0));
		
		// Create the input scanner
		myScan = new Scanner(System.in);
	}

    static void lookAround() {
    	// Room description will be given when the player decides to LOOK AROUND / LOOK ROOM / INSPECT ROOM / etc
    	// Display current room description
    	System.out.println(Player.getCurrentRoom().getDescription());
    	//Display current room features
    	
    	//NORTH
    	if (Player.getCurrentRoom().getNFeature() != null)
    		System.out.println("To the north is a " + Player.getCurrentRoom().getNFeature().getName());
    	
    	//EAST
    	if (Player.getCurrentRoom().getEFeature() != null) 
    		System.out.println("To the east is a " + Player.getCurrentRoom().getEFeature().getName());
    	
    	//SOUTH
    	if (Player.getCurrentRoom().getSFeature() != null)
    		System.out.println("To the south is a " + Player.getCurrentRoom().getSFeature().getName());
    	
    	//WEST
    	if (Player.getCurrentRoom().getWFeature() != null)
    		System.out.println("To the west is a " + Player.getCurrentRoom().getWFeature().getName());
    }

    private void useItem() {
        // Implement use item logic
        System.out.println("You try to use an item. What do you want to use?");
    }

    /**
     * Try to move towards the player's current focus (an item or door)
     */
    private void move() {
    	
    	if (Player.getCurrentFocus() != null) {
    		
    		int dir = -1;
    		
    		// Get all features of the current room
    		IFocusable[] features = Player.getCurrentRoom().getFeatures();
    		for (int i = 0; i < features.length; i++) {
    			
    			// Find which feature the player is trying to focus on
    			if (features[i] != null && features[i] == Player.getCurrentFocus()) {
    				
    				dir = i;
    			}
    		}
    		
    		switch (dir) {
    		
    		case 0:
    			move("north");
    			break;
    		case 1:
    			move("east");
    			break;
    		case 2:
    			move("south");
    			break;
    		case 3:
    			move("west");
    			break;
    		default:
    			System.out.println(GameInfo.getLocationNotFoundMessage());
    			break;
    		}
    	}
    	else {
    		
    		System.out.println(GameInfo.getLocationNotFoundMessage());
    	}	
    }
    
    /**
     * Try to move through a door
     * @param door the door to move through
     */
    private void move(Door door) {
    	
    	if (door.getValue() == 0) {
    		
    		Player.setCurrentRoom(door.enterDoor(Player.getCurrentRoom()));
    	}
    	else {
    		
    		System.out.println(GameInfo.getDoorLockedMessage());
    	}
    }
    
    /**
     * Try to move north/south/east/west
     * @param location north, south, east, or west
     */
    private void move(String location) {
    	
    	IFocusable item = null;
    	
    	switch (location) {
    	case "north": 
    		item = Player.getCurrentRoom().getNFeature();
    		break;
    	case "east":
    		item = Player.getCurrentRoom().getEFeature();
    		break;
    	case "south":
    		item = Player.getCurrentRoom().getSFeature();
    		break;
    	case "west":
    		item = Player.getCurrentRoom().getWFeature();
    		break;
    	}
    	
    	// If something was found in that corner, set it to the current focus
    	if (item != null) {
    		
    		System.out.println("In the " + location + " side of the room you find a " + item.getName());
    		Player.setCurrentFocus(item);
    	}
    	else {
    		System.out.println("There is nothing in this part of the room.");
    	}
    }
	
	// ### Methods ###
	
	// Game Logic Methods
	
    /**
     * Ends the game
     */
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
}
