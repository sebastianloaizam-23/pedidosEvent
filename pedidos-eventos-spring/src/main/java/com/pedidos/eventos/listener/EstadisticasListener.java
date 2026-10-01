package com.pedidos.eventos.listener;

import com.pedidos.eventos.evento.Evento;
import com.pedidos.eventos.evento.PedidoCreado;
import com.pedidos.eventos.modelo.Catalogo;
import com.pedidos.eventos.modelo.Estadisticas;
import com.pedidos.eventos.modelo.Producto;

/** ESCUCHA: reacciona a PedidoCreado acumulando estadísticas reales. */
public class EstadisticasListener implements EscuchaEvento {
    @Override
    public void onEvento(Evento evento) {
        if (evento instanceof PedidoCreado pedido) {
            Producto producto = Catalogo.porNombre(pedido.getProducto());
            long precio = producto != null ? producto.getPrecio() : 0;
            Estadisticas.registrar(pedido.getProducto(), precio);
            System.out.println("[EstadisticasListener] Registrando venta del pedido #" + pedido.getIdPedido());
        }
    }
}
