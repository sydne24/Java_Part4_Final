package com.nwtcstudent.java.textadventure;

import java.util.ArrayList;

public class Player {

	
	// ### Fields ###
	
	private static String name;
	private static Inventory inventory;
	
	
	// ### Constructor ###
	
	public Player(String playerName) {
		
		name = playerName;
		inventory = new Inventory();
	}
	
	// ### Nested Class ###
	
	// Player Inventory
	public class Inventory {
		
		// ### Fields ###
		
		// Items in the inventory
		private ArrayList<Item> items;
		
		
		// ### Constructor ###
		
		public Inventory() {
			
			items = new ArrayList<>();
		}
		
		
		// ### Methods ###
		
		// Get all items from the items list
		public ArrayList<Item> getAllItems() {
			
			return items;
		}
		
		// Get an item at a specific index
		public Item getItem(int itemIndex) {
			
			return items.get(itemIndex);
		}
		
		// Add an item to the inventory
		public void storeItem(Item i) {
			
			items.add(i);
		}
	}
}
