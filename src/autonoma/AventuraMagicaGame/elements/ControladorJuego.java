package autonoma.AventuraMagicaGame.elements;


import autonoma.AventuraMagicaGameBase.elements.Sprite;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase que gestiona la lógica principal del juego para un nivel específico.
 * Controla el jugador, enemigos, artefactos, símbolos de pregunta y sus interacciones.
 * @author Alejandra ortega 
 * @since 26-05-2025
 * @version 2.0
 */
 
public class ControladorJuego {
private Jugador jugador;
    private Nivel nivel;
    private List<Enemigo> enemigos;
    private List<Artefacto> artefactos;
    private List<SimboloPregunta> simbolos;

    /**
     * Constructor que inicializa el controlador con el nivel actual.
     * @param nivel Nivel que contiene enemigos, artefactos y símbolos.
     */
    public ControladorJuego(Nivel nivel) {
        if (nivel == null) {
            throw new IllegalArgumentException("El nivel no puede ser nulo");
        }
        this.nivel = nivel;
        this.jugador = new Jugador(400, 500);
        this.enemigos = new ArrayList<>(nivel.getEnemigos());
        this.artefactos = new ArrayList<>(nivel.getArtefactos());
        this.simbolos = new ArrayList<>(nivel.getSimbolosPregunta());
    }

    public void dibujarJugador(Graphics g) {
        if (jugador != null) {
            jugador.dibujar(g);
        }
    }

    public void dibujarEnemigos(Graphics g) {
        for (Enemigo enemigo : enemigos) {
            if (enemigo != null && enemigo.isVisible()) {
                enemigo.dibujar(g);
            }
        }
    }

    public void dibujarArtefactos(Graphics g) {
        for (Artefacto artefacto : artefactos) {
            if (artefacto != null && artefacto.isVisible() && !artefacto.isRecolectado()) {
                artefacto.dibujar(g);
            }
        }
    }

    public void dibujarSimbolos(Graphics g) {
        for (SimboloPregunta simbolo : simbolos) {
            if (simbolo != null && simbolo.isVisible()) {
                simbolo.dibujar(g);
            }
        }
    }

    /**
     * Verifica colisiones entre el jugador y los elementos del juego:
     * enemigos, artefactos y símbolos de pregunta.
     */
    public void verificarColisiones() {
        // Colisión con enemigos
        for (Enemigo enemigo : enemigos) {
            if (enemigo != null && enemigo.isVisible() && colision(jugador, enemigo)) {
                jugador.reducirVida(10);
                enemigo.setVisible(false);
            }
        }

        // Colisión con artefactos
        for (Artefacto artefacto : artefactos) {
            if (artefacto != null && artefacto.isVisible() && !artefacto.isRecolectado() && colision(jugador, artefacto)) {
                artefacto.setRecolectado(true);

                if (artefacto instanceof Botella) {
                    jugador.recolectarBotella();
                } else if (artefacto instanceof Esmeralda) {
                    jugador.recolectarEsmeralda();
                }
            }
        }

        // Colisión con símbolos
        for (SimboloPregunta simbolo : simbolos) {
            if (simbolo != null && simbolo.isVisible() &&
                simbolo.verificarColision(jugador.getX(), jugador.getY(), jugador.getAncho(), jugador.getAlto())) {

                int puntos = simbolo.manejarColision();

                if (puntos > 0) {
                    jugador.aumentarPuntaje(puntos);
                } else if (puntos < 0) {
                    jugador.disminuirPuntaje(-puntos);
                }
            }
        }
    }

    /**
     * Mueve todos los enemigos visibles.
     */
    public void moverEnemigos() {
        for (Enemigo enemigo : enemigos) {
            if (enemigo != null && enemigo.isVisible()) {
                enemigo.mover();
            }
        }
    }

    public Jugador getJugador() {
        return jugador;
    }

    /**
     * Verifica si hay colisión entre dos sprites.
     * @param a Sprite A
     * @param b Sprite B
     * @return true si hay colisión.
     */
    private boolean colision(Sprite a, Sprite b) {
        return a != null && b != null && a.getBounds().intersects(b.getBounds());
    }
}