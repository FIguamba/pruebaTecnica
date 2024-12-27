package com.figuamba.prueba.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.figuamba.prueba.exception.EntityNotFoundException;
import com.figuamba.prueba.exception.UniqueConstraintViolationException;
import com.figuamba.prueba.model.Cliente;
import com.figuamba.prueba.repository.ClienteRepository;
import com.figuamba.prueba.utils.EnumMensajes;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ClienteService {

	@Autowired
	private ClienteRepository clienteRepository;

	
	public List<Cliente> listarClientes() {
		return clienteRepository.findAll();
	}

	public Cliente obtenerCliente(Long id) {
		return clienteRepository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException(EnumMensajes.REGISTRO_NO_ENCONTRADO.getMensaje()));
	}

	@Transactional
	public Cliente guardarCliente(Cliente cliente) {
		try {
			return clienteRepository.save(cliente);
		} catch (Exception e) {
			if (e.getCause() instanceof org.hibernate.exception.ConstraintViolationException) {
				throw new UniqueConstraintViolationException("El cliente ya existe: " + cliente.getIdentificacion());
			}
			throw e;
		}
	}

	@Transactional
	public Cliente actualizarCliente(Cliente cliente) {
		if (clienteRepository.existsById(cliente.getId())) {
			return clienteRepository.save(cliente);
		} else {
			throw new EntityNotFoundException(EnumMensajes.REGISTRO_NO_ENCONTRADO.getMensaje() + cliente.getId());
		}
	}

	public void eliminarCliente(Long id) {
		clienteRepository.deleteById(id);
	}
}
