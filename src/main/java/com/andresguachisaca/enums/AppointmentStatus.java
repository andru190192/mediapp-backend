package com.andresguachisaca.enums;

public enum AppointmentStatus {

	AGENDADO(0), ATENDIDO(1), CANCELADO(2);

	private int status;

	private AppointmentStatus(int status) {
		this.status = status;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

}
