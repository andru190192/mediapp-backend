package com.andresguachisaca.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.andresguachisaca.dto.RateDTO;
import com.andresguachisaca.model.Doctor;

@Repository
public interface IDoctorDao extends JpaRepository<Doctor, Integer> {

	@Query(value = "select doc.* from doctor doc inner join specialty_doctor sd on sd.id_doctor = doc.id_doctor"
			+ " inner join specialty sp on sp.id_specialty = sd.id_specialty"
			+ " where sp.id_specialty = :idSpecialty and sp.status = true and doc.status = true", nativeQuery = true)
	List<Doctor> getDoctorsBySpecialty(@Param("idSpecialty") int idSpecialty);

	@Query(value = "select * from doctor where dni = :dni", nativeQuery = true)
	Doctor getByDni(@Param("dni") String dni);

	@Query(value = "select doc.id_doctor, doc.name, doc.surname, app.id_patient, app.registration_date, app.rate from doctor doc\n"
			+ "left join appointment app on app.id_doctor=doc.id_doctor\n"
			+ "order by doc.id_doctor", nativeQuery = true)
	List<RateDTO> getDoctorsWithRate();

}
