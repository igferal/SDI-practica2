package com.sdi.business.impl.classes;

import com.sdi.infrastructure.Factories;
import com.sdi.model.Seat;
import com.sdi.model.SeatStatus;
import com.sdi.model.Trip;
import com.sdi.persistence.SeatDao;

public class SeatMoveToExcluded {

	public void exclude(Long idUser, Long idTrip) {
		SeatDao seatDao = Factories.persistence.newSeatDao();
		Seat seat = seatDao.findByUserAndTrip(idUser, idTrip);
		
		if (seat == null) {
			seat = new Seat();
			seat.setUserId(idUser);
			seat.setTripId(idTrip);
			seat.setStatus(SeatStatus.EXCLUDED);
			seatDao.save(seat);
			Factories.services.createApplicationService().delete(idUser, idTrip);
		}
		else if(seat.getStatus().equals(SeatStatus.ACCEPTED)) {
			seat.setStatus(SeatStatus.EXCLUDED);
			seatDao.update(seat);
			incrementAvailablePax(idTrip);
		}
		
	}
	
	private void incrementAvailablePax(Long idTrip) {
		Trip trip = Factories.services.createTripService().findTrip(idTrip);
		trip.setAvailablePax(trip.getAvailablePax() + 1);
		Factories.persistence.newTripDao().update(trip);
	}

}
