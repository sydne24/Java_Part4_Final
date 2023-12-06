package com.nwtcstudent.java.textadventure;

import java.util.ArrayList;

/**
 * Information regarding the player.
 */
public final class Player {

	
	// ### Fields ###
	
	// 1.5 Proper use of the Static keyword
	// 1.6 Proper use of the final keyword
	// 2.3 Proper use of the Singleton pattern
	private static final Player instance = new Player();
	
	private String name;
	private Inventory inventory = new Inventory();
	
	// Player/Game states
	private IFocusable currentFocus;
	private Room currentRoom;
	private Item currentItem;
	
	// ### Methods ###
	
	/**
	 * Set the player's name
	 * @param playerName the name to be set.
	 */
	public void setName(String playerName)
	{
		instance.name = playerName;
	}
	
	/**
	 * @return the player's name.
	 */
	public String getName()
	{
		return instance.name;
	}
	
	/**
	 * @return the player's inventory.
	 */
	public Inventory getInventory() {
		
		return inventory;
	}
	
	/**
	 * @return the object the player is currently focusing on.
	 */
	public IFocusable getCurrentFocus() {
		
		return currentFocus;
	}
	
	/**
	 * Sets the player's focus.
	 * @param the object to focus on.
	 */
	public void setCurrentFocus(IFocusable f) {
		
		currentFocus = f;
	}
	
	/**
	 * @return the room the player is currently in.
	 */
	public Room getCurrentRoom() {
		
		return currentRoom;
	}
	
	/**
	 * Sets the room the player is currently in.
	 * @param room the room to set.
	 */
	public void setCurrentRoom(Room r) {
		
		currentRoom = r;
	}
	
	/**
	 * @return the item the player is currently holding.
	 */
	public Item getCurrentItem() {
		
		return currentItem;
	}
	
	/**
	 * Sets the item the player is currently holding.
	 * @param the item to be held.
	 */
	public void setCurrentItem(Item i) {
		
		currentItem = i;
	}
	
	public static Player getInstance() {
		
		return instance;
	}
	
	// ### Nested Class ###
	
	// 1.7 Use of nested classes.
	/**
	 * The inventory of the player.
	 */
	public class Inventory {
		
		// ### Fields ###
		
		// Items in the inventory
		// 3.2 - Use of an Array List
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
			// Sort items by name before checking
			// 2.5 Valid object comparison in at least one scenario
			items.sort(new IFocusable.CompareItemNames());
			
			// Create stringbuilder to hold item data
			StringBuilder output = new StringBuilder("\nYour Inventory:");
			
			// Append item data to string
			// 2.1 Use of lambda expressions in at least 5 scenarios
			// 3.5 Valid use of a foreach statement
			// 4.1 Use of a variable in a lambda expression
			getAllItems().forEach(x -> output.append("\n - " + x.getName()));
			
			System.out.println(output.toString());
		}
		
		// 1.3 Use of overloaded method
		/**
		 * Get an item by its index
		 * @param itemIndex the index of the item list.
		 * @return the item at the requested index.
		 */
		public Item getItem(int itemIndex) {
			
			// Only try to retrieve the item if the requested index is within the bounds of the list
			if (itemIndex >= 0 && itemIndex < items.size()) {
				
				return items.get(itemIndex);
			}
			
			// Return null otherwise
			return null;
		}
		
		// 1.3 Use of overloaded method
		/**
		 * Try to get an item by the item itself
		 * @param i the item requested
		 * @return the item, or null if it could not be found
		 */
		public Item getItem(Item item) {
			
			// Wrapper in order to get value from the lambda expression
			var foundItemWrapper = new Object(){Item foundItem = null;};
			
			// Find the item that matches the searched item
			// 2.1 Use of lambda expressions in at least 5 scenarios
			// 2.5 Valid object comparison in at least one scenario
			// 3.5 Valid use of a foreach statement
			items.forEach(i -> {
				if (item == i) {
					
					foundItemWrapper.foundItem = i;
					}
				});
			
			// Standard for each code
//			for (Item item : items) {
//				
//				// 2.5 Valid object comparison in at least one scenario
//				if (i == item) {
//					
//					foundItem = item;
//				}
//			}
			
			return foundItemWrapper.foundItem;
		}
		
		// 1.3 Use of overloaded method
		/**
		 * Try to get an item by its name
		 * @param i the name of the item requested
		 * @return the item, or null if it could not be found
		 */
		public Item getItem(String item) {
			
			// Wrapper in order to get value from the lambda expression
			var foundItemWrapper = new Object() {Item foundItem = null;};
			
			// Find the item that matches the searched item
			// 2.1 Use of lambda expressions in at least 5 scenarios
			// 3.5 Valid use of a foreach statement
			items.forEach(i -> {
				if (i.getName().toLowerCase().equals(item.toLowerCase())) {
					
					foundItemWrapper.foundItem = i;
					}
				});
			
			// Standard for each code
//			for (Item item : items) {
//				
//				if (item.getName().toLowerCase().equals(i.toLowerCase())) {
//					
//					foundItem = item;
//				}
//			}
			
			return foundItemWrapper.foundItem;
		}
		
		/**
		 * Add an item to the inventory.
		 * @param i the item to be added.
		 */
		public void storeItem(Item i) {
			
			items.add(i);
			System.out.println("You place the " + i.getName() + " into your bag.");
		}
		
		/**
		 * Remove an item from the inventory.
		 * @param i the item to be removed.
		 */
		public void removeItem(Item i) {
			
			items.remove(i);
		}
	}
}
