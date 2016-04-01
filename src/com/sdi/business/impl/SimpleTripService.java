package com.sdi.business.impl;

import java.util.Date;
import java.util.List;

import com.sdi.business.TripService;
import com.sdi.business.impl.classes.ListActiveTrip;
import com.sdi.business.impl.classes.ListPromoterTrips;
import com.sdi.business.impl.classes.SaveTripImpl;
import com.sdi.business.impl.classes.TripFind;
import com.sdi.business.impl.classes.UpdateTrip;
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

	public List<Trip> travelsPromoter(Long id) {
		return new ListPromoterTrips(id).listTrips();
	}

	@Override
	public void update(Trip trip) {
		 new UpdateTrip(trip).update();
	}

	@Override
	public Trip findTrip(Long id) {
		return new TripFind().findById(id);
	}
	
}
