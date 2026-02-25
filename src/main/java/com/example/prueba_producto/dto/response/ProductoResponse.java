package com.example.prueba_producto.dto.response;

public record ProductoResponse(
        Long id,
        String nombre,
        String descripcion,
        Double precio,
        int cantidad
    ) {

}
