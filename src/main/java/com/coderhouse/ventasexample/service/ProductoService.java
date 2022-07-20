package com.coderhouse.ventasexample.service;

import com.coderhouse.ventasexample.handle.ApiException;
import com.coderhouse.ventasexample.model.request.ProductoRequest;
import com.coderhouse.ventasexample.model.response.ProductoResponse;

import java.util.List;

public interface ProductoService {

    ProductoResponse buscarPorId(Long id);
    ProductoResponse buscarPorCodigo(Integer codigo);
    List<ProductoResponse> buscarTodos();
    ProductoResponse crear(ProductoRequest producto) throws ApiException;
    ProductoResponse actualizar(ProductoRequest producto) throws ApiException;
    void eliminar(ProductoRequest producto) throws ApiException;
    void eliminarPorId(Long id);
}
