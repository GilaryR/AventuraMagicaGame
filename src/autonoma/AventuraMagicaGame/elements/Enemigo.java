package autonoma.AventuraMagicaGame.elements;

import autonoma.AventuraMagicaBase.elements.Sprite;

/**
 * Clase base para representar enemigos en el juego Aventura Mágica.
 * Un enemigo es un {@link Sprite} con movimiento horizontal automático y capacidad de rebotar
 * al llegar a los bordes laterales de la pantalla.
 * 
 * Esta clase puede ser extendida para crear enemigos con diferentes velocidades, tamaños e imágenes.
 * 
 * @author Alejandra ortega 
 * @since 26-05-2025
 * @version 2.0
 */
public class Enemigo extends Sprite {
    
    /** Velocidad horizontal del enemigo. */
    private int velocidadX = 2;  // Valor por defecto

    /** Velocidad vertical del enemigo (inactiva por defecto). */
    private int velocidadY = 0;

    /**
     * Crea una nueva instancia de Enemigo con sus atributos iniciales.
     *
     * @param x           Posición horizontal inicial.
     * @param y           Posición vertical inicial.
     * @param rutaImagen  Ruta del recurso de imagen del enemigo.
     * @param ancho       Ancho del sprite.
     * @param alto        Alto del sprite.
     */
    public Enemigo(int x, int y, String rutaImagen, int ancho, int alto) {
        super(x, y, rutaImagen, ancho, alto);
    }

    /**
     * Mueve al enemigo según su velocidad. Rebota automáticamente al alcanzar los
     * bordes izquierdo o derecho de la pantalla (ancho 800 px).
     */
    public void mover() {
        x += velocidadX;
        y += velocidadY;

        // Rebote horizontal al llegar a los bordes
        if (x < 0 || x + getAncho() > 800) {
            velocidadX = -velocidadX;
        }
    }

    /**
     * Establece la velocidad horizontal del enemigo.
     *
     * @param velocidadX Nueva velocidad horizontal.
     */
    public void setVelocidad(int velocidadX) {
        this.velocidadX = velocidadX;
    }

    /**
     * Retorna la velocidad horizontal actual del enemigo.
     *
     * @return Velocidad en el eje X.
     */
    public int getVelocidad() {
        return velocidadX;
    }
}
