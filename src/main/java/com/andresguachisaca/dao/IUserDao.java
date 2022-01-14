package com.andresguachisaca.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.andresguachisaca.model.User;

@Repository
public interface IUserDao extends JpaRepository<User, Integer> {

	@Query(value = "select * from users where username = :username", nativeQuery = true)
	User getByUserName(@Param("username") String username);

}
