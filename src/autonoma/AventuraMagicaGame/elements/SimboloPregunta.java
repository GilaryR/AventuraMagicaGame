package autonoma.AventuraMagicaGame.elements;

import autonoma.AventuraMagicaGameBase.elements.Sprite;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javax.swing.JOptionPane;

/**
 * Representa un símbolo de pregunta en el juego que contiene un acertijo que el jugador debe resolver.
 * Cuando el jugador colisiona con este símbolo, se le presenta un acertijo aleatorio de la lista proporcionada.
 * 
 * @author [luisa Fernanda Henao]
 * @version 1.0
 * @author [Alejandra Ortega]
 * @version 2.0
 */
public class SimboloPregunta extends Sprite {

    private List<Acertijo> acertijos;
    private Acertijo acertijo;
    private boolean resuelto = false;

    /**
     * Crea una nueva instancia de SimboloPregunta en la posición especificada.
     * 
     * @param x La coordenada x del símbolo en el mapa.
     * @param y La coordenada y del símbolo en el mapa.
     * @param acertijos La lista de acertijos que pueden ser asignados a este símbolo.
     */
    public SimboloPregunta(int x, int y, List<Acertijo> acertijos) {
        super(x, y, "/autonoma/AventuraMagicaGame/images/Signo.png", 32, 32);
        this.acertijos = acertijos;
        // Elegir acertijo aleatorio al crear el símbolo
        this.acertijo = acertijos.get(new Random().nextInt(acertijos.size()));
    }

    /**
     * Maneja la colisión con el jugador, mostrando el acertijo y verificando la respuesta.
     * 
     * @return 1 si la respuesta fue correcta, 0 si fue incorrecta o si el acertijo ya había sido resuelto.
     */
    public int manejarColision() {
        if (resuelto) {
            // Ya se resolvió, no preguntar otra vez
            return 0;
        }

        String respuesta = JOptionPane.showInputDialog(null, acertijo.getPregunta(), "¡Resuelve el acertijo!", JOptionPane.QUESTION_MESSAGE);

        if (respuesta != null && (respuesta.equalsIgnoreCase(acertijo.getRespuesta())
                || respuesta.equalsIgnoreCase(acertijo.getRespuestaAlternativa()))) {
            JOptionPane.showMessageDialog(null, "¡Correcto! Has respondido bien el acertijo.");
            resuelto = true;  // marcar como resuelto para no repetir
            return 1;
        } else {
            JOptionPane.showMessageDialog(null, "Respuesta incorrecta. La respuesta correcta era: " + acertijo.getRespuesta());
            // Opcional: puedes pedir que vuelva a intentar o simplemente devolver 0
            return 0;
        }
    }

    /**
     * Verifica si hay colisión entre el símbolo y el jugador.
     * 
     * @param jugadorX La coordenada x del jugador.
     * @param jugadorY La coordenada y del jugador.
     * @param jugadorAncho El ancho del jugador.
     * @param jugadorAlto El alto del jugador.
     * @return true si hay colisión, false en caso contrario.
     */
    public boolean verificarColision(int jugadorX, int jugadorY, int jugadorAncho, int jugadorAlto) {
        Rectangle rectSimbolo = new Rectangle(this.x, this.y, this.ancho, this.alto);
        Rectangle rectJugador = new Rectangle(jugadorX, jugadorY, jugadorAncho, jugadorAlto);
        return rectSimbolo.intersects(rectJugador);
    }
}