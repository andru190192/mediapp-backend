package com.andresguachisaca.service.impl;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.andresguachisaca.dao.IScheduleDao;
import com.andresguachisaca.model.Schedule;
import com.andresguachisaca.service.IScheduleService;

@Service
public class ScheduleServiceImpl implements IScheduleService {

	@Autowired
	private IScheduleDao scheduleDao;

	@Override
	public Schedule save(Schedule schedule) {
		schedule.setStatus(true);
		schedule.setRegistrationDate(LocalDateTime.now());
		return scheduleDao.save(schedule);
	}

	@Override
	public Schedule update(Schedule schedule) {
		schedule.setUpdateDate(LocalDateTime.now());
		return scheduleDao.save(schedule);
	}

	@Override
	public void delete(int idSchedule) {
		scheduleDao.deleteById(idSchedule);
	}

	@Override
	public Schedule getById(int idSpecialty) {
		return scheduleDao.findById(idSpecialty).get();
	}

	@Override
	public List<Schedule> getList() {
		return scheduleDao.findAll().stream().sorted(Comparator.comparing(Schedule::getId))
				.collect(Collectors.toList());
	}

}
