package com.nwtcstudent.java.textadventure;

public class Room {
	
	// ### Fields ###
	
	private int id;
	private String name;
	private String description;
	
	private IFocusable NFeature;
	private IFocusable EFeature;
	private IFocusable SFeature;
	private IFocusable WFeature;
	
	
	// ### Constructor ###
	
	public Room(int doorID, String doorName, String doorDesc) {
		
		id = doorID;
		name = doorName;
		description = doorDesc;
	}
	
	
	// ### Methods ###
	
	public void setFeatures(IFocusable north, IFocusable east, IFocusable south, IFocusable west) {
		
		NFeature = north;
		EFeature = east;
		SFeature = south;
		WFeature = west;
	}

	
	// ### Properties ###
	
	public int getID() {
		
		return id;
	}

	public String getDescription() {
		
		return description;
	}

	public IFocusable getNFeature() {
		
		return NFeature;
	}

	public void setNFeature(IFocusable nFeature) {
		NFeature = nFeature;
	}

	public IFocusable getEFeature() {
		return EFeature;
	}

	public void setEFeature(IFocusable eFeature) {
		EFeature = eFeature;
	}

	public IFocusable getSFeature() {
		return SFeature;
	}

	public void setSFeature(IFocusable sFeature) {
		SFeature = sFeature;
	}

	public IFocusable getWFeature() {
		return WFeature;
	}

	public void setWFeature(IFocusable wFeatuer) {
		WFeature = wFeatuer;
	}
}
