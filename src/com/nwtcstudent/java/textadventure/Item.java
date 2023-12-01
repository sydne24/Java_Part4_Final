package com.nwtcstudent.java.textadventure;

/**
 * The Item class defines items the player can add to their inventory and use.
 */
public class Item implements IFocusable {
	
	// ### Fields ###
	
	/**
	 * Type of an item. Determines basic behavior/interaction with the environment.
	 */
	public enum ItemType { KEY, NOTE, VANITY, FOOD }

	private int id;
	private String name;
	private String description;
	private String usedDescription;
	private ItemType type;
	private int value;
	
	
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
	
	@Override
	public void interact(IFocusable focus) {
		
		if (focus != null) {
			
			// Assume the item is being used on a door
			// Feel free to change if you have other ideas
			Door door = (Door)focus;
		}
	}
}
