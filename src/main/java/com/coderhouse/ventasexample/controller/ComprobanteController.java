package com.coderhouse.ventasexample.controller;

import com.coderhouse.ventasexample.handle.ApiException;
import com.coderhouse.ventasexample.model.request.ComprobanteRequest;
import com.coderhouse.ventasexample.model.response.ComprobanteResponse;
import com.coderhouse.ventasexample.service.ComprobanteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/comprobante")
public class ComprobanteController {

    private final ComprobanteService service;

    @PostMapping()
    public ResponseEntity<ComprobanteResponse> crearComprobante(@RequestBody ComprobanteRequest request) throws ApiException {
        return ResponseEntity.ok(service.crear(request));
    }

}
