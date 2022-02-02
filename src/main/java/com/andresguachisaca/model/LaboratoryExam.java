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
@ApiModel(description = "Información Examenes de Laboratorio")
@Data
@Table(name = "laboratory_exam")
public class LaboratoryExam {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_laboratory_exam")
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
