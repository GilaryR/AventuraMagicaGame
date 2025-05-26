package autonoma.AventuraMagicaGame.thread;

import autonoma.AventuraMagicaGame.elements.GestorNivel;
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
        this.setDaemon(true); // Hilo demonio para que no impida la salida de la aplicación
    }

    @Override
    public void run() {
            verificarProgreso();
            esperarIntervalo();
        }

    private void verificarProgreso() {
        int requeridos = gestor.getNivelActual().getArtefactosRequeridos();
        int recolectados = jugador.getBotellasRecolectadas() + jugador.getEsmeraldasRecolectadas();
        
        if (recolectados >= requeridos) {
            reiniciarContadores();
            avanzarNivel();
        }
    }

    private void reiniciarContadores() {
        jugador.setBotellasRecolectadas(0);
        jugador.setEsmeraldasRecolectadas(0);
    }
public boolean avanzarNivel() {
    if (puedeAvanzarMas()) {
        int numeroNivel = 0;
        numeroNivel++;
        return true;
    }
    return false;
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
    private boolean puedeAvanzarMas() {
    int numeroNivel = 0;
    return numeroNivel < 3;
}
}