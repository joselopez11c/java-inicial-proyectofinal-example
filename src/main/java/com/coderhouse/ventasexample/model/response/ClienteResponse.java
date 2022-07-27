package com.coderhouse.ventasexample.model.response;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ClienteResponse {

    private String nombre;
    private String apellido;
    private long dni;
}
