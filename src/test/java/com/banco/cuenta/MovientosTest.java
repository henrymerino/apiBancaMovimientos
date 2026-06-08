package com.banco.cuenta;

import com.banco.cuenta.dto.MovimientoRequest;
import com.banco.cuenta.dto.MovimientoResponse;
import com.banco.cuenta.entity.Cuenta;
import com.banco.cuenta.exception.SaldoNoDisponibleException;
import com.banco.cuenta.mapper.MovimientoMapper;
import com.banco.cuenta.repository.CuentaRepository;
import com.banco.cuenta.repository.MovimientoRepository;
import com.banco.cuenta.services.implement.MovimientoServiceImpl;
import com.banco.cuenta.util.TipoMovimiento;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class MovimientosTest {

    @Mock
    private CuentaRepository cuentaRepository;

    @Mock
    private MovimientoRepository movimientoRepository;

    @Mock
    private MovimientoMapper movimientoMapper;

    @InjectMocks
    private MovimientoServiceImpl movimientoService;

    @Test
    void saldoNoDisponible() {

        Cuenta cuenta = new Cuenta();
        cuenta.setId(1L);
        cuenta.setSaldoActual(BigDecimal.valueOf(100));

        when(cuentaRepository.findByNumeroCuenta("123"))
                .thenReturn(Optional.of(cuenta));

        MovimientoRequest request =
                new MovimientoRequest(
                        "123",
                        TipoMovimiento.RETIRO,
                        BigDecimal.valueOf(-200));

        assertThrows(
                SaldoNoDisponibleException.class,
                () -> movimientoService.registrarMovimiento(request));
    }
}