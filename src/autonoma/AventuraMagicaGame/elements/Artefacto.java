package autonoma.AventuraMagicaGame.elements;

import autonoma.AventuraMagicaGameBase.elements.Sprite;
import java.awt.Graphics;
import java.awt.Rectangle;

/**
 * Clase abstracta que representa un artefacto dentro del juego.
 * Un artefacto es un objeto que puede ser recolectado por el jugador
 * y aplica un efecto específico al ser recogido.
 *  * 
 * @author Alejandra ortega 
 * @since 26-05-2025
 * @version 2.0
 */


public abstract class Artefacto extends Sprite {

    /**
     * Indica si el artefacto ya fue recolectado por el jugador.
     */
    private boolean recolectado;

    /**
     * Constructor de la clase Artefacto.
     *
     * @param x           Posición horizontal del artefacto.
     * @param y           Posición vertical del artefacto.
     * @param rutaImagen  Ruta del archivo de imagen que representa el artefacto.
     * @param ancho       Ancho del artefacto.
     * @param alto        Alto del artefacto.
     */
    public Artefacto(int x, int y, String rutaImagen, int ancho, int alto) {
        super(x, y, rutaImagen, ancho, alto);
        this.recolectado = false;  // Por defecto, el artefacto no está recolectado.
    }

    /**
     * Método abstracto que debe implementar cada artefacto concreto.
     * Define el efecto que se aplicará al jugador cuando recoja el artefacto.
     *
     * @param jugador El jugador que recoge el artefacto.
     */
    public abstract void aplicarEfecto(Jugador jugador);

    /**
     * Dibuja el artefacto en pantalla si es visible y tiene imagen asignada.
     *
     * @param g El contexto gráfico donde se dibujará el artefacto.
     */
    @Override
    public void dibujar(Graphics g) {
        if (isVisible() && imagen != null) {
            g.drawImage(imagen, x, y, getAncho(), getAlto(), null);
        }
    }

    /**
     * Devuelve un rectángulo que representa los límites del artefacto
     * para propósitos de detección de colisiones.
     *
     * @return Un objeto Rectangle con los límites del artefacto.
     */
    public Rectangle getBounds() {
        return new Rectangle(x, y, getAncho(), getAlto());
    }

    /**
     * Verifica si el artefacto colisiona con otro objeto representado por un rectángulo.
     *
     * @param otro El rectángulo con el que se quiere verificar la colisión.
     * @return true si hay colisión; false en caso contrario.
     */
    public boolean verificarColision(Rectangle otro) {
        return getBounds().intersects(otro);
    }

    /**
     * Indica si el artefacto ya ha sido recolectado.
     *
     * @return true si fue recolectado; false si aún no lo ha sido.
     */
    public boolean isRecolectado() {
        return recolectado;
    }

    /**
     * Marca el estado de recolección del artefacto.
     *
     * @param recolectado true si fue recolectado; false en caso contrario.
     */
    public void setRecolectado(boolean recolectado) {
        this.recolectado = recolectado;
    }
}

