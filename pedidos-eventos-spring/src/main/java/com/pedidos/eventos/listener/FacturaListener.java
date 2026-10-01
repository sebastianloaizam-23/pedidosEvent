package com.pedidos.eventos.listener;

import com.pedidos.eventos.evento.Evento;
import com.pedidos.eventos.evento.PedidoCreado;
import com.pedidos.eventos.modelo.Catalogo;
import com.pedidos.eventos.modelo.Mensaje;
import com.pedidos.eventos.modelo.Producto;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * ESCUCHA NUEVO, agregado para demostrar la ventaja de EVOLUCIÓN.
 * Se registra con una sola línea; PedidoService y EventBus no
 * necesitan modificarse en absoluto para que este listener funcione.
 */
public class FacturaListener implements EscuchaEvento {
    private static final DateTimeFormatter FORMATO = DateTimeFormatter.ofPattern("HH:mm:ss");
    private static final List<Mensaje> FACTURAS = Collections.synchronizedList(new LinkedList<>());

    @Override
    public void onEvento(Evento evento) {
        if (evento instanceof PedidoCreado pedido) {
            Producto producto = Catalogo.porNombre(pedido.getProducto());
            long precio = producto != null ? producto.getPrecio() : 0;
            String texto = "Factura generada del pedido #" + pedido.getIdPedido() + " (" + pedido.getProducto() + ") - $" + precio;
            FACTURAS.add(0, new Mensaje(texto, LocalTime.now().format(FORMATO)));
            System.out.println("[FacturaListener] Generando factura del pedido #" + pedido.getIdPedido());
        }
    }

    public static List<Mensaje> obtenerFacturas() { return List.copyOf(FACTURAS); }
}
