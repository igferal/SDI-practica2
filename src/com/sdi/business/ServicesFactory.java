package com.sdi.business;

public interface ServicesFactory {
	
	AlumnosService createAlumnosService();
	UserService createUserService();
	TripService createTripService();
	ApplicationService createApplicationService();
}
