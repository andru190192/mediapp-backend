package com.andresguachisaca.service.impl;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.andresguachisaca.dao.IUserDao;
import com.andresguachisaca.model.User;
import com.andresguachisaca.service.IUserService;

@Service
public class UserServiceImpl implements IUserService {

	@Autowired
	private IUserDao userDao;

	@Autowired
	private BCryptPasswordEncoder bcrypt;

	@Override
	public User save(User user) {
		user.setPassword(bcrypt.encode(user.getPassword()));
		user.setStatus(true);
		user.setRegistrationDate(LocalDateTime.now());
		return userDao.save(user);
	}

	@Override
	public User update(User patient) {
		patient.setUpdateDate(LocalDateTime.now());
		return userDao.save(patient);
	}

	@Override
	public void delete(int idSpecialty) {
		userDao.deleteById(idSpecialty);
	}

	@Override
	public User getById(int idSpecialty) {
		return userDao.findById(idSpecialty).get();
	}

	@Override
	public List<User> getList() {
		return userDao.findAll().stream().sorted(Comparator.comparing(User::getId)).collect(Collectors.toList());
	}

	@Override
	public User getByUserName(String username) {
		return userDao.getByUserName(username);
	}

}
