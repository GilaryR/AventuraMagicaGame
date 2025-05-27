package autonoma.AventuraMagicaGame.thread;

import autonoma.AventuraMagicaGame.util.GestorNivel;
import autonoma.AventuraMagicaGame.elements.Jugador;



public class HiloProgresoNivel extends Thread {
  private final Jugador jugador;
    private final GestorNivel gestor;
    private final Runnable alCompletarNivel;
    private volatile boolean enEjecucion;
    private static final int INTERVALO_VERIFICACION = 500;

    public HiloProgresoNivel(Jugador jugador, GestorNivel gestor, Runnable alCompletarNivel) {
        this.jugador = jugador;
        this.gestor = gestor;
        this.alCompletarNivel = alCompletarNivel;
        this.enEjecucion = true;
        this.setDaemon(true); // Para que el hilo no impida cerrar la aplicación
    }

    @Override
    public void run() {
        while (enEjecucion) {
            verificarProgreso();
            esperarIntervalo();
        }
    }

    private void verificarProgreso() {
        int requeridos = gestor.getNivelActual().getArtefactosRequeridos();
        int recolectados = jugador.getBotellasRecolectadas() + jugador.getEsmeraldasRecolectadas();

        if (recolectados >= requeridos) {
            reiniciarContadores();
            if (puedeAvanzarMas()) {
                alCompletarNivel.run();  // Ejecutar acción para avanzar de nivel
            }
        }
    }

    private void reiniciarContadores() {
        jugador.setBotellasRecolectadas(0);
        jugador.setEsmeraldasRecolectadas(0);
    }

    private boolean puedeAvanzarMas() {
        return gestor.getNumeroNivel() < gestor.getCantidadNiveles();
    }

    private void esperarIntervalo() {
        try {
            Thread.sleep(INTERVALO_VERIFICACION);
        } catch (InterruptedException e) {
            enEjecucion = false;
            Thread.currentThread().interrupt();
        }
    }

    public void detener() {
        enEjecucion = false;
        interrupt();
    }
}