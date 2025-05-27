package autonoma.AventuraMagicaGame.elements;

import autonoma.AventuraMagicaGameBase.elements.Sprite;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.List;
import java.util.Objects;
import javax.swing.JOptionPane;

/**
 * Representa un símbolo de pregunta en el juego, que activa acertijos al colisionar con el jugador.
 */
public class SimboloPregunta extends Sprite {
    private final List<Acertijo> acertijos;
    private boolean fueUsado = false;

    private static final int ANCHO = 48;
    private static final int ALTO = 48;
    private static final String RUTA_IMAGEN = "/autonoma/AventuraMagicaGame/images/Signo.png";

    /**
     * Crea un nuevo símbolo de pregunta.
     *
     * @param x coordenada x inicial (mínimo 100)
     * @param y coordenada y inicial
     * @param acertijos lista de acertijos asociados
     */
    public SimboloPregunta(int x, int y, List<Acertijo> acertijos) {
        super(Math.max(x, 100), y, RUTA_IMAGEN, ANCHO, ALTO);
        this.acertijos = Objects.requireNonNull(acertijos, "La lista de acertijos no puede ser null");
    }

    /**
     * Verifica si colisiona con el rectángulo del jugador.
     *
     * @param jugadorBounds El rectángulo del jugador
     * @return true si hay colisión y el símbolo es visible
     */
    public final boolean verificarColision(Rectangle jugadorBounds) {
        return getBounds().intersects(jugadorBounds) && isVisible();
    }

    /**
     * Maneja la colisión con el jugador, mostrando acertijos.
     *
     * @return Puntos obtenidos (10 si acierta, -5 si falla, 0 si ya fue usado)
     */
    public final int manejarColision() {
        if (!fueUsado && isVisible()) {
            boolean correcto = mostrarYValidar();
            fueUsado = true;
            setVisible(false);
            return correcto ? 10 : -5;
        }
        return 0;
    }

    /**
     * Muestra los acertijos y valida las respuestas del jugador.
     *
     * @return true si todas las respuestas son correctas
     */
    private boolean mostrarYValidar() {
        for (Acertijo acertijo : acertijos) {
            String respuesta = JOptionPane.showInputDialog(
                    null,
                    acertijo.getPregunta(),
                    "Acertijo",
                    JOptionPane.QUESTION_MESSAGE
            );

            if (respuesta == null || respuesta.trim().isEmpty() || !acertijo.verificar(respuesta.trim())) {
                JOptionPane.showMessageDialog(null, "¡Incorrecto!", "Fallaste", JOptionPane.ERROR_MESSAGE);
                return false;
            }
        }

        JOptionPane.showMessageDialog(null, "¡Correcto!", "Acertaste", JOptionPane.INFORMATION_MESSAGE);
        return true;
    }

    public boolean fueUsado() {
        return fueUsado;
    }

    public void setUsado(boolean usado) {
        this.fueUsado = usado;
    }

    @Override
    public void dibujar(Graphics g) {
        if (isVisible()) {
            g.drawImage(imagen, x, y, getAncho(), getAlto(), null);
        }
    }

    public void moverHaciaAbajo(int dy) {
        this.y += dy;
    }

    public boolean fueraDePantalla(int alturaVentana) {
        return y > alturaVentana;
    }

    public void reiniciar(int nuevaX, int nuevaY) {
        this.x = Math.max(nuevaX, 100);
        this.y = nuevaY;
        setVisible(true);
        this.fueUsado = false;
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, getAncho(), getAlto());
    }

    public boolean verificarColision(int x, int y, int ancho, int alto) {
        Rectangle rectSimbolo = getBounds();
        Rectangle rectOtro = new Rectangle(x, y, ancho, alto);
        return rectSimbolo.intersects(rectOtro);
    }
}
