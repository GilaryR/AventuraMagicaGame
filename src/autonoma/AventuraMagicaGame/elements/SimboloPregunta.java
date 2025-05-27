package autonoma.AventuraMagicaGame.elements;

import autonoma.AventuraMagicaGameBase.elements.Sprite;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.List;
import javax.swing.JOptionPane;

public class SimboloPregunta extends Sprite {
    private final List<Acertijo> acertijos;
    private boolean fueUsado = false;

    private static final int ANCHO = 48;  
    private static final int ALTO = 48;
    private static final String RUTA_IMAGEN = "/autonoma/AventuraMagicaGame/images/Signo.png";

    /**
     * Crea un nuevo símbolo de pregunta.
     * 
     * @param x La coordenada x inicial
     * @param y La coordenada y inicial
     * @param acertijos La lista de acertijos asociados a este símbolo
     */
    public SimboloPregunta(int x, int y, List<Acertijo> acertijos) {
        super(x < 100 ? 100 : x, y, RUTA_IMAGEN, ANCHO, ALTO);
        this.acertijos = acertijos;
    }

    /**
     * Verifica si colisiona con el rectángulo del jugador.
     * 
     * @param jugadorBounds El rectángulo del jugador
     * @return true si hay colisión y el símbolo es visible
     */
    public boolean verificarColision(Rectangle jugadorBounds) {
        Rectangle simboloBounds = getBounds();
        return simboloBounds.intersects(jugadorBounds) && isVisible();
    }

    /**
     * Maneja la colisión con el jugador, mostrando acertijos.
     * 
     * @return Puntos obtenidos (10 si acierta, -5 si falla, 0 si ya fue usado)
     */
    public int manejarColision() {
        if (!fueUsado && isVisible()) {
            boolean correcto = mostrarYValidar();
            fueUsado = true;
            setVisible(false);
            return correcto ? 10 : -5;
        }
        return 0;
    }

    private boolean mostrarYValidar() {
        for (Acertijo acertijo : acertijos) {
            String respuesta = JOptionPane.showInputDialog(null,
                    acertijo.getPregunta(), "Acertijo", JOptionPane.QUESTION_MESSAGE);

            if (respuesta == null || !acertijo.verificar(respuesta.trim())) {
                JOptionPane.showMessageDialog(null, "¡Incorrecto!", "Fallaste", JOptionPane.ERROR_MESSAGE);
                return false;
            }
        }
        JOptionPane.showMessageDialog(null, "¡Correcto!", "Acertaste", JOptionPane.INFORMATION_MESSAGE);
        return true;
    }

    /**
     * @return true si el símbolo ya fue usado
     */
    public boolean fueUsado() {
        return fueUsado;
    }

    /**
     * Establece si el símbolo ha sido usado.
     * 
     * @param usado El estado a establecer
     */
    public void setUsado(boolean usado) {
        this.fueUsado = usado;
    }
    /**
     * Dibuja el símbolo si es visible.
     * 
     * @param g El contexto gráfico
     */
    @Override
    public void dibujar(Graphics g) {
        if (isVisible()) {
            g.drawImage(imagen, x, y, getAncho(), getAlto(), null);
        }
    }

    /**
     * Mueve el símbolo hacia abajo.
     * 
     * @param dy La cantidad de píxeles a mover
     */
    public void moverHaciaAbajo(int dy) {
        this.y += dy;
    }

    /**
     * Verifica si el símbolo está fuera de la pantalla.
     * 
     * @param alturaVentana La altura de la ventana del juego
     * @return true si está fuera de la pantalla
     */
    public boolean fueraDePantalla(int alturaVentana) {
        return y > alturaVentana;
    }

    /**
     * Reinicia el símbolo a una nueva posición.
     * 
     * @param nuevaX La nueva coordenada x
     * @param nuevaY La nueva coordenada y
     */
    public void reiniciar(int nuevaX, int nuevaY) {
        this.x = Math.max(nuevaX, 100);
        this.y = nuevaY;
        setVisible(true);
        this.fueUsado = false;
    }

    /**
     * @return El rectángulo de colisión del símbolo
     */
    public Rectangle getBounds() {
        return new Rectangle(x, y, getAncho(), getAlto());
    }

    /**
     * Verifica colisión con otro rectángulo.
     * 
     * @param x La coordenada x del otro rectángulo
     * @param y La coordenada y del otro rectángulo
     * @param ancho El ancho del otro rectángulo
     * @param alto El alto del otro rectángulo
     * @return true si hay colisión
     */
    public boolean verificarColision(int x, int y, int ancho, int alto) {
        Rectangle rectSimbolo = new Rectangle(this.x, this.y, this.getAncho(), this.getAlto());
        Rectangle rectOtro = new Rectangle(x, y, ancho, alto);
        return rectSimbolo.intersects(rectOtro);
    }
}