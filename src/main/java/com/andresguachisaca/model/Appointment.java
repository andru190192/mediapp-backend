package com.andresguachisaca.model;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;

import io.swagger.annotations.ApiModel;
import lombok.Data;

@ApiModel(description = "Información de citas médicas")
@Data
@Entity
@Table(name = "appointment")
public class Appointment {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_appointment")
	private int id;

	@ManyToOne
	@JoinColumn(name = "id_patient", nullable = false, foreignKey = @ForeignKey(name = "patient_fk"))
	private Patient patient;

	@ManyToOne
	@JoinColumn(name = "id_doctor", nullable = false, foreignKey = @ForeignKey(name = "doctor_fk"))
	private Doctor doctor;

	@ManyToOne
	@JoinColumn(name = "id_specialty", nullable = false, foreignKey = @ForeignKey(name = "specialty_fk"))
	private Specialty specialty;

	@ManyToMany
	@JoinTable(name = "appointment_laboratory_exam", joinColumns = @JoinColumn(name = "id_appointment"), inverseJoinColumns = @JoinColumn(name = "id_laboratory_exam"), foreignKey = @ForeignKey(name = "appointment_fk"), inverseForeignKey = @ForeignKey(name = "laboratory_exam_fk"))
	private List<LaboratoryExam> laboratoryExams;
	// private Set<LaboratoryExam> laboratoryExams = new HashSet<LaboratoryExam>();

	@Transient
	@OneToMany(mappedBy = "medicine", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Recipe> recipes;

	@JsonSerialize(using = ToStringSerializer.class)
	@Column(name = "time_stamp", nullable = false)
	private LocalDateTime timestamp;

	@Column(nullable = false)
	private int duration;

	@Column(nullable = false, length = 300)
	private String reason;

	@Column(nullable = false, length = 300)
	private String comment;

	@Column(nullable = false, length = 500)
	private String diagnostic;

	@Column(nullable = false, length = 500)
	private String prescription;

	@Column(nullable = false)
	private int status;

	@JsonSerialize(using = ToStringSerializer.class)
	private LocalDateTime registrationDate;

	@JsonSerialize(using = ToStringSerializer.class)
	private LocalDateTime updateDate;

}
