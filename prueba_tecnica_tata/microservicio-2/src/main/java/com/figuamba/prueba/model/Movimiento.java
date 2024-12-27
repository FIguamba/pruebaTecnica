package com.figuamba.prueba.model;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "movimientos")
public class Movimiento {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private LocalDate fecha;

	@Column(nullable = false)
	private String tipoMovimiento;

	@Column(nullable = false)
	private BigDecimal valor;

	@Column(nullable = false)
	private BigDecimal saldo;

	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "cuenta_id", nullable = false)
	private Cuenta cuenta;
}
