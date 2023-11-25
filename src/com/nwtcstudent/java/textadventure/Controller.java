package com.nwtcstudent.java.textadventure;

import java.sql.SQLException;
import java.util.HashMap;

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

	public Controller() throws SQLException {
		
		logger = GameLogger.getInstance();
    	logger.log("Game Started", Level.INFO);
    	
    	db = new GameDB();
    	
    	initializeItems();
    	initializeRooms();
	}
	
	public void initializeItems() throws SQLException {
		
		itemList = db.getItems();
	}
	
	public void initializeRooms() throws SQLException {
		
		roomList = db.getRooms(itemList, doorList);
	}
	
	public void initializeDoors() throws SQLException {
		
		doorList = db.getDoors(roomList);
	}
	
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
	}
}
