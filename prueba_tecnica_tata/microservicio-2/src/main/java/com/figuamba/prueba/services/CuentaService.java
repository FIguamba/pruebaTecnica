package com.figuamba.prueba.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.figuamba.prueba.exception.EntityNotFoundException;
import com.figuamba.prueba.exception.UniqueConstraintViolationException;
import com.figuamba.prueba.model.Cuenta;
import com.figuamba.prueba.repository.CuentaRepository;
import com.figuamba.prueba.utils.EnumMensajes;

import jakarta.transaction.Transactional;

@Service
public class CuentaService {
	@Autowired
	private CuentaRepository cuentaRepository;

	public List<Cuenta> listarCuentas() {
		return cuentaRepository.findAll();
	}

	public Cuenta obtenerCuenta(Long id) {
		return cuentaRepository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException(EnumMensajes.REGISTRO_NO_ENCONTRADO.getMensaje()));
	}

	@Transactional
	public Cuenta guardarCuenta(Cuenta cuenta) {
		try {
			return cuentaRepository.save(cuenta);
		} catch (Exception e) {
			if (e.getCause() instanceof org.hibernate.exception.ConstraintViolationException) {
				throw new UniqueConstraintViolationException("La cuenta ya existe: " + cuenta.getNumeroCuenta());
			}
			throw e;
		}
	}

	@Transactional
	public Cuenta actualizarCuenta(Cuenta cuenta) {
		if (cuentaRepository.existsById(cuenta.getId())) {
			return cuentaRepository.save(cuenta);
		} else {
			throw new EntityNotFoundException(EnumMensajes.REGISTRO_NO_ENCONTRADO.getMensaje() + cuenta.getId());
		}
	}

	public void eliminarCuenta(Long id) {
		cuentaRepository.deleteById(id);
	}

	public Cuenta obtenerCuentaPorNumero(String numeroCuenta) {
		return cuentaRepository.findByNumeroCuenta(numeroCuenta);
	}
}