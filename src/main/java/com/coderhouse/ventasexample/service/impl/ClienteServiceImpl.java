package com.coderhouse.ventasexample.service.impl;

import com.coderhouse.ventasexample.builder.ClienteBuilder;
import com.coderhouse.ventasexample.dao.ClienteRepository;
import com.coderhouse.ventasexample.handle.ApiException;
import com.coderhouse.ventasexample.model.entity.ClienteEntity;
import com.coderhouse.ventasexample.model.request.ClienteRequest;
import com.coderhouse.ventasexample.model.response.ClienteResponse;
import com.coderhouse.ventasexample.service.ClienteService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ClienteServiceImpl implements ClienteService {

    private final ClienteRepository clienteRepository;

    @Override
    public ClienteResponse buscarPorDni(Long dni) {
        ClienteEntity cliente = clienteRepository.findById(dni).orElse(null);
        if (cliente != null) {
            return ClienteBuilder.entityToResponse(cliente);
        }
        return null;
    }

    @Override
    public List<ClienteResponse> buscarTodos() {
        List<ClienteEntity> listaClientesEntities = clienteRepository.findAll();
        List<ClienteResponse> listaClienteResponse = ClienteBuilder.entityToResponseList(listaClientesEntities);
        return listaClienteResponse;
    }

    @Override
    public ClienteResponse crear(ClienteRequest cliente) throws ApiException {
        try {
            if (buscarPorDni(cliente.getDni()) == null) {
                ClienteEntity clienteAGuardar = ClienteBuilder.requestToEntity(cliente);
                ClienteEntity entity = clienteRepository.save(clienteAGuardar);
                ClienteResponse clienteAResponder = ClienteBuilder.entityToResponse(entity);
                return clienteAResponder;
            } else {
                throw new ApiException("Cliente ya existe");
            }
        } catch (Exception e) {
            throw new ApiException(e.getMessage());
        }
    }

    @Override
    public ClienteResponse actualizar(ClienteRequest cliente) throws ApiException {
        try {
            if (buscarPorDni(cliente.getDni()) != null) {
                ClienteEntity entity = clienteRepository.save(ClienteBuilder.requestToEntity(cliente));
                return ClienteBuilder.entityToResponse(entity);
            } else {
                throw new ApiException("Cliente no existe");
            }
        } catch (Exception e) {
            throw new ApiException(e.getMessage());
        }
    }

    @Override
    public void eliminar(ClienteRequest cliente) throws ApiException {
        if (buscarPorDni(cliente.getDni()) != null) {
            ClienteEntity clienteAEliminar = ClienteBuilder.requestToEntity(cliente);
            clienteRepository.delete(clienteAEliminar);
        } else {
            throw new ApiException("Cliente no existe");
        }
    }

    @Override
    public void eliminarPorDni(Long dni) {
        clienteRepository.deleteById(dni);
    }
}
