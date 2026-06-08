package com.banco.cuenta.services.implement;


import com.banco.cuenta.dto.CuentaRequest;
import com.banco.cuenta.dto.CuentaResponse;
import com.banco.cuenta.entity.Cliente;
import com.banco.cuenta.entity.Cuenta;
import com.banco.cuenta.exception.RecursoNoEncontradoException;
import com.banco.cuenta.repository.ClienteRepository;
import com.banco.cuenta.repository.CuentaRepository;
import com.banco.cuenta.services.CuentaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class CuentaServiceImpl implements CuentaService {

    private final CuentaRepository cuentaRepository;
    private final ClienteRepository clienteRepository;

    @Override
    public CuentaResponse crearCuenta(CuentaRequest request) {

        if (cuentaRepository.existsByNumeroCuenta(
                request.numeroCuenta())) {

            throw new IllegalArgumentException(
                    "Ya existe una cuenta con número "
                            + request.numeroCuenta());
        }

        Cliente cliente = clienteRepository.findById(
                        request.clienteId())
                .orElseThrow(() ->
                        new RecursoNoEncontradoException(
                                "Cliente no encontrado"));

        Cuenta cuenta = new Cuenta();

        cuenta.setNumeroCuenta(request.numeroCuenta());
        cuenta.setTipoCuenta(request.tipoCuenta());
        cuenta.setSaldoInicial(request.saldoInicial());

        // El saldo actual inicia con el saldo inicial
        cuenta.setSaldoActual(request.saldoInicial());

        cuenta.setEstado(request.estado());
        cuenta.setCliente(cliente);

        Cuenta cuentaGuardada =
                cuentaRepository.save(cuenta);

        return mapToResponse(cuentaGuardada);
    }

    @Override
    @Transactional(readOnly = true)
    public CuentaResponse obtenerCuenta(Long id) {

        Cuenta cuenta = cuentaRepository.findById(id)
                .orElseThrow(() ->
                        new RecursoNoEncontradoException(
                                "Cuenta no encontrada con id: " + id));

        return mapToResponse(cuenta);
    }

    @Override
    @Transactional(readOnly = true)
    public List<CuentaResponse> listarCuentas() {

        return cuentaRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    @Override
    public CuentaResponse actualizarCuenta(
            Long id,
            CuentaRequest request) {

        Cuenta cuenta = cuentaRepository.findById(id)
                .orElseThrow(() ->
                        new RecursoNoEncontradoException(
                                "Cuenta no encontrada con id: " + id));

        Cliente cliente = clienteRepository.findById(
                        request.clienteId())
                .orElseThrow(() ->
                        new RecursoNoEncontradoException(
                                "Cliente no encontrado"));

        cuenta.setNumeroCuenta(request.numeroCuenta());
        cuenta.setTipoCuenta(request.tipoCuenta());
        cuenta.setEstado(request.estado());
        cuenta.setCliente(cliente);

        Cuenta cuentaActualizada =
                cuentaRepository.save(cuenta);

        return mapToResponse(cuentaActualizada);
    }

    @Override
    public void eliminarCuenta(Long id) {

        Cuenta cuenta = cuentaRepository.findById(id)
                .orElseThrow(() ->
                        new RecursoNoEncontradoException(
                                "Cuenta no encontrada con id: " + id));

        cuentaRepository.delete(cuenta);
    }

    private CuentaResponse mapToResponse(Cuenta cuenta) {

        return new CuentaResponse(
                cuenta.getId(),
                cuenta.getNumeroCuenta(),
                cuenta.getTipoCuenta().name(),
                cuenta.getSaldoInicial(),
                cuenta.getSaldoActual(),
                cuenta.getEstado(),
                cuenta.getCliente().getNombre()
        );
    }
}

