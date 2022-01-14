package com.andresguachisaca.service;

import org.springframework.transaction.annotation.Transactional;

import com.andresguachisaca.model.Doctor;

@Transactional
public interface IDoctorService extends ICrud<Doctor> {

}
