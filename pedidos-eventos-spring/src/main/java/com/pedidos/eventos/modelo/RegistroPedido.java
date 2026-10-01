package com.pedidos.eventos.modelo;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/** Una fila del historial de pedidos, guardada por HistorialListener. */
public class RegistroPedido {
    public final int idPedido;
    public final String producto;
    public final long precio;
    public final String hora;

    public RegistroPedido(int idPedido, String producto, long precio) {
        this.idPedido = idPedido;
        this.producto = producto;
        this.precio = precio;
        this.hora = LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss"));
    }
}
