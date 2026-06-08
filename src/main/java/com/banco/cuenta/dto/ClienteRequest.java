package com.banco.cuenta.dto;

public record ClienteRequest(

        String nombre,
        String genero,
        Integer edad,
        String identificacion,
        String direccion,
        String telefono,

        String clienteId,
        String contrasena,
        Boolean estado

) {
}
