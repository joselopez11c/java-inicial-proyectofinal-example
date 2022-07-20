package com.coderhouse.ventasexample.service;

import com.coderhouse.ventasexample.handle.ApiException;
import com.coderhouse.ventasexample.model.request.ComprobanteRequest;
import com.coderhouse.ventasexample.model.response.ComprobanteResponse;

import java.util.List;

public interface ComprobanteService {

    ComprobanteResponse crear(ComprobanteRequest request) throws ApiException;
}
