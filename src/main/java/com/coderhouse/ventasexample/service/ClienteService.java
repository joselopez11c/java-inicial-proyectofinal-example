package com.coderhouse.ventasexample.service;

import com.coderhouse.ventasexample.handle.ApiException;
import com.coderhouse.ventasexample.model.request.ClienteRequest;
import com.coderhouse.ventasexample.model.response.ClienteResponse;

import java.util.List;

public interface ClienteService {

    ClienteResponse buscarPorDni(Long dni) throws ApiException;
    List<ClienteResponse> buscarTodos();
    ClienteResponse crear(ClienteRequest cliente) throws ApiException;
    ClienteResponse actualizar(ClienteRequest cliente) throws ApiException;
    void eliminar(ClienteRequest cliente) throws ApiException;
    void eliminarPorDni(Long dni);
}
