package com.coderhouse.ventasexample.model.response;

import com.coderhouse.ventasexample.model.request.ProductoRequest;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class ItemResponse {

    private ProductoResponse producto;

}
