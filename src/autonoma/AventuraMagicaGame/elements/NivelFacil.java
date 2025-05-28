package autonoma.AventuraMagicaGame.elements;

import java.util.Arrays;
import java.util.List;

/**
 * Representa el nivel fácil del juego Aventura Mágica.
 * 
 * <p>Esta clase hereda de NivelBase y define las características específicas
 * del nivel fácil, incluyendo:</p>
 * <ul>
 *   <li>Tipos y cantidades de enemigos</li>
 *   <li>Tipos y cantidades de artefactos recolectables</li>
 *   <li>Requisitos para completar el nivel</li>
 *   <li>Fondo visual específico</li>
 * </ul>
 * 
 * <p>El nivel fácil contiene enemigos más simples y menos exigentes
 * que los niveles superiores.</p>
 * 
 * @author [Autor del código]
 * @version 1.0
 * @see NivelBase
 */
public class NivelFacil extends NivelBase {

    /**
     * Constructor que inicializa el nivel fácil.
     * Configura el fondo específico para este nivel.
     */
    public NivelFacil() {
        super();
        setFondo("/autonoma/AventuraMagicaGame/images/fondoFacil.jpg");  
    }
    
    /**
     * Cuenta las botellas que han sido recolectadas por el jugador.
     * 
     * @return El número total de botellas recolectadas
     */
    public int contarBotellasRecolectadas() {
        int contador = 0;
        for (Artefacto a : getArtefactos()) {
            if (a instanceof Botella && a.isRecolectado()) {
                contador++;
            }
        }
        return contador;
    }

    /**
     * Define los tipos de enemigos presentes en este nivel.
     * 
     * @return Lista de nombres de clases de enemigos para este nivel
     */
    @Override
    protected List<String> tiposEnemigos() {
        return Arrays.asList("Tucan", "Frailejon");
    }

    /**
     * Define los tipos de artefactos recolectables en este nivel.
     * 
     * @return Lista de nombres de clases de artefactos para este nivel
     */
    @Override
    protected List<String> tiposArtefactos() {
        return Arrays.asList("Botella", "Esmeralda");
    }

    /**
     * Especifica la cantidad de cada tipo de elemento en el nivel.
     * 
     * @param tipo El tipo de elemento (enemigo o artefacto)
     * @return La cantidad que debe aparecer de ese elemento
     */
    @Override
    protected int cantidadDe(String tipo) {
        switch (tipo) {
            case "Tucan": return 10;
            case "Frailejon": return 10;
            case "Botella": return 20;
            case "Esmeralda": return 3;
            default: return 0;
        }
    }

    /**
     * Indica cuántos artefactos deben ser recolectados para completar el nivel.
     * 
     * @return El número de artefactos requeridos para superar el nivel
     */
    @Override
    protected int artefactosRequeridos() {
        return 20;
    }
}