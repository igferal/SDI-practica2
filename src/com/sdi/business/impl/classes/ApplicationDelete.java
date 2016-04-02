package com.sdi.business.impl.classes;

import com.sdi.infrastructure.Factories;

public class ApplicationDelete {

	public void delete(Long idUser, Long idTrip) {
		Factories.persistence.newApplicationDao().delete(new Long[]{idTrip, idUser});
	}

}
