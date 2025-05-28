
package autonoma.AventuraMagicaGame.util;

import autonoma.AventuraMagicaGame.elements.Nivel;
import autonoma.AventuraMagicaGame.elements.NivelDificil;
import autonoma.AventuraMagicaGame.elements.NivelFacil;
import autonoma.AventuraMagicaGame.elements.NivelIntermedio;
import java.util.ArrayList;
import java.util.List;

/**
 * Gestor que administra los niveles del juego, controlando el progreso
 * y las transiciones entre ellos.
 * 
 *Esta clase mantiene una lista de niveles disponibles y gestiona:
 *El nivel actual en juego
 *Los requisitos para completar cada nivel
 * Las transiciones entre niveles
 * El estado de completado de los niveles
 * 
 * @author Gilary Rugeles
 * @since 27/05/2025
 * @version  3.1
 */
public class GestorNivel {
    /** Lista de niveles disponibles en el juego */
    private List<Nivel> niveles;
    
    /** Índice del nivel actual (0-based) */
    private int nivelActual;
    
    /** Array con los requisitos de botellas para cada nivel */
    private int[] botellasRequeridasPorNivel;
    
    /**
     * Constructor que inicializa el gestor con los niveles predeterminados.
     * 
     * <p>Los niveles se inicializan en este orden:</p>
     * <ol>
     *   <li>Nivel Fácil (requiere 20 botellas)</li>
     *   <li>Nivel Intermedio (requiere 40 botellas)</li>
     *   <li>Nivel Difícil (requiere 60 botellas)</li>
     * </ol>
     */
    public GestorNivel() {
        this.niveles = new ArrayList<>();
        this.nivelActual = 0;
        inicializarNiveles();
        
        this.botellasRequeridasPorNivel = new int[] {
            20, // Nivel Fácil
            40, // Nivel Intermedio
            60  // Nivel Difícil
        };
    }
    
    /**
     * Inicializa la lista de niveles con las implementaciones concretas.
     */
    private void inicializarNiveles() {
        niveles.add(new NivelFacil());      // Nivel 1
        niveles.add(new NivelIntermedio()); // Nivel 2
        niveles.add(new NivelDificil());    // Nivel 3
    }
    
    /**
     * Obtiene el nivel actualmente en juego.
     * 
     * @return Instancia del nivel actual
     * @throws IllegalStateException si no hay niveles disponibles
     */
    public Nivel getNivelActual() {
        if (niveles.isEmpty()) {
            throw new IllegalStateException("No hay niveles disponibles");
        }
        return niveles.get(nivelActual);
    }
    
    /**
     * Avanza al siguiente nivel si no es el último.
     */
    public void avanzarNivel() {
        if (!esUltimoNivel()) {
            nivelActual++;
        }
    }
    
    /**
     * Intenta pasar al siguiente nivel si se cumplen las condiciones.
     * 
     * @param botellasRecolectadas cantidad de botellas recolectadas en el nivel actual
     * @return true si se pudo avanzar al siguiente nivel, false si no se cumplieron
     *         las condiciones (nivel no completado o era el último nivel)
     */
    public boolean pasarAlSiguienteNivel(int botellasRecolectadas) {
        if (nivelActualCompletado(botellasRecolectadas) && !esUltimoNivel()) {
            avanzarNivel();
            return true;
        }
        return false;
    }
    
    /**
     * Verifica si el nivel actual es el último disponible.
     * 
     * @return true si es el último nivel, false si hay más niveles después del actual
     */
    public boolean esUltimoNivel() {
        return nivelActual >= niveles.size() - 1;
    }
    
    /**
     * Obtiene el número del nivel actual (comenzando desde 1).
     * 
     * @return Número del nivel actual en formato legible (1-based)
     */
    public int getNumeroNivel() {
        return nivelActual + 1;
    }
    
    /**
     * Obtiene la cantidad total de niveles disponibles.
     * 
     * @return Número total de niveles
     */
    public int size() {
        return niveles.size();
    }

    /**
     * Verifica si todos los niveles han sido completados.
     * 
     * @param botellasTotales Total de botellas recolectadas en el juego
     * @return true si se han recolectado suficientes botellas para completar
     *         todos los niveles, false en caso contrario
     */
    public boolean todosNivelesCompletados(int botellasTotales) {
        int totalRequerido = 0;
        for (int requeridas : botellasRequeridasPorNivel) {
            totalRequerido += requeridas;
        }
        return botellasTotales >= totalRequerido;
    }

    /**
     * Verifica si el nivel actual está completado.
     * 
     * @param botellasRecolectadas Botellas recolectadas en el nivel actual
     * @return true si las botellas recolectadas son iguales o superan
     *         las requeridas para el nivel actual
     */
    public boolean nivelActualCompletado(int botellasRecolectadas) {
        return botellasRecolectadas >= botellasRequeridasPorNivel[nivelActual];
    }

    /**
     * Obtiene la cantidad de artefactos (botellas) requeridos para completar
     * el nivel actual.
     * 
     * @return Cantidad de botellas necesarias para el nivel actual
     */
    public int getArtefactosRequeridosActual() {
        return botellasRequeridasPorNivel[nivelActual];
    }

    /**
     * Reinicia el gestor al primer nivel.
     */
    public void reiniciar() {
        nivelActual = 0;
    }

    /**
     * Verifica si hay niveles cargados en el gestor.
     * 
     * @return true si hay al menos un nivel disponible, false si la lista está vacía
     */
    public boolean tieneNiveles() {
        return !niveles.isEmpty();
    }

    /**
     * Obtiene los requisitos de botellas para un nivel específico.
     * 
     * @param numeroNivel Número del nivel (comenzando desde 1)
     * @return Cantidad de botellas requeridas para el nivel especificado
     * @throws IllegalArgumentException si el número de nivel es inválido
     */
    public int getBotellasRequeridasParaNivel(int numeroNivel) {
        if (numeroNivel < 1 || numeroNivel > botellasRequeridasPorNivel.length) {
            throw new IllegalArgumentException("Número de nivel inválido");
        }
        return botellasRequeridasPorNivel[numeroNivel - 1];
    }

    /**
     * Obtiene la cantidad total de niveles cargados.
     * 
     * @return Número total de niveles disponibles
     */
    public int getCantidadNiveles() {
        return niveles.size();
    }
}