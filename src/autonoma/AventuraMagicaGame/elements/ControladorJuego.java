package autonoma.AventuraMagicaGame.elements;

import autonoma.AventuraMagica.elements.Enemigo;
import autonoma.AventuraMagica.elements.Esmeralda;
import autonoma.AventuraMagica.elements.Jugador;
import autonoma.AventuraMagica.elements.Nivel;
import autonoma.AventuraMagica.elements.SimboloPregunta;
import autonoma.AventuraMagicaBase.elements.Sprite;

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
     * Constructor de la clase ControladorJuego.
     * Inicializa el jugador y carga todos los elementos del nivel proporcionado.
     *
     * @param nivel El nivel que contiene enemigos, artefactos y símbolos de pregunta.
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

    /**
     * Dibuja al jugador en pantalla si está disponible.
     *
     * @param g El contexto gráfico donde se dibujará.
     */
    public void dibujarJugador(Graphics g) {
        if (jugador != null) {
            jugador.dibujar(g);
        }
    }

    /**
     * Dibuja todos los enemigos visibles del nivel.
     *
     * @param g El contexto gráfico donde se dibujarán.
     */
    public void dibujarEnemigos(Graphics g) {
        for (Enemigo enemigo : enemigos) {
            if (enemigo != null && enemigo.isVisible()) {
                enemigo.dibujar(g);
            }
        }
    }

    /**
     * Dibuja todos los artefactos visibles que no han sido recolectados.
     *
     * @param g El contexto gráfico donde se dibujarán.
     */
    public void dibujarArtefactos(Graphics g) {
        for (Artefacto artefacto : artefactos) {
            if (artefacto != null && artefacto.isVisible() && !artefacto.isRecolectado()) {
                artefacto.dibujar(g);
            }
        }
    }

    /**
     * Dibuja los símbolos de pregunta visibles del nivel.
     *
     * @param g El contexto gráfico donde se dibujarán.
     */
    public void dibujarSimbolos(Graphics g) {
        for (SimboloPregunta simbolo : simbolos) {
            if (simbolo != null && simbolo.isVisible()) {
                simbolo.dibujar(g);
            }
        }
    }

    /**
     * Verifica todas las colisiones entre el jugador y:
     * - enemigos (reduce vida),
     * - artefactos (activa efecto y cambia estado),
     * - símbolos de pregunta (modifica puntaje).
     */
    public void verificarColisiones() {
        // Colisiones con enemigos
        for (Enemigo enemigo : enemigos) {
            if (enemigo.isVisible() && colision(jugador, enemigo)) {
                jugador.reducirVida(10);
                enemigo.setVisible(false);
            }
        }

        // Colisiones con artefactos
        for (Artefacto artefacto : artefactos) {
            if (artefacto.isVisible() && !artefacto.isRecolectado() && colision(jugador, artefacto)) {
                artefacto.setRecolectado(true);

                // Aplica efectos adicionales según el tipo de artefacto
                if (artefacto instanceof Botella) {
                    jugador.recolectarBotella();
                } else if (artefacto instanceof Esmeralda) {
                    jugador.recolectarEsmeralda();
                }
            }
        }

        // Colisiones con símbolos de pregunta
        for (SimboloPregunta simbolo : simbolos) {
            if (simbolo.isVisible() &&
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
     * Mueve a todos los enemigos visibles en el juego.
     */
    public void moverEnemigos() {
        for (Enemigo enemigo : enemigos) {
            if (enemigo.isVisible()) {
                enemigo.mover();
            }
        }
    }

    /**
     * Devuelve el jugador actual.
     *
     * @return Instancia del jugador.
     */
    public Jugador getJugador() {
        return jugador;
    }

    /**
     * Verifica si hay colisión entre el jugador y un artefacto.
     *
     * @param jugador El jugador del juego.
     * @param artefacto El artefacto a verificar.
     * @return true si hay intersección; false en caso contrario.
     */
    private boolean colision(Jugador jugador, Artefacto artefacto) {
        return jugador.getBounds().intersects(artefacto.getBounds());
    }

    /**
     * Verifica si hay colisión entre el jugador y un enemigo.
     *
     * @param jugador El jugador del juego.
     * @param enemigo El enemigo a verificar.
     * @return true si hay intersección; false en caso contrario.
     */
    private boolean colision(Jugador jugador, Enemigo enemigo) {
        return jugador.getBounds().intersects(enemigo.getBounds());
    }

    /**
     * Verifica si hay colisión entre dos sprites genéricos.
     *
     * @param a Sprite A.
     * @param b Sprite B.
     * @return true si hay intersección; false en caso contrario.
     */
    private boolean colision(Sprite a, Sprite b) {
        return a.getBounds().intersects(b.getBounds());
    }
}
