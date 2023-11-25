package com.nwtcstudent.java.textadventure;

public class Room {
    //keep rooms as simple boxes - just have N/S/E/W, don't worry about corners?
    //will have some sort of feature in each sector
	
	//fields
	private int id;
	private String name;
	private String description;
	
	private IFocusable NFeature;
	private IFocusable EFeature;
	private IFocusable SFeature;
	private IFocusable WFeature;
	
	// Constructor
	public Room(int doorID, String doorName, String doorDesc) {
		
		
	}
	
	// Methods
	
	public void setItems(Item north, Item east, Item south, Item west) {
		
		NFeature = north;
		EFeature = east;
		SFeature = south;
		WFeature = west;
	}
	
	
	
	public void setDoors(Door north, Door east, Door south, Door west) {
		
		if (north != null) {
			
			NFeature = north;
		}
		
		if (east != null) {
			
			EFeature = east;
		}
		
		if (south != null) {
			
			SFeature = south;
		}
		
		if (west != null) {
			
			WFeature = west;
		}
	}

	//getters and setters
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
