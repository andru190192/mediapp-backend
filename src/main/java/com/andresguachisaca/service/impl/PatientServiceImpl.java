package com.andresguachisaca.service.impl;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.andresguachisaca.dao.IPatientDao;
import com.andresguachisaca.model.Patient;
import com.andresguachisaca.service.IPatientService;

@Service
public class PatientServiceImpl implements IPatientService {

	@Autowired
	private IPatientDao patientDao;

	@Override
	public Patient save(Patient patient) {
		patient.setStatus(true);
		patient.setRegistrationDate(LocalDateTime.now());
		return patientDao.save(patient);
	}

	@Override
	public Patient update(Patient patient) {
		patient.setUpdateDate(LocalDateTime.now());
		return patientDao.save(patient);
	}

	@Override
	public void delete(int idSpecialty) {
		patientDao.deleteById(idSpecialty);
	}

	@Override
	public Patient getById(int idSpecialty) {
		return patientDao.findById(idSpecialty).get();
	}

	@Override
	public List<Patient> getList() {
		System.out.println("entre getList");
		System.out.println("patientList sImpl" + patientDao.findAll());
		return patientDao.findAll().stream().sorted(Comparator.comparing(Patient::getId)).collect(Collectors.toList());
	}

	@Override
	public Patient getByDni(String dni) {
		return patientDao.getByDni(dni);
	}

}
