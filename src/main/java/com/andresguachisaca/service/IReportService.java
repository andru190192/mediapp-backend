package com.andresguachisaca.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.andresguachisaca.dto.RateDTO;

@Transactional
public interface IReportService {

	List<RateDTO> getRateReport();

}
