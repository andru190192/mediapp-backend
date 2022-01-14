package com.andresguachisaca.service;

import org.springframework.transaction.annotation.Transactional;

import com.andresguachisaca.model.Patient;

@Transactional
public interface IPatientService extends ICrud<Patient> {

}
