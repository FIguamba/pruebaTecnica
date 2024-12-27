package com.figuamba.prueba.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class ClienteResponse {

	@JsonProperty("cliente(s)")
	private Cliente cliente;

	private String mensaje;

	@Data
	public static class Cliente {
		private Long id;
		private String nombre;
		private String genero;
		private Integer edad;
		private String identificacion;
		private String direccion;
		private String telefono;
		private String contrasena;
		private Boolean estado;
	}
}