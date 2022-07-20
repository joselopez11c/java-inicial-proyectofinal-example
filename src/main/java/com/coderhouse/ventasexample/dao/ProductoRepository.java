package com.coderhouse.ventasexample.dao;

import com.coderhouse.ventasexample.model.entity.ProductoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductoRepository extends JpaRepository<ProductoEntity, Long> {

    ProductoEntity findByCodigo(Integer codigo);

}
