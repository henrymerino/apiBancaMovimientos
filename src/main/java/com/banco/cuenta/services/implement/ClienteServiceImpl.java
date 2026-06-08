package com.banco.cuenta.services.implement;

import com.banco.cuenta.dto.ClienteRequest;
import com.banco.cuenta.dto.ClienteResponse;
import com.banco.cuenta.entity.Cliente;
import com.banco.cuenta.exception.RecursoNoEncontradoException;
import com.banco.cuenta.mappers.ClienteMapper;
import com.banco.cuenta.repository.ClienteRepository;
import com.banco.cuenta.services.ClienteService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class ClienteServiceImpl implements ClienteService {

    private final ClienteRepository clienteRepository;

    @Override
    public ClienteResponse crearCliente(ClienteRequest request) {

        Cliente cliente = new Cliente();

        cliente.setNombre(request.nombre());
        cliente.setGenero(request.genero());
        cliente.setEdad(request.edad());
        cliente.setIdentificacion(request.identificacion());
        cliente.setDireccion(request.direccion());
        cliente.setTelefono(request.telefono());
        cliente.setClienteId(request.clienteId());
        cliente.setContrasena(request.contrasena());
        cliente.setEstado(request.estado());

        Cliente clienteGuardado = clienteRepository.save(cliente);

        return ClienteMapper.toResponse(clienteGuardado);
    }

    @Override
    public ClienteResponse obtenerCliente(Long id) {
        Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(() ->
                        new RecursoNoEncontradoException(
                                "Cliente no encontrado con id: " + id));

        return ClienteMapper.toResponse(cliente);
    }

    @Override
    public List<ClienteResponse> listarClientes() {
        return clienteRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    @Override
    public ClienteResponse actualizarCliente(Long id, ClienteRequest request) {
        Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(() ->
                        new RecursoNoEncontradoException(
                                "Cliente no encontrado con id: " + id));

        cliente.setNombre(request.nombre());
        cliente.setGenero(request.genero());
        cliente.setEdad(request.edad());
        cliente.setIdentificacion(request.identificacion());
        cliente.setDireccion(request.direccion());
        cliente.setTelefono(request.telefono());
        cliente.setClienteId(request.clienteId());
        cliente.setContrasena(request.contrasena());
        cliente.setEstado(request.estado());

        Cliente actualizado = clienteRepository.save(cliente);

        return ClienteMapper.toResponse(actualizado);
    }

    @Override
    public void eliminarCliente(Long id) {
        Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(() ->
                        new RecursoNoEncontradoException(
                                "Cliente no encontrado con id: " + id));

        clienteRepository.delete(cliente);
    }

    private ClienteResponse mapToResponse(Cliente cliente) {

        return new ClienteResponse(
                cliente.getId(),
                cliente.getNombre(),
                cliente.getGenero(),
                cliente.getEdad(),
                cliente.getIdentificacion(),
                cliente.getDireccion(),
                cliente.getTelefono(),
                cliente.getClienteId(),
                cliente.getEstado()
        );
    }
}
