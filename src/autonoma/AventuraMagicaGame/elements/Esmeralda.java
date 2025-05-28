package autonoma.AventuraMagicaGame.elements;

/**
 * Representa un artefacto de tipo Esmeralda en el juego.
 * Al ser recolectada, aumenta la vida del jugador.
 * 
 * Esta clase hereda de {@link Artefacto} y define el comportamiento específico
 * de las esmeraldas en el juego.
 * @author Gilary
 * @version 1.0
 * @since 26/05/2025
 */
public class Esmeralda extends Artefacto {
    /** Ancho predeterminado del sprite de la esmeralda en píxeles */
    private static final int ANCHO = 40;
    
    /** Alto predeterminado del sprite de la esmeralda en píxeles */
    private static final int ALTO = 40;
    
    /** Ruta de la imagen del sprite de la esmeralda */
    private static final String RUTA = "/autonoma/AventuraMagicaGame/images/Esmeralda.png";

    /**
     * Crea una nueva esmeralda en la posición especificada.
     * 
     * @param x Posición horizontal inicial (coordenada x)
     * @param y Posición vertical inicial (coordenada y)
     */
    public Esmeralda(int x, int y) {
        super(x, y, RUTA, ANCHO, ALTO);
    }

    /**
     * Aplica el efecto de la esmeralda al jugador que la recolecta.
     * Incrementa la vida del jugador en 15 puntos.
     * 
     * @param jugador El jugador que recolecta la esmeralda
     */
    @Override
    public void aplicarEfecto(Jugador jugador) {
        jugador.aumentarVida(15);
    }
}