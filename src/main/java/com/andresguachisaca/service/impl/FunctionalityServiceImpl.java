package com.andresguachisaca.service.impl;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.andresguachisaca.dao.IFunctionalityDao;
import com.andresguachisaca.model.Functionality;
import com.andresguachisaca.service.IFunctionalityService;

@Service
public class FunctionalityServiceImpl implements IFunctionalityService {

	@Autowired
	private IFunctionalityDao functionalityDao;

	@Override
	public Functionality save(Functionality functionality) {
		functionality.setStatus(true);
		functionality.setRegistrationDate(LocalDateTime.now());
		return functionalityDao.save(functionality);
	}

	@Override
	public Functionality update(Functionality functionality) {
		functionality.setUpdateDate(LocalDateTime.now());
		return functionalityDao.save(functionality);
	}

	@Override
	public void delete(int idSpecialty) {
		functionalityDao.deleteById(idSpecialty);
	}

	@Override
	public Functionality getById(int idSpecialty) {
		return functionalityDao.findById(idSpecialty).get();
	}

	@Override
	public List<Functionality> getList() {
		return functionalityDao.findAll().stream().sorted(Comparator.comparing(Functionality::getId))
				.collect(Collectors.toList());
	}

	@Override
	public List<Functionality> getFunctionalitiesByUser(String username) {
		List<Functionality> functionalities = functionalityDao.getFunctionalitiesByUser(username);
		return functionalities;
	}

}
