package com.banco.cuenta.services;

import com.banco.cuenta.dto.ClienteRequest;
import com.banco.cuenta.dto.ClienteResponse;
import com.banco.cuenta.entity.Cliente;

import java.util.List;

public interface ClienteService {

    ClienteResponse crearCliente(ClienteRequest request);

    ClienteResponse obtenerCliente(Long id);

    List<ClienteResponse> listarClientes();

    ClienteResponse actualizarCliente(Long id, ClienteRequest request);

    void eliminarCliente(Long id);

}
