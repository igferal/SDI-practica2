package com.sdi.presentation;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
<<<<<<< HEAD
import javax.faces.bean.ViewScoped;
=======
>>>>>>> a6e632a3c9ad6f6691c0bf0b25c62fc0d89a2962
import javax.faces.context.FacesContext;

import com.sdi.dto.DTOAssembler;
import com.sdi.dto.PasajeroInfoDto;
import com.sdi.dto.TripDto;
import com.sdi.infrastructure.Factories;
import com.sdi.model.SeatStatus;
import com.sdi.model.Trip;
import com.sdi.model.User;

@ManagedBean(name = "tripInfo")
@ViewScoped
public class BeanTripInfo {

	private TripDto tripDto;
	private boolean isTripClosed;

	@PostConstruct
	public void init() {

		System.out.println("Creando Bean info trip");
		Trip trip = Factories.services.createTripService().findTrip(
				(Long) FacesContext.getCurrentInstance().getExternalContext()
						.getSessionMap().get("tripInfoParam"));
		setTripDto(DTOAssembler.generateTripDto(trip,
				(User) FacesContext.getCurrentInstance().getExternalContext()
						.getSessionMap().get("LOGGEDIN_USER")));
	}

	public TripDto getTripDto() {
		return tripDto;
	}

	public void setTripDto(TripDto tripDto) {
		this.tripDto = tripDto;
	}
	
	public String formattedDate(Date date) {
		return new SimpleDateFormat("dd/MM/yyyy HH:mm").format(date);
	}
	
	public List<PasajeroInfoDto> getAcceptedPassengers() {
		List<PasajeroInfoDto> pasajeros = new ArrayList<PasajeroInfoDto>();
		
		for (PasajeroInfoDto pasajero:tripDto.getPasajeros())
			if (pasajero.getSeatStatus() != null && pasajero.getSeatStatus().equals(SeatStatus.ACCEPTED))
				pasajeros.add(pasajero);
		
		return pasajeros;
	}
	
	public boolean isTripClosed() {
		isTripClosed = tripDto.getTrip().getClosingDate().before(new Date());
		System.out.println(isTripClosed);
		return isTripClosed;
	}

	public void setTripClosed(boolean isTripClosed) {
		this.isTripClosed = isTripClosed;
	}

}
