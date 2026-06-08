package com.banco.cuenta.services.implement;


import com.banco.cuenta.dto.CuentaReporteResponse;
import com.banco.cuenta.dto.ReporteResponse;
import com.banco.cuenta.entity.Cliente;
import com.banco.cuenta.entity.Cuenta;
import com.banco.cuenta.entity.Movimiento;
import com.banco.cuenta.exception.RecursoNoEncontradoException;
import com.banco.cuenta.repository.ClienteRepository;
import com.banco.cuenta.repository.CuentaRepository;
import com.banco.cuenta.repository.MovimientoRepository;
import com.banco.cuenta.services.ReporteService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ReporteServiceImpl implements ReporteService {

    private final ClienteRepository clienteRepository;
    private final CuentaRepository cuentaRepository;
    private final MovimientoRepository movimientoRepository;

    @Override
    public ReporteResponse generarReporte(
            String clienteId,
            LocalDate fechaInicio,
            LocalDate fechaFin) {

        Cliente cliente = clienteRepository.findByClienteId(clienteId)
                .orElseThrow(() ->
                        new RecursoNoEncontradoException(
                                "Cliente no encontrado"));

        List<Cuenta> cuentas =
                cuentaRepository.findByClienteId(cliente.getId());

        List<CuentaReporteResponse> detalleCuentas =
                cuentas.stream()
                        .map(cuenta -> construirDetalleCuenta(
                                cuenta,
                                fechaInicio,
                                fechaFin))
                        .toList();

        return new ReporteResponse(
                cliente.getNombre(),
                detalleCuentas
        );
    }

    private CuentaReporteResponse construirDetalleCuenta(
            Cuenta cuenta,
            LocalDate fechaInicio,
            LocalDate fechaFin) {

        LocalDateTime inicio =
                fechaInicio.atStartOfDay();

        LocalDateTime fin =
                fechaFin.atTime(LocalTime.MAX);

        List<Movimiento> movimientos =
                movimientoRepository.findByCuentaIdAndFechaBetween(
                        cuenta.getId(),
                        inicio,
                        fin
                );

        BigDecimal totalCreditos = movimientos.stream()
                .filter(m -> m.getValor().compareTo(BigDecimal.ZERO) > 0)
                .map(Movimiento::getValor)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        BigDecimal totalDebitos = movimientos.stream()
                .filter(m -> m.getValor().compareTo(BigDecimal.ZERO) < 0)
                .map(m -> m.getValor().abs())
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        return new CuentaReporteResponse(
                cuenta.getNumeroCuenta(),
                cuenta.getTipoCuenta().name(),
                cuenta.getSaldoActual(),
                totalCreditos,
                totalDebitos
        );
    }
}
