package com.figuamba.prueba.services;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.figuamba.prueba.dto.MovimientoDTO;
import com.figuamba.prueba.dto.ReporteDTO;
import com.figuamba.prueba.model.Cuenta;
import com.figuamba.prueba.model.Movimiento;
import com.figuamba.prueba.repository.CuentaRepository;
import com.figuamba.prueba.repository.MovimientoRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ReporteService {

	private final CuentaRepository cuentaRepository;
	private final MovimientoRepository movimientoRepository;


	public List<ReporteDTO> generarReporte(LocalDate fechaInicio, LocalDate fechaFin, Long clienteId) {
		List<Cuenta> cuentas = cuentaRepository.findByClienteId(clienteId);

		List<ReporteDTO> reportes = new ArrayList<>();

		for (Cuenta cuenta : cuentas) {
			List<Movimiento> movimientos = movimientoRepository.findByCuentaAndFechaBetween(cuenta, fechaInicio,
					fechaFin);

			ReporteDTO reporte = new ReporteDTO();
			reporte.setNumeroCuenta(cuenta.getNumeroCuenta());
			reporte.setSaldoInicial(cuenta.getSaldoInicial());

			List<MovimientoDTO> movimientosDTO = new ArrayList<>();
			for (Movimiento movimiento : movimientos) {
				MovimientoDTO movimientoDTO = new MovimientoDTO();
				movimientoDTO.setFecha(movimiento.getFecha());
				movimientoDTO.setTipoMovimiento(movimiento.getTipoMovimiento());
				movimientoDTO.setValor(movimiento.getValor());
				movimientoDTO.setSaldo(movimiento.getSaldo());
				movimientosDTO.add(movimientoDTO);
			}
			reporte.setMovimientos(movimientosDTO); // Asignar la lista de MovimientoDTOs
			reportes.add(reporte);
		}

		return reportes;
	}
}
