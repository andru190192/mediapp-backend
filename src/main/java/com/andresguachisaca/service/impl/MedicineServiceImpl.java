package com.andresguachisaca.service.impl;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.andresguachisaca.dao.IMedicineDao;
import com.andresguachisaca.model.Medicine;
import com.andresguachisaca.service.IMedicineService;

@Service
public class MedicineServiceImpl implements IMedicineService {

	@Autowired
	private IMedicineDao medicineDao;

	@Override
	public Medicine save(Medicine medicine) {
		medicine.setStatus(true);
		medicine.setRegistrationDate(LocalDateTime.now());
		return medicineDao.save(medicine);
	}

	@Override
	public Medicine update(Medicine medicine) {
		medicine.setUpdateDate(LocalDateTime.now());
		return medicineDao.save(medicine);
	}

	@Override
	public void delete(int idMedicine) {
		medicineDao.deleteById(idMedicine);
	}

	@Override
	public Medicine getById(int idMedicine) {
		return medicineDao.findById(idMedicine).get();
	}

	@Override
	public List<Medicine> getList() {
		return medicineDao.findAll().stream().sorted(Comparator.comparing(Medicine::getId))
				.collect(Collectors.toList());
	}

}
