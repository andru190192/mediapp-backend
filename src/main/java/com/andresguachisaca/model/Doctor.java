package com.andresguachisaca.model;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;

import io.swagger.annotations.ApiModel;
import lombok.Data;

@ApiModel(description = "Información de doctores")
@Data
@Entity
@Table(name = "doctor")
public class Doctor {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_doctor")
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

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "specialty_doctor", joinColumns = @JoinColumn(name = "id_doctor"), inverseJoinColumns = @JoinColumn(name = "id_specialty"), foreignKey = @ForeignKey(name = "doctor_fk"), inverseForeignKey = @ForeignKey(name = "specialty_fk"))
	private List<Specialty> specialties;

	@Column(nullable = false)
	private boolean status;

	@Column(nullable = false)
	@JsonSerialize(using = ToStringSerializer.class)
	private LocalDateTime registrationDate;

	@JsonSerialize(using = ToStringSerializer.class)
	private LocalDateTime updateDate;

}
