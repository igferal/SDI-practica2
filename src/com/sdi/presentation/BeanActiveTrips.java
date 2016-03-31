package com.sdi.presentation;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import com.sdi.business.TripService;
import com.sdi.infrastructure.Factories;
import com.sdi.model.Trip;

@ManagedBean(name = "activeTrips")
@ViewScoped
public class BeanActiveTrips implements Serializable {
	private static final long serialVersionUID = -8662200441018725390L;

	private List<Trip> trips = null;
	private List<Trip> filteredTrips = null;

	@PostConstruct
	public void init() {

		System.out.println("Creando Bean active trips");
		list();
	}

	public String list() {

		try {
			System.out.println("PASO");
			TripService tservice = Factories.services.createTripService();
			setTrips(tservice.listActiveTrips(new Date()));
			return "exito";

		} catch (Exception e) {
			e.printStackTrace();
			return "fallo";
		}

	}

	/*
	 * public boolean filterByCost(Object value, Object filter, Locale locale) {
	 * String filterText = (filter == null) ? null : filter.toString().trim();
	 * if(filterText == null||filterText.equals("")) { return true; }
	 * 
	 * if(value == null) { return false; }
	 * 
	 * return ((Comparable) value).compareTo(Integer.valueOf(filterText)) > 0; }
	 */

	public List<Trip> getTrips() {
		return trips;
	}

	public void setTrips(List<Trip> trips) {
		this.trips = trips;
	}

	public List<Trip> getFilteredTrips() {
		return filteredTrips;
	}

	public void setFilteredTrips(List<Trip> filteredTrips) {
		this.filteredTrips = filteredTrips;
	}

}
