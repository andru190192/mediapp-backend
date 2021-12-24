package com.andresguachisaca.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.andresguachisaca.model.Specialty; 

@Repository
public interface ISpecialityDao extends JpaRepository<Specialty, Integer> {

}
