package com.andresguachisaca.model;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "recipe")
public class Recipe {

	@EmbeddedId
	private RecipePK id;

	@ManyToOne
	@MapsId("idAppointment")
	@JoinColumn(name = "id_appointment")
	@JsonIgnoreProperties
	private Appointment appointment;

	@ManyToOne
	@MapsId("idMedicine")
	@JoinColumn(name = "id_medicine")
	@JsonIgnoreProperties
	private Medicine medicine;

	@Column(nullable = false)
	private int amount;

	private String prescription;

}
