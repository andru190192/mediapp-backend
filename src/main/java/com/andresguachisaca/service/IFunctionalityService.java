package com.andresguachisaca.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.andresguachisaca.model.Functionality;

@Transactional
public interface IFunctionalityService extends ICrud<Functionality> {

	List<Functionality> getFunctionalitiesByUser(String username);

}
