package com.sdi.business.impl;

import com.sdi.business.TripService;
import com.sdi.business.impl.classes.SaveTripImpl;
import com.sdi.model.Trip;

public class SimpleTripService implements TripService {

	@Override
	public void SaveTrip(Trip trip) {

		
		new SaveTripImpl(trip).save();
	}

}
