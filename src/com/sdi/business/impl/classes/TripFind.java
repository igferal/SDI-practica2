package com.sdi.business.impl.classes;

import com.sdi.infrastructure.Factories;
import com.sdi.model.Trip;

public class TripFind {

	public Trip find(Long id) {
		return Factories.persistence.newTripDao().findById(id);
	}

}
