package com.andresguachisaca.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.andresguachisaca.model.Schedule;

@Repository
public interface IScheduleDao extends JpaRepository<Schedule, Integer> {

	@Query(value = "select * from schedule where id_doctor = :idDoctor and status = true order by registration_date", nativeQuery = true)
	List<Schedule> getByDoctor(@Param("idDoctor") int idDoctor);

}
