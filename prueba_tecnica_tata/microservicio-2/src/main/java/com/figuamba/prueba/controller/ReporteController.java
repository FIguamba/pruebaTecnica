package com.figuamba.prueba.controller;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.figuamba.prueba.dto.ReporteDTO;
import com.figuamba.prueba.services.ReporteService;
import com.figuamba.prueba.utils.EnumMensajes;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/reportes")
@RequiredArgsConstructor
public class ReporteController {

	private final ReporteService reporteService;

	@GetMapping
	public ResponseEntity<?> generarReporte(@RequestParam("fechaInicio") String fechaInicio,
			@RequestParam("fechaFin") String fechaFin, @RequestParam("clienteId") Long clienteId) {
		try {
			// Convertir las fechas recibidas en formato String a LocalDate
			LocalDate fechaInicioParsed = LocalDate.parse(fechaInicio);
			LocalDate fechaFinParsed = LocalDate.parse(fechaFin);
			List<ReporteDTO> reporte = reporteService.generarReporte(fechaInicioParsed, fechaFinParsed, clienteId);

			return ResponseEntity.ok(setMap(EnumMensajes.REPORTE_GENERADO.getMensaje(), reporte));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(setMap(e.getMessage(), null));
		}
	}

	private HashMap<String, Object> setMap(String mensaje, Object objeto) {
		HashMap<String, Object> resp = new HashMap<>();
		if (objeto != null)
			resp.put("reporte", objeto);
		resp.put("mensaje", mensaje);
		return resp;
	}
}
