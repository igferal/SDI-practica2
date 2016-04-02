package com.sdi.business.impl.classes;

import java.util.Date;

import com.sdi.infrastructure.Factories;
import com.sdi.model.Application;
import com.sdi.model.Trip;

public class ApplicationSave {

	public boolean save(Application application) {
		Trip trip = Factories.services.createTripService().findTrip(application.getTripId());
		
		if (trip.getClosingDate().before(new Date())
				|| trip.getAvailablePax() < 1)
			return false;
		
		Factories.persistence.newApplicationDao().save(application);
		
		return true;
	}

}
