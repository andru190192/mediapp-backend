package com.andresguachisaca.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.andresguachisaca.model.Recipe;
import com.andresguachisaca.model.RecipePK;

@Repository
public interface IRecipeDao extends JpaRepository<Recipe, RecipePK> {

	@Query(value = "select * from recipe where id_appointment = :idAppointment", nativeQuery = true)
	List<Recipe> getByAppointment(@Param("idAppointment") int idAppointment);

}
