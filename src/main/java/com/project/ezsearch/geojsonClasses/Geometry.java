package com.project.ezsearch.geojsonClasses;

import java.util.List;

public class Geometry {

	String type;
	List<Double> coordinates;


	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public List<Double> getCoordinates() {
		return coordinates;
	}
	public void setCoordinates(List<Double> coordinates) {
		this.coordinates = coordinates;
	}
}
