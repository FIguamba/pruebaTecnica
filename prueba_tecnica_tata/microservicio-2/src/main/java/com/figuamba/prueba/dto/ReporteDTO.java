package com.figuamba.prueba.dto;

import java.math.BigDecimal;
import java.util.List;

import lombok.Data;

@Data
public class ReporteDTO {

	private String numeroCuenta;
	private BigDecimal saldoInicial;
	private List<MovimientoDTO> movimientos;
}
