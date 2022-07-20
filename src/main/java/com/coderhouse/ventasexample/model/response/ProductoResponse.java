package com.coderhouse.ventasexample.model.response;

import lombok.Builder;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.math.BigDecimal;

@Data
@Builder
public class ProductoResponse {

    private Integer productoId;
    private Integer codigo;
    private String descripcion;
    private Integer stock;
    private BigDecimal precio;
}
