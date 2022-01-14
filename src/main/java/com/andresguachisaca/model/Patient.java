package com.andresguachisaca.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@ApiModel(description = "Información de pacientes")
@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "patient")
public class Patient {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_patient")
	private int id;

	@Column(nullable = false, unique = true, length = 13)
	private String dni;

	@Column(nullable = false, length = 3)
	private int dniType;

	@Column(nullable = false, length = 150)
	private String name;

	@Column(nullable = false, length = 150)
	private String surname;

	@Column(nullable = false, length = 50)
	private String email;

	@Column(nullable = false, length = 10)
	private String phone;

	@Column(nullable = false, length = 300)
	private String address;

	@Column(nullable = false, length = 50)
	private String city;

	@Column(nullable = false)
	@JsonSerialize(using = ToStringSerializer.class)
	private LocalDateTime birthDate;

	@Column(nullable = false, length = 3)
	private String gender;

	@Column(nullable = false)
	private boolean status;

	@Column(nullable = false)
	@JsonSerialize(using = ToStringSerializer.class)
	private LocalDateTime registrationDate;

	@JsonSerialize(using = ToStringSerializer.class)
	private LocalDateTime updateDate;

}
