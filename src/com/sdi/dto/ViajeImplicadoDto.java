package com.sdi.dto;

import com.sdi.model.SeatStatus;
import com.sdi.model.Trip;

public class ViajeImplicadoDto extends Trip {

	private SeatStatus seatStatus;

	public ViajeImplicadoDto(Trip trip, SeatStatus seatStatus) {
		setArrivalDate(trip.getArrivalDate());
		setAvailablePax(trip.getAvailablePax());
		setClosingDate(trip.getClosingDate());
		setComments(trip.getComments());
		setDeparture(trip.getDeparture());
		setDepartureDate(trip.getDepartureDate());
		setDestination(trip.getDestination());
		setEstimatedCost(trip.getEstimatedCost());
		setId(trip.getId());
		setMaxPax(trip.getMaxPax());
		setPromoterId(trip.getPromoterId());
		setStatus(trip.getStatus());
		setSeatStatus(seatStatus);

	}

	public SeatStatus getSeatStatus() {
		return seatStatus;
	}

	public void setSeatStatus(SeatStatus seatStatus) {
		this.seatStatus = seatStatus;
	}

}
