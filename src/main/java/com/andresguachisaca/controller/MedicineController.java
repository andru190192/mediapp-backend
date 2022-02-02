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
import com.andresguachisaca.model.Medicine;
import com.andresguachisaca.service.IMedicineService;

@RestController
@RequestMapping("/medicines")
public class MedicineController {

	@Autowired
	private IMedicineService medicineService;

	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Medicine>> list() {
		List<Medicine> medicines = new ArrayList<>();
		try {
			medicines = medicineService.getList();
		} catch (Exception e) {
			return new ResponseEntity<List<Medicine>>(medicines, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<List<Medicine>>(medicines, HttpStatus.OK);
	}

	@GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public HttpEntity<Medicine> listById(@PathVariable("id") Integer id) {
		Medicine medicine = new Medicine();
		medicine = medicineService.getById(id);
		if (medicine == null) {
			throw new ModelNotFoundException("ID: " + id);
		}

		return new ResponseEntity<Medicine>(medicine, HttpStatus.OK);
	}

	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public HttpEntity<Medicine> save(@RequestBody Medicine medicineRequest) {
		Medicine medicine = new Medicine();
		try {
			System.out.println("medicineRequest " + medicineRequest.getId());
			medicine = medicineService.save(medicineRequest);
		} catch (Exception e) {
			return new ResponseEntity<Medicine>(medicine, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<Medicine>(medicine, HttpStatus.OK);
	}

	@PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public HttpEntity<Medicine> update(@RequestBody Medicine medicineRequest) {
		Medicine medicine = null;
		try {
			medicine = medicineService.getById(medicineRequest.getId());
			if (medicine == null) {
				throw new ModelNotFoundException("ID: " + medicineRequest.getId());
			} else {
				medicine = medicineService.update(medicineRequest);
			}
		} catch (Exception e) {
			return new ResponseEntity<Medicine>(medicine, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<Medicine>(medicine, HttpStatus.OK);
	}

	@DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public void delete(@PathVariable Integer id) {
		Medicine medicine = medicineService.getById(id);
		if (medicine == null) {
			throw new ModelNotFoundException("ID: " + id);
		} else {
			medicineService.delete(id);
		}
	}

}
