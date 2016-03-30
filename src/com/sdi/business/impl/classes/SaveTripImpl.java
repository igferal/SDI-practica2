package com.sdi.business.impl.classes;

import com.sdi.infrastructure.Factories;
import com.sdi.model.Trip;
import com.sdi.persistence.TripDao;

public class SaveTripImpl {

	private Trip trip;

	public SaveTripImpl(Trip trip) {

		this.trip = trip;
	}

	public void save() {
		TripDao tdao = Factories.persistence.newTripDao();
		tdao.save(trip);

	}

}
