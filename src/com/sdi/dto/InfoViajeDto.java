package com.sdi.dto;

import java.util.ArrayList;
import java.util.List;

import com.sdi.model.SeatStatus;

public class InfoViajeDto {

	private Long idUsuario;
	private String usuario;
	private SeatStatus seatStatus;

	private List<String> comentarios;

	private Double rating;
	
	public InfoViajeDto() {
		this.setComentarios(new  ArrayList<String>());
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	

	public Double getRating() {
		return rating;
	}

	public void setRating(Double rating) {
		this.rating = rating;
	}

	public List<String> getComentarios() {
		return comentarios;
	}

	public void setComentarios(List<String> comentarios) {
		this.comentarios = comentarios;
	}

	public SeatStatus getSeatStatus() {
		return seatStatus;
	}

	public void setSeatStatus(SeatStatus seatStatus) {
		this.seatStatus = seatStatus;
	}



	public Long getIdUsuario() {
		return idUsuario;
	}



	public void setIdUsuario(Long idUsuario) {
		this.idUsuario = idUsuario;
	}

}
