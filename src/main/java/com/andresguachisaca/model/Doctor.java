package com.andresguachisaca.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;

@ApiModel(description = "Información de doctores")
@Getter
@Setter
@Entity
@Table(name = "doctor")
public class Doctor {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idDoctor;

	@Column(name = "dni", nullable = false, length = 15)
	private String dni;

	@Column(nullable = false, length = 3)
	private int dniType;

	@Column(name = "name", nullable = false, length = 70)
	private String name;

	@Column(name = "surname", nullable = false, length = 70)
	private String surname;

}
