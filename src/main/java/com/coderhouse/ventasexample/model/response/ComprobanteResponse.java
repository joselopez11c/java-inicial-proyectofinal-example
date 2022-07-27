package com.coderhouse.ventasexample.model.response;

import lombok.Builder;
import lombok.Data;

import java.util.Date;
import java.util.Set;

@Data
@Builder
public class ComprobanteResponse {

    private Long comprobanteId;
    private Date fecha;
    private Set<ItemResponse> item;
    private Integer estado;

}
