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

		Schedule scheduleMorning = new Schedule();
		scheduleMorning.setDoctor(doctorBD);
		scheduleMorning.setStartHour(LocalDateTime.now().withHour(9));
		scheduleMorning.setEndHour(LocalDateTime.now().withHour(9));
		scheduleMorning.setRegistrationDate(LocalDateTime.now());

		scheduleList.add(scheduleMorning);

		
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

}
