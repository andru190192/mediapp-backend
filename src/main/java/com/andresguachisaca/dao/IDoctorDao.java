package com.andresguachisaca.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.andresguachisaca.model.Doctor;

@Repository
public interface IDoctorDao extends JpaRepository<Doctor, Integer> {

}
