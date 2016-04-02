package com.sdi.presentation;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Map.Entry;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import com.sdi.dto.DTOAssembler;
import com.sdi.model.SeatStatus;
import com.sdi.model.Trip;
import com.sdi.model.User;
import com.sdi.persistence.util.DateUtil;

@ManagedBean(name = "involvedTrips")
@ViewScoped
public class BeanInvolvedTrips implements Serializable {
	private static final long serialVersionUID = -8662200441018725393L;

	private Map<Trip, SeatStatus> trips;

	public boolean dateBefore(Date date) {

		Date now = new Date();
		return DateUtil.isAfter(date, now);

	}

	public String load() {
		return "exito";
	}

	@PostConstruct
	public void init() {

		System.out.println("Creando Bean involved trips");
		list();
	}

	public String list() {

		if (trips != null) {
			return "exito";
		}
		try {
			Map<String, Object> session = FacesContext.getCurrentInstance()
					.getExternalContext().getSessionMap();
			User user = (User) session.get("LOGGEDIN_USER");

			long l1, l2;

			l1 = System.currentTimeMillis();
			setTrips(DTOAssembler.getViajesImplicadosDto(user.getId())
					.getTrips());
			l2 = System.currentTimeMillis();

			System.out.println("tiempo tarda = " + (l2 - l1));

			return "exito";

		} catch (Exception e) {
			e.printStackTrace();
			return "fallo";
		}

	}

	public String getRole(Trip trip) {
		
		FacesContext facesContext = FacesContext.getCurrentInstance();
        ResourceBundle bundle = 
         facesContext.getApplication().getResourceBundle(facesContext, "msgs");

		if (trips.get(trip) == null) {

			if (trip.getAvailablePax() == 0) {
				return bundle.getString("statusSinPlaza");
			} else {
				return bundle.getString("statusPendiente");
			}
		} else if (trips.get(trip).equals(SeatStatus.ACCEPTED)) {
			return bundle.getString("statusAdmitido");
		} else {
			return bundle.getString("statusCancelado");
		}

	}

	public List<Entry<Trip, SeatStatus>> entryList() {
		return new ArrayList<>(trips.entrySet());

	}

	public String formattedDate(Date date) {
		return new SimpleDateFormat("dd/MM/yyyy HH:mm").format(date);
	}

	public Map<Trip, SeatStatus> getTrips() {
		return trips;
	}

	public void setTrips(Map<Trip, SeatStatus> trips) {
		this.trips = trips;
	}

}
