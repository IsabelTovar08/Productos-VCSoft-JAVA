package com.example.prueba_producto.dto.request;

public record CreateProductoRequest(
    String nombre,
    String descripcion,
    Double precio,
    int cantidad
) {

}
