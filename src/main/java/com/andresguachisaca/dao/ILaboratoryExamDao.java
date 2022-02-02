package com.andresguachisaca.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.andresguachisaca.model.LaboratoryExam;

@Repository
public interface ILaboratoryExamDao extends JpaRepository<LaboratoryExam, Integer> {

}
