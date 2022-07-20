package com.coderhouse.ventasexample.model.request;

import com.coderhouse.ventasexample.model.entity.ComprobanteEntity;
import com.coderhouse.ventasexample.model.entity.ProductoEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class ItemRequest {

    private Integer cantidad;
    private ProductoRequest producto;


}
