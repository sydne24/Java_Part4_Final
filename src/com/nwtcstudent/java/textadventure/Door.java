package com.nwtcstudent.java.textadventure;

import java.util.HashMap;

public class Door implements IFocusable {
	
	// ### Fields ###
	
	private int id;
	private String name;
	private String description;
	private int value;
	
	private HashMap<Integer, Room> connectedRooms;

	
	// ### Constructor ###
	
	public Door(int doorID, String doorName, String doorDesc, int doorValue) {
		
		id = doorID;
		name = doorName;
		description = doorDesc;
		
		// Value of the key needed to enter the door
		// 0 = the door is already unlocked
		value = doorValue;
		
		connectedRooms = new HashMap<Integer, Room>();
	}
	
	
	// ### Methods ###
	
	// Set the rooms being connected by this door
	public void setRooms(Room room1, Room room2) {
		
		connectedRooms.put(room1.getID(), room1);
		connectedRooms.put(room2.getID(), room2);
	}
	
	// Enter through the door connecting two rooms
	public Room enterDoor(Room currentRoom) {
		
		// Loop through the two rooms
		for (Integer roomID : connectedRooms.keySet()) {
			
			// Find which room the player is not currently in
			if (roomID != currentRoom.getID()) {
				
				// Return that room
				return connectedRooms.get(roomID);
			}
		}
		
		// If the connected room could not be found, return null (an error has occured)
		return null;
	}
	
	
	// ### Properties ###
	
	public int getID() {
		
		return id;
	}
	
	public String getName() {
		
		return name;
	}
	
	public void setName(String doorName) {
		
		name = doorName;
	}
	
	public String getDescription() {
		
		return description;
	}
	
	public void setDescription(String doorDesc) {
		
		description = doorDesc;
	}
	
	public int getValue() {
		
		return value;
	}
	
	public void setValue(int doorValue) {
		
		value = doorValue;
	}

	// When the door is interacted with, allow the player to enter
	//TODO: Add lock/key interaction
	@Override
	public void interact() {
		
		Controller.setCurrentRoom(enterDoor(Controller.getCurrentRoom()));
	}
}
