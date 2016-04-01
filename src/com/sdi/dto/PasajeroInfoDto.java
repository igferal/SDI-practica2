package com.sdi.dto;

import com.sdi.model.SeatStatus;

public class PasajeroInfoDto {

	private Long idUsuario;
	private String usuario;
	private SeatStatus seatStatus;

	public PasajeroInfoDto(Long idUsuario, String usuario, SeatStatus seatStatus) {
		this.idUsuario = idUsuario;
		this.usuario = usuario;
		this.seatStatus = seatStatus;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
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
