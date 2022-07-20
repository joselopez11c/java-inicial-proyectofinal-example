package com.coderhouse.ventasexample.model.request;

import lombok.Data;
import org.springframework.validation.annotation.Validated;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Set;

@Data
@Validated
public class ComprobanteRequest {

    private ClienteRequest cliente;
    private Set<ItemRequest> items;

}
