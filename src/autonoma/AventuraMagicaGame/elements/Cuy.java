package autonoma.AventuraMagicaGame.elements;

import autonoma.AventuraMagica.elements.Enemigo;

/**
 * Representa a un enemigo tipo Cuy dentro del juego Aventura Mágica.
 * El Cuy es un enemigo más pequeño y más rápido que otros, y se desplaza
 * con una velocidad definida mayor.
 * 
 * Hereda de la clase {@link Enemigo} y utiliza una imagen específica para su representación.
 * 
 * @author Alejandra ortega 
 * @since 26-05-2025
 * @version 2.0
 */
public class Cuy extends Enemigo {
    
    /** Ancho del sprite del cuy en píxeles. */
    private static final int ANCHO_CUY = 45;

    /** Alto del sprite del cuy en píxeles. */
    private static final int ALTO_CUY = 30;

    /** Ruta del recurso de imagen del cuy. */
    private static final String RUTA_IMAGEN = "/autonoma/AventuraMagica/images/cuy.png";

    /**
     * Crea una nueva instancia de Cuy en la posición dada.
     *
     * @param x Posición horizontal inicial del cuy.
     * @param y Posición vertical inicial del cuy.
     */
    public Cuy(int x, int y) {
        super(x, y, RUTA_IMAGEN, ANCHO_CUY, ALTO_CUY);
        setVelocidad(6); // Más rápido que otros enemigos
    }
}
