package com.coderhouse.ventasexample.controller;

import com.coderhouse.ventasexample.handle.ApiException;
import com.coderhouse.ventasexample.model.request.ProductoRequest;
import com.coderhouse.ventasexample.model.response.ProductoResponse;
import com.coderhouse.ventasexample.service.ClienteService;
import com.coderhouse.ventasexample.service.ProductoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/productos")
public class ProductoController {

    private final ProductoService service;

    @GetMapping()
    public ResponseEntity<List<ProductoResponse>> obtenerProductos() {
        return ResponseEntity.ok(service.buscarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductoResponse> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(service.buscarPorId(id));
    }

    @PostMapping("/actualizar")
    public ResponseEntity<ProductoResponse> actualizar(@Valid @RequestBody ProductoRequest cliente) throws ApiException {
        return ResponseEntity.ok(service.actualizar(cliente));
    }

    @PostMapping("/crear")
    public ResponseEntity<ProductoResponse> crear(@Valid @RequestBody ProductoRequest producto) throws ApiException {
        return ResponseEntity.ok(service.crear(producto));
    }

    @PostMapping("/eliminar")
    public void eliminar(@Valid @RequestBody ProductoRequest producto) throws ApiException {
        service.eliminar(producto);
    }

    @PostMapping("/eliminar/{dni}")
    public void eliminarPorId(@PathVariable Long id) {
        service.eliminarPorId(id);
    }

}
