package com.nwtcstudent.java.textadventure;

import java.util.ArrayList;

/**
 * Information regarding the player.
 */
public class Player {

	
	// ### Fields ###
	
	private static String name;
	private static Inventory inventory;
	
	private IFocusable currentFocus;
	
	
	// ### Constructor ###
	
	/**
	 * Initializes the player's values.
	 * @param playerName the name of the player.
	 */
	public Player(String playerName) {
		
		name = playerName;
		inventory = new Inventory();
	}
	
	// ### Nested Class ###
	
	/**
	 * The inventory of the player.
	 */
	public class Inventory {
		
		// ### Fields ###
		
		// Items in the inventory
		private ArrayList<Item> items;
		
		
		// ### Constructor ###
		
		/**
		 * Initialize the inventory.
		 */
		public Inventory() {
			
			items = new ArrayList<>();
		}
		
		
		// ### Methods ###
		
		/**
		 * Get all items from the inventory.
		 * @return all items from the items list.
		 */
		public ArrayList<Item> getAllItems() {
			
			return items;
		}
		
		/**
		 * @param itemIndex the index of the item list.
		 * @return an item at the requested index.
		 */
		public Item getItem(int itemIndex) {
			
			// Only try to retrieve the item if the requested index is within the bounds of the list
			if (itemIndex >= 0 && itemIndex < items.size()) {
				
				return items.get(itemIndex);
			}
			
			// Return null otherwise
			return null;
		}
		
		/**
		 * Add an item to the inventory.
		 * @param i the item to be added.
		 */
		public void storeItem(Item i) {
			
			items.add(i);
		}
	}
}
