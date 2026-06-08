package com.banco.cuenta.dto;

import java.util.List;

public record ReporteResponse(

        String cliente,

        List<CuentaReporteResponse> cuentas

) {
}
