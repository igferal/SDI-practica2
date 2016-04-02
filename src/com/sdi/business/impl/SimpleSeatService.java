package com.sdi.business.impl;

import com.sdi.business.SeatService;
import com.sdi.business.impl.classes.SeatMoveToAccepted;
import com.sdi.business.impl.classes.SeatMoveToExcluded;
import com.sdi.business.impl.classes.SeatMoveToPending;

public class SimpleSeatService implements SeatService {

	@Override
	public void moveToAccepted(Long idUser, Long idTrip) {
		new SeatMoveToAccepted().accept(idUser, idTrip);
	}

	@Override
	public void moveToPending(Long idUser, Long idTrip) {
		new SeatMoveToPending().move(idUser, idTrip);
	}

	@Override
	public void moveToExcluded(Long idUser, Long idTrip) {
		new SeatMoveToExcluded().exclude(idUser, idTrip);
	}

}
