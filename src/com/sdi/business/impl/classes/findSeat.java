package com.sdi.business.impl.classes;

import com.sdi.infrastructure.Factories;
import com.sdi.model.Seat;

public class findSeat {

	private Long[] id;

	public findSeat(Long[] id) {
		this.id = id;
	}

	public Seat find() {
		return Factories.persistence.newSeatDao().findById(id);
	}

}
