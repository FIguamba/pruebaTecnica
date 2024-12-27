package com.figuamba.prueba.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.figuamba.prueba.exception.InsufficientBalanceException;
import com.figuamba.prueba.model.Movimiento;
import com.figuamba.prueba.services.MovimientoService;

@RestController
@RequestMapping("/movimientos")
public class MovimientoController {
    @Autowired
    private MovimientoService movimientoService;

    @GetMapping
    public List<Movimiento> getAllMovimientos() {
        return movimientoService.listarMovimientos();
    }

    @PostMapping
    public Movimiento createMovimiento(@RequestBody Movimiento movimiento) {
        try {
            return movimientoService.createMovimiento(movimiento);
        } catch (InsufficientBalanceException e) {
            throw new RuntimeException(e.getMessage());
        }
    }
    
    
}