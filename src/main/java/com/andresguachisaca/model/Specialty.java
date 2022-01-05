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
import lombok.Data;

@ApiModel(description = "Información de especialidades")
@Data
@Entity
@Table(name = "specialty")
public class Specialty {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_specialty")
	private int id;

	@Column(nullable = false, unique = true, length = 70)
	private String name;

	@Column(nullable = false, length = 150)
	private String description;

	@Column(nullable = false)
	private boolean status;

	@JsonSerialize(using = ToStringSerializer.class)
	private LocalDateTime registrationDate;

	@JsonSerialize(using = ToStringSerializer.class)
	private LocalDateTime updateDate;

}
