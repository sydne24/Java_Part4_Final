package com.nwtcstudent.java.textadventure;

/**
 * The Item class defines items the player can add to their inventory and use.
 */
//1.2 - Polymorphic class structure
public class Item implements IFocusable {
	
	// ### Fields ###
	
	/**
	 * Type of an item. Determines basic behavior/interaction with the environment.
	 */
	//1.8 - Use of enumerations
	public enum ItemType { KEY, NOTE, VANITY, FOOD }

	//2.2 - example of encapsulation
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
	
	//1.4 - Use of overridden methods
	@Override
	public void interact(IFocusable focus) {
		
		switch(type){
			case FOOD:
				System.out.println("You have eaten the " + name);
				player.getInventory().removeItem(this);
				break;
			case VANITY:
				System.out.println("You have put on the " + name);
				description = usedDescription;
				break;
			case NOTE:
				System.out.println("The note reads: " + description);
				break;
			case KEY:
				// 2.5 - Valid object comparison
				if (focus instanceof Door)
				{
					if (value == focus.getValue())
					{
						System.out.println("You have unlocked the door.");
						((Door) focus).setValue(0);
						((Door) focus).setDescription("An unlocked door.");
						player.getInventory().removeItem(this);
					}
					else if (focus.getValue() == 0)
						System.out.println("This door is already unlocked.");
					else
						System.out.println("This key does not fit into the lock, there must be another.");
				}
				else 
					System.out.println("The key cannot be used on this.");
				break;
		}
	}
		
		//1.3 - Use of overloaded methods
		public void interact() {
			switch(type){
				case FOOD:
					System.out.println("You have eaten the " + name);
					player.getInventory().removeItem(this);
					break;
				case VANITY:
					System.out.println("You have put on the " + name);
					description = usedDescription;
					break;
				case NOTE:
					System.out.println("The note reads: " + description);
					break;
				case KEY:
					System.out.println("You are not near something you can use the key on.");
			}
		}
}
