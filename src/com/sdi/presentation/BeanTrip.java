package com.sdi.presentation;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import com.sdi.model.Trip;

@ManagedBean(name="trip")
@RequestScoped
public class BeanTrip {
	
	private Trip trip;
	
	
	

	public Trip getTrip() {
		return trip;
	}

	public void setTrip(Trip trip) {
		this.trip = trip;
	}

}
