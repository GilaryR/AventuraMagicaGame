package autonoma.AventuraMagicaGame.thread;

import autonoma.AventuraMagicaGame.util.GestorNivel;
import autonoma.AventuraMagicaGame.elements.Jugador;
/**
 * Hilo que verifica periódicamente el progreso del jugador en el nivel actual
 * y gestiona el avance de nivel cuando se completan los requisitos.
 * 
 *Este hilo se ejecuta en segundo plano y verifica en intervalos
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
    
    /** Intervalo de tiempo entre verificaciones en milisegundos */
    private static final int INTERVALO_VERIFICACION = 500;

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
            verificarProgreso();
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
        int requeridos = gestor.getArtefactosRequeridosActual();
        int botellasRecolectadas = jugador.getBotellasRecolectadas();
        
        if (botellasRecolectadas >= requeridos) {
            if (puedeAvanzarMas()) {
                alCompletarNivel.run();     // Avanza de nivel
                reiniciarContadores();      // Luego reinicia los contadores
            } else {
                System.out.println("¡Todos los niveles completados!");
                detener(); // Detiene el hilo si no hay más niveles
            }
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
}