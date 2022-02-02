package com.andresguachisaca.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.andresguachisaca.model.Medicine;

@Repository
public interface IMedicineDao extends JpaRepository<Medicine, Integer> {

}
