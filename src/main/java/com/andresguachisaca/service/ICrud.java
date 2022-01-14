package com.andresguachisaca.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface ICrud<T> {

	T save(T t);

	T update(T t);

	void delete(int id);

	T getById(int id);

	List<T> getList();

}
