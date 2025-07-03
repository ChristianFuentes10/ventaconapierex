package com.perfulandia.api.models;

import jakarta.persistence.*;
import lombok.*;
import java.util.Date;

@Data
@Entity
@Table(name = "ventas")
@NoArgsConstructor
@AllArgsConstructor
public class Venta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_venta")
    private Integer idVenta;

    @Column(name = "id_cliente")
    private Integer idCliente;

    @Column(name = "id_vendedor")
    private Integer idVendedor;

    @Column(name = "fecha_venta")
    private Date fecha_venta;
}