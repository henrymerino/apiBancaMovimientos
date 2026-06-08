package com.banco.cuenta.services;

import com.banco.cuenta.dto.ReporteResponse;

import java.time.LocalDate;

public interface ReporteService {

    ReporteResponse generarReporte(String clienteId, LocalDate fechaInicio,
            LocalDate fechaFin);
}
