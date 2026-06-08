package com.banco.cuenta.mapper;

import com.banco.cuenta.dto.MovimientoResponse;
import com.banco.cuenta.entity.Movimiento;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface MovimientoMapper {

    @Mapping(target = "cliente",
            source = "cuenta.cliente.nombre")

    @Mapping(target = "numeroCuenta",
            source = "cuenta.numeroCuenta")

    @Mapping(target = "tipoCuenta",
            expression = "java(movimiento.getCuenta().getTipoCuenta().name())")

    @Mapping(target = "saldoInicial",
            source = "cuenta.saldoInicial")

    @Mapping(target = "estadoCuenta",
            source = "cuenta.estado")

    @Mapping(target = "tipoMovimiento",
            expression = "java(movimiento.getTipoMovimiento().name())")

    @Mapping(target = "saldoDisponible",
            source = "saldo")
    MovimientoResponse toResponse(Movimiento movimiento);
}
