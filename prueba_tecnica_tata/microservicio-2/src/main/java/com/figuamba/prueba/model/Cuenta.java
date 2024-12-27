package com.figuamba.prueba.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "cuentas")
public class Cuenta implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false, unique = true)
	private String numeroCuenta;

	@Column(nullable = false)
	private String tipoCuenta;

	@Column(nullable = false)
	private BigDecimal saldoInicial;

	@Column(nullable = false)
	private Boolean estado;

	@Column(nullable = false)
	private Long clienteId;

	@OneToMany(mappedBy = "cuenta")
	private List<Movimiento> movimientos;
}
