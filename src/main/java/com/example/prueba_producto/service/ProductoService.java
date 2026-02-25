package com.example.prueba_producto.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.prueba_producto.domain.entity.Producto;
import com.example.prueba_producto.dto.request.CreateProductoRequest;
import com.example.prueba_producto.dto.request.UpdateProductoRequest;
import com.example.prueba_producto.dto.response.ProductoResponse;
import com.example.prueba_producto.repository.ProductoRepository;

@Service
public class ProductoService {
    private final ProductoRepository repository;

    public ProductoService(ProductoRepository repository) {
        this.repository = repository;
    }

    // SAVE
    public ProductoResponse create(CreateProductoRequest request) {
        Producto producto = new Producto(
                request.nombre(),
                request.descripcion(),
                request.precio(),
                request.cantidad());

        Producto newProducto = repository.save(producto);

        return mapToResponse(newProducto);
    }

    // UPDATE
    public ProductoResponse update(UpdateProductoRequest request) {

        Producto productoEncontrado = repository.findById(request.id()).orElse(null);

        if (productoEncontrado == null) {
            return null;
        }

        productoEncontrado.setNombre(request.nombre());
        productoEncontrado.setDescricion(request.descripcion());
        productoEncontrado.setPrice(request.precio());
        productoEncontrado.setCantidad(request.cantidad());

        Producto Productoactualizado = repository.save(productoEncontrado);

        return mapToResponse(Productoactualizado);
    }

    // GET ALL
    public List<ProductoResponse> obtenerProductos() {
        List<Producto> productos = repository.findAll();

        if (productos.isEmpty()) {
            return null;
        }

        return productos.stream().map(this::mapToResponse).toList();
    }

    // GET BY ID
    public ProductoResponse obtenerProductoPorId(Long id) {
        Producto producto = repository.findById(id).orElse(null);
        if (producto == null) {
            return null;
        }

        return mapToResponse(producto);
    }

    // SAVE
    public boolean eliminar(Long id) {
        Producto productoEncontrado = repository.findById(id).orElse(null);

        if (productoEncontrado == null) {
            return false;
        }

        repository.delete(productoEncontrado);
        return true;
    }

    // OBTENER MENOS CONSONANTES
    public ProductoResponse obtenerProductoConMenosConsonantes() {
        List<Producto> productos = repository.findAll();

        if (productos.isEmpty()) {
            return null;
        }

        Producto productoSeleccionado = null;

        int menosConsonantes = Integer.MAX_VALUE;

        for (Producto producto : productos) {
            int consonantes = contarConsonantes(producto.getNombre());

            if (consonantes < menosConsonantes) {
                menosConsonantes = consonantes;
                productoSeleccionado = producto;
            }
        }

        return mapToResponse(productoSeleccionado);
    }

    // OBTENER NÚMERO PRIMO MÁS CERCANO AL PRECIO MÁS BAJO
    public Integer obtenerPrimoMasBajo() {
        List<Producto> productos = repository.findAll();

        if (productos.isEmpty()) {
            return 0;
        }


        // ENCONTRAR PRECIO MÁS BAJO
        Double precioMasBajo = productos.get(0).getPrice();

        for (Producto producto : productos) {
            if(producto.getPrice() < precioMasBajo){
                precioMasBajo = producto.getPrice();
            }
        }

        int precioEntero = (int) Math.round(precioMasBajo);

        return obtenerPrimoCercanoAlMenor(precioEntero);

    }

    public int totalPrecio(){
        List<Producto> productos = repository.findAll();
        if(productos == null || productos.isEmpty())
        return 0;

        return sumaRecursiva(productos, 0);
    }

    // HELPER PARA CONTAR CONSONANTES
    private int contarConsonantes(String text) {
        if (text == null || text.isBlank()) {
            return 0;
        }

        int contador = 0;
        String textoNormalizado = text.toLowerCase();
        String consonantes = "aeiouáéíóúü";

        for (char c : textoNormalizado.toCharArray()) {
            if (Character.isLetter(c) && !consonantes.contains(String.valueOf(c))) {
                contador++;
            }
        }

        return contador;
    }

    // HELPER PARA VERIFICAR SI UN NÚMERO ES PRIMO 
    // (Número natural mayor que 1 que tiene únicamente dos divisores positivos distintos: él mismo y el 1)
    private boolean esPrimo(int numero){
        if(numero <= 1)
            return false;

        for(int i = 2; i< numero; i ++){
            if(numero % i == 0) {
                return false;
            }
        }

        return true;
    }

    // HELPER PARA OBTENER EL NÚMERO PRIMO MÁS CERCANO AL MENOR 
    private int obtenerPrimoCercanoAlMenor(int menor){
        if(menor <= 0) return 2;

        int distancia = 0;

        while (distancia <= menor) {
            int numeroBajo = menor - distancia;

            int numeroSuperior = menor + distancia;

            if(numeroBajo >= 2 && esPrimo(numeroBajo)){
                return numeroBajo;
            }
             if(esPrimo(numeroSuperior)){
                return numeroSuperior;
            }

            distancia ++;
        }

        return 2;
    }

    private int sumaRecursiva(List<Producto> productos, int index){

        if(index >= productos.size()){
            return 0;
        }

        return productos.get(index).getCantidad() + sumaRecursiva(productos, index + 1);
    }


    // HELPER PARA MAPEAR DE ENTIDAD A DTO
    private ProductoResponse mapToResponse(Producto producto) {
        return new ProductoResponse(
                producto.getId(),
                producto.getNombre(),
                producto.getDescricion(),
                producto.getPrice(),
                producto.getCantidad());
    }
}
