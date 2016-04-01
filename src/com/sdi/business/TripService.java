package com.sdi.business;

import java.util.Date;
import java.util.List;

import com.sdi.model.Trip;

public interface TripService {
<<<<<<< HEAD
	
	Trip findTrip(Long id);
	void saveTrip(Trip trip);
=======

	void saveTrip(Trip trip);

>>>>>>> a6e632a3c9ad6f6691c0bf0b25c62fc0d89a2962
	List<Trip> listActiveTrips(Date date);

	List<Trip> travelsPromoter(Long id);

	void update(Trip trip);

	Trip findById(Long id);

}
