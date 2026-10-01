package com.pedidos.eventos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.awt.Desktop;
import java.net.URI;

/**
 * Punto de entrada de la VERSIÓN WEB (Spring Boot).
 *
 * Para la versión de consola (la que ya explicaste), sigue
 * ejecutando com.pedidos.eventos.Main como siempre — no depende de
 * Spring y sigue funcionando igual.
 */
@SpringBootApplication
public class TiendaApplication {
    public static void main(String[] args) {
        SpringApplication.run(TiendaApplication.class, args);
    }

    @Component
    static class AbrirNavegadorAlIniciar {
        @EventListener(ApplicationReadyEvent.class)
        public void alIniciar() {
            try {
                if (Desktop.isDesktopSupported() && Desktop.getDesktop().isSupported(Desktop.Action.BROWSE)) {
                    Desktop.getDesktop().browse(URI.create("http://localhost:8080"));
                } else {
                    System.out.println("Abre manualmente: http://localhost:8080");
                }
            } catch (Exception e) {
                System.out.println("Abre manualmente: http://localhost:8080");
            }
        }
    }
}
