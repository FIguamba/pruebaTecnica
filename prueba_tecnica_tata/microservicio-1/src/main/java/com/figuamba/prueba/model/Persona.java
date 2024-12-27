package com.figuamba.prueba.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import lombok.Data;
import lombok.ToString;

//@MappedSuperclass
@Entity
@Data
@Inheritance(strategy = InheritanceType.JOINED)
@ToString
public class Persona {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private String nombre;

	@Column(nullable = false)
	private String genero;

	@Column(nullable = false)
	private Integer edad;

	@Column(unique = true, nullable = false)
	private String identificacion;

	@Column
	private String direccion;

	@Column
	private String telefono;

}