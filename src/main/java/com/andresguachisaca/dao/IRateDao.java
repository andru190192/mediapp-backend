package com.andresguachisaca.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.andresguachisaca.dto.RateDTO;

@Repository
public interface IRateDao extends JpaRepository<RateDTO, Integer> {

	@Query(value = "select doc.id_doctor, doc.name, doc.surname, app.id_patient, app.registration_date, app.rate from doctor doc\n"
			+ "left join appointment app on app.id_doctor=doc.id_doctor\n"
			+ "order by doc.id_doctor", nativeQuery = true)
	List<RateDTO> getDoctorsWithRate();

}
