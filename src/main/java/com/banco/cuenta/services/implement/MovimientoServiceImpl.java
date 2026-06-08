package com.banco.cuenta.services.implement;


import com.banco.cuenta.dto.MovimientoRequest;
import com.banco.cuenta.dto.MovimientoResponse;
import com.banco.cuenta.entity.Cuenta;
import com.banco.cuenta.entity.Movimiento;
import com.banco.cuenta.exception.CupoDiarioExcedidoException;
import com.banco.cuenta.exception.RecursoNoEncontradoException;
import com.banco.cuenta.exception.SaldoNoDisponibleException;
//import com.banco.cuenta.mappers.MovimientoMapper;
import com.banco.cuenta.mapper.MovimientoMapper;
import com.banco.cuenta.repository.CuentaRepository;
import com.banco.cuenta.repository.MovimientoRepository;
import com.banco.cuenta.services.MovimientoService;
import com.banco.cuenta.util.TipoMovimiento;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class MovimientoServiceImpl implements MovimientoService {

    //private static final BigDecimal LIMITE_DIARIO = BigDecimal.valueOf(1000);

    @Value("${banco.movimientos.limite-diario-retiro}")
    private BigDecimal limiteDiarioRetiro;

    private final CuentaRepository cuentaRepository;
    private final MovimientoRepository movimientoRepository;
    private final MovimientoMapper movimientoMapper;

    @Override
    public MovimientoResponse registrarMovimiento(MovimientoRequest request) {

        Cuenta cuenta = cuentaRepository.findByNumeroCuenta(
                        request.numeroCuenta())
                .orElseThrow(() ->
                        new RecursoNoEncontradoException("Cuenta no encontrada"));

        BigDecimal valorMovimiento = request.valor();

        // Validar saldo insuficiente
        if (valorMovimiento.compareTo(BigDecimal.ZERO) < 0 &&
                cuenta.getSaldoActual().add(valorMovimiento)
                        .compareTo(BigDecimal.ZERO) < 0) {

            throw new SaldoNoDisponibleException(
                    "Saldo no disponible");
        }

        // Validar límite diario
        validarLimiteDiario(cuenta.getId(), valorMovimiento);

        BigDecimal nuevoSaldo =
                cuenta.getSaldoActual().add(valorMovimiento);

        cuenta.setSaldoActual(nuevoSaldo);

        Movimiento movimiento = new Movimiento();

        movimiento.setFecha(LocalDateTime.now());
        movimiento.setValor(valorMovimiento);
        movimiento.setSaldo(nuevoSaldo);

        movimiento.setTipoMovimiento(
                valorMovimiento.compareTo(BigDecimal.ZERO) > 0
                        ? TipoMovimiento.DEPOSITO
                        : TipoMovimiento.RETIRO);

        movimiento.setCuenta(cuenta);

        movimientoRepository.save(movimiento);

        return movimientoMapper.toResponse(movimiento);
      //return MovimientoMapper.toResponse(movimiento);

    }

    @Override
    public MovimientoResponse obtenerMovimiento(Long id) {
        return null;
    }

    @Override
    public List<MovimientoResponse> listarMovimientos() {
        return List.of();
    }

    @Override
    public void eliminarMovimiento(Long id) {

    }

    private void validarLimiteDiario(
            Long cuentaId,
            BigDecimal valorMovimiento) {

        if (valorMovimiento.compareTo(BigDecimal.ZERO) > 0) {
            return;
        }

        LocalDate hoy = LocalDate.now();

        BigDecimal totalRetirosHoy =
                movimientoRepository
                        .obtenerRetirosDelDia(cuentaId, hoy);

        BigDecimal total =
                totalRetirosHoy.add(valorMovimiento.abs());

        if (total.compareTo(limiteDiarioRetiro) > 0) {

            throw new CupoDiarioExcedidoException(
                    "Cupo diario excedido");
        }
    }

}