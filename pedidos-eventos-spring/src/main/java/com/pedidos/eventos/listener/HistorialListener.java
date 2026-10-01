package com.pedidos.eventos.listener;

import com.pedidos.eventos.evento.Evento;
import com.pedidos.eventos.evento.PedidoCreado;
import com.pedidos.eventos.modelo.Catalogo;
import com.pedidos.eventos.modelo.Producto;
import com.pedidos.eventos.modelo.RegistroPedido;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * ESCUCHA NUEVO: guarda cada pedido en un historial en memoria para
 * que el front pueda mostrarlo. Se agrega igual que cualquier otro
 * listener, sin tocar PedidoService ni EventBus — es la misma
 * demostración de "facilidad de evolución" del ejercicio original.
 */
public class HistorialListener implements EscuchaEvento {
    private static final List<RegistroPedido> HISTORIAL = Collections.synchronizedList(new LinkedList<>());

    @Override
    public void onEvento(Evento evento) {
        if (evento instanceof PedidoCreado pedido) {
            Producto producto = Catalogo.porNombre(pedido.getProducto());
            long precio = producto != null ? producto.getPrecio() : 0;
            HISTORIAL.add(0, new RegistroPedido(pedido.getIdPedido(), pedido.getProducto(), precio));
            System.out.println("[HistorialListener] Guardando pedido #" + pedido.getIdPedido() + " en el historial");
        }
    }

    public static List<RegistroPedido> obtenerHistorial() { return List.copyOf(HISTORIAL); }
}
