package com.banco.cuenta.dto;

import com.banco.cuenta.util.TipoCuenta;

import java.math.BigDecimal;

public record CuentaRequest(

        String numeroCuenta,

        TipoCuenta tipoCuenta,

        BigDecimal saldoInicial,

        Boolean estado,

        Long clienteId

) {
}
