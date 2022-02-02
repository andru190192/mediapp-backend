package com.andresguachisaca.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.andresguachisaca.model.Schedule;

@Transactional
public interface IScheduleService extends ICrud<Schedule> {

	List<Schedule> getByDoctor(int idDoctor);

}
