package com.andresguachisaca.controller;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.andresguachisaca.dto.ErrorDTO;
import com.andresguachisaca.dto.ResponseDTO;
import com.andresguachisaca.model.Functionality;
import com.andresguachisaca.model.User;
import com.andresguachisaca.service.IFunctionalityService;
import com.andresguachisaca.service.IUserService;

@RestController
@RequestMapping("/login")
public class LoginController {

	@Autowired
	private IFunctionalityService functionalityService;

	@Autowired
	private IUserService userDao;

	@Autowired
	private BCryptPasswordEncoder bcrypt;

	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResponseDTO> loginByUsernameAndPassword(@RequestBody Map<String, Object> request) {

		ErrorDTO error = new ErrorDTO("MA-1", "Error", "El usuario o la contraseña son incorrectos");
		ResponseDTO response = new ResponseDTO();
		response.setTimestamp(LocalDateTime.now());
		;

		try {

			String username = request.get("username").toString();
			String password = request.get("password").toString();

			User user = userDao.getByUserName(username);

			if (user == null || !bcrypt.matches(password, user.getPassword())) {
				response.setError(error);
				return new ResponseEntity<ResponseDTO>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			}

			List<Functionality> functionalities = functionalityService.getFunctionalitiesByUser(username);

			Map<String, Object> data = new HashMap<String, Object>();
			data.put("functionalities", functionalities);
			data.put("username", user.getUsername());

			response.setTitle("Exito");
			response.setMessage("Login exitoso");
			response.setData(data);

		} catch (Exception e) {
			response.setError(error);
			return new ResponseEntity<ResponseDTO>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<ResponseDTO>(response, HttpStatus.OK);
	}

}
