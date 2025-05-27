
package autonoma.AventuraMagicaGame.util;

import autonoma.AventuraMagicaGame.elements.Nivel;
import autonoma.AventuraMagicaGame.elements.NivelDificil;
import autonoma.AventuraMagicaGame.elements.NivelFacil;
import autonoma.AventuraMagicaGame.elements.NivelIntermedio;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Gilary
 */

public class GestorNivel {
    private List<Nivel> niveles;
    private int nivelActual;
    private int[] botellasRequeridasPorNivel;
    
    public GestorNivel() {
        this.niveles = new ArrayList<>();
        this.nivelActual = 0;
        inicializarNiveles();
        
        // Inicializar array con los requisitos de botellas por nivel
        this.botellasRequeridasPorNivel = new int[] {
            20, // Nivel Fácil
            40, // Nivel Intermedio
            60  // Nivel Difícil
        };
    }
    
    private void inicializarNiveles() {
        niveles.add(new NivelFacil());      // Nivel 1
        niveles.add(new NivelIntermedio()); // Nivel 2
        niveles.add(new NivelDificil());    // Nivel 3
    }
    
    /**
     * Obtiene el nivel actual
     * @return Nivel en juego actualmente
     */
    public Nivel getNivelActual() {
        if (niveles.isEmpty()) {
            throw new IllegalStateException("No hay niveles disponibles");
        }
        return niveles.get(nivelActual);
    }
    
    /**
     * Avanza al siguiente nivel disponible
     */
    public void avanzarNivel() {
        if (!esUltimoNivel()) {
            nivelActual++;
        }
    }
    
    /**
     * Verifica si el nivel actual es el último
     * @return true si es el último nivel, false en caso contrario
     */
    public boolean esUltimoNivel() {
        return nivelActual >= niveles.size() - 1;
    }
    
    /**
     * Obtiene el número del nivel actual (basado en 1)
     * @return Número del nivel actual
     */
    public int getNumeroNivel() {
        return nivelActual + 1;
    }
    
    /**
     * Obtiene la cantidad total de niveles
     * @return Número total de niveles disponibles
     */
    public int size() {
        return niveles.size();
    }
    
    /**
     * Verifica si todos los niveles han sido completados
     * @param botellasTotalesRecolectadas
     * @param botellasTotales Total de botellas recolectadas en el juego
     * @return true si todos los niveles están completados
     */
    public boolean todosNivelesCompletados(int botellasTotales) {
        int totalRequerido = 0;
        for (int requeridas : botellasRequeridasPorNivel) {
            totalRequerido += requeridas;
        }
        return botellasTotales >= totalRequerido;
    }
    
    /**
     * Verifica si el nivel actual está completado
     * @param botellasRecolectadas Botellas recolectadas en el nivel actual
     * @return true si el nivel está completado
     */
    public boolean nivelActualCompletado(int botellasRecolectadas) {
        return botellasRecolectadas >= getNivelActual().getArtefactosRequeridos();
    }
    
    /**
     * Obtiene los artefactos requeridos para el nivel actual
     * @return Cantidad de artefactos necesarios
     */
    public int getArtefactosRequeridosActual() {
        return getNivelActual().getArtefactosRequeridos();
    }
    
    /**
     * Reinicia el gestor al primer nivel
     */
    public void reiniciar() {
        nivelActual = 0;
    }
    
    /**
     * Verifica si hay niveles disponibles
     * @return true si hay niveles cargados
     */
    public boolean tieneNiveles() {
        return !niveles.isEmpty();
    }
    
    /**
     * Obtiene los requisitos de botellas para un nivel específico
     * @param numeroNivel Número del nivel (1-based)
     * @return Botellas requeridas para ese nivel
     */
    public int getBotellasRequeridasParaNivel(int numeroNivel) {
        if (numeroNivel < 1 || numeroNivel > botellasRequeridasPorNivel.length) {
            throw new IllegalArgumentException("Número de nivel inválido");
        }
        return botellasRequeridasPorNivel[numeroNivel - 1];
    
    }
    public int getCantidadNiveles() {
        return niveles.size(); // la cantidad total de niveles cargados
    }
}