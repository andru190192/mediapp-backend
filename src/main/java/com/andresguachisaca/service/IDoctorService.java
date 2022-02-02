package com.andresguachisaca.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.andresguachisaca.model.Doctor;

@Transactional
public interface IDoctorService extends ICrud<Doctor> {

	List<Doctor> getDoctorsBySpecialty(Integer idSpecialty);

	Doctor getByDni(String dni);

}
