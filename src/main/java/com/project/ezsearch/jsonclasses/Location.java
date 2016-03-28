package com.project.ezsearch.jsonclasses;

import java.util.List;

public class Location {

	String city;
	List<String> display_address;
	double geo_accuracy;
	String postal_code;
	String country_code;
	List<String> address;
	Position coordinate;
	String state_code;

//getters and setter

	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public List<String> getDisplay_address() {
		return display_address;
	}
	public void setDisplay_address(List<String> display_address) {
		this.display_address = display_address;
	}
	public double getGeo_accuracy() {
		return geo_accuracy;
	}
	public void setGeo_accuracy(double geo_accuracy) {
		this.geo_accuracy = geo_accuracy;
	}
	public String getPostal_code() {
		return postal_code;
	}
	public void setPostal_code(String postal_code) {
		this.postal_code = postal_code;
	}
	public String getCountry_code() {
		return country_code;
	}
	public void setCountry_code(String country_code) {
		this.country_code = country_code;
	}
	public List<String> getAddress() {
		return address;
	}
	public void setAddress(List<String> address) {
		this.address = address;
	}
	public Position getCoordinate() {
		return coordinate;
	}
	public void setCoordinate(Position coordinate) {
		this.coordinate = coordinate;
	}
	public String getState_code() {
		return state_code;
	}
	public void setState_code(String state_code) {
		this.state_code = state_code;
	}
}
