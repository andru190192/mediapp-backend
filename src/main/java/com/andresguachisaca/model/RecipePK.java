package com.andresguachisaca.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Embeddable
public class RecipePK implements Serializable {

	private static final long serialVersionUID = 1L;

	@Column(name = "id_appointment")
	private int idAppointment;

	@Column(name = "id_medicine")
	private int idMedicine;

}
