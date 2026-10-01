package com.pedidos.eventos.config;

import org.springframework.stereotype.Component;

/**
 * Guarda si el checkbox de "activar FacturaListener" está prendido.
 * No forma parte del patrón de eventos: es solo el interruptor que
 * usa el bean de EventBus para decidir si FacturaListener actúa.
 */
@Component
public class EstadoFacturaActiva {
    private volatile boolean activa = false;

    public boolean isActiva() { return activa; }
    public void setActiva(boolean activa) { this.activa = activa; }
}
