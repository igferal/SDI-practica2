package com.sdi.dto;

import java.util.HashMap;
import java.util.Map;

import com.sdi.model.SeatStatus;
import com.sdi.model.Trip;

public class ViajeImplicadoDto {

	private Map<Trip, SeatStatus> trips = new HashMap<Trip, SeatStatus>();

	public Map<Trip, SeatStatus> getTrips() {
		return trips;
	}

	public ViajeImplicadoDto() {

		this.trips = new HashMap<Trip, SeatStatus>();
	}

}
