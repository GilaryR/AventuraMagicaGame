package autonoma.AventuraMagicaGame.elements;

import autonoma.AventuraMagicaGame.elements.Enemigo;

/**
 * Clase que representa un enemigo tipo Capybara en el juego.
 * La capybara es un enemigo con velocidad moderada y tamaño definido.
 * @author Alejandra ortega 
 * @since 26-05-2025
 * @version 2.0
 */
public class Capybara extends Enemigo {

    /**
     * Ancho predeterminado de la capybara en píxeles.
     */
    private static final int ANCHO = 80;

    /**
     * Alto predeterminado de la capybara en píxeles.
     */
    private static final int ALTO = 50;

    /**
     * Ruta de la imagen que representa visualmente a la capybara.
     */
    private static final String RUTA_IMAGEN = "/autonoma/AventuraMagicaGame/images/Capybara.png";

    /**
     * Constructor de la clase Capybara.
     * Inicializa la posición y configura los atributos básicos del enemigo.
     *
     * @param x Coordenada horizontal donde se ubica la capybara.
     * @param y Coordenada vertical donde se ubica la capybara.
     */
    public Capybara(int x, int y) {
        super(x, y, RUTA_IMAGEN, ANCHO, ALTO);
        setVelocidad(4); // Establece una velocidad fija para la capybara
    }
}
