package com.perfulandia.api.dto;

import lombok.Data;

@Data
public class VentaDTO {
    private Long id;
    private String producto;
    private int cantidad;
    private double precioUnitario;
}
