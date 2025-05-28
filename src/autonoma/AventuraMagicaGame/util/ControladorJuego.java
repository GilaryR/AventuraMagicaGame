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
 * Controlador principal del juego que gestiona la lógica, interacciones y estado
 * de todos los elementos del juego para un nivel específico.
 * <p>
 * Esta clase se encarga de:
 * <ul>
 *   <li>Gestionar el movimiento del jugador y enemigos</li>
 *   <li>Detectar y manejar colisiones entre elementos</li>
 *   <li>Controlar la recolección de artefactos</li>
 *   <li>Administrar la interacción con símbolos de pregunta</li>
 *   <li>Renderizar todos los elementos del juego</li>
 * </ul>
 * 
 * @author Alejandra Ortega
 * @author Gilary Rugeles
 * @version 3.1
 * @since 26-05-2025
 */
public class ControladorJuego {
  
    private Jugador jugador;
    private NivelBase nivel;
    private List<Enemigo> enemigos;
    private List<Artefacto> artefactos;
    private List<SimboloPregunta> simbolos;

    /**
     * Crea un nuevo controlador de juego para el nivel especificado.
     * 
     * @param nivel El nivel que será controlado por esta instancia
     * @throws IllegalArgumentException Si el nivel proporcionado es nulo
     */
    public ControladorJuego(NivelBase nivel) {
        if (nivel == null) {
            throw new IllegalArgumentException("El nivel no puede ser nulo");
        }
        this.nivel = nivel;
        inicializarJuego();
    }

    /**
     * Inicializa todos los elementos del juego basándose en el nivel actual.
     * Crea el jugador, enemigos, artefactos y símbolos de pregunta.
     */
    private void inicializarJuego() {
        this.jugador = new Jugador(400, 500);
        nivel.setJugador(jugador);
        nivel.generarElementos();
        nivel.generarSimbolosPregunta();

        enemigos = new ArrayList<>(nivel.getEnemigos());
        artefactos = new ArrayList<>(nivel.getArtefactos());
        simbolos = new ArrayList<>(nivel.getSimbolosPregunta());
    }

    /**
     * Mueve al jugador según el desplazamiento indicado, respetando los límites del panel.
     * 
     * @param dx Desplazamiento en el eje X
     * @param dy Desplazamiento en el eje Y
     * @param anchoPanel Ancho del área de juego
     * @param altoPanel Alto del área de juego
     */
    public void moverJugador(int dx, int dy, int anchoPanel, int altoPanel) {
        if (jugador != null) {
            jugador.mover(dx, dy, anchoPanel, altoPanel);
        }
    }

    /**
     * Mueve todos los enemigos visibles en el nivel actual.
     */
    public void moverEnemigos() {
        for (Enemigo enemigo : enemigos) {
            if (enemigo != null && enemigo.isVisible()) {
                enemigo.mover();
            }
        }
    }

    /**
     * Verifica todas las posibles colisiones entre el jugador y otros elementos del juego.
     * Actualiza los estados de los elementos según las colisiones detectadas.
     */
    public void verificarColisiones() {
        verificarColisionJugadorEnemigos();
        verificarColisionJugadorArtefactos();
        verificarColisionJugadorSimbolos();
    }

    /**
     * Maneja las colisiones entre el jugador y los enemigos.
     * Reduce la vida del jugador y oculta al enemigo en caso de colisión.
     */
    private void verificarColisionJugadorEnemigos() {
        for (Enemigo enemigo : enemigos) {
            if (enemigo != null && enemigo.isVisible() && colision(jugador, enemigo)) {
                jugador.reducirVida(5);
                enemigo.setVisible(false);
                ReproductorSonido.reproducir("/autonoma/AventuraMagicaGame/sounds/ColisionJugador.wav");
            }
        }
    }

    /**
     * Maneja las colisiones entre el jugador y los artefactos.
     * Recolecta los artefactos y actualiza el estado del jugador según el tipo de artefacto.
     */
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

    /**
     * Maneja las colisiones entre el jugador y los símbolos de pregunta.
     * Presenta acertijos al jugador y actualiza el puntaje si responde correctamente.
     */
    private void verificarColisionJugadorSimbolos() {
        for (SimboloPregunta simbolo : simbolos) {
            if (simbolo != null && simbolo.isVisible() && simbolo.verificarColision(
                    jugador.getX(), jugador.getY(), jugador.getAncho(), jugador.getAlto())) {
                int puntos = simbolo.manejarColision();
                if (puntos > 0) {
                    jugador.aumentarPuntaje(puntos);
                }
            }
        }
    }

    /**
     * Dibuja al jugador en el contexto gráfico proporcionado.
     * 
     * @param g El contexto gráfico donde se dibujará el jugador
     */
    public void dibujarJugador(Graphics g) {
        if (jugador != null) {
            jugador.dibujar(g);
        }
    }

    /**
     * Dibuja todos los enemigos visibles en el contexto gráfico proporcionado.
     * 
     * @param g El contexto gráfico donde se dibujarán los enemigos
     */
    public void dibujarEnemigos(Graphics g) {
        for (Enemigo enemigo : enemigos) {
            if (enemigo != null && enemigo.isVisible()) {
                enemigo.dibujar(g);
            }
        }
    }

    /**
     * Dibuja todos los artefactos no recolectados en el contexto gráfico proporcionado.
     * 
     * @param g El contexto gráfico donde se dibujarán los artefactos
     */
    public void dibujarArtefactos(Graphics g) {
        for (Artefacto artefacto : artefactos) {
            if (artefacto != null && artefacto.isVisible() && !artefacto.isRecolectado()) {
                artefacto.dibujar(g);
            }
        }
    }

    /**
     * Dibuja todos los símbolos de pregunta visibles en el contexto gráfico proporcionado.
     * 
     * @param g El contexto gráfico donde se dibujarán los símbolos
     */
    public void dibujarSimbolos(Graphics g) {
        for (SimboloPregunta simbolo : simbolos) {
            if (simbolo != null && simbolo.isVisible()) {
                simbolo.dibujar(g);
            }
        }
    }

    /**
     * Cambia el nivel actual del juego y reinicia todos los elementos asociados.
     * 
     * @param nuevoNivel El nuevo nivel que será controlado
     * @throws IllegalArgumentException Si el nuevo nivel es nulo
     */
    public void setNivel(NivelBase nuevoNivel) {
        if (nuevoNivel == null) {
            throw new IllegalArgumentException("El nuevo nivel no puede ser nulo");
        }
        this.nivel = nuevoNivel;
        inicializarJuego();
    }

    /**
     * Obtiene la instancia del jugador actual.
     * 
     * @return El jugador controlado por este controlador de juego
     */
    public Jugador getJugador() {
        return jugador;
    }

    /**
     * Verifica si dos sprites están colisionando.
     * 
     * @param a Primer sprite a verificar
     * @param b Segundo sprite a verificar
     * @return true si los sprites están colisionando, false en caso contrario
     */
    private boolean colision(Sprite a, Sprite b) {
        if (a == null || b == null) {
            return false;
        }
        return a.getBounds().intersects(b.getBounds());
    }
}