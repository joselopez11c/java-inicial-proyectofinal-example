package com.coderhouse.ventasexample.service.impl;

import com.coderhouse.ventasexample.builder.ComprobanteBuilder;
import com.coderhouse.ventasexample.dao.ComprobanteRepository;
import com.coderhouse.ventasexample.handle.ApiException;
import com.coderhouse.ventasexample.model.entity.ComprobanteEntity;
import com.coderhouse.ventasexample.model.request.ComprobanteRequest;
import com.coderhouse.ventasexample.model.response.ComprobanteResponse;
import com.coderhouse.ventasexample.service.ComprobanteService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ComprobanteServiceImpl implements ComprobanteService {

    private final ComprobanteRepository repository;

    @Override
    public ComprobanteResponse buscarPorId(Long id) {
        ComprobanteEntity comprobante = repository.findById(id).orElse(null);
        return ComprobanteBuilder.entityToResponse(comprobante);
    }

    @Override
    public List<ComprobanteResponse> buscarTodos() {
        List<ComprobanteEntity> listaComprobantesEntities = repository.findAll();
        List<ComprobanteResponse> listaComprobanteResponse =
                ComprobanteBuilder.entityToResponseList(listaComprobantesEntities);
        return listaComprobanteResponse;
    }

    @Override
    public ComprobanteResponse crear(ComprobanteRequest comprobante) throws ApiException {
        try {
            if (buscarPorId(comprobante.getComprobanteId()) == null) {
                ComprobanteEntity comprobanteAGuardar = ComprobanteBuilder.requestToEntity(comprobante);
                ComprobanteEntity entity = repository.save(comprobanteAGuardar);
                ComprobanteResponse comprobanteAResponder = ComprobanteBuilder.entityToResponse(entity);
                return comprobanteAResponder;
            } else {
                throw new ApiException("Comprobante ya existe");
            }
        } catch (Exception e) {
            throw new ApiException(e.getMessage());
        }
    }

    @Override
    public ComprobanteResponse actualizar(ComprobanteRequest comprobante) throws ApiException {
        try {
            if (buscarPorId(comprobante.getComprobanteId()) != null) {
                ComprobanteEntity entity = repository.save(ComprobanteBuilder.requestToEntity(comprobante));
                return ComprobanteBuilder.entityToResponse(entity);
            } else {
                throw new ApiException("Comprobante no existe");
            }
        } catch (Exception e) {
            throw new ApiException(e.getMessage());
        }
    }

    @Override
    public void eliminar(ComprobanteRequest comprobante) throws ApiException {
        if (buscarPorId(comprobante.getComprobanteId()) != null) {
            ComprobanteEntity comprobanteAEliminar = ComprobanteBuilder.requestToEntity(comprobante);
            repository.delete(comprobanteAEliminar);
        } else {
            throw new ApiException("Comprobante no existe");
        }
    }

    @Override
    public void eliminarPorId(Long dni) {
        repository.deleteById(dni);
    }
}
