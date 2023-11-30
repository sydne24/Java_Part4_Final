package com.nwtcstudent.java.textadventure;

/**
 * Describes an object that can be focused on/interacted with.
 */
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
	public void interact(IFocusable focus);
}
