package com.banco.cuenta.controller;



import com.banco.cuenta.dto.CuentaRequest;
import com.banco.cuenta.dto.CuentaResponse;
import com.banco.cuenta.services.CuentaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cuentas")
@RequiredArgsConstructor
@Tag(name = "Cuenta", description = "Operaciones sobre cuenta")
public class CuentaController {

    private final CuentaService cuentaService;

    @PostMapping
    @Operation(summary = "Crear cuenta")
    public CuentaResponse crearCuenta(
            @Valid @RequestBody CuentaRequest request) {

        return cuentaService.crearCuenta(request);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar cuenta por ID cuenta")
    public CuentaResponse obtenerCuenta(
            @PathVariable Long id) {

        return cuentaService.obtenerCuenta(id);
    }

    @GetMapping
    @Operation(summary = "Listar cuentas")
    public List<CuentaResponse> listarCuentas() {

        return cuentaService.listarCuentas();
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar cuenta por ID y campos")
    public CuentaResponse actualizarCuenta(
            @PathVariable Long id,
            @Valid @RequestBody CuentaRequest request) {

        return cuentaService.actualizarCuenta(id, request);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar cuenta por ID")
    public void eliminarCuenta(
            @PathVariable Long id) {

        cuentaService.eliminarCuenta(id);
    }
}
