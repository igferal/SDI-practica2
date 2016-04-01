package com.sdi.business.impl;

import java.util.Date;
import java.util.List;

import com.sdi.business.TripService;
import com.sdi.business.impl.classes.ListActiveTrip;
import com.sdi.business.impl.classes.SaveTripImpl;
import com.sdi.business.impl.classes.TripFind;
import com.sdi.model.Trip;

public class SimpleTripService implements TripService {

	@Override
	public void saveTrip(Trip trip) {

		
		new SaveTripImpl(trip).save();
	}

	@Override
	public List<Trip> listActiveTrips(Date date) {
		
		return new ListActiveTrip(date).listActiveTrips();
	}

	@Override
	public Trip findTrip(Long id) {
		return new TripFind().find(id);
	}

}
