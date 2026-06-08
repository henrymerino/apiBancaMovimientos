package com.banco.cuenta.dto;

public record ClienteResponse(

        Long id,

        String nombre,

        String genero,

        Integer edad,

        String identificacion,

        String direccion,

        String telefono,

        String clienteId,

        Boolean estado

) {
}