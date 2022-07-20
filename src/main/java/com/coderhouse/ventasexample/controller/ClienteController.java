package com.coderhouse.ventasexample.controller;

import com.coderhouse.ventasexample.handle.ApiException;
import com.coderhouse.ventasexample.model.request.ClienteRequest;
import com.coderhouse.ventasexample.model.response.ClienteResponse;
import com.coderhouse.ventasexample.service.ClienteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/clientes")
public class ClienteController {

    private final ClienteService clienteService;

    @GetMapping()
    public ResponseEntity<List<ClienteResponse>> obtenerClientes() {
        return ResponseEntity.ok(clienteService.buscarTodos());
    }

    @GetMapping("/{dni}")
    public ResponseEntity<ClienteResponse> buscarClientePorDni(@PathVariable Long dni) throws ApiException {
        return ResponseEntity.ok(clienteService.buscarPorDni(dni));
    }

    @PostMapping("/actualizar")
    public ResponseEntity<ClienteResponse> actualizarCliente(@Valid @RequestBody ClienteRequest cliente) throws ApiException {
        return ResponseEntity.ok(clienteService.actualizar(cliente));
    }

    @PostMapping("/crear")
    public ResponseEntity<ClienteResponse> crearCliente(@Valid @RequestBody ClienteRequest cliente) throws ApiException {
        return ResponseEntity.ok(clienteService.crear(cliente));
    }

    @PostMapping("/eliminar")
    public void eliminar(@Valid @RequestBody ClienteRequest cliente) throws ApiException {
        clienteService.eliminar(cliente);
    }

    @PostMapping("/eliminar/{dni}")
    public void eliminar(@PathVariable Long dni) {
        clienteService.eliminarPorDni(dni);
    }

}
