package com.andresguachisaca.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.andresguachisaca.model.Functionality;

@Repository
public interface IFunctionalityDao extends JpaRepository<Functionality, Integer> {

	@Query(value = "select f.* from functionality_rol fr"
			+ " inner join user_rol ur on ur.id_rol = fr.id_rol inner join functionality f on f.id_functionality = fr.id_functionality inner join users u on u.id_user = ur.id_user "
			+ "where u.username = :username", nativeQuery = true)
	List<Functionality> getFunctionalitiesByUser(@Param("username") String username);

}
