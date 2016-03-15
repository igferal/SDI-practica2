package com.sdi.dto;

import java.util.ArrayList;
import java.util.List;

import com.sdi.model.Trip;
import com.sdi.model.User;

public class ComentarEnViajeDto {

	private Trip trip;
	private List<User> participantes = new ArrayList<User>();
	
	public ComentarEnViajeDto(Trip trip) {
		this.trip = trip;
	}
	public Trip getTrip() {
		return trip;
	}
	public List<User> getParticipantes() {
		return participantes;
	}	
	
}
