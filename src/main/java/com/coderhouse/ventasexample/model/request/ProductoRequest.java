package com.coderhouse.ventasexample.model.request;

import lombok.Data;
import org.springframework.validation.annotation.Validated;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.math.BigDecimal;

@Data
@Validated
public class ProductoRequest {

    private Integer codigo;
    private String descripcion;
    private Integer cantidad;
    private BigDecimal precio;

}
