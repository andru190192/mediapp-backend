package com.andresguachisaca.model;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;

@Entity
@Table(name = "functionality")
public class Functionality {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_functionality")
	private int id;

	@Column(length = 20)
	private String icon;

	@Column(length = 20)
	private String name;

	@Column(length = 50)
	private String url;

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "functionality_rol", joinColumns = @JoinColumn(name = "id_functionality"), inverseJoinColumns = @JoinColumn(name = "id_rol"))
	private List<Rol> roles;

	@Column(nullable = false)
	private boolean status;

	@Column(nullable = false)
	@JsonSerialize(using = ToStringSerializer.class)
	private LocalDateTime registrationDate;

	@JsonSerialize(using = ToStringSerializer.class)
	private LocalDateTime updateDate;

}
