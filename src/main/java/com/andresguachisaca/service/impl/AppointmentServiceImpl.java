package com.andresguachisaca.service.impl;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.andresguachisaca.dao.IAppointmentDao;
import com.andresguachisaca.dao.IRecipeDao;
import com.andresguachisaca.enums.AppointmentStatus;
import com.andresguachisaca.model.Appointment;
import com.andresguachisaca.model.Medicine;
import com.andresguachisaca.service.IAppointmentService;
import com.andresguachisaca.util.Utils;

@Service
public class AppointmentServiceImpl implements IAppointmentService {

	@Autowired
	private IAppointmentDao appointmentDao;

	@Autowired
	private IRecipeDao recipeDao;

	@Override
	public Appointment save(Appointment appointment) {
		appointment.setTimestamp(appointment.getTimestamp().withSecond(0));
		appointment.setStatus(0); // estado agendado
		appointment.setRegistrationDate(LocalDateTime.now());
		return appointmentDao.save(appointment);
	}

	@Override
	public Appointment update(Appointment appointment) {
		try {
			// System.out.println("appointment " +
			// appointment.getRecipes().get(0).getId().getIdAppointment());
			// System.out.println("appointment2 " +
			// appointment.getRecipes().get(0).getAppointment());
			appointment.setUpdateDate(LocalDateTime.now());

			// List<Recipe> recipes = new ArrayList<Recipe>();

			if (appointment.getRecipes() != null) {

				appointment.getRecipes().forEach(recipe -> {
					// RecipePK recipePK = new RecipePK(recipe.getId().getIdAppointment(),
					// recipe.getId().getIdMedicine());
					// recipeDao.findById(recipePK).get();
					// recipes.add(recipe);
					Appointment _appointment = new Appointment();
					_appointment.setId(recipe.getId().getIdAppointment());

					Medicine medicine = new Medicine();
					medicine.setId(recipe.getId().getIdMedicine());

					recipe.setAppointment(_appointment);
					recipe.setMedicine(medicine);
					recipeDao.save(recipe);
				});
			}

			return appointmentDao.save(appointment);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Appointment();
	}

	@Override
	public void delete(int idAppointment) {
		appointmentDao.deleteById(idAppointment);
	}

	@Override
	public Appointment getById(int idAppointment) {
		return appointmentDao.findById(idAppointment).get();
	}

	@Override
	public List<Appointment> getList() {
		return appointmentDao.findAll().stream().sorted(Comparator.comparing(Appointment::getId))
				.collect(Collectors.toList());
	}

	@Override
	public List<Appointment> getByDoctorAndDate(int idDoctor, LocalDateTime date) {
		List<Appointment> appointments = appointmentDao.getByDoctorAndDate(idDoctor, date);
		return appointments;
	}

	@Override
	public List<Appointment> getBySpecialtyAndDoctorAndDate(int idSpecialty, int idDoctor, LocalDateTime date) {

		LocalDateTime dateEnd = date;
		dateEnd = dateEnd.withHour(23);
		dateEnd = dateEnd.withMinute(59);
		dateEnd = dateEnd.withSecond(59);

		date = date.withHour(0);
		date = date.withMinute(0);
		date = date.withSecond(0);

		System.out.println("dateStart impl " + date);
		System.out.println("dateEnd impl " + dateEnd);
		List<Appointment> appointments = null;
		try {
			appointments = appointmentDao.getBySpecialtyAndDoctorAndDate(idSpecialty, idDoctor, date, dateEnd);
			System.out.println("appointments impl" + appointments.size());
			return appointments;
		} catch (Exception e) {
			System.out.println(e);
		}
		return appointments;
	}

	@Override
	public boolean hasNumberOfAppointmentsAllowed(int idPatient, LocalDateTime date) {
		List<Appointment> appointments = appointmentDao.getByPatientAndStartDateAndEndDateAndStatus(idPatient,
				Utils.getDateWithStartTimeOfDay(date), Utils.getDateWithEndTimeOfDay(date),
				AppointmentStatus.AGENDADO.getStatus()); // estado agendado
		System.out.println(Utils.getDateWithStartTimeOfDay(date));
		System.out.println(Utils.getDateWithEndTimeOfDay(date));
		System.out.println("validacion de citas diarias appointments.size() - " + appointments.size());
		return appointments.size() < Utils.NUMBER_OF_DAILY_APPOINTMENTS_ALLOWED;
	}

	@Override
	public List<Appointment> getByPatientAndStartDateAndEndDate(int idPatient, LocalDateTime startDate,
			LocalDateTime endDate, int status) {
		return appointmentDao.getByPatientAndStartDateAndEndDateAndStatus(idPatient, startDate, endDate, status); // estado
																													// agendado
	}

	@Override
	public List<Appointment> getByDoctorAndStartDateAndEndDate(int idDoctor, LocalDateTime startDate,
			LocalDateTime endDate) {
		return appointmentDao.getByDoctorAndStartDateAndEndDate(idDoctor, startDate, endDate);
	}

}
