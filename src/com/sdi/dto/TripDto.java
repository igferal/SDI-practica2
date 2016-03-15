package com.sdi.dto;

import java.util.HashMap;
import java.util.Map;

import com.sdi.model.Trip;

public class TripDto {

	private Trip trip;
	private Map<String, InfoViajeDto> infoPasajeros;
	private long idPromotor;
	private String promotor;
	private InfoViajeDto infoPromotor;
	private boolean isInTrip;
	private long idUser;

	public TripDto(Trip trip, long idUser) {
		this.setTrip(trip);
		this.setInfoPasajeros(new HashMap<String, InfoViajeDto>());
		this.idUser = idUser;
	}

	private boolean checkInTrip(long idUser) {
		if (idPromotor == idUser)
			return true;
		
		for (InfoViajeDto info:infoPasajeros.values())
			if (info.getIdUsuario() == idUser)
				return true;
		
		return false;
	}



	public Map<String, InfoViajeDto> getInfoPasajeros() {
		return infoPasajeros;
	}

	public void setInfoPasajeros(Map<String, InfoViajeDto> infoPasajeros) {
		this.infoPasajeros = infoPasajeros;
		this.isInTrip = checkInTrip(idUser);
	}



	public Trip getTrip() {
		return trip;
	}



	public void setTrip(Trip trip) {
		this.trip = trip;
	}




	public String getPromotor() {
		return promotor;
}
	public void setPromotor(String promotor) {
		this.promotor = promotor;
	}



	public InfoViajeDto getInfoPromotor() {
		return infoPromotor;
	}



	public void setInfoPromotor(InfoViajeDto infoPromotor) {
		this.infoPromotor = infoPromotor;
		this.isInTrip = checkInTrip(idUser);
	}



	public Long getIdPromotor() {
		return idPromotor;
	}



	public void setIdPromotor(Long idPromotor) {
		this.idPromotor = idPromotor;
	}

	public boolean isIsInTrip() {
		return isInTrip;
	}


}
