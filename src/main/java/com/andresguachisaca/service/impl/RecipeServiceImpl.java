package com.andresguachisaca.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.andresguachisaca.dao.IRecipeDao;
import com.andresguachisaca.model.Recipe;
import com.andresguachisaca.service.IRecipeService;

@Service
public class RecipeServiceImpl implements IRecipeService {

	@Autowired
	private IRecipeDao recipeDao;

	@Override
	public List<Recipe> getByAppointment(int appointmentId) {
		return recipeDao.getByAppointment(appointmentId);
	}

}
