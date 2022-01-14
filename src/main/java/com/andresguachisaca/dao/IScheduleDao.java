package com.andresguachisaca.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.andresguachisaca.model.Schedule;

@Repository
public interface IScheduleDao extends JpaRepository<Schedule, Integer> {

}
