package com.pedidos.eventos;

import com.pedidos.eventos.bus.EventBus;
import com.pedidos.eventos.listener.EmailListener;
import com.pedidos.eventos.listener.EstadisticasListener;
import com.pedidos.eventos.listener.FacturaListener;
import com.pedidos.eventos.listener.InventarioListener;
import com.pedidos.eventos.service.PedidoService;

public class Main {
    public static void main(String[] args) {
        EventBus eventBus = new EventBus();

        // SUSCRIPCIÓN: cada listener se registra a sí mismo en el canal "pedidos".
        eventBus.suscribir("pedidos", new InventarioListener());
        eventBus.suscribir("pedidos", new EmailListener());
        eventBus.suscribir("pedidos", new EstadisticasListener());

        // Para demostrar la ventaja de EVOLUCIÓN sin tocar PedidoService,
        // descomenta la siguiente línea y vuelve a ejecutar:
        // eventBus.suscribir("pedidos", new FacturaListener());

        PedidoService pedidoService = new PedidoService(eventBus);

        pedidoService.crearPedido("Laptop");
        System.out.println();
        pedidoService.crearPedido("Teclado mecánico");
    }
}
