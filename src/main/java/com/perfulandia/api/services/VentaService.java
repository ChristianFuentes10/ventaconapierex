package com.perfulandia.api.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.perfulandia.api.models.Venta;
import com.perfulandia.api.repositories.VentaRepository;

@Service
public class VentaService {

    @Autowired
    VentaRepository repository; // Asumiendo que existe un repositorio para manejar las ventas

    public Venta guardar(Venta venta) {
        // Lógica para guardar una venta
        return null;
    }

    public List<Venta> listar() {
        // Lógica para listar todas las ventas
        return repository.findAll(); 
    }

    public Optional<Venta> obtenerPorId(Long id) {
        // Lógica para obtener una venta por ID
        return repository.findById(id);
                
    }

    public Venta actualizar(Long id, Venta venta) {
        // Lógica para actualizar una venta
        return repository.save(venta);
                
    }

    public void  eliminar(Long id) {
        // Lógica para eliminar una venta
        return repository.deleteById(id);
    }
}
