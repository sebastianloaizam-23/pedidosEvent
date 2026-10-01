package com.pedidos.eventos.config;

import com.pedidos.eventos.bus.EventBus;
import com.pedidos.eventos.listener.EmailListener;
import com.pedidos.eventos.listener.EstadisticasListener;
import com.pedidos.eventos.listener.FacturaListener;
import com.pedidos.eventos.listener.HistorialListener;
import com.pedidos.eventos.listener.InventarioListener;
import com.pedidos.eventos.service.PedidoService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Único lugar donde Spring arma la arquitectura basada en eventos.
 * Las clases evento/, bus/, listener/ y service/ son EXACTAMENTE las
 * mismas que usa Main.java (versión de consola) — aquí solo se
 * conectan como beans para que el controlador REST las use.
 */
@Configuration
public class EventosConfig {

    @Bean
    public FacturaListener facturaListener() {
        return new FacturaListener();
    }

    @Bean
    public EventBus eventBus(FacturaListener facturaListener, EstadoFacturaActiva estadoFactura) {
        EventBus bus = new EventBus();

        bus.suscribir("pedidos", new InventarioListener());
        bus.suscribir("pedidos", new EmailListener());
        bus.suscribir("pedidos", new EstadisticasListener());
        bus.suscribir("pedidos", new HistorialListener());

        // Suscrito siempre, pero solo actúa si el checkbox del front lo
        // activó (el EventBus original no tiene "desuscribir"; así se
        // simula el apagado sin modificarlo).
        bus.suscribir("pedidos", evento -> {
            if (estadoFactura.isActiva()) facturaListener.onEvento(evento);
        });

        return bus;
    }

    @Bean
    public PedidoService pedidoService(EventBus eventBus) {
        return new PedidoService(eventBus);
    }
}
