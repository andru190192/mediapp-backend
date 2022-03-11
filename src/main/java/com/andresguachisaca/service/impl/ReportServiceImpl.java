package com.andresguachisaca.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.andresguachisaca.dao.IDoctorDao;
import com.andresguachisaca.dao.IRateDao;
import com.andresguachisaca.dto.RateDTO;
import com.andresguachisaca.service.IReportService;

@Service
public class ReportServiceImpl implements IReportService {

	@Autowired
	private IRateDao rateDao;

	@Autowired
	private IDoctorDao doctorDao;

	@Override
	public List<RateDTO> getRateReport() {
		List<RateDTO> doctors = doctorDao.getDoctorsWithRate();
		doctors.forEach(e -> {
			System.out.println("doctor " + e.getId());
		});
		return doctors;
	}

}
