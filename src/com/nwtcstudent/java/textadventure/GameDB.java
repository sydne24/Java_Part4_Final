package com.nwtcstudent.java.textadventure;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

import org.apache.derby.impl.sql.execute.ConstraintInfo;
import org.apache.derby.jdbc.EmbeddedDataSource;

import com.nwtcstudent.java.textadventure.Item.ItemType;

/**
 * GameDB manages the derby database connection for loading item and room data.
 */
public class GameDB {

	// ### Fields ###
	
	EmbeddedDataSource ds;
	Connection conn;
	Statement stmt;
	
	
	// ### Constructor ###
	
	/**
	 * Sets up the game database.
	 * @throws SQLException
	 */
	public GameDB() throws SQLException {

		
		// Set up the database
		ds = new EmbeddedDataSource();
		ds.setDatabaseName("foobar");
		ds.setCreateDatabase("create");
		
		conn = ds.getConnection();
		stmt = conn.createStatement();
		
		// Enabled to recreate the database
		// The database is not provided through github (for now), so this must be called at least the first time the application is run to initially create the database
		// Delete or do not include the "foobar" database folder when you upload to github, in order to reduce the file size
		//repairDB();
	}
	
	
	// ### Methods ###
	
	/**
	 * Reset the database. Not called in the standard program execution.
	 * @throws SQLException
	 */
	public void repairDB() throws SQLException {
		
		// Try to drop all tables. Compacted code as it is fine for these to fail.
		try {stmt.executeUpdate("DROP TABLE Item");} catch (Exception e) {}
		try {stmt.executeUpdate("DROP TABLE Door");} catch (Exception e) {}
		try {stmt.executeUpdate("DROP TABLE Room");} catch (Exception e) {}
		
		// ROOM_ID, ROOM_NAME, ROOM_DESCRIPTION, NORTH, EAST, SOUTH, WEST
		stmt.executeUpdate("CREATE TABLE Room ("
				+ "room_id INTEGER PRIMARY KEY, "
				+ "room_name VARCHAR(20),"
				+ "room_description VARCHAR(1000), "
				+ "north VARCHAR(6), "
				+ "east VARCHAR(6), "
				+ "south VARCHAR(6), "
				+ "west VARCHAR(6))");
		
		// DOOR_ID, DOOR_NAME, DOOR_DESCRIPTION, DOOR_VALUE, ROOM_FROM, ROOM_TO
		stmt.executeUpdate("CREATE TABLE Door ("
				+ "door_id INTEGER PRIMARY KEY, "
				+ "door_name VARCHAR(20), "
				+ "door_description VARCHAR(1000), "
				+ "door_value INTEGER, "
				+ "room_from INTEGER REFERENCES Room(room_id), "
				+ "room_to INTEGER REFERENCES Room(room_id))");
		
		// ITEM_ID, ITEM_NAME, ITEM_DESCRIPTION, ITEM_USED_DESCRIPTION, ITEM_TYPE, ITEM_VALUE
		stmt.executeUpdate("CREATE TABLE Item ("
				+ "item_id INTEGER PRIMARY KEY, "
				+ "item_name VARCHAR(20), "
				+ "item_description VARCHAR(1000), "
				+ "item_used_description VARCHAR(1000), "
				+ "item_type VARCHAR(20), "
				+ "item_value INTEGER)");
		
		
		// Insert custom room mapping data here
		// Feel free to replace everything already set here when creating the new room/door/item map
		
		// In the ROOM's "NORTH/EAST/SOUTH/WEST" fields, give the item type in UPPERCASE, then an underscore, then the item/room's ID
		
		// Item
		stmt.executeUpdate("INSERT INTO Item VALUES(0, 'Rusty Key', 'An old key. I wonder if it can be used anywhere?', 'The rusty key fits into the lock and the door opens', 'KEY', 1)");
		stmt.executeUpdate("INSERT INTO Item VALUES(1, 'Crumpled Note', 'An old balled-up note', 'The faded words reveal, ''Aging has a wonderful beauty...''', 'NOTE', 0)");
		stmt.executeUpdate("INSERT INTO Item VALUES(2, 'Golden Key', 'A golden key fit for royalty', 'The key fits snugly into its lock, and the door opens', 'KEY', 2)");
		stmt.executeUpdate("INSERT INTO Item VALUES(3, 'Clipboard', 'A plain clipboard. It does not seem to belong in this room.', 'Hastily written words recall, ''In the kingdom of the blind, the one-eyed man is king''', 'NOTE', 0)");
		stmt.executeUpdate("INSERT INTO Item VALUES(4, 'Crowbar', 'Just beat it', 'You smash the crowbar into the door. It easily pops out of its frame.', 'KEY', 3)");
		
		// Room
		stmt.executeUpdate("INSERT INTO Room VALUES(0, 'Main Room', 'The room feels dark and hollow. You have no idea how you got here.', 'DOOR_0', 'DOOR_1', 'ITEM_0', 'ITEM_1')");
		stmt.executeUpdate("INSERT INTO Room VALUES(1, 'Overgrown Room', 'The walls are caked in moss. Their bioluminescence dimly lights the room to reveal several flowers.', '', 'ITEM_3', 'DOOR_0', 'ITEM_2')");
		stmt.executeUpdate("INSERT INTO Room VALUES(2, 'Stone Room', 'It''s cold and damp in here. The walls are cracked and let in small amounts of light.', 'ITEM_4', 'DOOR_2', '', 'DOOR_1')");
		stmt.executeUpdate("INSERT INTO Room VALUES(3, 'The Outside World', 'Sun shines down onto you and the field ahead. You''ve found your escape.', 'END', 'END', 'END', 'DOOR_2')");
		
		// Door
		stmt.executeUpdate("INSERT INTO Door VALUES(0, 'Overgrown Door', 'A door covered in greenery and flora.', 1, 0, 1)");
		stmt.executeUpdate("INSERT INTO Door VALUES(1, 'Barricaded Door', 'An old saferoom door with a singular peephole.', 2, 0, 2)");
		stmt.executeUpdate("INSERT INTO Door VALUES(2, 'Weak Door', 'A door barely kept on its hinges. Light seeps through the gaps in its unstable frame.', 3, 2, 3)");
	}
	
	/**
	 * Get the items from the database
	 * @return the list of items.
	 * @throws SQLException
	 */
	public HashMap<Integer, Item> getItems() throws SQLException {
		
		// Select item information
		ResultSet rs = stmt.executeQuery("SELECT item_id, item_name, item_description, item_used_description, item_type, item_value FROM Item");
		
		// Hashmap to store items
		HashMap<Integer, Item> itemList = new HashMap<>();
		
		while (rs.next()) {
			
			int id = rs.getInt("item_id");
			String name = rs.getString("item_name");
			String description = rs.getString("item_description");
			String used_description = rs.getString("item_used_description");
			String typeStr = rs.getString("item_Type");
			ItemType type = ItemType.valueOf(typeStr);
			int value = rs.getInt("item_value");
			
			Item item = new Item(id, name, description, used_description, type, value);
			itemList.put(id, item);
		}
		
		return itemList;
	}
	
	/**
	 * Get the rooms from the database
	 * @return the list of rooms.
	 * @throws SQLException
	 */
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
	
	/**
	 * Get the doors from the database
	 * @param rooms the rooms the doors connect.
	 * @return the list of doors.
	 * @throws SQLException
	 */
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
	
	/**
	 * Set up the connection between rooms and their features.
	 * @param items the list of available items.
	 * @param rooms the list of available rooms.
	 * @param doors the list of available doors.
	 * @throws SQLException
	 * @throws NumberFormatException
	 */
	public void setRoomFeatures(HashMap<Integer, Item> items, HashMap<Integer, Room> rooms, HashMap<Integer, Door> doors) throws SQLException, NumberFormatException {
		
		// Select room feature information
		ResultSet rs = stmt.executeQuery("SELECT room_id, north, east, south, west FROM Room");
		
		// Loop through the rooms to retrieve and process their data
		while (rs.next()) {
			
			
			// Get the id of the current room
			Integer id = rs.getInt("room_id");
			
			// Array of directional names
			String[] dir = new String[] {"NORTH", "EAST", "SOUTH", "WEST"};
			
			// Arrays of features' data ("safe" values provided initially)
			String[] north_features = new String[] {"", "0"};
			String[] east_features = new String[] {"", "0"};
			String[] south_features = new String[] {"", "0"};
			String[] west_features = new String[] {"", "0"};
			
			// 2D array of all string features from above
			String[][] featureStrList = {north_features, east_features, south_features, west_features};
			
			// Loop over the features and try to parse out the actual (real/unsafe) data from the database
			// If the "real" data is valid, it will overwrite the safe data held in its corresponding feature in the featureStrList
			for (int i = 0; i < featureStrList.length; i++) {
				
				try {
					
					// Try to split the string based on the underscore character.
					String[] featureDetails = rs.getString(dir[i]).split("_");
					
					// Test if the second value can be converted to an integer.
					Integer.valueOf(featureDetails[1]);
					
					// If all previous tests completed, add this feature to the features array in its correct spot.
					featureStrList[i] = featureDetails;
				}
				catch (Exception e) {
					
					// Code reaching here means the validation failed.
					// No extra error handling is needed as safe data is provided initially.
				}
			}
			
			// This is the array of the actual feature objects (at most 4 per room).
			IFocusable[] features = new IFocusable[4];
			
			/* Loop through the feature data arrays and extract their data
			 * Data held in the feature data arrays:
			 * [0] = the type of feature (primarily ITEM or DOOR)
			 * [1] = the id of the feature within the database
			 * Together this tells the program which item type and id to select */
			for (int i = 0; i < featureStrList.length; i++) {
				
				// Type of the currently looped feature
				String featureType = featureStrList[i][0];
				
				// ID of the currently looped feature. We can do Integer.valueOf safely because of prior validation.
				int featureID = Integer.valueOf(featureStrList[i][1]);
				
				// Create a null feature. If there is a feature in this corner of the room, it will be filled by an item or door.
				IFocusable feature = null;
				
				// If the feature is an item, refer to the items list.
				if (featureType.equals("ITEM")) {
					
					feature = items.get(featureID);
				}

				// If the feature is a door, refer to the doors list.
				if (featureType.equals("DOOR")) {
					
					feature = doors.get(featureID);
				}
				
				// TODO: If the feature is an end point, add proper game-ending code to run
				if (featureType.equals("END")) {
					
					// Placeholder method. Should probably be called from the Controller class instead of here.
					endGame();
				}
				
				// Add the feature (null, item, or door) to the features array.
				features[i] = feature; 
			}
			
			// Set the features to the room as provided by the database.
			rooms.get(id).setFeatures(features[0], features[1], features[2], features[3]);
		}
	}
	
	/**
	 * Placeholder method. Should probably be called from the Controller class instead of here.
	 */
	public void endGame() {
		
		
	}
}
