package com.sdi.presentation;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import com.sdi.business.TripService;
import com.sdi.infrastructure.Factories;
import com.sdi.model.AddressPoint;

@ManagedBean(name = "tripController")
@SessionScoped
public class BeanTripController {

	@ManagedProperty(value = "#{trip}")
	private BeanTrip trip;
	private AddressPoint departure;
	private AddressPoint arrival;
	private Date dateSalida;
	private Date dateInscripcion;
	private Date dateLlegada;

	public Date getDateInscripcion() {
		return dateInscripcion;
	}

	public void setDateInscripcion(Date dateInscripcion) {
		this.dateInscripcion = dateInscripcion;
	}

	public Date getDateLlegada() {
		return dateLlegada;
	}

	public void setDateLlegada(Date dateLlegada) {
		this.dateLlegada = dateLlegada;
	}

	public BeanTrip getTrip() {
		return trip;
	}

	public void setTrip(BeanTrip trip) {
		this.trip = trip;
	}

	@PostConstruct
	public void init() {
		
		departure = new AddressPoint("", "", "", "", "", null);
		setArrival(new AddressPoint("", "", "", "", "", null));
		System.out.println("BeanAlumnos - PostConstruct");
		// Buscamos el alumno en la sesión. Esto es un patrón factoría
		// claramente.
		trip = (BeanTrip) FacesContext.getCurrentInstance()
				.getExternalContext().getSessionMap().get(new String("trip"));
		// si no existe lo creamos e inicializamos
		if (trip == null) {
			System.out.println("BeanTrip - No existia");
			trip = new BeanTrip();
			FacesContext.getCurrentInstance().getExternalContext()
					.getSessionMap().put("trip", trip);
		}
	}

	public void save() {

		TripService tservice;
		

		try {
		
			trip.setArrivalDate(dateSalida);
			trip.setClosingDate(dateInscripcion);
			trip.setDepartureDate(dateSalida);
			trip.setDeparture(departure);
			trip.setDestination(arrival);
			/*
			 * 
			 * IR METIENDO COMPROBACIONES *
			 */
			tservice = Factories.services.createTripService();

		
			
			tservice.SaveTrip(trip);

		} catch (Exception e) {
			e.printStackTrace();
		
		}

	}

	@PreDestroy
	public void end() {
		System.out.println("BeanAlumnos - PreDestroy");
	}

	

	public AddressPoint getDeparture() {
		return departure;
	}

	public void setDeparture(AddressPoint departure) {
		this.departure = departure;
	}

	public Date getDateSalida() {
		return dateSalida;
	}

	public void setDateSalida(Date dateSalida) {
		this.dateSalida = dateSalida;
	}

	public AddressPoint getArrival() {
		return arrival;
	}

	public void setArrival(AddressPoint arrival) {
		this.arrival = arrival;
	}

	

}
