# Tienda — arquitectura basada en eventos (Spring Boot)

Proyecto Maven/Spring Boot. Dos formas de ejecutarlo, ambas usando las
mismas clases de arquitectura.

⚠️ **Importante**: no pude compilar ni correr este proyecto en mi
entorno de sandbox porque no tiene acceso a Maven Central (el
repositorio de donde se descargan las dependencias de Spring Boot).
Sí revisé el código a mano con cuidado, pero te recomiendo abrirlo en
IntelliJ (que sí tiene acceso a internet) y probarlo antes de tu
exposición, por si aparece algún detalle que se me haya escapado.

## Estructura

```
src/main/java/com/pedidos/eventos/
 ├── evento/        → Evento, PedidoCreado             (arquitectura, sin cambios)
 ├── bus/           → EventBus                          (arquitectura, sin cambios)
 ├── listener/      → Inventario/Email/Estadisticas/Historial/FacturaListener
 ├── service/       → PedidoService                     (arquitectura, sin cambios)
 ├── modelo/        → Producto, Catalogo, Estadisticas, RegistroPedido, Mensaje (NO es la arquitectura, es el "inventario" que los listeners consultan)
 ├── config/        → EventosConfig (arma el EventBus como bean de Spring)
 ├── web/           → TiendaController (REST, sin lógica de negocio)
 ├── Main.java      → versión de CONSOLA (la que ya explicaste, no usa Spring)
 └── TiendaApplication.java → versión web (Spring Boot)
src/main/resources/static/index.html → front de la tienda
pom.xml
```

## Cómo ejecutar en IntelliJ

1. Abre la carpeta `pedidos-eventos-spring` como proyecto Maven en
   IntelliJ (File → Open; IntelliJ detecta el `pom.xml` solo).
2. Espera a que descargue las dependencias (necesita internet).
3. Para la versión de **consola**: ejecuta `Main.java` — igual que
   siempre, sin Spring de por medio.
4. Para la versión **web**: ejecuta `TiendaApplication.java`. Al
   arrancar, se abre el navegador solo en `http://localhost:8080` con
   la tienda funcionando (catálogo, stock real, historial,
   estadísticas, confirmaciones).

## Qué es arquitectura y qué no

Lo que ya explicaste no cambió: `evento/`, `bus/`, `listener/` y
`service/` funcionan exactamente igual. Lo único nuevo:

- **`modelo/`** — no es parte del patrón, es el catálogo/inventario
  que antes no existía (antes los listeners solo imprimían texto).
- **`config/EventosConfig`** — el mismo código que antes tenías en
  `Main.java` para armar el bus y suscribir listeners, pero como
  configuración de Spring en vez de líneas sueltas.
- **`web/TiendaController`** — traduce clics del navegador en
  llamadas a `pedidoService.crearPedido(...)`. No decide nada de
  negocio, solo conecta.

## Endpoints (por si los quieres probar con Postman/curl)

- `GET /api/estado` — catálogo, historial, confirmaciones, facturas y
  estadísticas actuales.
- `POST /api/pedido?productoId=laptop` — crea un pedido real.
- `POST /api/factura?activar=true` — activa/desactiva `FacturaListener`
  en caliente.
