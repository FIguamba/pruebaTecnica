package com.figuamba.prueba.dto;

import java.util.List;

import com.figuamba.prueba.model.Movimiento;

import lombok.Data;

@Data
public class EstadoCuentaDTO {
    private String clienteId;
    private List<CuentaDTO> cuentas;

    @Data
    public static class CuentaDTO {
        private String numeroCuenta;
        private Double saldoInicial;
        private List<Movimiento> movimientos;
    }
}
