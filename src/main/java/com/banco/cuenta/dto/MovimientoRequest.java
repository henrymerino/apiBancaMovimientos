package com.banco.cuenta.dto;

import com.banco.cuenta.util.TipoMovimiento;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record MovimientoRequest(
        @NotBlank(message = "El numero de cuenta es obligatorio") String numeroCuenta,
        @NotNull(message = "El tipo de movimiento es obligatorio")
        TipoMovimiento tipoMovimiento,
        @NotNull(message = "El valor es obligatorio") BigDecimal valor) {
}
