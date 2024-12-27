package com.figuamba.prueba.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.figuamba.prueba.dto.ClienteResponse;

@Service
public class ClienteService {

	@Autowired
	private RestTemplate restTemplate;

	public ClienteService(RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}

	public boolean validarCliente(Long clienteId) {
		String url = "http://localhost:8083/clientes/" + clienteId;
		try {
			return restTemplate.getForObject(url, ClienteResponse.class) != null;
		} catch (Exception e) {
			return false;
		}
	}

	public ClienteResponse obtenerCliente(Long clienteId) {
		String url = "http://localhost:8083/clientes/" + clienteId;

		ClienteResponse clienteResponse = restTemplate.getForObject(url, ClienteResponse.class);

		System.out.println("Mensaje: " + clienteResponse);

		return clienteResponse;
	}
}