package com.pedidos.eventos.modelo;

import java.util.LinkedHashMap;
import java.util.Map;

/** Estado compartido que actualiza EstadisticasListener en cada evento. */
public class Estadisticas {
    private static int totalPedidos = 0;
    private static int totalUnidades = 0;
    private static long totalIngresos = 0;
    private static final Map<String, Integer> porProducto = new LinkedHashMap<>();

    public static synchronized void registrar(String producto, long precio) {
        totalPedidos++;
        totalUnidades++;
        totalIngresos += precio;
        porProducto.merge(producto, 1, Integer::sum);
    }

    public static synchronized int getTotalPedidos() { return totalPedidos; }
    public static synchronized int getTotalUnidades() { return totalUnidades; }
    public static synchronized long getTotalIngresos() { return totalIngresos; }
    public static synchronized Map<String, Integer> getPorProducto() { return new LinkedHashMap<>(porProducto); }
}
