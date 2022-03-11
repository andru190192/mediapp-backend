package com.andresguachisaca.controller;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.andresguachisaca.dto.AppointmentRequestDTO;
import com.andresguachisaca.dto.ErrorDTO;
import com.andresguachisaca.dto.ResponseDTO;
import com.andresguachisaca.exception.ModelNotFoundException;
import com.andresguachisaca.model.Appointment;
import com.andresguachisaca.model.Doctor;
import com.andresguachisaca.model.Patient;
import com.andresguachisaca.model.Schedule;
import com.andresguachisaca.model.Specialty;
import com.andresguachisaca.service.IAppointmentService;
import com.andresguachisaca.service.IDoctorService;
import com.andresguachisaca.service.IScheduleService;
import com.andresguachisaca.util.Utils;

@RestController
@RequestMapping("/appointments")
public class AppointmentController {

	@Autowired
	private IAppointmentService appointmentService;

	@Autowired
	private IScheduleService scheduleService;

	@Autowired
	private IDoctorService doctorService;

	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Appointment>> list() {
		List<Appointment> appointmentList = new ArrayList<>();
		try {
			appointmentList = appointmentService.getList();
		} catch (Exception e) {
			return new ResponseEntity<List<Appointment>>(appointmentList, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<List<Appointment>>(appointmentList, HttpStatus.OK);
	}

	@GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public HttpEntity<Appointment> listById(@PathVariable("id") Integer id) {
		Appointment appointment = new Appointment();
		appointment = appointmentService.getById(id);
		if (appointment == null) {
			throw new ModelNotFoundException("ID: " + id);
		}

		return new ResponseEntity<Appointment>(appointment, HttpStatus.OK);
	}

	@PostMapping(value = "/getByPatientAndDateAndStatus", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Appointment>> getByPatientAndDateAndStatus(@RequestBody Appointment appointmentRequest) {
		List<Appointment> appointments = appointmentService.getByPatientAndStartDateAndEndDate(
				appointmentRequest.getPatient().getId(), appointmentRequest.getTimestamp(),
				Utils.getDateWithEndTimeOfDay(appointmentRequest.getTimestamp()), appointmentRequest.getStatus());
		return new ResponseEntity<List<Appointment>>(appointments, HttpStatus.OK);
	}

	@PostMapping(value = "/getByDoctorAndDate", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Appointment>> getByDoctorAndDate(@RequestBody Appointment appointmentRequest) {
		List<Appointment> appointments = appointmentService.getByDoctorAndStartDateAndEndDate(
				appointmentRequest.getDoctor().getId(), appointmentRequest.getTimestamp(),
				Utils.getDateWithEndTimeOfDay(appointmentRequest.getTimestamp()));
		return new ResponseEntity<List<Appointment>>(appointments, HttpStatus.OK);
	}

	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResponseDTO> save(@RequestBody Appointment appointmentRequest) {

		ResponseDTO response = new ResponseDTO();
		Appointment appointment = new Appointment();

		System.out.println("request " + appointmentRequest.getDoctor().getId());
		System.out.println("request " + appointmentRequest.getReason());

		try {
			// solo se permite guardar la cita si el paciente tiene menos de 2 citas
			// agendadas para ese día
			if (appointmentService.hasNumberOfAppointmentsAllowed(appointmentRequest.getPatient().getId(),
					appointmentRequest.getTimestamp())) {

				appointment = appointmentService.save(appointmentRequest);

				Map<String, Object> data = new HashMap<String, Object>();
				data.put("appointment", appointment);
				response.setData(data);
				return new ResponseEntity<ResponseDTO>(response, HttpStatus.OK);
			} else {
				ErrorDTO error = new ErrorDTO("MA-2", "Error",
						"No se puede agendar mas de " + Utils.NUMBER_OF_DAILY_APPOINTMENTS_ALLOWED + " citas por día");
				response.setError(error);
				return new ResponseEntity<ResponseDTO>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			}
		} catch (Exception e) {
			ErrorDTO error = new ErrorDTO("MA-1", "Error", "Ocurrio un error");
			response.setError(error);
			return new ResponseEntity<ResponseDTO>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public void delete(@PathVariable Integer id) {
		Appointment appointment = appointmentService.getById(id);
		if (appointment == null) {
			throw new ModelNotFoundException("ID: " + id);
		} else {
			appointmentService.delete(id);
		}
	}

	@PostMapping(value = "/validateSchedules", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResponseDTO> validateSchedules(@RequestBody AppointmentRequestDTO request) {

		LocalDateTime correctDate = request.getTimestamp();
		correctDate.withHour(0);
		correctDate.withMinute(0);

		ResponseDTO response = new ResponseDTO();

		try {

			List<Appointment> appointments = new ArrayList<Appointment>();
			List<Appointment> appointmentsByDoctor = new ArrayList<Appointment>();
			List<Doctor> doctors = doctorService.getDoctorsBySpecialty(request.getIdSpecialty());

			for (Doctor doctor2 : doctors) {
				System.out.println(doctor2.getId() + " - " + doctor2.getName() + " " + doctor2.getSurname());
			}

			doctors.forEach(doctor -> {
				List<Appointment> appointmentsBD = appointmentService
						.getBySpecialtyAndDoctorAndDate(request.getIdSpecialty(), doctor.getId(), correctDate);

				System.out.println(doctor.getId() + " - appointmentsBD.size() " + appointmentsBD.size());

				appointmentsBD.forEach(appointment -> {
					appointmentsByDoctor.add(appointment);
				});
			});

			doctors.forEach(doctor -> {

				List<Schedule> schedules = scheduleService.getByDoctor(doctor.getId());

				schedules.forEach(schedule -> {

					int count = 0;
					LocalDateTime maxTimestamp;
					Appointment appointment;

					do {

						Duration durationTaken = Duration.of(count, ChronoUnit.MINUTES);

						appointment = new Appointment();
						Patient patient = new Patient();
						Specialty speciality = new Specialty();

						patient.setId(request.getIdPatient());
						speciality.setId(request.getIdSpecialty());

						LocalDateTime timestamp = correctDate;
						timestamp = timestamp.withHour(schedule.getStartHour().getHour());
						timestamp = timestamp.withMinute(schedule.getStartHour().getMinute());

						maxTimestamp = correctDate;
						maxTimestamp = maxTimestamp.withHour(schedule.getEndHour().getHour());
						maxTimestamp = maxTimestamp.withMinute(schedule.getEndHour().getMinute());

						appointment.setDoctor(doctor);
						appointment.setSpecialty(speciality);
						appointment.setPatient(patient);
						appointment.setDuration(30);
						appointment.setRegistrationDate(LocalDateTime.now());
						appointment.setStatus(0);
						appointment.setTimestamp(timestamp.plus(durationTaken));

						// System.out.println("appointment timestamp " + appointment.getTimestamp());

						appointments.add(appointment);

						count = count + 30;

					} while (maxTimestamp.compareTo(appointment.getTimestamp()) != 0);

				});
			});

			System.out.println("appointments size" + appointments.size());
			System.out.println("appointmentsByDoctor size" + appointmentsByDoctor.size());

			// predicado para filtrar las citas repetidas de la base de datos
			Predicate<Appointment> predicateToFilter = appointment -> appointmentsByDoctor.stream().noneMatch(
					appointmentBD -> (appointment.getTimestamp().getHour() == appointmentBD.getTimestamp().getHour())
							&& (appointment.getTimestamp().getMinute() == appointmentBD.getTimestamp().getMinute())
							&& appointment.getDoctor().getId() == appointmentBD.getDoctor().getId());

			List<Appointment> appointmentsFiltered = appointments.stream().filter(predicateToFilter)
					.collect(Collectors.toList());

			appointmentsFiltered.forEach(appointment -> {
				System.out.println(appointment.getDoctor().getId() + " - " + appointment.getTimestamp());
			});

			// se quita las citas menores a la hora actual
			List<Appointment> appointmentsToday = appointmentsFiltered.stream()
					.filter(appointment -> appointment.getTimestamp().isAfter(LocalDateTime.now()))
					.collect(Collectors.toList());

			Map<String, Object> data = new HashMap<String, Object>();
			data.put("appointments", appointmentsToday);

			response.setData(data);
			response.setTimestamp(LocalDateTime.now());

		} catch (Exception e) {
			ErrorDTO error = new ErrorDTO("MA-1", "Error", "Ocurrio un error");
			response.setError(error);
			return new ResponseEntity<ResponseDTO>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return new ResponseEntity<ResponseDTO>(response, HttpStatus.OK);

	}

	@PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public HttpEntity<Appointment> update(@RequestBody Appointment appointmentRequest) {
		Appointment appointment = null;
		try {
			appointment = appointmentService.getById(appointmentRequest.getId());
			// System.out.println("appointment " +
			// appointmentRequest.getRecipes().get(0).getId().getIdAppointment());
			// System.out.println("appointment2 " +
			// appointmentRequest.getRecipes().get(0).getMedicine().getId());
			if (appointment == null) {
				throw new ModelNotFoundException("ID: " + appointmentRequest.getId());
			} else {
				appointment = appointmentService.update(appointmentRequest);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Appointment>(appointment, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<Appointment>(appointment, HttpStatus.OK);
	}

	@PostMapping(value = "/getByPatientAndStatus", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Appointment>> getByPatientAndStatus(@RequestBody Appointment appointmentRequest) {
		List<Appointment> appointments = appointmentService
				.getByPatientAndStatus(appointmentRequest.getPatient().getId(), appointmentRequest.getStatus());
		return new ResponseEntity<List<Appointment>>(appointments, HttpStatus.OK);
	}

}
