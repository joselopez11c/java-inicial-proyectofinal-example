package com.coderhouse.ventasexample.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Entity
@Table(name = "COMPROBANTE")
public class ComprobanteEntity {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "COMPROBANTEID")
    private Integer comprobanteId;

    @Column(name = "CANTIDAD")
    private Integer cantidad;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "FECHA")
    private Date fecha;

    @Column(name = "TOTAL")
    private BigDecimal total;

    @Column(name = "ESTADO")
    private Integer estado;

    //bi-directional many-to-one association to Cliente
    @ManyToOne
    @JoinColumn(name="clienteid")
    private ClienteEntity cliente;

    //bi-directional many-to-one association to Linea
    @OneToMany(mappedBy="comprobante", fetch=FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<ItemEntity> item;

}
