package com.figuamba.prueba.utils;

public enum EnumMensajes {
	REGISTRO_CREADO("Registr Creado."),
	REGISTRO_ENCONTRADO("Registro(s) encontrado(s)."), 
	REGISTRO_NO_ENCONTRADO("Registro(s) no encontrado(s). "),
	REGISTRO_ELIMINADO("Regitro eliminado"), 
	REGISTRO_ACTUALIZADO("Registro actualizado"),
	REGISTRO_TOTAL_ENCONTRADOS("Total de registros encontrados");

	private final String mensaje;

	private EnumMensajes(String mensaje) {

		this.mensaje = mensaje;
	}

	public String getMensaje() {
		return mensaje;
	}
}
