package com.andresguachisaca.dto;

import java.util.List;

import com.andresguachisaca.model.Functionality;

import lombok.Data;

@Data
public class LoginResponseDTO {

	private List<Functionality> functionalities;

	private String username;

}
