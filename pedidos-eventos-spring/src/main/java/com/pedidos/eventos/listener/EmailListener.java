package com.pedidos.eventos.listener;

import com.pedidos.eventos.evento.Evento;
import com.pedidos.eventos.evento.PedidoCreado;
import com.pedidos.eventos.modelo.Mensaje;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/** ESCUCHA: reacciona a PedidoCreado guardando una confirmación real. */
public class EmailListener implements EscuchaEvento {
    private static final DateTimeFormatter FORMATO = DateTimeFormatter.ofPattern("HH:mm:ss");
    private static final List<Mensaje> CONFIRMACIONES = Collections.synchronizedList(new LinkedList<>());

    @Override
    public void onEvento(Evento evento) {
        if (evento instanceof PedidoCreado pedido) {
            String texto = "Confirmación enviada del pedido #" + pedido.getIdPedido() + " (" + pedido.getProducto() + ")";
            CONFIRMACIONES.add(0, new Mensaje(texto, LocalTime.now().format(FORMATO)));
            System.out.println("[EmailListener] Enviando confirmación del pedido #" + pedido.getIdPedido());
        }
    }

    public static List<Mensaje> obtenerConfirmaciones() { return List.copyOf(CONFIRMACIONES); }
}
