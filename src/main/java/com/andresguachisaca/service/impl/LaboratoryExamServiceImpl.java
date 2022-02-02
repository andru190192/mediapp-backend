package com.andresguachisaca.service.impl;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.andresguachisaca.dao.ILaboratoryExamDao;
import com.andresguachisaca.model.LaboratoryExam;
import com.andresguachisaca.service.ILaboratoryExamService;

@Service
public class LaboratoryExamServiceImpl implements ILaboratoryExamService {

	@Autowired
	private ILaboratoryExamDao laboratoryExamDao;

	@Override
	public LaboratoryExam save(LaboratoryExam laboratoryExam) {
		laboratoryExam.setStatus(true);
		laboratoryExam.setRegistrationDate(LocalDateTime.now());
		return laboratoryExamDao.save(laboratoryExam);
	}

	@Override
	public LaboratoryExam update(LaboratoryExam laboratoryExam) {
		laboratoryExam.setUpdateDate(LocalDateTime.now());
		return laboratoryExamDao.save(laboratoryExam);
	}

	@Override
	public void delete(int idLaboratoryExam) {
		laboratoryExamDao.deleteById(idLaboratoryExam);
	}

	@Override
	public LaboratoryExam getById(int idLaboratoryExam) {
		return laboratoryExamDao.findById(idLaboratoryExam).get();
	}

	@Override
	public List<LaboratoryExam> getList() {
		return laboratoryExamDao.findAll().stream().sorted(Comparator.comparing(LaboratoryExam::getId))
				.collect(Collectors.toList());
	}

}
