package com.banco.cuenta.mappers;

import com.banco.cuenta.dto.ClienteResponse;
import com.banco.cuenta.entity.Cliente;

public class ClienteMapper {

    private  ClienteMapper(){

    }

    public static ClienteResponse toResponse(Cliente cliente){

        return new ClienteResponse(
                cliente.getId(),
                cliente.getNombre(),
                cliente.getGenero(),
                cliente.getEdad(),
                cliente.getIdentificacion(),
                cliente.getDireccion(),
                cliente.getTelefono(),
                cliente.getClienteId(),
                cliente.getEstado()
        );

    }

    private ClienteResponse mapToResponse(Cliente cliente) {

        return new ClienteResponse(
                cliente.getId(),
                cliente.getNombre(),
                cliente.getGenero(),
                cliente.getEdad(),
                cliente.getIdentificacion(),
                cliente.getDireccion(),
                cliente.getTelefono(),
                cliente.getClienteId(),
                cliente.getEstado()
        );
    }
}
