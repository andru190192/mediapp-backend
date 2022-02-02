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

@Entity
@ApiModel(description = "Información de Medicinas")
@Data
@Table(name = "medicine")
public class Medicine {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_medicine")
	private int id;

	@Column(nullable = false, unique = true, length = 150)
	private String name;

	@Column(nullable = false)
	private boolean status;

	@Column(nullable = false)
	@JsonSerialize(using = ToStringSerializer.class)
	private LocalDateTime registrationDate;

	@JsonSerialize(using = ToStringSerializer.class)
	private LocalDateTime updateDate;

}
