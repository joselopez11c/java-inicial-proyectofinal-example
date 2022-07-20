package com.coderhouse.ventasexample.builder;

import com.coderhouse.ventasexample.model.entity.ProductoEntity;
import com.coderhouse.ventasexample.model.request.ProductoRequest;
import com.coderhouse.ventasexample.model.response.ProductoResponse;

import java.util.List;
import java.util.stream.Collectors;

public class ProductoBuilder {

    public static ProductoResponse entityToResponse(ProductoEntity producto) {
        return ProductoResponse.builder()
                .productoId(producto.getProductoId().intValue())
                .codigo(producto.getCodigo())
                .precio(producto.getPrecio())
                .stock(producto.getStock())
                .descripcion(producto.getDescripcion())
                .build();
    }

    public static List<ProductoResponse> entityToResponseList(List<ProductoEntity> entityList) {
        return entityList.stream().map(ProductoBuilder::entityToResponse).collect(Collectors.toList());
    }

    public static ProductoEntity requestToEntity(ProductoRequest producto) {
        return ProductoEntity.builder()
                .codigo(producto.getCodigo())
                .precio(producto.getPrecio())
                .stock(producto.getStock())
                .descripcion(producto.getDescripcion())
                .build();
    }
}
