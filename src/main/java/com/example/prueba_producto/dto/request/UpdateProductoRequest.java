package com.example.prueba_producto.dto.request;

public record UpdateProductoRequest(
        Long id,
        String nombre,
        String descripcion,
        Double precio,
        int cantidad) {

}
