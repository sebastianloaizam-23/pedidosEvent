package com.pedidos.eventos.bus;

import com.pedidos.eventos.evento.Evento;
import com.pedidos.eventos.listener.EscuchaEvento;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * BUS DE EVENTOS.
 * Es el intermediario: recibe publicaciones y notifica a los escuchas
 * suscritos a cada canal. El publicador y los escuchas nunca se conocen
 * entre sí directamente; solo conocen al bus. Aquí ocurre la
 * INVOCACIÓN IMPLÍCITA propia de este estilo arquitectónico.
 */
public class EventBus {

    // canal -> lista de escuchas suscritos a ese canal
    private final Map<String, List<EscuchaEvento>> suscriptores = new HashMap<>();

    /** Un componente se SUSCRIBE a un canal indicando qué hacer cuando llegue un evento. */
    public void suscribir(String canal, EscuchaEvento escucha) {
        suscriptores.computeIfAbsent(canal, k -> new ArrayList<>()).add(escucha);
    }

    /** Un componente PUBLICA un evento; el bus decide quién debe enterarse. */
    public void publicar(Evento evento) {
        String canal = evento.getCanal();
        System.out.println("[EventBus] Evento recibido en canal '" + canal + "': " + evento);

        List<EscuchaEvento> escuchas = suscriptores.getOrDefault(canal, List.of());
        for (EscuchaEvento escucha : escuchas) {
            // INVOCACIÓN IMPLÍCITA: el bus invoca al escucha. Ni el evento ni
            // PedidoService supieron nunca que este escucha existía.
            escucha.onEvento(evento);
        }
    }
}
