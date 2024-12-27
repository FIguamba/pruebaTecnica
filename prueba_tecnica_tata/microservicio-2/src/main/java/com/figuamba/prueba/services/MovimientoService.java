package com.figuamba.prueba.services;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.figuamba.prueba.exception.EntityNotFoundException;
import com.figuamba.prueba.exception.InsufficientBalanceException;
import com.figuamba.prueba.model.Cuenta;
import com.figuamba.prueba.model.Movimiento;
import com.figuamba.prueba.repository.CuentaRepository;
import com.figuamba.prueba.repository.MovimientoRepository;
import com.figuamba.prueba.utils.EnumMensajes;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MovimientoService {
	@Autowired
	private MovimientoRepository movimientoRepository;

	@Autowired
	private CuentaRepository cuentaRepository;

	public List<Movimiento> listarMovimientos() {
		return movimientoRepository.findAll();
	}

	public Movimiento obtenerMovimiento(Long id) {
		return movimientoRepository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException(EnumMensajes.REGISTRO_NO_ENCONTRADO.getMensaje()));
	}

	@Transactional
	public Movimiento guardarMovimiento(Movimiento Movimiento) {
		try {
			return movimientoRepository.save(Movimiento);
		} catch (Exception e) {
			throw e;
		}
	}

	@Transactional
	public Movimiento actualizarMovimiento(Movimiento Movimiento) {
		if (movimientoRepository.existsById(Movimiento.getId())) {
			return movimientoRepository.save(Movimiento);
		} else {
			throw new EntityNotFoundException(EnumMensajes.REGISTRO_NO_ENCONTRADO.getMensaje() + Movimiento.getId());
		}
	}

	public void eliminarMovimiento(Long id) {
		movimientoRepository.deleteById(id);
	}

	@Transactional
	public Movimiento createMovimiento(Movimiento movimiento) throws InsufficientBalanceException {
		Cuenta cuenta = cuentaRepository.findById(movimiento.getCuenta().getId())
				.orElseThrow(() -> new RuntimeException(EnumMensajes.CUENTA_NO_ENCONTRADA.getMensaje()));

		BigDecimal nuevoSaldo = cuenta.getSaldoInicial().add(movimiento.getValor());
		nuevoSaldo = nuevoSaldo.setScale(2, RoundingMode.HALF_UP);
		if (nuevoSaldo.compareTo(BigDecimal.ZERO) < 0) {
			throw new InsufficientBalanceException(EnumMensajes.SALDO_NO_DISPONIBLE.getMensaje());
		}

		cuenta.setSaldoInicial(nuevoSaldo);
		cuentaRepository.save(cuenta);

		return movimientoRepository.save(movimiento);
	}

}