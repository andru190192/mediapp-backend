package com.andresguachisaca.service;

import java.util.List;

public interface ICrud<T> {

	T save(T t);

	T update(T t);

	void delete(int id);

	T getById(int id);

	List<T> getList();

}
