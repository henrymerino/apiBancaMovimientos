package com.banco.cuenta.controller;


import com.banco.cuenta.dto.MovimientoRequest;
import com.banco.cuenta.dto.MovimientoResponse;

import com.banco.cuenta.services.MovimientoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movimientos")
@RequiredArgsConstructor
@Tag(name = "Movimientos", description = "Operaciones sobre movimientos")
public class MovimientoController {

    private final MovimientoService movimientoService;

    @PostMapping
    @Operation(summary = "Registrar movimiento")
    public MovimientoResponse registrarMovimiento(
            @Valid @RequestBody MovimientoRequest request) {

        return movimientoService.registrarMovimiento(request);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar movimiento por ID")
    public MovimientoResponse obtenerMovimiento(
            @PathVariable Long id) {

        return movimientoService.obtenerMovimiento(id);
    }

    @GetMapping
    @Operation(summary = "Listar movimientos")
    public List<MovimientoResponse> listarMovimientos() {

        return movimientoService.listarMovimientos();
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar movimiento por ID")
    public void eliminarMovimiento(
            @PathVariable Long id) {

        movimientoService.eliminarMovimiento(id);
    }
}