package autonoma.AventuraMagicaGame.elements;

import autonoma.AventuraMagicaGameBase.elements.Sprite;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javax.swing.JOptionPane;

public class SimboloPregunta extends Sprite {

    private static List<Acertijo> acertijosGlobal;
    private boolean fueUsado = false;
    private Acertijo acertijoAsignado;

    private static final int ANCHO = 48;
    private static final int ALTO = 48;
    private static final String RUTA_IMAGEN = "/autonoma/AventuraMagicaGame/images/Signo.png";

    public SimboloPregunta(int x, int y, List<Acertijo> acertijosIniciales) {
        super(Math.max(x, 100), y, RUTA_IMAGEN, ANCHO, ALTO);
        if (acertijosGlobal == null) {
            acertijosGlobal = new ArrayList<>(acertijosIniciales);
        }

        // Asignar un acertijo aleatorio al símbolo
        if (!acertijosGlobal.isEmpty()) {
            Random random = new Random();
            int indice = random.nextInt(acertijosGlobal.size());
            this.acertijoAsignado = acertijosGlobal.remove(indice);
        }
    }

    public boolean verificarColision(Rectangle jugadorBounds) {
        return getBounds().intersects(jugadorBounds) && isVisible();
    }

    /**
     * Marca el símbolo como usado y lo oculta.
     */
    public void marcarComoUsado() {
        fueUsado = true;
        setVisible(false);
    }

    /**
     * Permite que la parte gráfica obtenga la pregunta.
     */
    public String getPregunta() {
        return acertijoAsignado != null ? acertijoAsignado.getPregunta() : null;
    }

    /**
     * Verifica si la respuesta es correcta.
     */
    public boolean verificarRespuesta(String respuesta) {
        if (acertijoAsignado == null || respuesta == null || respuesta.trim().isEmpty()) {
            return false;
        }
        return acertijoAsignado.verificar(respuesta.trim());
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
        // No reasignamos acertijo porque ya se usó
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, getAncho(), getAlto());
    }

    public boolean fueUsado() {
        return fueUsado;
    }

    public void setUsado(boolean usado) {
        this.fueUsado = usado;
    }

    public boolean verificarColision(int x, int y, int ancho, int alto) {
        Rectangle rectSimbolo = new Rectangle(this.x, this.y, getAncho(), getAlto());
        Rectangle rectOtro = new Rectangle(x, y, ancho, alto);
        return rectSimbolo.intersects(rectOtro);
    }

    /**
     * Método que maneja la colisión con el jugador y retorna puntos ganados o perdidos.
     * Marca el símbolo como usado y lo oculta.
     * Aquí puedes implementar lógica más avanzada si quieres.
     * @return puntos a sumar (positivo) o restar (negativo)
     */
    public int manejarColision() {
        if (fueUsado) {
            return 0; // Ya fue usado, no da puntos
        }
        marcarComoUsado();

        // Simulación de resultado: por ejemplo, si el acertijo existe y es correcto, +10, sino -5
        // Aquí solo retornamos un valor fijo para ejemplo
        // Podrías hacer lógica real consultando el acertijo, etc.

        return 10; // puntos positivos por ejemplo
    }
}