package autonoma.AventuraMagicaGame.thread;

import autonoma.AventuraMagicaGame.ui.PanelJuego;

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
            }
        }
    }
}