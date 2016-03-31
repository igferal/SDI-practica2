package com.sdi.business;

import java.util.Date;
import java.util.List;

import com.sdi.model.Trip;

public interface TripService {
	
	void saveTrip(Trip trip);
	
	List<Trip> listActiveTrips(Date date);

}