package com.andresguachisaca.service.impl;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.andresguachisaca.dao.ISpecialityDao;
import com.andresguachisaca.model.Specialty;
import com.andresguachisaca.service.ISpecialtyService;

@Service
public class SpecialtyServiceImpl implements ISpecialtyService {

	@Autowired
	private ISpecialityDao specialityDao;

	@Override
	public Specialty save(Specialty specialty) {
		specialty.setStatus(true);
		specialty.setRegistrationDate(LocalDateTime.now());
		return specialityDao.save(specialty);
	}

	@Override
	public Specialty update(Specialty specialty) {
		specialty.setUpdateDate(LocalDateTime.now());
		return specialityDao.save(specialty);
	}

	@Override
	public void delete(int idSpecialty) {
		specialityDao.deleteById(idSpecialty);
	}

	@Override
	public Specialty getById(int idSpecialty) {
		return specialityDao.findById(idSpecialty).get();
	}

	@Override
	public List<Specialty> getList() {
		return specialityDao.findAll().stream().sorted(Comparator.comparing(Specialty::getId))
				.collect(Collectors.toList());

	}

}
