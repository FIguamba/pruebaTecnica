package com.figuamba.prueba.controller;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.figuamba.prueba.dto.ClienteResponse;
import com.figuamba.prueba.model.Cuenta;
import com.figuamba.prueba.services.ClienteService;
import com.figuamba.prueba.services.CuentaService;
import com.figuamba.prueba.utils.EnumMensajes;

@RestController
@RequestMapping("/cuentas")
public class CuentaController {
	@Autowired
	private CuentaService cuentaService;

	@Autowired
	private ClienteService clienteService;

	@GetMapping
	public List<Cuenta> getAllCuentas() {
		return cuentaService.listarCuentas();
	}

	@PostMapping
	public ResponseEntity<?> createCuenta(@RequestBody Cuenta cuenta) {
		try {
			ClienteResponse cliente = clienteService.obtenerCliente(cuenta.getClienteId());
			if (cliente != null && cliente.getCliente() != null) {
				System.err.println(cliente.toString());
				return ResponseEntity.status(HttpStatus.CREATED)
						.body(setMap(EnumMensajes.REGISTRO_CREADO.getMensaje(), cuentaService.guardarCuenta(cuenta)));
			} else
				return ResponseEntity.status(HttpStatus.BAD_REQUEST)
						.body(setMap(EnumMensajes.REGISTRO_NO_CREADO.getMensaje(), null));
		} catch (Exception e) {

			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body((setMap(e.getMessage(), null)));
		}
	}

	@GetMapping("/cuentas/cliente/{id}")
	public ClienteResponse obtenerCliente(@PathVariable Long id) {
		return clienteService.obtenerCliente(id);
	}

	private HashMap<String, Object> setMap(String mensaje, Object objeto) {
		HashMap<String, Object> resp = new HashMap<>();
		if (objeto != null)
			resp.put("cliente(s)", objeto);
		resp.put("mensaje", mensaje);
		return resp;
	}
}
