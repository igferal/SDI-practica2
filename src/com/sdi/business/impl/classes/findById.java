package com.sdi.business.impl.classes;
import com.sdi.infrastructure.Factories;
import com.sdi.model.Trip;

public class findById {

	private Long id;

	public findById(Long id) {
		this.id = id;
	}

	public Trip find() {

		return Factories.persistence.newTripDao().findById(id);
	}

}
