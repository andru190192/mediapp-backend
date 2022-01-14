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
import com.andresguachisaca.model.Patient;
import com.andresguachisaca.service.IPatientService;

@RestController
@RequestMapping("/patients")
public class PatientController {

	@Autowired
	private IPatientService patientService;

	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Patient>> list() {
		List<Patient> patientList = new ArrayList<>();
		try {
			System.out.println("Entre controller");
			patientList = patientService.getList();
			System.out.println("patientList "+ patientList);
		} catch (Exception e) {
			return new ResponseEntity<List<Patient>>(patientList, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<List<Patient>>(patientList, HttpStatus.OK);
	}

	@GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public HttpEntity<Patient> listById(@PathVariable("id") Integer id) {
		Patient patient = Patient.builder().build();
		patient = patientService.getById(id);
		if (patient == null) {
			throw new ModelNotFoundException("ID: " + id);
		}

		return new ResponseEntity<Patient>(patient, HttpStatus.OK);
	}

	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public HttpEntity<Patient> save(@RequestBody Patient patientRequest) {
		Patient patient = Patient.builder().build();
		try {
			System.out.println("patientRequest " + patientRequest.getId());
			patient = patientService.save(patientRequest);
		} catch (Exception e) {
			return new ResponseEntity<Patient>(patient, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<Patient>(patient, HttpStatus.OK);
	}

	@PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public HttpEntity<Patient> update(@RequestBody Patient patientRequest) {
		Patient patient = null;
		try {
			patient = patientService.getById(patientRequest.getId());
			if (patient == null) {
				throw new ModelNotFoundException("ID: " + patientRequest.getId());
			} else {
				patient = patientService.update(patientRequest);
			}
		} catch (Exception e) {
			return new ResponseEntity<Patient>(patient, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<Patient>(patient, HttpStatus.OK);
	}

	@DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public void delete(@PathVariable Integer id) {
		Patient patient = patientService.getById(id);
		if (patient == null) {
			throw new ModelNotFoundException("ID: " + id);
		} else {
			patientService.delete(id);
		}
	}

}
