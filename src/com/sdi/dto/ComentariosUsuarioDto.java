package com.sdi.dto;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.sdi.model.Trip;
import com.sdi.model.User;

public class ComentariosUsuarioDto {
	
	private User user;
	private Map<User, List<Comentario>> comentarios = new HashMap<User, List<Comentario>>();

	public static class Comentario {
		private Long tripId;
		private String departure;
		private String destination;
		private Date departureDate;
		private String comment;
		private double valoracion;
		
		public Comentario(Trip trip, String comment, double valoracion) {
			this.tripId = trip.getId();
			this.departure = trip.getDeparture().getCity();
			this.departureDate = trip.getDepartureDate();
			this.destination = trip.getDestination().getCity();
			this.comment = comment;
			this.valoracion = valoracion;
		}

		public Long getTripId() {
			return tripId;
		}

		public void setTripId(Long tripId) {
			this.tripId = tripId;
		}

		public String getDeparture() {
			return departure;
		}

		public void setDeparture(String departure) {
			this.departure = departure;
		}

		public String getDestination() {
			return destination;
		}

		public void setDestination(String destination) {
			this.destination = destination;
		}

		public Date getDepartureDate() {
			return departureDate;
		}

		public void setDepartureDate(Date departureDate) {
			this.departureDate = departureDate;
		}

		public String getComment() {
			return comment;
		}

		public void setComment(String comment) {
			this.comment = comment;
		}

		public double getValoracion() {
			return valoracion;
		}

		public void setValoracion(double valoracion) {
			this.valoracion = valoracion;
		}

		
		
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Map<User, List<Comentario>> getComentarios() {
		return comentarios;
	}
	
}
