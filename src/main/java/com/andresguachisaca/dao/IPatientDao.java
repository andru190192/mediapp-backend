package com.andresguachisaca.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.andresguachisaca.model.Patient;

@Repository
public interface IPatientDao extends JpaRepository<Patient, Integer> {

}
