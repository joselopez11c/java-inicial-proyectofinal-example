package com.coderhouse.ventasexample.builder;

import com.coderhouse.ventasexample.model.entity.ComprobanteEntity;
import com.coderhouse.ventasexample.model.request.ComprobanteRequest;
import com.coderhouse.ventasexample.model.response.ComprobanteResponse;

import java.util.List;
import java.util.stream.Collectors;

public class ComprobanteBuilder {

    public static ComprobanteResponse entityToResponse(ComprobanteEntity comprobante) {
        return ComprobanteResponse.builder()
                .build();
    }

    public static ComprobanteEntity requestToEntity(ComprobanteRequest comprobante) {
        return ComprobanteEntity.builder()
                .build();
    }

    public static List<ComprobanteResponse> entityToResponseList(List<ComprobanteEntity> entities) {
        return entities.stream().map(ComprobanteBuilder::entityToResponse).collect(Collectors.toList());
    }
}
