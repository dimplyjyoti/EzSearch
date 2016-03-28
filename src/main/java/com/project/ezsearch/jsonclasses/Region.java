package com.project.ezsearch.jsonclasses;

public class Region {

	public Position getSpan() {
		return span;
	}
	public void setSpan(Position span) {
		this.span = span;
	}
	public Position getCenter() {
		return center;
	}
	public void setCenter(Position center) {
		this.center = center;
	}
	Position span;
	Position center;
}
