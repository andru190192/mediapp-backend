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
import com.andresguachisaca.model.Functionality;
import com.andresguachisaca.service.IFunctionalityService;

@RestController
@RequestMapping("/functionalities")
public class FunctionalityController {

	@Autowired
	private IFunctionalityService functionalityService;

	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Functionality>> list() {
		List<Functionality> functionalityList = new ArrayList<>();
		try {
			functionalityList = functionalityService.getList();
		} catch (Exception e) {
			return new ResponseEntity<List<Functionality>>(functionalityList, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<List<Functionality>>(functionalityList, HttpStatus.OK);
	}

	@GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public HttpEntity<Functionality> listById(@PathVariable("id") Integer id) {
		Functionality functionality = new Functionality();
		functionality = functionalityService.getById(id);
		if (functionality == null) {
			throw new ModelNotFoundException("ID: " + id);
		}

		return new ResponseEntity<Functionality>(functionality, HttpStatus.OK);
	}

	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public HttpEntity<Functionality> save(@RequestBody Functionality functionalityRequest) {
		Functionality functionality = new Functionality();
		try {
			System.out.println("functionalityRequest " + functionalityRequest.getId());
			functionality = functionalityService.save(functionalityRequest);
		} catch (Exception e) {
			return new ResponseEntity<Functionality>(functionality, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<Functionality>(functionality, HttpStatus.OK);
	}

	@PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public HttpEntity<Functionality> update(@RequestBody Functionality functionalityRequest) {
		Functionality functionality = null;
		try {
			functionality = functionalityService.getById(functionalityRequest.getId());
			if (functionality == null) {
				throw new ModelNotFoundException("ID: " + functionalityRequest.getId());
			} else {
				functionality = functionalityService.update(functionalityRequest);
			}
		} catch (Exception e) {
			return new ResponseEntity<Functionality>(functionality, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<Functionality>(functionality, HttpStatus.OK);
	}

	@DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public void delete(@PathVariable Integer id) {
		Functionality functionality = functionalityService.getById(id);
		if (functionality == null) {
			throw new ModelNotFoundException("ID: " + id);
		} else {
			functionalityService.delete(id);
		}
	}

}
