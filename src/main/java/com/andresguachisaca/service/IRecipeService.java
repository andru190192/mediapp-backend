package com.andresguachisaca.service;

import java.util.List;

import com.andresguachisaca.model.Recipe;

public interface IRecipeService {

	List<Recipe> getByAppointment(int appointmentId);

}
