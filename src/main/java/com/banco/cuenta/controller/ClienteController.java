package com.banco.cuenta.controller;


import com.banco.cuenta.services.ClienteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



import com.banco.cuenta.dto.ClienteRequest;
import com.banco.cuenta.dto.ClienteResponse;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clientes")
@RequiredArgsConstructor
@Tag(name = "Clientes", description = "Operaciones sobre clientes")
public class ClienteController {

    private final ClienteService clienteService;

    @PostMapping
    @Operation(summary = "Crear cliente")
    public ClienteResponse crearCliente(
            @Valid @RequestBody ClienteRequest request) {

        return clienteService.crearCliente(request);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar cliente por ID")
    public ClienteResponse obtenerCliente(
            @PathVariable Long id) {

        return clienteService.obtenerCliente(id);
    }

    @GetMapping
    @Operation(summary = "Listar clientes")
    public List<ClienteResponse> listarClientes() {

        return clienteService.listarClientes();
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar cliente por ID y campos a actualizar")
    public ClienteResponse actualizarCliente(
            @PathVariable Long id,
            @Valid @RequestBody ClienteRequest request) {

        return clienteService.actualizarCliente(id, request);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar cliente por ID")
    public void eliminarCliente(
            @PathVariable Long id) {

        clienteService.eliminarCliente(id);
    }
}
