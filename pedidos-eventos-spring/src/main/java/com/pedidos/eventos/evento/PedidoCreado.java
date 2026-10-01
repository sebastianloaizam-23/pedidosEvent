package com.pedidos.eventos.evento;

/**
 * EVENTO concreto: representa el hecho de que un pedido fue creado.
 * Solo contiene la información que interesa a quienes reaccionan a él.
 */
public class PedidoCreado implements Evento {

    private final int idPedido;
    private final String producto;

    public PedidoCreado(int idPedido, String producto) {
        this.idPedido = idPedido;
        this.producto = producto;
    }

    public int getIdPedido() {
        return idPedido;
    }

    public String getProducto() {
        return producto;
    }

    @Override
    public String getCanal() {
        return "pedidos"; // CANAL: todos los eventos de pedidos viajan por aquí
    }

    @Override
    public String toString() {
        return "PedidoCreado{idPedido=" + idPedido + ", producto='" + producto + "'}";
    }
}
