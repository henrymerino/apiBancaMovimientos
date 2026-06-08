package com.banco.cuenta.services;

import com.banco.cuenta.dto.CuentaRequest;
import com.banco.cuenta.dto.CuentaResponse;

import java.util.List;

public interface CuentaService {

    CuentaResponse crearCuenta(CuentaRequest request);

    CuentaResponse obtenerCuenta(Long id);

    List<CuentaResponse> listarCuentas();

    CuentaResponse actualizarCuenta(Long id, CuentaRequest request);

    void eliminarCuenta(Long id);
}
