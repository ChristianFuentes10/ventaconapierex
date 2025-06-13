package com.perfulandia.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.perfulandia.api.models.Venta;

public interface VentaRepository extends JpaRepository<Venta, Long> {
}