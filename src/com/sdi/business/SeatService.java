package com.sdi.business;

public interface SeatService {

	void moveToAccepted(Long idUser, Long idTrip);
	void moveToPending(Long idUser, Long idTrip);
	void moveToExcluded(Long idUser, Long idTrip);
}
