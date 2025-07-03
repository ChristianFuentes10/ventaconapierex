package com.perfulandia.api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.perfulandia.api.dto.VentaDTO;
import com.perfulandia.api.services.VentaService;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;
import org.springframework.hateoas.Link;

@RestController
@RequestMapping("/api/ventas")
public class VentaController {

    @Autowired
    private VentaService service;

    @PostMapping
    public ResponseEntity<VentaDTO> crear(@RequestBody VentaDTO venta) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.crear(venta));
    }

    @GetMapping
    public ResponseEntity<List<VentaDTO>> listar() {
        return ResponseEntity.ok(service.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<VentaDTO> obtener(@PathVariable Integer id) {
        try {
            return ResponseEntity.ok(service.obtenerPorId(id));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<VentaDTO> actualizar(@PathVariable Integer id, @RequestBody VentaDTO venta) {
        try {
            return ResponseEntity.ok(service.actualizar(id, venta));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Integer id) {
        try {
            service.eliminar(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    //METODO HATEOAS para buscar por ID
    @GetMapping("/hateoas/{id}")
    public VentaDTO obtenerHATEOAS(@PathVariable Integer id) {
        VentaDTO dto = service.obtenerPorId(id);

        // links urls de la misma API
        dto.add(linkTo(methodOn(VentaController.class).obtenerHATEOAS(id)).withSelfRel());
        dto.add(linkTo(methodOn(VentaController.class).obtenerTodosHATEOAS()).withRel("todos"));
        dto.add(linkTo(methodOn(VentaController.class).eliminar(id)).withRel("eliminar"));

        // link HATEOAS para API Gateway "A mano"
        dto.add(Link.of("http://localhost:8888/api/proxy/ventas/" + dto.getId()).withSelfRel());
        dto.add(Link.of("http://localhost:8888/api/proxy/ventas/" + dto.getId()).withRel("Modificar HATEOAS").withType("PUT"));
        dto.add(Link.of("http://localhost:8888/api/proxy/ventas/" + dto.getId()).withRel("Eliminar HATEOAS").withType("DELETE"));

        return dto;
    }

    //METODO HATEOAS para listar todas las ventas utilizando HATEOAS
    @GetMapping("/hateoas")
    public List<VentaDTO> obtenerTodosHATEOAS() {
        List<VentaDTO> lista = service.listar();

        for (VentaDTO dto : lista) {
            // link url de la misma API
            dto.add(linkTo(methodOn(VentaController.class).obtenerHATEOAS(dto.getId())).withSelfRel());

            // link HATEOAS para API Gateway "A mano"
            dto.add(Link.of("http://localhost:8888/api/proxy/ventas").withRel("Get todos HATEOAS"));
            dto.add(Link.of("http://localhost:8888/api/proxy/ventas/" + dto.getId()).withRel("Crear HATEOAS").withType("POST"));
        }

        return lista;
    }
}
