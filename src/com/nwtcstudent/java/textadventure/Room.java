package com.nwtcstudent.java.textadventure;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * A room the player may enter and navigate.
 */
public class Room {
	
	// ### Fields ###
	// 2-2: example of encapsulation
	private int id;
	private String name;
	private String description;
	
	private IFocusable nFeature;
	private IFocusable eFeature;
	private IFocusable sFeature;
	private IFocusable wFeature;
	
	
	// ### Constructor ###
	
	/**
	 * Create a new room.
	 * @param roomID the room's id.
	 * @param roomName the room's name.
	 * @param roomDesc the room's description.
	 */
	public Room(int roomID, String roomName, String roomDesc) {
		
		id = roomID;
		name = roomName;
		description = roomDesc;
	}
	
	
	// ### Methods ###
	
	/**
	 * Set all features within the room
	 * @param north the north feature.
	 * @param east the east feature.
	 * @param south the south feature.
	 * @param west the west feature.
	 */
	public void setFeatures(IFocusable north, IFocusable east, IFocusable south, IFocusable west) {
		
		nFeature = north;
		eFeature = east;
		sFeature = south;
		wFeature = west;
	}

	
	// ### Properties ###
	
	/**
	 * @return the room's id.
	 */
	public int getID() {
		
		return id;
	}
	
	/**
	 * @return the room's name.
	 */
	public String getName() {
		
		return name;
	}
	
	/**
	 * @return the room's description
	 */
	public String getDescription() {
		
		return description;
	}
	
	/**
	 * @return the room's north feature
	 */
	public IFocusable getNFeature() {
		
		return nFeature;
	}
	
	/**
	 * @param nFeature the room's north feature
	 */
	public void setNFeature(IFocusable nFeature) {
		this.nFeature = nFeature;
	}
	
	/**
	 * @return the room's east feature
	 */
	public IFocusable getEFeature() {
		return eFeature;
	}
	
	/**
	 * @param eFeature the room's east feature
	 */
	public void setEFeature(IFocusable eFeature) {
		this.eFeature = eFeature;
	}
	
	/**
	 * @return the room's south feature
	 */
	public IFocusable getSFeature() {
		return sFeature;
	}
	
	/**
	 * @param sFeature the room's south feature
	 */
	public void setSFeature(IFocusable sFeature) {
		this.sFeature = sFeature;
	}
	
	/**
	 * @return the room's west feature
	 */
	public IFocusable getWFeature() {
		return wFeature;
	}
	
	/**
	 * @param wFeature the room's west feature
	 */
	public void setWFeature(IFocusable wFeature) {
		this.wFeature = wFeature;
	}
	
	// 3.5 Valid use of a foreach statement
	/**
	 * Attempt to get a feature from the list of features
	 * @param featureName the name of the feature to return. 
	 * @return the feature, or null if none is found.
	 */
	public IFocusable getFeature(String featureName) {
		
		// Wrapper in order to get value from the lambda expression
		var featureWrapper = new Object() {IFocusable foundFeature = null;};
		
		// Convert the features array to a list
		List<IFocusable> features = Arrays.asList(getFeatures());
		
		// Search the feature list for the requested feature
		// 2.1 Use of lambda expressions in at least 5 scenarios
		// 3.5 Valid use of a foreach statement
		features.forEach(f -> {
			if (f != null && f.getName().toLowerCase().equals(featureName.toLowerCase())) {
			
			featureWrapper.foundFeature = f;
			}
		});
		
		
		// Standard for each code
//		for (IFocusable f : getFeatures()) {
//			
//			if (f.getName().toLowerCase().equals(featureName.toLowerCase())) {
//				
//				foundFeature = f;
//				break;
//			}
//		}
		
		return featureWrapper.foundFeature;
	}
	
	/**
	 * @return all features of the room
	 */
	public IFocusable[] getFeatures() {
		
		return new IFocusable[] {nFeature, eFeature, sFeature, wFeature};
	}
}
