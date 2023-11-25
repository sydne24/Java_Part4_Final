package com.nwtcstudent.java.textadventure;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;

import org.apache.derby.jdbc.EmbeddedDataSource;

import com.nwtcstudent.java.textadventure.Item.ItemType;

/**
 * GameDB manages the derby database connection for loading item and room data.
 */
public class GameDB {

	EmbeddedDataSource ds;
	Connection conn;
	Statement stmt;
	
	public GameDB() throws SQLException {

		
		// Set up the database
		ds = new EmbeddedDataSource();
		ds.setDatabaseName("foobar");
		ds.setCreateDatabase("create");
		
		conn = ds.getConnection();
		stmt = conn.createStatement();
		
		
	}
	
	public HashMap<Integer, Item> getItems() throws SQLException {
		
		// Get items from database
		ResultSet rs = stmt.executeQuery("SELECT item_id, item_name, item_description, item_type, item_value FROM Item");
		
		// Hashmap to store items
		HashMap<Integer, Item> itemList = new HashMap<>();
		
		while (rs.next()) {
			
			int id = rs.getInt("item_id");
			String name = rs.getString("item_name");
			String description = rs.getString("item_description");
			String typeStr = rs.getString("item_Type");
			ItemType type = ItemType.valueOf(typeStr);
			int value = rs.getInt("item_value");
			
			Item item = new Item(id, name, description, type, value);
			itemList.put(id, item);
		}
		
		return itemList;
	}
	
	public HashMap<Integer, Room> getRooms(HashMap<Integer, Item> items, HashMap<Integer, Door> doors) throws SQLException {
		
		// Get rooms from database
		ResultSet rs = stmt.executeQuery("SELECT room_id, room_name, room_description,"
									+ "north_item, east_item, south_item, west_item"
									+ "north_door, east_door, south_door, west_door FROM Room");
		// Hashmap to store rooms
		HashMap<Integer, Room> roomList = new HashMap<>();
		
		while (rs.next()) {
			
			int id = rs.getInt("room_id");
			String name = rs.getString("room_name");
			String description = rs.getString("room_description");
			String typeStr = rs.getString("room_Type");
			
			// Create room with basic information
			Room room = new Room(id, name, description);
			
			// Set items in the room
			Integer north_item = rs.getInt("north_item");
			Integer east_item = rs.getInt("east_item");
			Integer south_item = rs.getInt("south_item");
			Integer west_item = rs.getInt("west_item");
			room.setItems(items.get(north_item), items.get(east_item), items.get(south_item), items.get(west_item));
			
			// Set doors in the room
			Integer north_door = rs.getInt("north_door");
			Integer east_door = rs.getInt("east_door");
			Integer south_door = rs.getInt("south_door");
			Integer west_door = rs.getInt("west_door");
			room.setDoors(doors.get(north_door), doors.get(east_door), doors.get(south_door), doors.get(west_door));
			
			roomList.put(id, room);
		}
		
		return roomList;
	}
	
	public HashMap<Integer, Door> getDoors(HashMap<Integer, Room> rooms) throws SQLException {
		
		// Get items from database
		ResultSet rs = stmt.executeQuery("SELECT door_id, door_name, door_description, door_value FROM Door");
		
		// Hashmap to store items
		HashMap<Integer, Door> doorList = new HashMap<>();
		
		while (rs.next()) {
			
			int id = rs.getInt("door_id");
			String name = rs.getString("door_name");
			String description = rs.getString("door_description");
			int value = rs.getInt("door_value");
			
			Door door = new Door(id, name, description, value);
			doorList.put(id, door);
		}
		
		return doorList;
	}
}
