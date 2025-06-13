package com.perfulandia.api.restcontrollers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.perfulandia.api.models.Venta;
import com.perfulandia.api.services.VentaService;

@RestController
@RequestMapping("/api/ventas")
public class VentaController {

    @Autowired
    private VentaService service;

    @PostMapping
    public ResponseEntity<Venta> crear(@RequestBody Venta venta) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.agregar(venta));
    }

    @GetMapping
    public ResponseEntity<List<Venta>> listar() {
        return ResponseEntity.ok(service.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Venta> obtener(@PathVariable Long id) {
        Optional<Venta> venta = service.obtenerPorId(id);
        if (venta.isPresent()) {
            return ResponseEntity.ok(venta.get());
        } else {
            return ResponseEntity.notFound().build();
        }
                
    }

    @PutMapping("/{id}")
    public ResponseEntity<Venta> actualizar(@PathVariable Long id, @RequestBody Venta venta) {
        Optional<Venta> ventaExistente = service.obtenerPorId(id);
        
        if (ventaExistente.isPresent()) {
            Venta ventaDb = ventaExistente.get();
            ventaDb.setProducto(venta.getProducto());
            ventaDb.setCantidad(venta.getCantidad());
            ventaDb.setPrecioUnitario(venta.getPrecioUnitario());
            Venta ventaActualizada = service.actualizar(id, ventaDb);
            return ResponseEntity.ok(ventaActualizada);
        } 
        else 
        {  
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id) {
        Optional<Venta> venta = service.obtenerPorId(id);
        if (venta.isPresent()) {
            service.eliminar(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
