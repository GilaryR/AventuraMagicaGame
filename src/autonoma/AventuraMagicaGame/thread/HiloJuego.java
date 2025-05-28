package autonoma.AventuraMagicaGame.thread;

import autonoma.AventuraMagicaGame.exceptions.SonidoNoEncontradoException;
import autonoma.AventuraMagicaGame.ui.PanelJuego;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Hilo principal del juego que gestiona el ciclo de actualización y repintado.
 * 
 * Esta clase extiende de {@link Thread} y ejecuta un bucle continuo mientras el juego
 * está en ejecución. En cada iteración, actualiza el estado del juego y repinta el
 * panel principal. Se utiliza una pausa de 100 ms para mantener una tasa de refresco
 * constante de 10 actualizaciones por segundo.
 * 
 * También maneja posibles excepciones relacionadas con la carga de sonidos.
 * 
 * @author Alejandra Ortega
 * @version 3.1
 * @since 28/05/2025
 */
public class HiloJuego extends Thread {

    /** Panel principal donde se actualiza y dibuja el juego. */
    private final PanelJuego panelJuego;

    /** Bandera que controla la ejecución del hilo. */
    private boolean enEjecucion;

    /**
     * Crea una nueva instancia del hilo de juego.
     *
     * @param panelJuego El panel del juego que se actualizará y repintará.
     */
    public HiloJuego(PanelJuego panelJuego) {
        this.panelJuego = panelJuego;
        this.enEjecucion = true;
    }

    /**
     * Detiene la ejecución del hilo de juego.
     */
    public void detener() {
        this.enEjecucion = false;
    }

    /**
     * Método que ejecuta el bucle principal del juego.
     * Actualiza el estado del juego y lo redibuja a intervalos regulares.
     * También maneja interrupciones y errores de sonido.
     */
    @Override
    public void run() {
        while (enEjecucion) {
            try {
                panelJuego.actualizarJuego();  // Lógica de juego
                panelJuego.repaint();          // Redibujar pantalla
                Thread.sleep(100);             // Espera para mantener el ritmo (10 FPS)
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (SonidoNoEncontradoException ex) {
                Logger.getLogger(HiloJuego.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
