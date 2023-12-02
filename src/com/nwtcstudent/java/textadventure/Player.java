package com.nwtcstudent.java.textadventure;

import java.util.ArrayList;

/**
 * Information regarding the player.
 */
public class Player {

	
	// ### Fields ###
	
	private static Player instance = new Player();
	
	private String name;
	public Inventory inventory = new Inventory();
	
	private IFocusable currentFocus;
	
	// ### Methods ###
	
	public void setName(String playerName)
	{
		instance.name = playerName;
	}
	
	public String getName()
	{
		return instance.name;
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
		 * Print all items in the inventory.
		 */
		public void checkInventory()
		{
			String joined = getAllItems().toString().replace("[", "").replace("]", " ");
			
			System.out.println(joined);
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
		
		/**
		 * Remove an item from the inventory.
		 * @param i the item to be removed.
		 */
		public void removeItem(Item i) {
			
			items.remove(i);
		}
	}
	
	public static Player getInstance() {
		
		return instance;
	}
}
