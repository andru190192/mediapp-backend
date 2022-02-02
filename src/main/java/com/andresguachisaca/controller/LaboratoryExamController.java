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
import com.andresguachisaca.model.LaboratoryExam;
import com.andresguachisaca.service.ILaboratoryExamService;

@RestController
@RequestMapping("/exams")
public class LaboratoryExamController {

	@Autowired
	private ILaboratoryExamService laboratoryExamService;

	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<LaboratoryExam>> list() {
		List<LaboratoryExam> exams = new ArrayList<>();
		try {
			exams = laboratoryExamService.getList();
		} catch (Exception e) {
			return new ResponseEntity<List<LaboratoryExam>>(exams, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<List<LaboratoryExam>>(exams, HttpStatus.OK);
	}

	@GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public HttpEntity<LaboratoryExam> listById(@PathVariable("id") Integer id) {
		LaboratoryExam exam = new LaboratoryExam();
		exam = laboratoryExamService.getById(id);
		if (exam == null) {
			throw new ModelNotFoundException("ID: " + id);
		}

		return new ResponseEntity<LaboratoryExam>(exam, HttpStatus.OK);
	}

	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public HttpEntity<LaboratoryExam> save(@RequestBody LaboratoryExam examRequest) {
		LaboratoryExam exam = new LaboratoryExam();
		try {
			System.out.println("examRequest " + examRequest.getId());
			exam = laboratoryExamService.save(examRequest);
		} catch (Exception e) {
			return new ResponseEntity<LaboratoryExam>(exam, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<LaboratoryExam>(exam, HttpStatus.OK);
	}

	@PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public HttpEntity<LaboratoryExam> update(@RequestBody LaboratoryExam examRequest) {
		LaboratoryExam exam = null;
		try {
			exam = laboratoryExamService.getById(examRequest.getId());
			if (exam == null) {
				throw new ModelNotFoundException("ID: " + examRequest.getId());
			} else {
				exam = laboratoryExamService.update(examRequest);
			}
		} catch (Exception e) {
			return new ResponseEntity<LaboratoryExam>(exam, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<LaboratoryExam>(exam, HttpStatus.OK);
	}

	@DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public void delete(@PathVariable Integer id) {
		LaboratoryExam exam = laboratoryExamService.getById(id);
		if (exam == null) {
			throw new ModelNotFoundException("ID: " + id);
		} else {
			laboratoryExamService.delete(id);
		}
	}

}
