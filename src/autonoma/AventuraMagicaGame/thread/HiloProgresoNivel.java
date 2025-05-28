
package autonoma.AventuraMagicaGame.thread;

import autonoma.AventuraMagicaGame.elements.Jugador;
import autonoma.AventuraMagicaGame.util.GestorNivel;


/**
 * Hilo que verifica periódicamente el progreso del jugador en el nivel actual
 * y gestiona el avance de nivel cuando se completan los requisitos.
 * 
 * Este hilo se ejecuta en segundo plano y verifica en intervalos
 * regulares si el jugador ha recolectado suficientes artefactos para completar
 * el nivel actual. Cuando se cumplen los requisitos, ejecuta una acción de callback
 * para avanzar al siguiente nivel.
 * 
 * El hilo puede ser detenido de forma segura llamando al método {@link #detener()}.
 * @author Gilary Rugeles
 * @since 27/05/2025
 * @version  3.1
 */
public class HiloProgresoNivel extends Thread {
    /** Jugador cuyo progreso se está monitorizando */
    private final Jugador jugador;
    
    /** Gestor que contiene la información de los niveles */
    private final GestorNivel gestor;
    
    /** Acción a ejecutar cuando se completa un nivel */
    private final Runnable alCompletarNivel;
    
    /** Flag que controla la ejecución del hilo */
    private volatile boolean enEjecucion;
    
    /** Flag que indica si se está procesando un cambio de nivel */
    private volatile boolean cambiandoNivel;
    
    /** Intervalo de tiempo entre verificaciones en milisegundos */
    private static final int INTERVALO_VERIFICACION = 500;
    
    /** Tiempo de espera después del cambio de nivel en milisegundos */
    private static final int PAUSA_CAMBIO_NIVEL = 1000;

    /**
     * Crea una nueva instancia del hilo de verificación de progreso.
     * 
     * @param jugador Jugador cuyo progreso se va a monitorizar
     * @param gestor Gestor que contiene la información de niveles
     * @param alCompletarNivel Acción a ejecutar cuando se complete un nivel
     */
    public HiloProgresoNivel(Jugador jugador, GestorNivel gestor, Runnable alCompletarNivel) {
        this.jugador = jugador;
        this.gestor = gestor;
        this.alCompletarNivel = alCompletarNivel;
        this.enEjecucion = true;
        this.cambiandoNivel = false;
        this.setDaemon(true); // Para que el hilo no impida cerrar la aplicación
    }

    /**
     * Método principal del hilo que realiza las verificaciones periódicas.
     * 
     * <p>El hilo continuará ejecutándose hasta que se llame a {@link #detener()}.</p>
     */
    @Override
    public void run() {
        while (enEjecucion) {
            if (!cambiandoNivel) {
                verificarProgreso();
            }
            esperarIntervalo();
        }
    }

    /**
     * Verifica si el jugador ha recolectado suficientes artefactos para completar el nivel.
     * 
     * <p>Si se cumplen los requisitos, ejecuta la acción de completado de nivel
     * y reinicia los contadores del jugador.</p>
     */
    private void verificarProgreso() {
        try {
            int requeridos = gestor.getArtefactosRequeridosActual();
            int botellasRecolectadas = jugador.getBotellasRecolectadas();
            if (botellasRecolectadas >= requeridos) {
                procesarCompletadoNivel();
            }
        } catch (Exception e) {
            System.err.println("Error verificando progreso: " + e.getMessage());
        }
    }

    /**
     * Procesa el completado del nivel actual de forma sincronizada.
     */
    private synchronized void procesarCompletadoNivel() {
        if (cambiandoNivel) {
            return; // Ya se está procesando un cambio de nivel
        }
        
        cambiandoNivel = true;
        
        try {
            if (puedeAvanzarMas()) {
      
                // Primero reiniciar contadores
                reiniciarContadores();
                
                // Luego avanzar de nivel
                alCompletarNivel.run();
                
                // Pausa para permitir que se procese el cambio completamente
                Thread.sleep(PAUSA_CAMBIO_NIVEL);

                
            } else {
                detener(); // Detiene el hilo si no hay más niveles
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            enEjecucion = false;
        } finally {
            cambiandoNivel = false;
        }
    }

    /**
     * Reinicia los contadores de artefactos recolectados del jugador.
     */
    private void reiniciarContadores() {
        jugador.setBotellasRecolectadas(0);
        jugador.setEsmeraldasRecolectadas(0);
 
    }

    /**
     * Comprueba si hay más niveles disponibles después del actual.
     * 
     * @return true si hay más niveles disponibles, false si se ha alcanzado el último nivel
     */
    private boolean puedeAvanzarMas() {
        return gestor.getNumeroNivel() < gestor.getCantidadNiveles();
    }

    /**
     * Espera el intervalo de verificación configurado.
     * 
     * <p>Si el hilo es interrumpido durante la espera, se detendrá su ejecución.</p>
     */
    private void esperarIntervalo() {
        try {
            Thread.sleep(INTERVALO_VERIFICACION);
        } catch (InterruptedException e) {
            enEjecucion = false;
            Thread.currentThread().interrupt();
        }
    }

    /**
     * Detiene de forma segura la ejecución del hilo.
     * 
     * <p>Este método puede ser llamado desde otro hilo para terminar la ejecución.</p>
     */
    public void detener() {
        enEjecucion = false;
        interrupt();
    }
    
    /**
     * Verifica si el hilo está actualmente cambiando de nivel.
     * 
     * @return true si se está procesando un cambio de nivel
     */
    public boolean estaCambiandoNivel() {
        return cambiandoNivel;
    }
}