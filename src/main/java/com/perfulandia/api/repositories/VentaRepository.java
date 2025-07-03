package com.perfulandia.api.repositories;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.perfulandia.api.models.Venta;

@Repository
public interface VentaRepository extends JpaRepository<Venta, Integer> {
}