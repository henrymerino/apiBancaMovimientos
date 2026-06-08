package com.banco.cuenta.dto;

import java.math.BigDecimal;

public record CuentaReporteResponse(

        String numeroCuenta,

        String tipoCuenta,

        BigDecimal saldoActual,

        BigDecimal totalCreditos,

        BigDecimal totalDebitos

) {
}
