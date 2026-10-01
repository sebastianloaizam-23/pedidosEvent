package com.pedidos.eventos.evento;

/**
 * Interfaz base para todos los EVENTOS del sistema.
 * Un evento representa "algo que ocurrió" en el dominio de la aplicación.
 */
public interface Evento {
    /** Nombre del CANAL por el cual se publica este evento. */
    String getCanal();
}
