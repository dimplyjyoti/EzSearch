package com.project.ezsearch.jsonclasses;

import java.util.List;

public class SearchResponse {

	Region region;
	int total;
	List<Business> businesses;
	public Region getRegion() {
		return region;
	}
	public void setRegion(Region region) {
		this.region = region;
	}
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public List<Business> getBusinesses() {
		return businesses;
	}
	public void setBusinesses(List<Business> businesses) {
		this.businesses = businesses;
	}
}
