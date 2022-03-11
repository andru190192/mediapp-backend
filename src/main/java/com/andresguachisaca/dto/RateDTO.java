package com.andresguachisaca.dto;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RateDTO {

	@Id
	@Column(name = "id_doctor")
	private int id;

	private String name;

	private String surname;

	private int idPatient;

	private LocalDateTime registrationDate;

	private Integer rate;

}
