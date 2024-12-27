package com.figuamba.prueba.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.figuamba.prueba.model.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
	
}

