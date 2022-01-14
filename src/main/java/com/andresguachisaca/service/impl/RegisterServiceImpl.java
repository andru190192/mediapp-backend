package com.andresguachisaca.service.impl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.andresguachisaca.dao.IPatientDao;
import com.andresguachisaca.dao.IUserDao;
import com.andresguachisaca.dto.RegisterDTO;
import com.andresguachisaca.model.Patient;
import com.andresguachisaca.model.Rol;
import com.andresguachisaca.model.User;
import com.andresguachisaca.service.IRegisterService;

@Service
public class RegisterServiceImpl implements IRegisterService {

	@Autowired
	private IPatientDao patientDao;

	@Autowired
	private IUserDao userDao;

	@Autowired
	private BCryptPasswordEncoder bcrypt;

	@Override
	public void save(RegisterDTO registerDTO) {

		// mapper DTO to Patient Entity
		Patient patient = Patient.builder().address(registerDTO.getAddress()).birthDate(registerDTO.getBirthDate())
				.city(registerDTO.getCity()).dni(registerDTO.getDni()).dniType(registerDTO.getDniType())
				.email(registerDTO.getEmail()).gender(registerDTO.getGender()).name(registerDTO.getName())
				.surname(registerDTO.getSurname()).phone(registerDTO.getPhone()).status(true)
				.registrationDate(LocalDateTime.now()).build();

		// save patient
		patientDao.save(patient);

		// assign patient role
		List<Rol> roles = new ArrayList<Rol>();
		roles.add(Rol.builder().id(1).build());

		// mapper DTO to User Entity
		User user = User.builder().username(registerDTO.getUsername())
				.password(bcrypt.encode(registerDTO.getPassword())).registrationDate(LocalDateTime.now()).roles(roles)
				.status(true).build();

		// save user
		userDao.save(user);
	}

}
