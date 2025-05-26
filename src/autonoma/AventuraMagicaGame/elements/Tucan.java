package autonoma.AventuraMagicaGame.elements;

public class Tucan extends Enemigo {
    private static final int ANCHO = 60;
    private static final int ALTO = 40;
    private static final String RUTA_IMAGEN = "/autonoma/AventuraMagicaGame/images/Tucan.png";

    /**
     * Crea un nuevo Tucán en la posición especificada.
     * 
     * @param x La coordenada x inicial
     * @param y La coordenada y inicial
     */
    public Tucan(int x, int y) {
        super(x, y, RUTA_IMAGEN, ANCHO, ALTO);
        setVelocidad(3); // Velocidad específica
    }
}