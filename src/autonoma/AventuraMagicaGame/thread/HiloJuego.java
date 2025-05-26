package autonoma.AventuraMagicaGame.thread;

import autonoma.AventuraMagicaGame.exceptions.SonidoNoEncontradoException;
import autonoma.AventuraMagicaGame.ui.PanelJuego;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author jgiugtiñut
 */
public class HiloJuego extends Thread {
    private final PanelJuego panelJuego;
    private boolean enEjecucion;

    public HiloJuego(PanelJuego panelJuego) {
        this.panelJuego = panelJuego;
        this.enEjecucion = true;
    }

    public void detener() {
        this.enEjecucion = false;
    }

    @Override
    public void run() {
        while (enEjecucion) {
            try {
                panelJuego.actualizarJuego();
                panelJuego.repaint();
                Thread.sleep(100); // 10 actualizaciones por segundo
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (SonidoNoEncontradoException ex) {
                Logger.getLogger(HiloJuego.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}