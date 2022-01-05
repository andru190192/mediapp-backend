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
import com.andresguachisaca.model.Doctor;
import com.andresguachisaca.service.IDoctorService;

@RestController
@RequestMapping("/doctors")
public class DoctorController {

	@Autowired
	private IDoctorService doctorService;

	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Doctor>> list() {
		List<Doctor> doctorList = new ArrayList<>();
		try {
			doctorList = doctorService.getList();
		} catch (Exception e) {
			return new ResponseEntity<List<Doctor>>(doctorList, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<List<Doctor>>(doctorList, HttpStatus.OK);
	}

	@GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public HttpEntity<Doctor> listById(@PathVariable("id") Integer id) {
		Doctor doctor = new Doctor();
		doctor = doctorService.getById(id);
		if (doctor == null) {
			throw new ModelNotFoundException("ID: " + id);
		}

		return new ResponseEntity<Doctor>(doctor, HttpStatus.OK);
	}

	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public HttpEntity<Doctor> save(@RequestBody Doctor doctorRequest) {
		Doctor doctor = new Doctor();
		try {
			System.out.println("doctorRequest " + doctorRequest.getId());
			doctor = doctorService.save(doctorRequest);
		} catch (Exception e) {
			return new ResponseEntity<Doctor>(doctor, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<Doctor>(doctor, HttpStatus.OK);
	}

	@PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public HttpEntity<Doctor> update(@RequestBody Doctor doctorRequest) {
		Doctor doctor = null;
		try {
			doctor = doctorService.getById(doctorRequest.getId());
			if (doctor == null) {
				throw new ModelNotFoundException("ID: " + doctorRequest.getId());
			} else {
				doctor = doctorService.update(doctorRequest);
			}
		} catch (Exception e) {
			return new ResponseEntity<Doctor>(doctor, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<Doctor>(doctor, HttpStatus.OK);
	}

	@DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public void delete(@PathVariable Integer id) {
		Doctor doctor = doctorService.getById(id);
		if (doctor == null) {
			throw new ModelNotFoundException("ID: " + id);
		} else {
			doctorService.delete(id);
		}
	}

}
