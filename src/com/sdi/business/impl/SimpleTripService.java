package com.sdi.business.impl;

import java.util.Date;
import java.util.List;

import com.sdi.business.TripService;
import com.sdi.business.impl.classes.ListActiveTrip;
import com.sdi.business.impl.classes.ListPromoterTrips;
import com.sdi.business.impl.classes.SaveTripImpl;
<<<<<<< HEAD
import com.sdi.business.impl.classes.TripFind;
=======
import com.sdi.business.impl.classes.UpdateTrip;
import com.sdi.business.impl.classes.findById;
>>>>>>> a6e632a3c9ad6f6691c0bf0b25c62fc0d89a2962
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
<<<<<<< HEAD
	public Trip findTrip(Long id) {
		return new TripFind().find(id);
	}

=======
	public List<Trip> travelsPromoter(Long id) {
		return new ListPromoterTrips(id).listTrips();
	}

	@Override
	public void update(Trip trip) {
		 new UpdateTrip(trip).update();
	}

	@Override
	public Trip findById(Long id) {
		// TODO Auto-generated method stub
		return new findById(id).find();
	}
	
	

>>>>>>> a6e632a3c9ad6f6691c0bf0b25c62fc0d89a2962
}
