package com.andresguachisaca.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.andresguachisaca.model.Recipe;
import com.andresguachisaca.service.IRecipeService;

@RestController
@RequestMapping("/recipes")
public class RecipeController {

	@Autowired
	private IRecipeService recipeService;

	@GetMapping(value = "getByAppointment/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Recipe>> list(@PathVariable("id") Integer id) {
		List<Recipe> recipes = new ArrayList<>();
		try {
			recipes = recipeService.getByAppointment(id);
		} catch (Exception e) {
			return new ResponseEntity<List<Recipe>>(recipes, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<List<Recipe>>(recipes, HttpStatus.OK);
	}

}
