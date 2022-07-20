package com.coderhouse.ventasexample.service;

import com.coderhouse.ventasexample.handle.ApiException;
import com.coderhouse.ventasexample.model.request.ComprobanteRequest;
import com.coderhouse.ventasexample.model.response.ComprobanteResponse;

import java.util.List;

public interface ComprobanteService {

    ComprobanteResponse buscarPorId(Long id);
    List<ComprobanteResponse> buscarTodos();
    ComprobanteResponse crear(ComprobanteRequest comprobante) throws ApiException;
    ComprobanteResponse actualizar(ComprobanteRequest comprobante) throws ApiException;
    void eliminar(ComprobanteRequest comprobante) throws ApiException;
    void eliminarPorId(Long id);
}
