package com.andresguachisaca.service;

import org.springframework.transaction.annotation.Transactional;

import com.andresguachisaca.dto.RegisterDTO;

@Transactional
public interface IRegisterService {

	void save(RegisterDTO registerDTO);

}
