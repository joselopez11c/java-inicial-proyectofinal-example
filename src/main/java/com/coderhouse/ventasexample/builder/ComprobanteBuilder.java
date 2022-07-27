package com.coderhouse.ventasexample.builder;

import com.coderhouse.ventasexample.model.entity.ComprobanteEntity;
import com.coderhouse.ventasexample.model.entity.ItemEntity;
import com.coderhouse.ventasexample.model.request.ComprobanteRequest;
import com.coderhouse.ventasexample.model.response.ComprobanteResponse;
import com.coderhouse.ventasexample.model.response.ItemResponse;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class ComprobanteBuilder {

    public static ComprobanteResponse entityToResponse(ComprobanteEntity comprobante) {
        return ComprobanteResponse.builder()
                .comprobanteId(comprobante.getComprobanteId().longValue())
                .fecha(comprobante.getFecha())
                //.item(itemsEntityToResponse(comprobante.getItem()))
                .estado(comprobante.getEstado())
                .build();
    }

    private static Set<ItemResponse> itemsEntityToResponse(Set<ItemEntity> item) {
        return item.stream().map(ComprobanteBuilder::itemEntityToResponse).collect(Collectors.toSet());
    }

    private static ItemResponse itemEntityToResponse(ItemEntity item) {
        return ItemResponse.builder()
                .producto(ProductoBuilder.entityToResponse(item.getProducto()))
                .build();
    }

}
