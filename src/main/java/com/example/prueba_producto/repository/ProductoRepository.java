package com.example.prueba_producto.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.prueba_producto.domain.entity.Producto;

public interface ProductoRepository extends JpaRepository<Producto, Long> {

}
