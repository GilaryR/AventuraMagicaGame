
package autonoma.AventuraMagicaGameBase.elements;

import java.awt.Rectangle;

/**
 * Clase base para sprites que se mueven en el juego
 * @author Gilary 
 * @since 26/05/2025
 * @version 2.0
 */
public abstract class SpriteMobile extends Sprite {
    protected int step; // Paso/pixeles de movimiento
    protected int direccionX, direccionY; // Dirección del movimiento

    public SpriteMobile(int x, int y, String rutaImagen, int ancho, int alto) {
        super(x, y, rutaImagen, ancho, alto);
        this.step = 5; // Valor por defecto
        this.direccionX = 1; // 1 = derecha, -1 = izquierda
        this.direccionY = 1; // 1 = abajo, -1 = arriba
    }

    /**
     * Método para mover el sprite
     * @param limitesPantalla Rectangle con los límites del área de juego
     */
    public void mover(Rectangle limitesPantalla) {
        x += step * direccionX;
        y += step * direccionY;
        
        // Detección de bordes
        if (x <= 0 || x >= limitesPantalla.width - ancho) {
            direccionX *= -1; // Cambiar dirección horizontal
        }
        if (y <= 0 || y >= limitesPantalla.height - alto) {
            direccionY *= -1; // Cambiar dirección vertical
        }
    }

    // Getters y Setters
    public int getStep() { return step; }
    public void setStep(int step) { this.step = step; }
    public int getDireccionX() { return direccionX; }
    public void setDireccionX(int direccionX) { this.direccionX = direccionX; }
    public int getDireccionY() { return direccionY; }
    public void setDireccionY(int direccionY) { this.direccionY = direccionY; }
}