package com.andresguachisaca.service.impl;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.andresguachisaca.dao.IDoctorDao;
import com.andresguachisaca.model.Doctor;
import com.andresguachisaca.service.IDoctorService;

@Service
public class DoctorServiceImpl implements IDoctorService {

	@Autowired
	private IDoctorDao doctorDao;

	@Override
	public Doctor save(Doctor specialty) {
		specialty.setStatus(true);
		specialty.setRegistrationDate(LocalDateTime.now());
		return doctorDao.save(specialty);
	}

	@Override
	public Doctor update(Doctor specialty) {
		specialty.setUpdateDate(LocalDateTime.now());
		return doctorDao.save(specialty);
	}

	@Override
	public void delete(int idSpecialty) {
		doctorDao.deleteById(idSpecialty);
	}

	@Override
	public Doctor getById(int idSpecialty) {
		return doctorDao.findById(idSpecialty).get();
	}

	@Override
	public List<Doctor> getList() {
		return doctorDao.findAll().stream().sorted(Comparator.comparing(Doctor::getId)).collect(Collectors.toList());

	}

}
