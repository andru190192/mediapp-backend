package com.andresguachisaca.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.andresguachisaca.exception.ModelNotFoundException;
import com.andresguachisaca.model.User;
import com.andresguachisaca.service.IUserService;

@RestController
@RequestMapping("/users")
public class UserController {

	@Autowired
	private IUserService userService;

	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<User>> list() {
		List<User> userList = new ArrayList<>();
		try {
			userList = userService.getList();
		} catch (Exception e) {
			return new ResponseEntity<List<User>>(userList, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<List<User>>(userList, HttpStatus.OK);
	}

	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public HttpEntity<User> save(@RequestBody User userRequest) {
		User user = new User();
		try {
			System.out.println("userRequest " + userRequest.getId());
			user = userService.save(userRequest);
		} catch (Exception e) {
			return new ResponseEntity<User>(user, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<User>(user, HttpStatus.OK);
	}

	@PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public HttpEntity<User> update(@RequestBody User userRequest) {
		User user = null;
		try {
			user = userService.getById(userRequest.getId());
			if (user == null) {
				throw new ModelNotFoundException("ID: " + userRequest.getId());
			} else {
				user = userService.update(userRequest);
			}
		} catch (Exception e) {
			return new ResponseEntity<User>(user, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<User>(user, HttpStatus.OK);
	}

	@DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public void delete(@PathVariable Integer id) {
		User user = userService.getById(id);
		if (user == null) {
			throw new ModelNotFoundException("ID: " + id);
		} else {
			userService.delete(id);
		}
	}

}
