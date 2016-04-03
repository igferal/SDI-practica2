package com.sdi.presentation;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import com.sdi.business.SeatService;
import com.sdi.dto.DTOAssembler;
import com.sdi.dto.ViajeImplicadoDto;
import com.sdi.infrastructure.Factories;
import com.sdi.model.SeatStatus;
import com.sdi.model.User;
import com.sdi.persistence.util.DateUtil;

@ManagedBean(name = "involvedTrips")
@ViewScoped
public class BeanInvolvedTrips implements Serializable {
	private static final long serialVersionUID = -8662200441018725393L;

	private List<ViajeImplicadoDto> trips;

	public boolean dateBefore(Date date) {

		if (date == null) {
			Date now = new Date();
			return DateUtil.isAfter(date, now);
		}
		return false;

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
			setTrips(DTOAssembler.getViajesImplicadosDto(user.getId()));
			l2 = System.currentTimeMillis();

			System.out.println("tiempo tarda = " + (l2 - l1));

			return "exito";

		} catch (Exception e) {
			e.printStackTrace();
			return "fallo";
		}

	}

	public String getRole(ViajeImplicadoDto trip) {

		FacesContext facesContext = FacesContext.getCurrentInstance();
		ResourceBundle bundle = facesContext.getApplication()
				.getResourceBundle(facesContext, "msgs");

		if (DateUtil.isAfter(new Date(), trip.getClosingDate())
				&& trip.getSeatStatus() == null) {

			return bundle.getString("statusSinPlaza");

		}

		else if (trip.getSeatStatus() == null) {

			if (trip.getAvailablePax() == 0) {
				return bundle.getString("statusSinPlaza");
			} else {
				return bundle.getString("statusPendiente");
			}
		} else if (trip.getSeatStatus().equals(SeatStatus.ACCEPTED)) {
			return bundle.getString("statusAdmitido");
		} else {
			return bundle.getString("statusCancelado");
		}

	}

	public String cancelSeat(ViajeImplicadoDto trip) {

		try {

			Map<String, Object> session = FacesContext.getCurrentInstance()
					.getExternalContext().getSessionMap();
			User user = (User) session.get("LOGGEDIN_USER");

			SeatService sService = Factories.services.createSeatService();
			sService.moveToExcluded(user.getId(), trip.getId());

		} catch (Exception e) {

			e.printStackTrace();
			return "fallo";
		}

		return "exito";
	}

	public List<ViajeImplicadoDto> getTrips() {
		return trips;
	}

	public void setTrips(List<ViajeImplicadoDto> trips) {
		this.trips = trips;
	}

	public String formattedDate(Date date) {
		return new SimpleDateFormat("dd/MM/yyyy HH:mm").format(date);
	}

}
