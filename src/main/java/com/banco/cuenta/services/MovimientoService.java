package com.banco.cuenta.services;

import com.banco.cuenta.dto.MovimientoRequest;
import com.banco.cuenta.dto.MovimientoResponse;

import java.util.List;

public interface MovimientoService {

    public MovimientoResponse registrarMovimiento(MovimientoRequest request);

    MovimientoResponse obtenerMovimiento(Long id);

    List<MovimientoResponse> listarMovimientos();

    void eliminarMovimiento(Long id);
}
