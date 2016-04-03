package com.sdi.presentation;

import java.io.Serializable;
import java.util.Map;

import javax.enterprise.context.SessionScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import com.sdi.model.AddressPoint;
import com.sdi.model.Trip;
import com.sdi.model.TripStatus;
import com.sdi.model.User;
import com.sdi.model.Waypoint;
import com.sdi.persistence.util.DateUtil;

@ManagedBean(name = "trip")
@SessionScoped
public class BeanTrip extends Trip implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6283148030775995714L;

	public BeanTrip() {

	}

	public void fillTrip() {

		setArrivalDate(DateUtil.fromString("2016-06-12"));
		setAvailablePax(4);
		setClosingDate(DateUtil.fromString("2016-06-10"));
		setComments("Tratar de ser lo mas limpios posibles");
		setDeparture(new AddressPoint("General Elorza", "Oviedo", "Asturias",
				"España", "33006", new Waypoint(23.4D, 43.45D)));
		setDepartureDate(DateUtil.fromString("2016-06-11"));
		setDestination(new AddressPoint("Paseo de la Castellana ", "Madrid",
				"Madrid", "España", "67890", new Waypoint(56D, 65D)));
		setEstimatedCost(70D);
		setMaxPax(4);
		Map<String, Object> session = FacesContext.getCurrentInstance()
				.getExternalContext().getSessionMap();
		User user = (User) session.get("LOGGEDIN_USER");
		setPromoterId(user.getId());
		setStatus(TripStatus.OPEN);

	}

}
