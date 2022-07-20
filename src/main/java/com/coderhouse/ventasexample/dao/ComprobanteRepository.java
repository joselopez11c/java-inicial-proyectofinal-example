package com.coderhouse.ventasexample.dao;

import com.coderhouse.ventasexample.model.entity.ComprobanteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ComprobanteRepository extends JpaRepository<ComprobanteEntity, Long> {

}
