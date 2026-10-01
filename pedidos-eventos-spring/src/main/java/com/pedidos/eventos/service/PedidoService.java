package com.pedidos.eventos.service;

import com.pedidos.eventos.bus.EventBus;
import com.pedidos.eventos.evento.PedidoCreado;

/**
 * FUENTE DE EVENTO (productor / publicador).
 * Este servicio solo conoce al EventBus y sabe publicar en él.
 * NO conoce, ni le importa, quién reacciona al evento PedidoCreado.
 * Este es el punto clave del DESACOPLAMIENTO de la arquitectura.
 */
public class PedidoService {

    private final EventBus eventBus;
    private int contadorId = 1;

    public PedidoService(EventBus eventBus) {
        this.eventBus = eventBus;
    }

    public void crearPedido(String producto) {
        int id = contadorId++;
        System.out.println("Pedido creado: #" + id + " (" + producto + ")");

        PedidoCreado evento = new PedidoCreado(id, producto);

        System.out.println("[PedidoService] Publicando evento: " + evento.getClass().getSimpleName());
        eventBus.publicar(evento); // CONECTOR: publicación en el bus, NO llamada directa a listeners
    }
}
