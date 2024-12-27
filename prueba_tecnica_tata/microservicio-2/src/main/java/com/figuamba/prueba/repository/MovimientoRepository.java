package com.figuamba.prueba.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.figuamba.prueba.model.Cuenta;
import com.figuamba.prueba.model.Movimiento;

public interface MovimientoRepository extends JpaRepository<Movimiento, Long> {

	List<Movimiento> findByCuentaAndFechaBetween(Cuenta cuenta, LocalDate fechaInicio, LocalDate fechaFin);
}
