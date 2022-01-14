package com.andresguachisaca.service;

import org.springframework.transaction.annotation.Transactional;

import com.andresguachisaca.model.Schedule;

@Transactional
public interface IScheduleService extends ICrud<Schedule> {

}
