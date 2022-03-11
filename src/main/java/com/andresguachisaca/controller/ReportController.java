package com.andresguachisaca.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.andresguachisaca.dto.RateDTO;
import com.andresguachisaca.service.IReportService;

@RestController
@RequestMapping("/reports")
public class ReportController {

	@Autowired
	private IReportService reportService;

	@GetMapping(value = "/rate/doctos", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<RateDTO>> list() {
		List<RateDTO> patientList = new ArrayList<>();
		try {
			System.out.println("Entre controller");
			patientList = reportService.getRateReport();
			System.out.println("patientList " + patientList);
		} catch (Exception e) {
			return new ResponseEntity<List<RateDTO>>(patientList, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<List<RateDTO>>(patientList, HttpStatus.OK);
	}

}
