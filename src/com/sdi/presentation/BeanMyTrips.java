package com.sdi.presentation;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import com.sdi.business.TripService;
import com.sdi.infrastructure.Factories;
import com.sdi.model.Trip;
import com.sdi.model.User;

@ManagedBean(name = "myTrips")
@ViewScoped
public class BeanMyTrips implements Serializable {
	private static final long serialVersionUID = -8662200441018725393L;

	private List<Trip> trips = null;
	private List<Object> selectedTrips = null;
	private Map<Long, Boolean> selectedIds = new HashMap<Long, Boolean>();

	private Trip selectedTrip;

	@PostConstruct
	public void init() {

		System.out.println("Creando Bean my trips");
		// setSelectedTrips(new ArrayList<Trip>());
		listMyTrips();
	}

	public String cancel() {

		try {

			// Long id =
			System.out.println("cancelling size=" + selectedIds.size());

		} catch (NumberFormatException e) {
			e.printStackTrace();
			return "fallo";
		} catch (Exception e) {
			e.printStackTrace();

			return "fallo";
		}
		return "exito";
	}

	public String listMyTrips() {

		Map<String, Object> session = FacesContext.getCurrentInstance()
				.getExternalContext().getSessionMap();
		User user = (User) session.get("LOGGEDIN_USER");
		System.out.println("creando bean myTrips");

		try {
			TripService tservice = Factories.services.createTripService();
			setTrips(tservice.travelsPromoter(user.getId()));
			return "exito";

		} catch (Exception e) {
			e.printStackTrace();
			return "fallo";
		}

	}

	public List<Trip> getTrips() {
		return trips;
	}

	public void setTrips(List<Trip> trips) {
		this.trips = trips;
	}

	public String formattedDate(Date date) {
		return new SimpleDateFormat("dd/MM/yyyy HH:mm").format(date);
	}

	public Trip getSelectedTrip() {
		return selectedTrip;
	}

	public void setSelectedTrip(Trip selectedTrip) {
		this.selectedTrip = selectedTrip;
	}

	public List<Object> getSelectedTrips() {
		return selectedTrips;
	}

	public void setSelectedTrips(List<Object> selectedTrips) {
		this.selectedTrips = selectedTrips;
	}

	public Map<Long, Boolean> getSelectedIds() {
		return selectedIds;
	}

	public void setSelectedIds(Map<Long, Boolean> selectedIds) {
		this.selectedIds = selectedIds;
	}
}
