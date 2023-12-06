package com.nwtcstudent.java.textadventure;

import java.util.Comparator;

/**
 * The Item class defines items the player can add to their inventory and use.
 */
// 1.2 Polymorphic class structure with use of parent classes and interfaces
public class Item implements IFocusable, Comparable<Item> {
	
	// ### Fields ###
	
	/**
	 * Type of an item. Determines basic behavior/interaction with the environment.
	 */
	// 1.8 - Use of enumerations
	public enum ItemType { KEY, NOTE, VANITY, FOOD }

	// 2.2 - Example of encapsulation
	private int id;
	private String name;
	private String description;
	private String usedDescription;
	private ItemType type;
	private int value;
	
	private static Player player = Player.getInstance();
	
	
	// ### Constructor ###
	
	/**
	 * Creates an item.
	 * @param itemID the item's id.
	 * @param itemName the item's name.
	 * @param itemDesc the item's description.
	 * @param usedItemDesc a description of how the item is used.
	 * @param itemType the type of the item, defined by the ItemType enumerator.
	 * @param itemValue the item's value, such as a key's door level.
	 */
	public Item(int itemID, String itemName, String itemDesc, String usedItemDesc, ItemType itemType, int itemValue) {
		
		id = itemID;
		name = itemName;
		description = itemDesc;
		usedDescription = usedItemDesc;
		type = itemType;
		value = itemValue;
	}
	
	
	// ### Properties ###
	
	/**
	 * @return the item's id.
	 */
	@Override
	public int getID() {
		
		return id;
	}
	
	/**
	 * @return the item's name.
	 */
	@Override
	public String getName() {
		
		return name;
	}
	
	/**
	 * @return the item's description.
	 */
	@Override
	public String getDescription() {
		
		return description;
	}
	
	/**
	 * @return the item's used description.
	 */
	public String getUsedDescription() {
		
		return usedDescription;
	}
	
	/**
	 * @return the item's type.
	 */
	public ItemType getType() {
		
		return type;
	}
	
	/**
	 * @return the item's value.
	 */
	@Override
	public int getValue() {
		
		return value;
	}
	
	// 1.4 Use of Overriden Methods
	/**
	 * Specifies the item's interaction
	 */
	public void interact() {
		switch(type){
		
			// Eat the food
			case FOOD:
				System.out.println(usedDescription);
				player.getInventory().removeItem(this);
				break;
				
			// Wear vanity item (if it is not already worn)
			case VANITY:
				
				if (this.value == 0) {
					
					System.out.println(usedDescription);
					player.getInventory().removeItem(this);
				}
				else {
					
					System.out.println("You are already wearing this item");
				}
				break;
			// Read the note
			case NOTE:
				System.out.println(usedDescription);
				break;
				
			// Try to open a door with the key
			case KEY:
				IFocusable currentFocus = player.getCurrentFocus();
				if (currentFocus != null && currentFocus.getClass() == Door.class) {
					
					Door door = (Door)currentFocus;
					if (door.getValue() == this.value) {
						
						// Unlock the door
						door.setValue(0);
						player.getInventory().removeItem(this);
						System.out.println(usedDescription + "\nYou can now use this door!");
					}
				}
				else {
					
					System.out.println("Try focusing on a door before using this key.");
				}
		}
	}

	// 1.9 Proper use of @Override notation
	// 3.4 Use of the Comparator or Comparable interface
	/**
	 * Compare items by their names
	 * @return the difference between the two item's names
	 */
	@Override
	public int compareTo(Item o) {
		
		return this.getName().compareTo(o.getName());
	}
}
