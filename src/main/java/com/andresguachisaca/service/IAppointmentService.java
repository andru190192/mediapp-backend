package com.andresguachisaca.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.andresguachisaca.model.Appointment;

@Transactional
public interface IAppointmentService extends ICrud<Appointment> {

	List<Appointment> getByDoctorAndDate(int idDoctor, LocalDateTime date);

	List<Appointment> getBySpecialtyAndDoctorAndDate(int idSpecialty, int idDoctor, LocalDateTime date);

	boolean hasNumberOfAppointmentsAllowed(int idPatient, LocalDateTime date);

	List<Appointment> getByPatientAndStartDateAndEndDate(int idPatient, LocalDateTime startDate, LocalDateTime endDate,
			int status);

	List<Appointment> getByDoctorAndStartDateAndEndDate(int idDoctor, LocalDateTime startDate, LocalDateTime endDate);

}
