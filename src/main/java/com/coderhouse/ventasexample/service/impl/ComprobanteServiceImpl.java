package com.coderhouse.ventasexample.service.impl;

import com.coderhouse.ventasexample.builder.ComprobanteBuilder;
import com.coderhouse.ventasexample.dao.ClienteRepository;
import com.coderhouse.ventasexample.dao.ComprobanteRepository;
import com.coderhouse.ventasexample.dao.ItemRepository;
import com.coderhouse.ventasexample.dao.ProductoRepository;
import com.coderhouse.ventasexample.handle.ApiException;
import com.coderhouse.ventasexample.model.entity.ComprobanteEntity;
import com.coderhouse.ventasexample.model.entity.ItemEntity;
import com.coderhouse.ventasexample.model.entity.ProductoEntity;
import com.coderhouse.ventasexample.model.request.ClienteRequest;
import com.coderhouse.ventasexample.model.request.ComprobanteRequest;
import com.coderhouse.ventasexample.model.request.ItemRequest;
import com.coderhouse.ventasexample.model.response.ComprobanteResponse;
import com.coderhouse.ventasexample.service.ComprobanteService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.swing.text.html.parser.Entity;
import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ComprobanteServiceImpl implements ComprobanteService {

    private final ComprobanteRepository comprobanteRepository;

    private final ItemRepository itemRepository;
    private final ClienteRepository clienteRepository;
    private final ProductoRepository productoRepository;


    @Override
    public ComprobanteResponse crear(ComprobanteRequest comprobante) throws ApiException {

        // Primero validamos si los datos del compronte son válidos
        if (isValidRequest(comprobante)) {
            // Transformamos el comprobante de la clase Request a Entity para ser guardado en la BD
            ComprobanteEntity entityAGuardar = crearComprobanteConProductos(comprobante);
            // El entity resultante lo guardamos usando el repository
            var entitySaved = comprobanteRepository.save(entityAGuardar);
            var items = itemRepository.saveAll(guardarItems(entitySaved.getComprobanteId(), entityAGuardar.getItem()));
            // Es necesario reudicr el stock de los productos vendidos
            reducirStockDeProductos(comprobante);
            // Solo queda responder a la capa Controller el comprobante en una clase Response
            return ComprobanteBuilder.entityToResponse(entitySaved);
        } else {
            throw new ApiException("Ocurrió un error inesperado");
        }
    }

    private List<ItemEntity> guardarItems(Integer comprobanteId, Set<ItemEntity> item) {
        return item.stream().peek(itemEntity -> itemEntity.setComprobanteId(comprobanteId)).collect(Collectors.toList());
    }

    private void reducirStockDeProductos(ComprobanteRequest comprobante) {
        //Recorremos la lista.
        comprobante.getItems().forEach(item -> {
            //Obtenemos el producto según el ID elegido
            var productoDB = this.productoRepository.findByCodigo(item.getProducto().getCodigo()).get();
            // Descontamos lo vendido con el stock actual en la BD
            Integer nuevoStock = productoDB.getStock() - item.getCantidad();
            // Asignamos el nuevo stock
            productoDB.setProductoId(nuevoStock.longValue());
            //Guardamos el producto con el stock actualizado
            productoRepository.save(productoDB);
        });
    }

    private ComprobanteEntity crearComprobanteConProductos(ComprobanteRequest comprobante) {
        return ComprobanteEntity.builder() // Este funciona por lombok al usar @Builder en la clase ComprobanteEntity
                .cliente(clienteRepository.findById(comprobante.getCliente().getDni()).get()) // Se guarda el cliente de la BD
                .fecha(obtenerFechaActual()) // Calculamos la fecha actual para el comprobante
                .item(obtenerItems(comprobante.getItems())) // Se usa un método para guardar todos los items (Convetir de Request a Entity)
                .total(calcularPrecioTotal(comprobante.getItems())) //Se suma todos los precios
                .estado(0) // Podemos tener estados según la venta, si es 0 puede ser emitido, 1 pagado, 2 cancelado.  ( OPCIONAL )
                .build();
    }

    private BigDecimal calcularPrecioTotal(Set<ItemRequest> items) {
        //Realizar lógica basado en lo aprendido. En este ejemplo es Java 8, para el caso del curso debe ser Java 7.
        return items.stream()
                .flatMap(item -> {
                    var producto = productoRepository.findByCodigo(item.getProducto().getCodigo());
                    return producto.stream().map(current -> current.getPrecio().multiply(BigDecimal.valueOf(item.getCantidad().longValue())));
                }).reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    private Set<ItemEntity> obtenerItems(Set<ItemRequest> items) {
        Set<ItemEntity> itemsEntity = new HashSet<>();
        //Hacemos un for o un foreach para recorrer la los items
        items.forEach(item -> itemsEntity.add(obtenerItem(item)));
        return itemsEntity;
    }

    private ItemEntity obtenerItem(ItemRequest item) {
        var producto = productoRepository.findByCodigo(item.getProducto().getCodigo());
        return ItemEntity.builder()
                .cantidad(item.getCantidad())
                .descripcion(producto.get().getDescripcion())
                .precio(producto.get().getPrecio())
                .producto(producto.get())
                .build();
    }

    private Date obtenerFechaActual() {
        //Aquí se debe invocar al sevricio externo
        return new Date();
    }

    private boolean isValidRequest(ComprobanteRequest comprobante) {
        // Aquí validamos si el cliente existe y tambien si los items son válidos
        return isValidCliente(comprobante.getCliente())
                && isValidItems(comprobante.getItems());
    }

    private boolean isValidItems(Set<ItemRequest> items) {
        // Se debe verificar que los productos existan y tengan suficiente stock para la venta
        return existsProducts(items) && existsStock(items);
    }

    private boolean existsStock(Set<ItemRequest> items) {
        // Como es una lista de productos debemos ver por cada producto. Para eso usamos el for.
        for (ItemRequest item : items) {
            var produdcto = existsProduct(item);
            if (existsProduct(item).isPresent()) {
                return true;
            }
            if (item.getCantidad() < produdcto.get().getStock()) {
                return false;
            }
        }
        return false;
    }

    private boolean existsProducts(Set<ItemRequest> items) {
        for (ItemRequest item : items) {
            return existsProduct(item).isPresent();
        }
        return true;
    }

    private Optional<ProductoEntity> existsProduct(ItemRequest item) {
        var codigoProducto = item.getProducto().getCodigo();
        return this.productoRepository.findByCodigo(codigoProducto);
    }

    private boolean isValidCliente(ClienteRequest cliente) {
        return clienteRepository.existsById(cliente.getDni());
    }

}
