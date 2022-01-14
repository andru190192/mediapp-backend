package com.andresguachisaca.dto;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class RegisterDTO {

	private String dni;

	private int dniType;

	private String name;

	private String surname;

	private String email;

	private String phone;

	private String address;

	private String city;

	private LocalDateTime birthDate;

	private String gender;

	private String username;

	private String password;

}
