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
import com.andresguachisaca.model.Specialty;
import com.andresguachisaca.service.ISpecialtyService;

@RestController
@RequestMapping("/specialties")
public class SpecialityController {

	@Autowired
	private ISpecialtyService specialtyService;

	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Specialty>> list() {
		List<Specialty> specialtyList = new ArrayList<>();
		try {
			specialtyList = specialtyService.getList();
		} catch (Exception e) {
			return new ResponseEntity<List<Specialty>>(specialtyList, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<List<Specialty>>(specialtyList, HttpStatus.OK);
	}

	@GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public HttpEntity<Specialty> listById(@PathVariable("id") Integer id) {
		Specialty specialty = new Specialty();
		specialty = specialtyService.getById(id);
		if (specialty == null) {
			throw new ModelNotFoundException("ID: " + id);
		}

		return new ResponseEntity<Specialty>(specialty, HttpStatus.OK);
	}

	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public HttpEntity<Specialty> save(@RequestBody Specialty specialtyRequest) {
		Specialty specialty = new Specialty();
		try {
			System.out.println("specialtyRequest " + specialtyRequest.getId());
			specialty = specialtyService.save(specialtyRequest);
		} catch (Exception e) {
			return new ResponseEntity<Specialty>(specialty, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<Specialty>(specialty, HttpStatus.OK);
	}

	@PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public HttpEntity<Specialty> update(@RequestBody Specialty specialtyRequest) {
		Specialty specialty = null;
		try {
			specialty = specialtyService.getById(specialtyRequest.getId());
			if (specialty == null) {
				throw new ModelNotFoundException("ID: " + specialtyRequest.getId());
			} else {
				specialty = specialtyService.update(specialtyRequest);
			}
		} catch (Exception e) {
			return new ResponseEntity<Specialty>(specialty, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<Specialty>(specialty, HttpStatus.OK);
	}

	@DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public void delete(@PathVariable Integer id) {
		Specialty specialty = specialtyService.getById(id);
		if (specialty == null) {
			throw new ModelNotFoundException("ID: " + id);
		} else {
			specialtyService.delete(id);
		}
	}

}
