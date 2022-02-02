package com.andresguachisaca.dao;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.andresguachisaca.model.Appointment;

@Repository
public interface IAppointmentDao extends JpaRepository<Appointment, Integer> {

	@Query(value = "select * from appointment where id_doctor = :idDoctor and time_stamp >= now() and time_stamp <= :dateTime and status = 0", nativeQuery = true)
	List<Appointment> getByDoctorAndDate(@Param("idDoctor") int idDoctor, @Param("dateTime") LocalDateTime dateTime);

	@Query(value = "select app.* from appointment app " + "inner join doctor doc on doc.id_doctor = app.id_doctor"
			+ " where app.id_specialty = :idSpecialty "
			+ "and app.id_doctor = :idDoctor and app.status = 0 and app.time_stamp >= :dateTimeStart "
			+ "and app.time_stamp <= :dateTimeEnd order by app.time_stamp", nativeQuery = true)
	List<Appointment> getBySpecialtyAndDoctorAndDate(@Param("idSpecialty") int idSpecialty,
			@Param("idDoctor") int idDoctor, @Param("dateTimeStart") LocalDateTime dateTimeStart,
			@Param("dateTimeEnd") LocalDateTime dateTimeEnd);

	@Query(value = "SELECT * FROM appointment WHERE id_patient = :idPatient AND status = :status AND time_stamp >= :startDate AND time_stamp <= :endDate ORDER BY time_stamp", nativeQuery = true)
	List<Appointment> getByPatientAndStartDateAndEndDateAndStatus(@Param("idPatient") int idPatient,
			@Param("startDate") LocalDateTime startDate, @Param("endDate") LocalDateTime endDate, @Param("status") int status);
	
	@Query(value = "SELECT * FROM appointment WHERE id_doctor = :idDoctor AND status = 0 AND time_stamp >= :startDate AND time_stamp <= :endDate ORDER BY time_stamp", nativeQuery = true)
	List<Appointment> getByDoctorAndStartDateAndEndDate(@Param("idDoctor") int idPatient,
			@Param("startDate") LocalDateTime startDate, @Param("endDate") LocalDateTime endDate);

}
