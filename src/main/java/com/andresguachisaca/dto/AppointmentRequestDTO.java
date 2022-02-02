package com.andresguachisaca.dto;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class AppointmentRequestDTO {

	private int idSpecialty;

	//private int idDoctor;

	private int idPatient;

	private LocalDateTime timestamp;

}
