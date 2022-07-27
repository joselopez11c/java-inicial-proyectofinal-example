package com.coderhouse.ventasexample.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.transaction.Transactional;
import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Entity
@Table(name = "ITEM")
@Transactional
public class ItemEntity {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "ITEMID")
    private Integer itemId;

    @Column(name = "CANTIDAD")
    private Integer cantidad;

    @Column(name = "DESCRIPCION")
    private String descripcion;

    @Column(name = "PRECIO")
    private BigDecimal precio;


    @Column(name = "COMPROBANTEID")
    private Integer comprobanteId;

    //bi-directional many-to-one association to Producto
    @ManyToOne
    @JoinColumn(name="PRODUCTOID")
    private ProductoEntity producto;
//
//    //bi-directional many-to-one association to Comprobante
//    @ManyToOne
//    @JoinColumn(name="COMPROBANTEID")
//    private ComprobanteEntity comprobante;


}
