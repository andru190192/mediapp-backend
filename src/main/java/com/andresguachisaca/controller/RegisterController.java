package com.andresguachisaca.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.andresguachisaca.dto.RegisterDTO;
import com.andresguachisaca.exception.ModelNotFoundException;
import com.andresguachisaca.service.IRegisterService;

@RestController
@RequestMapping("/register")
public class RegisterController {

	@Autowired
	private IRegisterService registerService;

	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public void save(@RequestBody RegisterDTO registerRequest) {
		try {
			registerService.save(registerRequest);
		} catch (Exception e) {
			throw new ModelNotFoundException("Error: " + e);
			// new ResponseEntity<RegisterDTO>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
