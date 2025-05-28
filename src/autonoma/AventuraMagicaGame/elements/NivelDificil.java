package autonoma.AventuraMagicaGame.elements;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Implementación del nivel difícil del juego que extiende de NivelBase.
 * Contiene una configuración específica de enemigos, artefactos y acertijos
 * con mayor dificultad que otros niveles.
 * 
 * @author Gilary
 * @version 1.0
 * @since 26/05/2025
 */
public class NivelDificil extends NivelBase {

    /**
     * Constructor que inicializa el nivel difícil con su configuración específica.
     * Establece el fondo personalizado para este nivel.
     */
    public NivelDificil() {
        super();
        setFondo("/autonoma/AventuraMagicaGame/images/fondoDificil.jpg");
    }

    /**
     * Define los tipos de enemigos disponibles en este nivel.
     * @return Lista de strings con los nombres de los enemigos
     */
    @Override
    protected List<String> tiposEnemigos() {
        return Arrays.asList("Tucan", "Frailejon", "Cuy", "Capybara");
    }

    /**
     * Define los tipos de artefactos disponibles en este nivel.
     * @return Lista de strings con los nombres de los artefactos
     */
    @Override
    protected List<String> tiposArtefactos() {
        return Arrays.asList("Botella", "Esmeralda");
    }

    /**
     * Especifica la cantidad de cada elemento en el nivel.
     * @param tipo El tipo de elemento a consultar
     * @return Cantidad de elementos del tipo especificado
     */
    @Override
    protected int cantidadDe(String tipo) {
        switch (tipo) {
            case "Tucan": return 20;
            case "Frailejon": return 3;
            case "Cuy": return 4;
            case "Capybara": return 3;
            case "Botella": return 45;
            case "Esmeralda": return 4;
            default: return 0;
        }
    }

    /**
     * Establece la cantidad de artefactos requeridos para completar el nivel.
     * @return Número de artefactos necesarios (45 en este nivel)
     */
    @Override
    protected int artefactosRequeridos() {
        return 45;
    }


}