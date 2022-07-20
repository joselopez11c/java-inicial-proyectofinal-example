package com.coderhouse.ventasexample.service.impl;

import com.coderhouse.ventasexample.builder.ComprobanteBuilder;
import com.coderhouse.ventasexample.dao.ClienteRepository;
import com.coderhouse.ventasexample.dao.ComprobanteRepository;
import com.coderhouse.ventasexample.dao.ProductoRepository;
import com.coderhouse.ventasexample.handle.ApiException;
import com.coderhouse.ventasexample.model.request.ClienteRequest;
import com.coderhouse.ventasexample.model.request.ComprobanteRequest;
import com.coderhouse.ventasexample.model.request.ItemRequest;
import com.coderhouse.ventasexample.model.response.ComprobanteResponse;
import com.coderhouse.ventasexample.service.ComprobanteService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class ComprobanteServiceImpl implements ComprobanteService {

    private final ComprobanteRepository comprobanteRepository;
    private final ClienteRepository clienteRepository;
    private final ProductoRepository productoRepository;


    @Override
    public ComprobanteResponse crear(ComprobanteRequest comprobante) throws ApiException {

            if (isValidRequest(comprobante)) {
                var entity = ComprobanteBuilder.requestToEntity(comprobante);
                var entitySaved = comprobanteRepository.save(entity);
                return ComprobanteBuilder.entityToResponse(entitySaved);
            } else {
                throw new ApiException("Ocurrió un error inesperado");
            }
    }

    private boolean isValidRequest(ComprobanteRequest comprobante) throws ApiException {
        return isValidCliente(comprobante.getCliente()) && isValidItems(comprobante.getItems());
    }

    private boolean isValidItems(Set<ItemRequest> items) {
        return false;
    }

    private boolean isValidCliente(ClienteRequest cliente) {
        return false;
    }

}
