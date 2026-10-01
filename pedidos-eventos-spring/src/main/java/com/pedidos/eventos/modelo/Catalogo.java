package com.pedidos.eventos.modelo;

import java.util.List;

/** Catálogo compartido en memoria de la tienda. */
public class Catalogo {

    private static final List<Producto> PRODUCTOS = List.of(
        new Producto("laptop",  "Laptop",               3200000, 5),
        new Producto("teclado", "Teclado mecanico",      180000, 12),
        new Producto("monitor", "Monitor 27 pulgadas",   850000, 4),
        new Producto("mouse",   "Mouse inalambrico",      65000, 20)
    );

    public static List<Producto> todos() { return PRODUCTOS; }

    public static Producto porId(String id) {
        return PRODUCTOS.stream().filter(p -> p.getId().equals(id)).findFirst().orElse(null);
    }

    public static Producto porNombre(String nombre) {
        return PRODUCTOS.stream().filter(p -> p.getNombre().equals(nombre)).findFirst().orElse(null);
    }
}
