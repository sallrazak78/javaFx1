package model;

import javafx.beans.property.SimpleObjectProperty;

public class LocationModel {
	private SimpleObjectProperty<Location> location;
	public LocationModel(Location location) {
		this.location=new SimpleObjectProperty<Location>();
		this.location.set(location);
	}
	public SimpleObjectProperty<Location> getLocation() {
		return location;
	}
	public void setLocation(SimpleObjectProperty<Location> location) {
		this.location = location;
	}
	
}
