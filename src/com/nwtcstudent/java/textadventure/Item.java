package com.nwtcstudent.java.textadventure;

public class Item implements IFocusable {
	
	// ### Fields ###
	
	// Type of the item. Determines basic behavior/interaction with environment
	public enum ItemType { KEY, NOTE }

	private int id;
	private String name;
	private String description;
	private String usedDescription;
	private ItemType type;
	private int value;
	
	
	// ### Constructor ###
	
	public Item(int itemID, String itemName, String itemDesc, String usedItemDesc, ItemType itemType, int itemValue) {
		
		id = itemID;
		name = itemName;
		description = itemDesc;
		usedDescription = usedItemDesc;
		type = itemType;
		value = itemValue;
	}
	
	
	// ### Properties ###
	
	public int getID() {
		
		return id;
	}
	
	public String getName() {
		
		return name;
	}
	
	public String getDescription() {
		
		return description;
	}
	
	public String getUsedDescription() {
		
		return usedDescription;
	}
	
	public ItemType getType() {
		
		return type;
	}
	
	public int getValue() {
		
		return value;
	}
	
	@Override
	public void interact() {
		
		
	}
}
