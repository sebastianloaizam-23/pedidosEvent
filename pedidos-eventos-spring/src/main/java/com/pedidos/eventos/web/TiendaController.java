package com.pedidos.eventos.web;

import com.pedidos.eventos.config.EstadoFacturaActiva;
import com.pedidos.eventos.listener.EmailListener;
import com.pedidos.eventos.listener.FacturaListener;
import com.pedidos.eventos.listener.HistorialListener;
import com.pedidos.eventos.modelo.Catalogo;
import com.pedidos.eventos.modelo.Estadisticas;
import com.pedidos.eventos.modelo.Producto;
import com.pedidos.eventos.service.PedidoService;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * NO contiene lógica de negocio ni de la arquitectura de eventos.
 * Solo traduce clics del navegador en llamadas reales a
 * PedidoService, y devuelve el estado actual como JSON (Spring +
 * Jackson lo serializan solos, sin código manual).
 */
@RestController
@RequestMapping("/api")
public class TiendaController {

    private final PedidoService pedidoService;
    private final EstadoFacturaActiva estadoFactura;

    public TiendaController(PedidoService pedidoService, EstadoFacturaActiva estadoFactura) {
        this.pedidoService = pedidoService;
        this.estadoFactura = estadoFactura;
    }

    @GetMapping("/estado")
    public Map<String, Object> estado() {
        return construirEstado();
    }

    @PostMapping("/pedido")
    public Map<String, Object> crearPedido(@RequestParam String productoId) {
        Producto producto = Catalogo.porId(productoId);
        if (producto != null && producto.getStock() > 0) {
            pedidoService.crearPedido(producto.getNombre()); // llamada real a la arquitectura
        }
        return construirEstado();
    }

    @PostMapping("/factura")
    public Map<String, Object> factura(@RequestParam boolean activar) {
        estadoFactura.setActiva(activar);
        return construirEstado();
    }

    private Map<String, Object> construirEstado() {
        Map<String, Object> estado = new LinkedHashMap<>();
        estado.put("catalogo", Catalogo.todos());
        estado.put("historial", HistorialListener.obtenerHistorial());
        estado.put("confirmaciones", EmailListener.obtenerConfirmaciones());
        estado.put("facturas", FacturaListener.obtenerFacturas());
        estado.put("facturaActiva", estadoFactura.isActiva());

        Map<String, Object> stats = new LinkedHashMap<>();
        stats.put("totalPedidos", Estadisticas.getTotalPedidos());
        stats.put("totalUnidades", Estadisticas.getTotalUnidades());
        stats.put("totalIngresos", Estadisticas.getTotalIngresos());
        stats.put("porProducto", Estadisticas.getPorProducto());
        estado.put("estadisticas", stats);

        return estado;
    }
}
