package com.banco.cuenta.mappers;

import com.banco.cuenta.dto.MovimientoResponse;
import com.banco.cuenta.entity.Cuenta;
import com.banco.cuenta.entity.Movimiento;


public final class MovimientoMapper {

    private MovimientoMapper() {
    }

    public static MovimientoResponse toResponse(Movimiento movimiento) {

        Cuenta cuenta = movimiento.getCuenta();

        return new MovimientoResponse(
                movimiento.getId(),
                movimiento.getFecha(),
                cuenta.getCliente().getNombre(),
                cuenta.getNumeroCuenta(),
                cuenta.getTipoCuenta().name(),
                cuenta.getSaldoInicial(),
                cuenta.getEstado(),
                movimiento.getTipoMovimiento().name(),
                movimiento.getValor(),
                movimiento.getSaldo()
        );
    }
}
