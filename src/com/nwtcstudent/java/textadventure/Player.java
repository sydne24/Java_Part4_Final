package com.nwtcstudent.java.textadventure;

import java.util.ArrayList;

public class Player {

	private static String name;
	private static Inventory inventory;
	
	public Player(String playerName) {
		
		name = playerName;
		inventory = new Inventory();
	}
	
	// Player inventory
	public class Inventory {
		
		// Items in the inventory
		private ArrayList<Item> items;
		
		// Constructor
		public Inventory() {
			
			items = new ArrayList<>();
		}
		
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
