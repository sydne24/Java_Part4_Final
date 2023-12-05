package com.nwtcstudent.java.textadventure;

import java.util.HashMap;

public class Door implements IFocusable {
	
	// ### Fields ###
	// 2.2 - example of encapsulation
	private int id;
	private String name;
	private String description;
	private int value;
	
	private HashMap<Integer, Room> connectedRooms;

	
	// ### Constructor ###
	
	/**
	 * Sets up a door object.
	 * @param doorID the door's id.
	 * @param doorName the door's name.
	 * @param doorDesc the door's description.
	 * @param doorValue the door's value. This determines what level of key is needed to open the door.
	 */
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
	
	/**
	 * Sets the rooms being connected by this door.
	 * @param room1 the room to connect from.
	 * @param room2 the room to connect to.
	 */
	public void setRooms(Room room1, Room room2) {
		
		connectedRooms.put(room1.getID(), room1);
		connectedRooms.put(room2.getID(), room2);
	}
	
	/**
	 * Enter through the door connecting two rooms.
	 * @param currentRoom the current room.
	 * @return the new room.
	 */
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
	
	/**
	 * @return the door's id.
	 */
	@Override
	public int getID() {
		
		return id;
	}
	
	/**
	 * @return the door's name.
	 */
	@Override
	public String getName() {
		
		return name;
	}
	
	/**
	 * @param doorName the name of the door.
	 */
	public void setName(String doorName) {
		
		name = doorName;
	}
	
	/**
	 * @return the door's description.
	 */
	@Override
	public String getDescription() {
		
		return description;
	}
	
	/**
	 * @param doorDesc the description of the door
	 */
	public void setDescription(String doorDesc) {
		
		description = doorDesc;
	}
	
	/**
	 * @return the door's value.
	 */
	@Override
	public int getValue() {
		
		return value;
	}
	
	/**
	 * @param doorValue the value of the door.
	 */
	public void setValue(int doorValue) {
		
		value = doorValue;
	}

	// When the door is interacted with, allow the player to enter
	// Alternatively, if the door is an END point, trigger game-ending code
	//TODO: Add lock/key interaction
	@Override
	public void interact(IFocusable focus) {
		
		// If the id is -1, this means the door is an END point
		if (id == -1) {
			
			System.out.println(GameInfo.getEndMessage());
			Controller.endGame();
		}
		else if (value != 0)
		{
			System.out.println("This door is locked, try using a key to open it.");
		}
		else {
			Controller.setCurrentRoom(enterDoor(Controller.getCurrentRoom()));
		}
	}
}
