package com.andresguachisaca.service.impl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.andresguachisaca.dao.IDoctorDao;
import com.andresguachisaca.dao.IScheduleDao;
import com.andresguachisaca.model.Doctor;
import com.andresguachisaca.model.Schedule;
import com.andresguachisaca.service.IDoctorService;
import com.andresguachisaca.util.Utils;

@Service
public class DoctorServiceImpl implements IDoctorService {

	@Autowired
	private IDoctorDao doctorDao;

	@Autowired
	private IScheduleDao scheduleDao;

	@Override
	public Doctor save(Doctor doctor) {
		doctor.setStatus(true);
		doctor.setRegistrationDate(LocalDateTime.now());

		Doctor doctorBD = doctorDao.save(doctor);

		List<Schedule> scheduleList = new ArrayList<>();

		// se establece los 2 horarios por defecto para los medicos 9:00 - 12:00 y 16:00
		// - 18:00
		Schedule scheduleMorning = new Schedule();
		scheduleMorning.setDoctor(doctorBD);
		scheduleMorning.setStartHour(LocalDateTime.now().withHour(Utils.DEFAULT_MORNING_START_TIME));
		scheduleMorning.setEndHour(LocalDateTime.now().withHour(Utils.DEFAULT_MORNING_END_TIME));
		scheduleMorning.setStatus(true);
		scheduleMorning.setRegistrationDate(LocalDateTime.now());

		Schedule scheduleAfternoon = new Schedule();
		scheduleAfternoon.setDoctor(doctorBD);
		scheduleAfternoon.setStartHour(LocalDateTime.now().withHour(Utils.DEFAULT_AFTERNOON_START_TIME));
		scheduleAfternoon.setEndHour(LocalDateTime.now().withHour(Utils.DEFAULT_AFTERNOON_END_TIME));
		scheduleAfternoon.setStatus(true);
		scheduleAfternoon.setRegistrationDate(LocalDateTime.now());

		scheduleList.add(scheduleMorning);
		scheduleList.add(scheduleAfternoon);

		for (Schedule schedule : scheduleList) {
			scheduleDao.save(schedule);
		}

		return doctorBD;
	}

	@Override
	public Doctor update(Doctor doctor) {
		doctor.setUpdateDate(LocalDateTime.now());
		return doctorDao.save(doctor);
	}

	@Override
	public void delete(int idDoctor) {
		doctorDao.deleteById(idDoctor);
	}

	@Override
	public Doctor getById(int idDoctor) {
		return doctorDao.findById(idDoctor).get();
	}

	@Override
	public List<Doctor> getList() {
		return doctorDao.findAll().stream().sorted(Comparator.comparing(Doctor::getId)).collect(Collectors.toList());
	}

	@Override
	public List<Doctor> getDoctorsBySpecialty(Integer idSpecialty) {
		return doctorDao.getDoctorsBySpecialty(idSpecialty);
	}

	@Override
	public Doctor getByDni(String dni) {
		return doctorDao.getByDni(dni);
	}

}
