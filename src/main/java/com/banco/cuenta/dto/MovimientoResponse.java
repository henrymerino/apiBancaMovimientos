package com.banco.cuenta.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record MovimientoResponse(

        Long id,
        LocalDateTime fecha,

        String cliente,

        String numeroCuenta,

        String tipoCuenta,

        BigDecimal saldoInicial,

        Boolean estadoCuenta,

        String tipoMovimiento,

        BigDecimal valor,

        BigDecimal saldoDisponible

) {
}
