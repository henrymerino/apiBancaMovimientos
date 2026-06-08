package com.banco.cuenta.controller;


import com.banco.cuenta.dto.ReporteResponse;

import com.banco.cuenta.services.ReporteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/reportes")
@RequiredArgsConstructor
@Tag(name = "Reporte", description = "Reporte sobre clientes y sus movimientos, por ID cliente y fechas")
public class ReporteController {

    private final ReporteService reporteService;

    @GetMapping
    @Operation(summary = "Genera reporte por ID cliente, fecha inicio, fecha fin")
    public ReporteResponse generarReporte(

            @RequestParam String clienteId,

            @RequestParam
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
            LocalDate fechaInicio,

            @RequestParam
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
            LocalDate fechaFin) {

        return reporteService.generarReporte(
                clienteId,
                fechaInicio,
                fechaFin
        );
    }
}
