package com.figuamba.prueba.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.figuamba.prueba.model.Cuenta;

public interface CuentaRepository extends JpaRepository<Cuenta, Long> {

	Cuenta findByNumeroCuenta(String numeroCuenta); 
	
	List<Cuenta> findByClienteId(Long clienteId);
	
}
