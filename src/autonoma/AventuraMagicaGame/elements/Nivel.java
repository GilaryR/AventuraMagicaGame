
package autonoma.AventuraMagicaGame.elements;

import java.awt.Image;
import java.util.List;

/**
 * Interfaz que define el contrato para los niveles del juego.
 * Especifica los elementos que debe contener cada nivel y los métodos
 * para acceder a ellos.
 * @author Gilary Rugeles
 * @since 26/05/2025
 * @version  1.0
 */

public interface Nivel {
    
    /**
     * Obtiene la lista de enemigos presentes en el nivel.
     * @return Lista de objetos Enemigo del nivel
     */
    List<Enemigo> getEnemigos();
    
    /**
     * Obtiene la lista de artefactos recolectables del nivel.
     * @return Lista de objetos Artefacto del nivel
     */
    List<Artefacto> getArtefactos();
    
    /**
     * Obtiene la lista de símbolos de pregunta (acertijos) del nivel.
     * @return Lista de objetos SimboloPregunta del nivel
     */
    List<SimboloPregunta> getSimbolosPregunta();

    /**
     * Obtiene la cantidad de artefactos requeridos para completar el nivel.
     * @return Número de artefactos necesarios para superar el nivel
     */
    int getArtefactosRequeridos();

    /**
     * Obtiene el tamaño o complejidad del nivel.
     * @return Valor numérico que representa el tamaño/complejidad del nivel
     */
    int size();

    /**
     * Obtiene la imagen de fondo del nivel.
     * @return Objeto Image con el fondo del nivel
     */
    Image getFondo();
}