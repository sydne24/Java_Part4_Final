package com.nwtcstudent.java.textadventure;

public class Room {
    //keep rooms as simple boxes - just have N/S/E/W, don't worry about corners?
    //will have some sort of feature in each sector
	
	//fields
	private int ID;
	private String Description;
	private String NFeature;
	private String EFeature;
	private String SFeature;
	private String WFeatuer;

	//getters and setters
	public int getID() {
		return ID;
	}

	public void setID(int id) {
		ID = id;
	}

	public String getDescription() {
		return Description;
	}

	public void setDescription(String description) {
		Description = description;
	}

	public String getNFeature() {
		return NFeature;
	}

	public void setNFeature(String nFeature) {
		NFeature = nFeature;
	}

	public String getEFeature() {
		return EFeature;
	}

	public void setEFeature(String eFeature) {
		EFeature = eFeature;
	}

	public String getSFeature() {
		return SFeature;
	}

	public void setSFeature(String sFeature) {
		SFeature = sFeature;
	}

	public String getWFeatuer() {
		return WFeatuer;
	}

	public void setWFeatuer(String wFeatuer) {
		WFeatuer = wFeatuer;
	}
	
	//Methods
}
