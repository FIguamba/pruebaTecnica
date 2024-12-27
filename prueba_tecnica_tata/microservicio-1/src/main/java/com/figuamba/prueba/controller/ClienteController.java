package com.figuamba.prueba.controller;

import java.util.HashMap;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.figuamba.prueba.model.Cliente;
import com.figuamba.prueba.service.ClienteService;
import com.figuamba.prueba.utils.EnumMensajes;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/clientes")
@RequiredArgsConstructor
public class ClienteController {
	private final ClienteService clienteService;

	@GetMapping
	public ResponseEntity<?> listarClientes() {
		List<Cliente> listaClientes = clienteService.listarClientes();
		return ResponseEntity.status(HttpStatus.OK).body(
				setMap(EnumMensajes.REGISTRO_TOTAL_ENCONTRADOS.getMensaje()+ " : " + listaClientes.size(), listaClientes));
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> obtenerCliente(@PathVariable Long id) {

		try {
			Cliente clienteBuscado = clienteService.obtenerCliente(id);
			return ResponseEntity.status(HttpStatus.OK)
					.body((setMap(EnumMensajes.REGISTRO_ENCONTRADO.getMensaje(), clienteBuscado)));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body((setMap(e.getMessage(), null)));
		}
	}

	@PostMapping
	public ResponseEntity<?> guardarCliente(@RequestBody Cliente cliente) {

		try {
			Cliente clienteCreado = clienteService.guardarCliente(cliente);
			return ResponseEntity.status(HttpStatus.CREATED)
					.body(setMap(EnumMensajes.REGISTRO_CREADO.getMensaje(), clienteCreado));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body((setMap(e.getMessage(), null)));
		}
	}

	@PatchMapping("/{id}")
	public ResponseEntity<?> actualizarCliente(@PathVariable Long id, @RequestBody Cliente cliente) {

		try {
			Cliente clienteEncontrado = clienteService.obtenerCliente(id);
			if (clienteEncontrado != null) {
				cliente.setId(clienteEncontrado.getId());
				return ResponseEntity.status(HttpStatus.OK).body(setMap(EnumMensajes.REGISTRO_ACTUALIZADO.getMensaje(),
						clienteService.actualizarCliente(cliente)));
			} else {
				return ResponseEntity.status(HttpStatus.NOT_FOUND)
						.body(setMap(EnumMensajes.REGISTRO_NO_ENCONTRADO.getMensaje(), null));
			}
		} catch (Exception e) {

			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body((setMap(e.getMessage(), null)));
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> eliminarCliente(@PathVariable Long id) {
		try {
			Cliente clienteEncontrado = clienteService.obtenerCliente(id);
			if (clienteEncontrado != null) {
				clienteService.eliminarCliente(id);
				return ResponseEntity.status(HttpStatus.OK)
						.body(setMap(EnumMensajes.REGISTRO_ELIMINADO.getMensaje(), null));
			} else {
				return ResponseEntity.status(HttpStatus.NOT_FOUND)
						.body(setMap(EnumMensajes.REGISTRO_NO_ENCONTRADO.getMensaje(), null));
			}
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body((setMap(e.getMessage(), null)));
		}
	}

	private HashMap<String, Object> setMap(String mensaje, Object objeto) {
		HashMap<String, Object> resp = new HashMap<>();
		if (objeto != null)
			resp.put("cliente(s)", objeto);
		resp.put("mensaje", mensaje);
		return resp;
	}
}
