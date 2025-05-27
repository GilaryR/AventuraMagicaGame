package autonoma.AventuraMagicaGame.elements;

import autonoma.AventuraMagicaGameBase.elements.Sprite;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javax.swing.JOptionPane;

public class SimboloPregunta extends Sprite {

    private static List<Acertijo> acertijosGlobal; // Lista compartida entre todos los símbolos
    private boolean fueUsado = false;

    private static final int ANCHO = 48;  
    private static final int ALTO = 48;
    private static final String RUTA_IMAGEN = "/autonoma/AventuraMagicaGame/images/Signo.png";

    /**
     * Crea un nuevo símbolo de pregunta.
     * 
     * @param x Coordenada X
     * @param y Coordenada Y
     * @param acertijosIniciales Lista inicial de acertijos (solo una vez)
     */
    public SimboloPregunta(int x, int y, List<Acertijo> acertijosIniciales) {
        super(Math.max(x, 100), y, RUTA_IMAGEN, ANCHO, ALTO);
        if (acertijosGlobal == null) {
            acertijosGlobal = new ArrayList<>(acertijosIniciales); // Copiar solo la primera vez
        }
    }

    /**
     * Verifica colisión con el jugador.
     * 
     * @param jugadorBounds Rectángulo del jugador
     * @return true si colisiona
     */
    public boolean verificarColision(Rectangle jugadorBounds) {
        return getBounds().intersects(jugadorBounds) && isVisible();
    }

    /**
     * Maneja la colisión mostrando acertijos y otorgando puntos.
     * 
     * @return Puntos ganados (+10 si correcto, -5 si incorrecto, 0 si ya fue usado)
     */
    public int manejarColision() {
        if (!fueUsado && isVisible()) {
            boolean acierto = mostrarUnAcertijoAleatorio();
            fueUsado = true;
            setVisible(false);
            return acierto ? 10 : -5; // también puedes sumar vidas aparte si acierta
        }
        return 0;
    }

    /**
     * Muestra una pregunta aleatoria y la elimina de la lista global si fue usada.
     * 
     * @return true si la respuesta fue correcta
     */
    private boolean mostrarUnAcertijoAleatorio() {
        if (acertijosGlobal == null || acertijosGlobal.isEmpty()) {
            JOptionPane.showMessageDialog(null, "¡Ya no quedan preguntas!", "Info", JOptionPane.INFORMATION_MESSAGE);
            return false;
        }

        Random random = new Random();
        int indice = random.nextInt(acertijosGlobal.size());
        Acertijo acertijo = acertijosGlobal.remove(indice); // eliminar para que no se repita

        String respuesta = JOptionPane.showInputDialog(null,
                acertijo.getPregunta(), "Acertijo", JOptionPane.QUESTION_MESSAGE);

        if (respuesta == null || respuesta.trim().isEmpty() || !acertijo.verificar(respuesta.trim())) {
            JOptionPane.showMessageDialog(null, "¡Incorrecto!", "Fallaste", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        JOptionPane.showMessageDialog(null, "¡Correcto!", "Acertaste", JOptionPane.INFORMATION_MESSAGE);
        // Aquí podrías sumar una vida al jugador si gestionas vidas desde otra clase
        return true;
    }

    /**
     * Dibuja el símbolo si es visible.
     * 
     * @param g Contexto gráfico
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
     * @param dy Cantidad de píxeles
     */
    public void moverHaciaAbajo(int dy) {
        this.y += dy;
    }

    /**
     * Verifica si el símbolo está fuera de la pantalla.
     * 
     * @param alturaVentana Altura de la ventana
     * @return true si salió de la pantalla
     */
    public boolean fueraDePantalla(int alturaVentana) {
        return y > alturaVentana;
    }

    /**
     * Reinicia el símbolo en nueva posición y visible.
     * 
     * @param nuevaX Coordenada x
     * @param nuevaY Coordenada y
     */
    public void reiniciar(int nuevaX, int nuevaY) {
        this.x = Math.max(nuevaX, 100);
        this.y = nuevaY;
        setVisible(true);
        this.fueUsado = false;
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

    /**
     * Verifica colisión con coordenadas de otro rectángulo.
     * 
     * @param x coordenada x
     * @param y coordenada y
     * @param ancho ancho del rectángulo
     * @param alto alto del rectángulo
     * @return true si colisionan
     */
    public boolean verificarColision(int x, int y, int ancho, int alto) {
        Rectangle rectSimbolo = new Rectangle(this.x, this.y, getAncho(), getAlto());
        Rectangle rectOtro = new Rectangle(x, y, ancho, alto);
        return rectSimbolo.intersects(rectOtro);
    }
}
