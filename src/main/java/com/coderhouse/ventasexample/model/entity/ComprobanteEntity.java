package com.coderhouse.ventasexample.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Entity
@Table(name = "COMPROBANTE")
@Transactional
public class ComprobanteEntity {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "COMPROBANTEID")
    private Integer comprobanteId;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "FECHA")
    private Date fecha;

    @Column(name = "TOTAL")
    private BigDecimal total;


    //bi-directional many-to-one association to Cliente
    @ManyToOne
    @JoinColumn(name="CLIENTEID")
    private ClienteEntity cliente;

    @Column(name = "ESTADO")
    private Integer estado;
//
//    //bi-directional many-to-one association to Linea
//    @OneToMany(mappedBy="comprobante", cascade = CascadeType.ALL)
    @Transient
    private Set<ItemEntity> item;

}
