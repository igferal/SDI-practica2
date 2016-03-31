package com.sdi.presentation;

import java.util.Date;
import java.util.ResourceBundle;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import com.sdi.business.TripService;
import com.sdi.infrastructure.Factories;
import com.sdi.model.AddressPoint;
import com.sdi.model.TripStatus;
import com.sdi.model.Waypoint;
import com.sdi.persistence.util.DateUtil;

@ManagedBean(name = "tripController")
@SessionScoped
public class BeanTripController {

	@ManagedProperty(value = "#{trip}")
	private BeanTrip trip;
	private AddressPoint departure;
	private AddressPoint destination;
	private Date dateSalida;
	private Date dateInscripcion;
	private Date dateLlegada;
	private String costeEstimado;
	private String plazasDisponibles;
	private String plazasMaximas;
	private String coordenadasOrigen;
	private String coordenadasDestino;

	public String getCoordenadasOrigen() {
		return coordenadasOrigen;
	}

	public void setCoordenadasOrigen(String coordenadasOrigen) {
		this.coordenadasOrigen = coordenadasOrigen;
	}

	public String getCoordenadasDestino() {
		return coordenadasDestino;
	}

	public void setCoordenadasDestino(String coordenadasDestino) {
		this.coordenadasDestino = coordenadasDestino;
	}

	public String getCosteEstimado() {
		return costeEstimado;
	}

	public void setCosteEstimado(String costeEstimado) {
		this.costeEstimado = costeEstimado;
	}

	public String getPlazasDisponibles() {
		return plazasDisponibles;
	}

	public void setPlazasDisponibles(String plazasDisponibles) {
		this.plazasDisponibles = plazasDisponibles;
	}

	public String getPlazasMaximas() {
		return plazasMaximas;
	}

	public void setPlazasMaximas(String plazasMaximas) {
		this.plazasMaximas = plazasMaximas;
	}

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
		return destination;
	}

	public void setArrival(AddressPoint arrival) {
		this.destination = arrival;
	}

	@PostConstruct
	public void init() {

		departure = new AddressPoint("", "", "", "", "", new Waypoint(0D, 0D));
		setArrival(new AddressPoint("", "", "", "", "", new Waypoint(0D, 0D)));
		System.out.println("BeanAlumnos - PostConstruct");
		
		trip = (BeanTrip) FacesContext.getCurrentInstance()
				.getExternalContext().getSessionMap().get(new String("trip"));
		if (trip == null) {
			System.out.println("BeanTrip - No existia");
			trip = new BeanTrip();
			FacesContext.getCurrentInstance().getExternalContext()
					.getSessionMap().put("trip", trip);
		}
	}

	public String save() {

		TripService tservice;
		FacesContext context = FacesContext.getCurrentInstance();
		ResourceBundle bundle = context.getApplication().getResourceBundle(
				context, "msgs");
		String respuesta = "exito";

		completeWaypoints();

		try {

			Double estimatedCost = Double.parseDouble(costeEstimado);
			Integer availablePax = Integer.parseInt(plazasDisponibles);
			Integer maxPax = Integer.parseInt(plazasMaximas);

			if (maxPax < availablePax) {

				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage(FacesMessage.SEVERITY_ERROR, "",

						bundle.getString("createTrip_wrongSeats")));
				return "fallo";

			}

			if (DateUtil.isAfter(dateInscripcion, dateSalida)
					|| DateUtil.isAfter(dateSalida, dateLlegada)) {

				FacesContext.getCurrentInstance().addMessage(
						null,
						new FacesMessage(FacesMessage.SEVERITY_ERROR, "",
								bundle.getString("createTrip_wrongDates")));
				return "fallo";
			}

			trip.setArrivalDate(dateLlegada);
			trip.setAvailablePax(availablePax);
			trip.setClosingDate(dateLlegada);
			trip.setDeparture(departure);
			trip.setDepartureDate(dateSalida);
			trip.setDestination(destination);
			trip.setEstimatedCost(estimatedCost);
			trip.setMaxPax(maxPax);
			trip.setStatus(TripStatus.OPEN);
			trip.setPromoterId(314L);

			tservice = Factories.services.createTripService();

			tservice.saveTrip(trip);

		} catch (NumberFormatException e) {
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "", bundle
							.getString("createTrip_wrongNumberInputs")));
			e.printStackTrace();
			return "fallo";

		} catch (Exception e) {
			e.printStackTrace();
			return "fallo";
		}

		return respuesta;

	}

	private void completeWaypoints() {

		if (coordenadasOrigen != null && coordenadasOrigen != "") {
			String[] coorOr = coordenadasOrigen.split("C");
			Double latOr = Double.parseDouble(coorOr[0]);
			Double lonOr = Double.parseDouble(coorOr[1]);
			Waypoint cO = new Waypoint(latOr, lonOr);
			departure.setWaypoint(cO);

		}
		if (coordenadasDestino != null && coordenadasDestino != "") {
			String[] coorDes = coordenadasOrigen.split("C");
			Double latDes = Double.parseDouble(coorDes[0]);
			Double lonDes = Double.parseDouble(coorDes[1]);
			Waypoint cD = new Waypoint(latDes, lonDes);
			destination.setWaypoint(cD);
		}
	}

	@PreDestroy
	public void end() {
		System.out.println("BeanAlumnos - PreDestroy");
	}

}