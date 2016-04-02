package com.sdi.business.impl.classes;
import alb.util.log.Log;

import com.sdi.infrastructure.Factories;
import com.sdi.model.Trip;

public class UpdateTrip {

	private Trip trip;

	public UpdateTrip(Trip trip) {
		this.trip = trip;
	}

	public void update() {
		Factories.persistence.newTripDao().update(trip);
		Log.info("Viaje [%s] actualizado", trip.getId());
	}

}
