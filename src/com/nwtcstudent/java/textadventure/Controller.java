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
	private static GameLogger logger = GameLogger.getInstance();
	
	// Player
	private static Player player = Player.getInstance();
	
	// Data parser
	private static Parser parser = Parser.getInstance();
	
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
        	
        	// 8.1 Demonstration of reading/writing data from the console
            input = myScan.nextLine();
        	parser.parseInput(input);
        }

        // Close the scanner when the game is finished
        myScan.close();
    }
	
	// ### Methods ###
	
	/**
	 * Set up the game
	 * @throws SQLException
	 */
	private void setup() throws SQLException {
		
		// Start up the logger
		logger.log("Game Started", Level.INFO);
		
		// Create an instance of the database
		db = new GameDB();
		
		// Initialize all the items, rooms, and doors for the game
		initializeItems();
		initializeRooms();
		initializeDoors();
		initializeItemLibrary();
		
		// Set controller reference within the parser
		parser.setController(this);
		
		// Set the current room to room 0 (starting room)
		player.setCurrentRoom(roomList.get(0));
		
		// Create the input scanner
		myScan = new Scanner(System.in);
		
		db.closeDB();
	}
	
	/**
	 * Retrieve data about the current room.
	 */
    public void lookAround() {
    	// Room description will be given when the player decides to LOOK AROUND / LOOK ROOM / INSPECT ROOM / etc
    	// Display current room description
    	System.out.println(player.getCurrentRoom().getDescription());
    	//Display current room features
    	
    	//NORTH
    	if (player.getCurrentRoom().getNFeature() != null)
    		System.out.println("To the north is a " + player.getCurrentRoom().getNFeature().getName());
    	
    	//EAST
    	if (player.getCurrentRoom().getEFeature() != null) 
    		System.out.println("To the east is a " + player.getCurrentRoom().getEFeature().getName());
    	
    	//SOUTH
    	if (player.getCurrentRoom().getSFeature() != null)
    		System.out.println("To the south is a " + player.getCurrentRoom().getSFeature().getName());
    	
    	//WEST
    	if (player.getCurrentRoom().getWFeature() != null)
    		System.out.println("To the west is a " + player.getCurrentRoom().getWFeature().getName());
    }
    
    /**
     * Inspect the player's current focus
     */
    public void inspect() {
    	
    	IFocusable currentFocus = player.getCurrentFocus();
    	if (currentFocus != null) {
    		
    		System.out.println(currentFocus.getDescription());
    	}
    }
    
    /**
     * Set the player's focus to the item and inspect it
     * @param focus the object to focus on
     */
    public void inspect(IFocusable focus) {
    	
    	if (focus != null) {
    		
    		// List of features in the current room
        	IFocusable[] features = player.getCurrentRoom().getFeatures();
        	
        	for (int i = 0; i < features.length; i++) {
        		
        		// Check if the requested focusable is in the current room
        		if (features[i] == focus) {
        			
        			// Set the focus to the focusable and inspect it
        			player.setCurrentFocus(focus);
        	    	inspect();
        	    	
        	    	return;
        		}
        	}
        	
        	// This code will only be reached if the above code failed
        	
        	if (focus.getClass() == Item.class) {
        		
        		inventoryInspect((Item)focus);
        	}
        	else {
        		
        		System.out.println(GameInfo.getItemNotFoundMessage());
        	}
    	}
    	else {
    		
    		System.out.println(GameInfo.getItemNotFoundMessage());
    	}
    }
    
    /**
     * Inspect an item in the player's inventory
     * @param item
     */
    public void inventoryInspect(Item item) {
    	
    	// Check if the item is in the player's inventory
    	if (player.getInventory().getItem(item) != null) {
    		
    		// If it is, return the item's description
    		System.out.println(item.getDescription());
    	}
    	else {
    		
    		System.out.println(GameInfo.getItemNotFoundMessage());
    	}
    }

    /**
     * Try to move towards the player's current focus (an item or door)
     */
	public void move() {
    	
    	if (player.getCurrentFocus() != null) {
    		
    		int dir = -1;
    		
    		// Get all features of the current room
    		IFocusable[] features = player.getCurrentRoom().getFeatures();
    		for (int i = 0; i < features.length; i++) {
    			
    			// Find which feature the player is trying to focus on
    			// 2.5 Valid object comparison in at least one scenario
    			if (features[i] != null && features[i] == player.getCurrentFocus()) {
    				
    				dir = i;
    				break;
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
	public void move(IFocusable feature) {
    	
    	if (feature != null) {
    		
    		player.setCurrentFocus(feature);
    		System.out.println("You see a " + feature.getName());
    	}
    	else {
    		
    		System.out.println(GameInfo.getLocationNotFoundMessage());
    	}
    }
    
    /**
     * Try to move north/south/east/west
     * @param location north, south, east, or west
     */
	public void move(String location) {
    	
    	IFocusable feature = null;
    	
    	switch (location) {
    	case "north": 
    		feature = player.getCurrentRoom().getNFeature();
    		break;
    	case "east":
    		feature = player.getCurrentRoom().getEFeature();
    		break;
    	case "south":
    		feature = player.getCurrentRoom().getSFeature();
    		break;
    	case "west":
    		feature = player.getCurrentRoom().getWFeature();
    		break;
    	}
    	
    	// If something was found in that corner, set it to the current focus
    	if (feature != null) {
    		
    		player.setCurrentFocus(feature);
    		System.out.println("You see a " + feature.getName());
    	}
    	else {
    		System.out.println("There is nothing in this part of the room.");
    	}
    }
    
    /**
     * Try to take the last focused item
     */
    public void take() {
    	
    	if (player.getCurrentFocus() != null && player.getCurrentFocus().getClass() == Item.class) {
    		
    		Item item = (Item)player.getCurrentFocus();
    		take(item);
    	}
    	else {
    		System.out.println("Please specify an item.");
    	}
    }
    
    /**
     * Try to take the specified item
     * @param item the item to take.
     */
    public void take(Item item) {
    	
    	if (item != null) {
    		
    		// Get a reference to the current room
    		Room currentRoom = player.getCurrentRoom();
    		
    		// Get features from the room
    		IFocusable features[] = currentRoom.getFeatures();
    		
    		// Loop through the features and compare to the item
    		for (int i = 0; i < features.length; i++) {
    			
    			// 2.5 Valid object comparison in at least one scenario
    			if (item == features[i]) {
    				
    				// Switch based on the location (NORTH/EAST/SOUTH/WEST), removing the item from that location
    				switch (i) {
    				case 0:
    					currentRoom.setNFeature(null);
    					break;
    				case 1:
    					currentRoom.setEFeature(null);
    					break;
    				case 2:
    					currentRoom.setSFeature(null);
    					break;
    				case 3:
    					currentRoom.setWFeature(null);
    					break;
    				}
    				
    				// Add the item to the player's inventory
    				player.getInventory().storeItem(item);
    				
    				// Exit the loop
    				break;
    			}
    			// If i is 3 (last comparable feature) and still doesn't match, the item must not exist in the room
    			else if (i == 3) {
    				
    				System.out.println("The item you wish to take couldn't be found here.");
    			}
    		}
    		
    	}
    }
    
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
 	
 	/**
 	 * Initializes the named item list for the Parser
 	 */
 	public void initializeItemLibrary() {
 		// 3.5 Valid use of a foreach statement
 		for (Item i : Controller.itemList.values()) {
 			parser.addItemToLib(i);
 		}
 	}
 	
	
	// ### Properties ###
	
 	/**
 	 * @return all items available in the game.
 	 */
	public HashMap<Integer, Item> getItems() {
		
		return itemList;
	}
	
	/**
	 * @return all rooms available in the game.
	 */
	public HashMap<Integer, Room> getRooms() {
		
		return roomList;
	}
}
