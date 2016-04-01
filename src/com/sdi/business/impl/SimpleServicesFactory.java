package com.sdi.business.impl;


import com.sdi.business.AlumnosService;
import com.sdi.business.ServicesFactory;
import com.sdi.business.TripService;
import com.sdi.business.UserService;

public class SimpleServicesFactory implements ServicesFactory {

	@Override
	public AlumnosService createAlumnosService() {
		return new SimpleAlumnosService();
	}
	
	@Override
	public UserService createUserService() {
		return new SimpleUserService();
	}

	@Override
	public TripService createTripService() {
		return new SimpleTripService();
	}

}
