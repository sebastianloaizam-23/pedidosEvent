package com.pedidos.eventos.listener;

import com.pedidos.eventos.evento.Evento;
import com.pedidos.eventos.evento.PedidoCreado;
import com.pedidos.eventos.modelo.Catalogo;
import com.pedidos.eventos.modelo.Producto;

/** ESCUCHA: reacciona a PedidoCreado descontando stock real del catálogo. */
public class InventarioListener implements EscuchaEvento {
    @Override
    public void onEvento(Evento evento) {
        if (evento instanceof PedidoCreado pedido) {
            Producto producto = Catalogo.porNombre(pedido.getProducto());
            if (producto != null) producto.decrementarStock();
            System.out.println("[InventarioListener] Actualizando inventario para: " + pedido.getProducto());
        }
    }
}
