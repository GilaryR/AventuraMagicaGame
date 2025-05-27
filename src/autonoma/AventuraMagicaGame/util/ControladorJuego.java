package autonoma.AventuraMagicaGame.util;

import autonoma.AventuraMagicaGame.elements.Artefacto;
import autonoma.AventuraMagicaGame.elements.Botella;
import autonoma.AventuraMagicaGame.elements.Enemigo;
import autonoma.AventuraMagicaGame.elements.Esmeralda;
import autonoma.AventuraMagicaGame.elements.Jugador;
import autonoma.AventuraMagicaGame.elements.Nivel;
import autonoma.AventuraMagicaGame.elements.NivelBase;
import autonoma.AventuraMagicaGame.elements.SimboloPregunta;
import autonoma.AventuraMagicaGameBase.elements.Sprite;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase que gestiona la lógica principal del juego para un nivel específico.
 * Controla el jugador, enemigos, artefactos, símbolos de pregunta y sus interacciones.
 * @author Alejandra Ortega 
 * @since 26-05-2025
 * @version 3.0
 */
public class ControladorJuego {
 
    private Jugador jugador;
    private NivelBase nivel;
    private List<Enemigo> enemigos;
    private List<Artefacto> artefactos;
    private List<SimboloPregunta> simbolos;

    public ControladorJuego(NivelBase nivel) {
        if (nivel == null) {
            throw new IllegalArgumentException("El nivel no puede ser nulo");
        }
        this.nivel = nivel;
        inicializarJuego();
    }

    /** Inicializa los elementos del juego basados en el nivel */
    private void inicializarJuego() {
        this.jugador = new Jugador(400, 500);
        nivel.setJugador(jugador);
        nivel.generarElementos();
        nivel.generarSimbolosPregunta();

        enemigos = new ArrayList<>(nivel.getEnemigos());
        artefactos = new ArrayList<>(nivel.getArtefactos());
        simbolos = new ArrayList<>(nivel.getSimbolosPregunta());
    }

    /** Mueve al jugador según el desplazamiento indicado, respetando límites */
    public void moverJugador(int dx, int dy, int anchoPanel, int altoPanel) {
        if (jugador != null) {
            jugador.mover(dx, dy, anchoPanel, altoPanel);
        }
    }

    /** Mueve a todos los enemigos visibles */
    public void moverEnemigos() {
        for (Enemigo enemigo : enemigos) {
            if (enemigo != null && enemigo.isVisible()) {
                enemigo.mover();
            }
        }
    }

    /** Verifica todas las colisiones y actualiza los estados de los elementos */
    public void verificarColisiones() {
        verificarColisionJugadorEnemigos();
        verificarColisionJugadorArtefactos();
        verificarColisionJugadorSimbolos();
    }

    /** Maneja la colisión entre jugador y enemigos */
    private void verificarColisionJugadorEnemigos() {
        for (Enemigo enemigo : enemigos) {
            if (enemigo != null && enemigo.isVisible() && colision(jugador, enemigo)) {
                jugador.reducirVida(5);
                enemigo.setVisible(false);
                ReproductorSonido.reproducir("/autonoma/AventuraMagicaGame/sounds/ColisionJugador.wav");
            }
        }
    }

    /** Maneja la colisión entre jugador y artefactos */
    private void verificarColisionJugadorArtefactos() {
        for (Artefacto artefacto : artefactos) {
            if (artefacto != null && artefacto.isVisible() && !artefacto.isRecolectado() && colision(jugador, artefacto)) {
                artefacto.setRecolectado(true);

                if (artefacto instanceof Botella) {
                    jugador.recolectarBotella();
                    ReproductorSonido.reproducir("/autonoma/AventuraMagicaGame/sounds/Botella.wav");
                } else if (artefacto instanceof Esmeralda) {
                    jugador.recolectarEsmeralda();
                    ReproductorSonido.reproducir("/autonoma/AventuraMagicaGame/sounds/Esmeralda.wav");
                }
            }
        }
    }

    /** Maneja la colisión entre jugador y símbolos de pregunta */
    private void verificarColisionJugadorSimbolos() {
        for (SimboloPregunta simbolo : simbolos) {
            if (simbolo != null && simbolo.isVisible() && simbolo.verificarColision(
                    jugador.getX(), jugador.getY(), jugador.getAncho(), jugador.getAlto())) {
                int puntos = simbolo.manejarColision();
                if (puntos > 0) {
                    jugador.aumentarPuntaje(puntos);
                } else if (puntos < 0) {
                    jugador.disminuirPuntaje(-puntos);
                }
            }
        }
    }

    /** Dibuja todos los elementos en pantalla */
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

    /** Cambia el nivel actual y reinicia los elementos asociados */
    public void setNivel(NivelBase nuevoNivel) {
        if (nuevoNivel == null) {
            throw new IllegalArgumentException("El nuevo nivel no puede ser nulo");
        }
        this.nivel = nuevoNivel;
        inicializarJuego();
    }

    public Jugador getJugador() {
        return jugador;
    }

    /** Verifica si dos sprites colisionan */
    private boolean colision(Sprite a, Sprite b) {
        if (a == null || b == null) {
            return false;
        }
        return a.getBounds().intersects(b.getBounds());
    }
}
