package com.coderhouse.ventasexample.builder;

import com.coderhouse.ventasexample.model.entity.ClienteEntity;
import com.coderhouse.ventasexample.model.request.ClienteRequest;
import com.coderhouse.ventasexample.model.response.ClienteResponse;

import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class ClienteBuilder {

    public static ClienteResponse entityToResponse(ClienteEntity cliente) {
        return ClienteResponse.builder()
                .dni(cliente.getDni())
                .apellido(cliente.getApellido())
                .nombre(cliente.getNombre())
                .build();
    }

    public static List<ClienteResponse> entityToResponseList(List<ClienteEntity> entityList) {
        return entityList.stream().map(ClienteBuilder::entityToResponse).collect(Collectors.toList());
    }

    public static ClienteEntity requestToEntity(ClienteRequest cliente) {
        return ClienteEntity.builder()
                .dni(cliente.getDni())
                .apellido(cliente.getApellido())
                .nombre(cliente.getNombre())
                .build();
    }
}
