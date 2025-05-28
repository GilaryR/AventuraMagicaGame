package autonoma.AventuraMagicaGame.elements;

import java.util.Arrays;
import java.util.List;

/**
 * Representa el nivel intermedio del juego Aventura Mágica.
 * Define los tipos y cantidades de enemigos y artefactos específicos de este nivel,
 * así como el fondo visual y los requisitos para completar el nivel.
 * 
 * Extiende la clase {@link NivelBase} para implementar la lógica específica de un nivel concreto.
 * 
 * @author 
 * @version 1.0
 * @since 28/05/2025
 */
public class NivelIntermedio extends NivelBase {

    /**
     * Constructor del nivel intermedio. Inicializa el fondo correspondiente al nivel.
     */
    public NivelIntermedio() {
        super();
        setFondo("/autonoma/AventuraMagicaGame/images/fondoIntermedio.jpg");
    }

    /**
     * Define los tipos de enemigos que aparecen en este nivel.
     * 
     * @return una lista con los nombres de los enemigos: "Tucan", "Frailejon" y "Cuy".
     */
    @Override
    protected List<String> tiposEnemigos() {
        return Arrays.asList("Tucan", "Frailejon", "Cuy");
    }

    /**
     * Define los tipos de artefactos disponibles en este nivel.
     * 
     * @return una lista con los nombres de artefactos: "Botella" y "Esmeralda".
     */
    @Override
    protected List<String> tiposArtefactos() {
        return Arrays.asList("Botella", "Esmeralda");
    }

    /**
     * Define la cantidad específica de enemigos o artefactos de un tipo dado.
     * 
     * @param tipo el nombre del tipo (enemigo o artefacto).
     * @return la cantidad correspondiente, o 0 si el tipo no es reconocido.
     */
    @Override
    protected int cantidadDe(String tipo) {
        switch (tipo) {
            case "Tucan": return 6;
            case "Frailejon": return 2;
            case "Cuy": return 5;
            case "Botella": return 40;
            case "Esmeralda": return 6;
            default: return 0;
        }
    }

    /**
     * Define la cantidad de artefactos necesarios para completar este nivel.
     * 
     * @return la cantidad mínima de artefactos requeridos (40).
     */
    @Override
    protected int artefactosRequeridos() {
        return 40;
    }
}
