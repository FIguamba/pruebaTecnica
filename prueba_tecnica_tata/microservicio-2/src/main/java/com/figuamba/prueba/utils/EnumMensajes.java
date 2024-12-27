package com.figuamba.prueba.utils;

public enum EnumMensajes {
	CUENTA_NO_ENCONTRADA("Cuenta no encontrada"),
	REGISTRO_NO_CREADO("Registro no creado."),
	REGISTRO_CREADO("Registro Creado."),
	REGISTRO_ENCONTRADO("Registro(s) encontrado(s)."),
	REGISTRO_NO_ENCONTRADO("Registro(s) no encontrado(s). "), REGISTRO_ELIMINADO("Regitro eliminado"),
	REGISTRO_ACTUALIZADO("Registro actualizado"), REGISTRO_TOTAL_ENCONTRADOS("Total de registros encontrados"),
	SALDO_NO_DISPONIBLE("Saldo no disponible"), TIPO_CORRIENTE("CORRIENTE"), TIPO_AHORROS("AHORROS"),
	REPORTE_GENERADO("Reporte generado");

	private final String mensaje;

	private EnumMensajes(String mensaje) {

		this.mensaje = mensaje;
	}

	public String getMensaje() {
		return mensaje;
	}
}
