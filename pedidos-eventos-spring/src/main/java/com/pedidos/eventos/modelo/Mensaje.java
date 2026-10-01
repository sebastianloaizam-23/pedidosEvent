package com.pedidos.eventos.modelo;

/** Mensaje con marca de hora, usado por EmailListener y FacturaListener. */
public class Mensaje {
    public final String texto;
    public final String hora;

    public Mensaje(String texto, String hora) {
        this.texto = texto;
        this.hora = hora;
    }
}
