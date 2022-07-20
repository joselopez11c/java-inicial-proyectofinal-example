package com.coderhouse.ventasexample.model.response;

import lombok.*;

@Data
@Builder
public class ClienteResponse {

    private String nombre;
    private String apellido;
    private long dni;
}
