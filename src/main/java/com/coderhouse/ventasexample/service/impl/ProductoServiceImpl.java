package com.coderhouse.ventasexample.service.impl;

import com.coderhouse.ventasexample.builder.ProductoBuilder;
import com.coderhouse.ventasexample.dao.ProductoRepository;
import com.coderhouse.ventasexample.handle.ApiException;
import com.coderhouse.ventasexample.model.entity.ProductoEntity;
import com.coderhouse.ventasexample.model.request.ProductoRequest;
import com.coderhouse.ventasexample.model.response.ProductoResponse;
import com.coderhouse.ventasexample.service.ProductoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductoServiceImpl implements ProductoService {

    private final ProductoRepository productoRepository;

    @Override
    public ProductoResponse buscarPorId(Long id) {
        ProductoEntity producto = productoRepository.findById(id).orElse(null);
        return ProductoBuilder.entityToResponse(producto);
    }

    @Override
    public ProductoResponse buscarPorCodigo(Integer codigo) {
        var producto = productoRepository.findByCodigo(codigo);
        if (producto.isPresent()) {
            return ProductoBuilder.entityToResponse(producto.get());
        }
        return null;
    }

    @Override
    public List<ProductoResponse> buscarTodos() {
        List<ProductoEntity> listaProductosEntities = productoRepository.findAll();
        return ProductoBuilder.entityToResponseList(listaProductosEntities);
    }

    @Override
    public ProductoResponse crear(ProductoRequest producto) throws ApiException {
        try {
            if (buscarPorCodigo(producto.getCodigo()) == null) {
                ProductoEntity productoAGuardar = ProductoBuilder.requestToEntity(producto);
                ProductoEntity entity = productoRepository.save(productoAGuardar);
                return ProductoBuilder.entityToResponse(entity);
            } else {
                throw new ApiException("Producto ya existe");
            }
        } catch (Exception e) {
            throw new ApiException(e.getMessage());
        }
    }

    @Override
    public ProductoResponse actualizar(ProductoRequest producto) throws ApiException {
        try {
            if (buscarPorCodigo(producto.getCodigo()) == null) {
                ProductoEntity entity = productoRepository.save(ProductoBuilder.requestToEntity(producto));
                return ProductoBuilder.entityToResponse(entity);
            } else {
                throw new ApiException("Producto no existe");
            }
        } catch (Exception e) {
            throw new ApiException(e.getMessage());
        }
    }

    @Override
    public void eliminar(ProductoRequest producto) throws ApiException {
        if (buscarPorCodigo(producto.getCodigo()) == null) {
            ProductoEntity productoAEliminar = ProductoBuilder.requestToEntity(producto);
            productoRepository.delete(productoAEliminar);
        } else {
            throw new ApiException("Producto no existe");
        }
    }

    @Override
    public void eliminarPorId(Long id) {
        productoRepository.deleteById(id);
    }
}
