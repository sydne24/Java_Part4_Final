package com.nwtcstudent.java.textadventure;

import java.util.Comparator;

/**
 * Describes an object that can be focused on/interacted with.
 */
// 1.2 Polymorphic class structure with use of parent classes and interfaces
public interface IFocusable {
	
	// Primary functions of all IFocusable objects
	/**
	 * @return the object's id.
	 */
	public int getID();
	
	/**
	 * @return the object's name
	 */
	public String getName();
	
	/**
	 * @return the object's description
	 */
	public String getDescription();
	
	/**
	 * @return the object's value.
	 */
	public int getValue();
	
	/**
	 * Defines interaction between the player and this object.
	 */
	public void interact();
	
	
	// 3.4 Use of the Comparator or Comparable interface
	public class CompareItemNames implements Comparator<Item> {
		
		// 1.9 Proper use of @Override notation
		/**
		 * Compare the items by name
		 */
		@Override
		public int compare(Item o1, Item o2) {
			
			return o1.compareTo(o2);
		}
	}
}
