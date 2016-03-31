package com.sdi.presentation;

import java.io.Serializable;
import java.util.Date;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import com.sdi.business.TripService;
import com.sdi.infrastructure.Factories;
import com.sdi.model.Trip;

@ManagedBean(name = "activeTrips")
@ViewScoped
public class BeanActiveTrips implements Serializable {
	private static final long serialVersionUID = -8662200441018725390L;

	private Trip[] trips = null;

	@PostConstruct
	public void init() {

		
		System.out.println("Creando Bean active trips");
		list();
	}

	public String list() {

		try {
			TripService tservice = Factories.services.createTripService();
			trips = (Trip[]) tservice.listActiveTrips(new Date()).toArray(new Trip[0]);
			return "exito";

		} catch (Exception e) {
			e.printStackTrace();
			return "fallo";
		}

	}

	public Trip[] getTrips() {
		return trips;
	}

	public void setTrips(Trip[] trips) {
		this.trips = trips;
	}

}
