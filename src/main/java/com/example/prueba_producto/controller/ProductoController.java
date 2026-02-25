package com.example.prueba_producto.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.prueba_producto.dto.request.CreateProductoRequest;
import com.example.prueba_producto.dto.request.UpdateProductoRequest;
import com.example.prueba_producto.dto.response.ProductoResponse;
import com.example.prueba_producto.service.ProductoService;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;



@RestController
@RequestMapping("/api/v1/productos")
public class ProductoController {
    private final ProductoService service;

    public ProductoController(ProductoService service) {
        this.service = service;
    }


    @PostMapping()
    public  ResponseEntity<ProductoResponse> create(@RequestBody CreateProductoRequest request) {
        //TODO: process POST request
        ProductoResponse response = service.create(request);
        return ResponseEntity.ok(response);
    }

    @PutMapping()
    public  ResponseEntity<ProductoResponse> update(@RequestBody UpdateProductoRequest request) {
        //TODO: process POST request
        ProductoResponse response = service.update(request);
        return ResponseEntity.ok(response);
    }

     @DeleteMapping("/{id}")
    public  boolean delete(@PathVariable Long id)  {
        //TODO: process POST request
        boolean response = service.eliminar(id);
        return response;
    }

    @GetMapping()
    public ResponseEntity<List<ProductoResponse>> obtenerProductos() {

        List<ProductoResponse> response = service.obtenerProductos();

        if(response == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductoResponse> obtenerProductoPorId(@PathVariable Long id) {

        ProductoResponse response = service.obtenerProductoPorId(id);

        if(response == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(response);
    }
    
    @GetMapping("/menos-consonantes")
    public ResponseEntity<ProductoResponse> obtenerMenosConsonantes() {

        ProductoResponse response = service.obtenerProductoConMenosConsonantes();

        if(response == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(response);
    }
    

    @GetMapping("/cercano-primo")
    public ResponseEntity<Integer> obetnerMasCercanoPrimo() {

        Integer response = service.obtenerPrimoMasBajo();

        if(response == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(response);
    }

    @GetMapping("total")
    public ResponseEntity<Integer> obetnerTotal() {

        Integer response = service.totalPrecio();
        
        return ResponseEntity.ok(response);
    }
    
}
