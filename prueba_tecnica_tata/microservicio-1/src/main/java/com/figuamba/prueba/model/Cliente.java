package com.figuamba.prueba.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
@Table(name = "clientes")
public class Cliente extends Persona {

	@Column(nullable = false)
	private String contrasena;

	@Column(nullable = false)
	private Boolean estado;
}
