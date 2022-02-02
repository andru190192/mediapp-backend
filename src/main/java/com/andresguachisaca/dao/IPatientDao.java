package com.andresguachisaca.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.andresguachisaca.model.Patient;

@Repository
public interface IPatientDao extends JpaRepository<Patient, Integer> {

	@Query(value = "select * from patient where dni = :dni", nativeQuery = true)
	Patient getByDni(@Param("dni") String dni);

}
