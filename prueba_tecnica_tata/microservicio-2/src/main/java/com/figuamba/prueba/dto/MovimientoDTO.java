package com.figuamba.prueba.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import lombok.Data;

@Data
public class MovimientoDTO {
	private LocalDate fecha;
	private String tipoMovimiento;
	private BigDecimal valor;
	private BigDecimal saldo;
}
