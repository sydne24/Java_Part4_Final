package com.nwtcstudent.java.textadventure;

import java.sql.SQLException;
import java.util.HashMap;

import java.util.Scanner;
import org.apache.logging.log4j.Level;

/**
 * The Controller class initializes and controls the flow of the game
 */
public class Controller {
	
	private GameLogger logger;
	private GameDB db;
	
	private static HashMap<Integer, Item> itemList;
	private static HashMap<Integer, Room> roomList;
	private static HashMap<Integer, Door> doorList;
	
	// Game states
	private static Room currentRoom;
	static Scanner myScan = new Scanner(System.in);

	// Constructor
	public Controller() throws SQLException {
		
		logger = GameLogger.getInstance();
    	logger.log("Game Started", Level.INFO);
    	
    	db = new GameDB();
    	
    	initializeItems();
    	initializeRooms();
    	initializeDoors();
	}
	
	// Methods
	
	// Initialize the list of items
	public void initializeItems() throws SQLException {
		
		itemList = db.getItems();
	}
	
	// Initialize the list of rooms
	public void initializeRooms() throws SQLException {
		
		roomList = db.getRooms();
	}
	
	// Initialize the list of doors
	public void initializeDoors() throws SQLException {
		
		doorList = db.getDoors(roomList);
		db.setRoomFeatures(itemList, roomList, doorList);
	}
	
	// Properties
	
	public static HashMap<Integer, Item> getItems() {
		
		return itemList;
	}
	
	public static HashMap<Integer, Room> getRooms() {
		
		return roomList;
	}
	
	public static Room getCurrentRoom() {
		
		return currentRoom;
	}
	
	public static void setCurrentRoom(Room room) {
		
		currentRoom = room;
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
