package com.pedidos.eventos.listener;

import com.pedidos.eventos.evento.Evento;

/**
 * Contrato que deben cumplir todos los ESCUCHAS (listeners).
 * Cada listener implementa onEvento(...) con la reacción que le interesa.
 */
public interface EscuchaEvento {
    void onEvento(Evento evento);
}
