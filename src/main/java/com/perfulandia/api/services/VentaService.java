package com.perfulandia.api.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.perfulandia.api.dto.VentaDTO;
import com.perfulandia.api.models.Venta;
import com.perfulandia.api.repositories.VentaRepository;

@Service
public class VentaService {

    @Autowired
    private VentaRepository ventaRepository;

    private VentaDTO toDTO(Venta venta){
        return new VentaDTO(
            venta.getIdVenta(),
            venta.getIdCliente(),
            venta.getIdVendedor(),
            venta.getFecha_venta() // Usar el getter correcto según la entidad
        );
    }

    private Venta toEntity(VentaDTO dto) {
        Venta venta = new Venta();
        venta.setIdVenta(dto.getId());
        venta.setIdCliente(dto.getIdCliente());
        venta.setIdVendedor(dto.getIdVendedor());
        venta.setFecha_venta(dto.getFechaVenta()); // Usar el setter correcto según la entidad
        return venta;
    }

    public VentaDTO crear(VentaDTO dto) {
        Venta venta = toEntity(dto);
        return toDTO(ventaRepository.save(venta));
    }

    public List<VentaDTO> listar() {
        return ventaRepository.findAll().stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public VentaDTO obtenerPorId(Integer id) {
        Venta venta = ventaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Venta no encontrada"));
        return toDTO(venta);
    }

    public VentaDTO actualizar(Integer id, VentaDTO dto) {
        Venta existente = ventaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Venta no encontrada"));

        existente.setIdCliente(dto.getIdCliente());
        existente.setIdVendedor(dto.getIdVendedor());
        existente.setFecha_venta(dto.getFechaVenta()); // Usar el setter correcto

        return toDTO(ventaRepository.save(existente));
    }

    public void eliminar(Integer id) {
        ventaRepository.deleteById(id);
    }
}
