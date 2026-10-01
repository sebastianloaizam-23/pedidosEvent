package com.pedidos.eventos.modelo;

/**
 * Producto del catálogo. NO forma parte del patrón de eventos: es
 * simplemente el dato de inventario que los listeners consultan y
 * modifican (por ejemplo, InventarioListener le resta stock).
 */
public class Producto {
    private final String id;
    private final String nombre;
    private final long precio;
    private int stock;

    public Producto(String id, String nombre, long precio, int stock) {
        this.id = id;
        this.nombre = nombre;
        this.precio = precio;
        this.stock = stock;
    }

    public String getId() { return id; }
    public String getNombre() { return nombre; }
    public long getPrecio() { return precio; }
    public int getStock() { return stock; }

    public synchronized void decrementarStock() {
        if (stock > 0) stock--;
    }
}
