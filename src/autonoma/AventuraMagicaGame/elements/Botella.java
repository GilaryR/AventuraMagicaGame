package autonoma.AventuraMagicaGame.elements;

import autonoma.AventuraMagicaGame.elements.Artefacto;

/**
 * Clase que representa una botella dentro del juego.
 * Al ser recolectada por el jugador, aumenta su puntaje.
 * 
 * @author Alejandra ortega 
 * @since 26-05-2025
 * @version 2.0
 */

 
public class Botella extends Artefacto {

    /**
     * Ancho predeterminado de la botella en píxeles.
     */
    private static final int ANCHO = 40;

    /**
     * Alto predeterminado de la botella en píxeles.
     */
    private static final int ALTO = 40;

    /**
     * Ruta de la imagen utilizada para representar la botella.
     */
    private static final String RUTA = "/autonoma/AventuraMagica/images/botella.png";

    /**
     * Crea una nueva botella en la posición indicada.
     *
     * @param x Coordenada horizontal donde aparecerá la botella.
     * @param y Coordenada vertical donde aparecerá la botella.
     */
    public Botella(int x, int y) {
        super(x, y, RUTA, ANCHO, ALTO);
    }

    /**
     * Aplica el efecto de la botella al jugador.
     * En este caso, incrementa su puntaje en 15 puntos.
     *
     * @param jugador El jugador que recoge la botella.
     */
    @Override
    public void aplicarEfecto(Jugador jugador) {
        jugador.aumentarPuntaje(15);
    }
}
