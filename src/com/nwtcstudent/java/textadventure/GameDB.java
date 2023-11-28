package com.nwtcstudent.java.textadventure;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
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
	
	public void setupdb() throws SQLException {
		
		
		// ROOM_ID, ROOM_NAME, ROOM_DESCRIPTION, NORTH_ITEM, EAST_ITEM, SOUTH_ITEM, WEST_ITEM
		stmt.execute("CREATE TABLE Room");
		
		// DOOR_ID, DOOR_NAME, DOOR_DESCRIPTION, DOOR_VALUE, ROOM_FROM, ROOM_TO
		stmt.execute("CREATE TABLE Door");
		
		// ITEM_ID, ITEM_NAME, ITEM_DESCRIPTION, ITEM_TYPE, ITEM_VALUE
		stmt.execute("CREATE TABLE Item");
		
		stmt.execute("INSERT INTO Item VALUES(0, 'Rusty Key', 'An old key. I wonder if it can be used anywhere?', 'KEY', 1)");
		stmt.execute("INSERT INTO Item VALUES(1, 'Crumpled Note', 'The faded words reveal, ''Aging has a wonderful beauty...''', 'NOTE', 0)");
		stmt.execute("INSERT INTO Item VALUES(2, 'Golden Key', 'A golden key fit for royalty', 'KEY', 2)");
		stmt.execute("INSERT INTO Item VALUES(3, 'Clipboard', 'Hastily written words recall, ''In the kingdom of the blind, the one-eyed man is king''', 'NOTE', 0)");
		stmt.execute("INSERT INTO Item VALUES(4, 'Crowbar', 'Just beat it', 'KEY', 3)");
		
		
		stmt.execute("INSERT INTO Door VALUES(0, 'Overgrown Door', 'A door covered in greenery and flora', 1, 0, 1)");
		stmt.execute("INSERT INTO Door VALUES(1, 'Barricaded Door', 'An old saferoom door with a singular peephole.', 2, 0, 2)");
		stmt.execute("INSERT INTO Door VALUES(2, 'Weak Door', 'A door barely kept on its hinges. Light seeps through the gaps in its unstable frame.', 3, 2, 3)");
		
		stmt.execute("INSERT INTO Room VALUES(0, 'Main Room', 'The room feels dark and hollow. You have no idea how you got here.', 'DOOR0', 'DOOR1', 'ITEM0', 'ITEM1')");
		stmt.execute("INSERT INTO Room VALUES(1, 'Overgrown Room', 'The walls are caked in moss. Their bioluminescence dimly lights the room to reveal several flowers.', '', 'ITEM3', 'DOOR0', 'ITEM2')");
		stmt.execute("INSERT INTO Room VALUES(2, 'Stone Room', 'It's cold and damp in here. The walls are cracked and let in small amounts of light.', 'ITEM4', 'DOOR2', '', 'DOOR1')");
		stmt.execute("INSERT INTO Room VALUES(3, 'The Outside World', 'Sun shines down onto you and the field ahead. You've found your escape.', 'END', 'END', 'END', 'DOOR2')");
	}
	
	// Get the items from the database
	public HashMap<Integer, Item> getItems() throws SQLException {
		
		// Select item information
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
	
	// Get the rooms from the database
	public HashMap<Integer, Room> getRooms() throws SQLException {
		
		// Select room information
		ResultSet rs = stmt.executeQuery("SELECT room_id, room_name, room_description,"
									+ "north, east, south, west FROM Room");
		// Hashmap to store rooms
		HashMap<Integer, Room> roomList = new HashMap<>();
		
		while (rs.next()) {
			
			int id = rs.getInt("room_id");
			String name = rs.getString("room_name");
			String description = rs.getString("room_description");
			
			// Create room with basic information
			Room room = new Room(id, name, description);
			
			roomList.put(id, room);
		}
		
		return roomList;
	}
	
	// Get the doors from the database
	public HashMap<Integer, Door> getDoors(HashMap<Integer, Room> rooms) throws SQLException {
		
		// Select door information
		ResultSet rs = stmt.executeQuery("SELECT door_id, door_name, door_description, door_value, room_from, room_to FROM Door");
		
		// Hashmap to store items
		HashMap<Integer, Door> doorList = new HashMap<>();
		
		while (rs.next()) {
			
			Integer id = rs.getInt("door_id");
			String name = rs.getString("door_name");
			String description = rs.getString("door_description");
			int value = rs.getInt("door_value");
			
			// Get rooms this door connects
			Integer room1 = rs.getInt("room_from");
			Integer room2 = rs.getInt("room_to");
			
			// Create door and set the rooms it connects
			Door door = new Door(id, name, description, value);
			door.setRooms(rooms.get(room1), rooms.get(room2));
			
			// Add door to list of doors
			doorList.put(id, door);
		}
		
		return doorList;
	}
	
	// Set up the connection between room features
	public void setRoomFeatures(HashMap<Integer, Item> items, HashMap<Integer, Room> rooms, HashMap<Integer, Door> doors) throws SQLException, NumberFormatException {
		
		// Select room-door information
		ResultSet rs = stmt.executeQuery("SELECT door_id, north, east, south, west FROM Room");
		
		// Loop through the rooms and get data
		while (rs.next()) {
			
			Integer id = rs.getInt("door_id");
			String[] north_features = rs.getString("north").split("_");
			String[] east_features = rs.getString("east").split("_");
			String[] south_features = rs.getString("south").split("_");
			String[] west_features = rs.getString("west").split("_");
			
			// 2D array of all feature data
			String[][] featureStrList = {north_features, east_features, south_features, west_features};
			
			// Array of actual feature objects (4 per room)
			IFocusable[] features = new IFocusable[4];
			
			// Loop through arrays and extract data
			for (int i = 0; i < featureStrList.length; i++) {
				
				String featureType = featureStrList[i][0];
				int featureID = Integer.valueOf(featureStrList[i][1]);
				
				// Create a null feature. If there is a feature in this corner of the room, it will be filled by an item or door
				IFocusable feature = null;
				
				// If the feature is an item, refer to the items list
				if (featureType.equals("ITEM")) {
					
					feature = items.get(featureID);
				}

				// If the feature is a door, refer to the doors list
				if (featureType.equals("DOOR")) {
					
					feature = doors.get(featureID);
				}
				
				// TODO: If the feature is an end point, get the specialized end door
				if (featureType.equals("END")) {
					
					
				}
				
				// Add the feature (null, item, or door) to the features array
				features[i] = feature; 
			}
			
			// Set the doors to the room as provided by the database
			rooms.get(id).setFeatures(features[0], features[1], features[2], features[3]);
		}
	}
}
