package com.sdi.business.impl.classes;

import com.sdi.infrastructure.Factories;
import com.sdi.model.Seat;
import com.sdi.model.SeatStatus;
import com.sdi.model.Trip;
import com.sdi.persistence.SeatDao;

public class SeatMoveToAccepted {

	public void accept(Long idUser, Long idTrip) {
		SeatDao seatDao = Factories.persistence.newSeatDao();
		Seat seat = seatDao.findByUserAndTrip(idUser, idTrip);
		
		System.out.println(seat);
		
		if (seat == null) {
			seat = new Seat();
			seat.setUserId(idUser);
			seat.setTripId(idTrip);
			seat.setStatus(SeatStatus.ACCEPTED);
			seatDao.update(seat);
			decrementAvailablePax(idTrip);
		}
		else if(seat.getStatus().equals(SeatStatus.EXCLUDED)) {
			seat.setStatus(SeatStatus.ACCEPTED);
			seatDao.update(seat);
			decrementAvailablePax(idTrip);
		}
	}

	private void decrementAvailablePax(Long idTrip) {
		Trip trip = Factories.services.createTripService().findTrip(idTrip);
		trip.setAvailablePax(trip.getAvailablePax() - 1);
		Factories.persistence.newTripDao().update(trip);
	}

}
