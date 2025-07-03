package com.perfulandia.api.dto;

import org.springframework.hateoas.RepresentationModel;
import lombok.*;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VentaDTO extends RepresentationModel<VentaDTO> {

    private Integer id;
    private Integer idCliente;
    private Integer idVendedor;
    private Date fechaVenta;

}
