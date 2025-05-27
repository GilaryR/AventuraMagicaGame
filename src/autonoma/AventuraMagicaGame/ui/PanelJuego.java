package autonoma.AventuraMagicaGame.ui;


import autonoma.AventuraMagicaGame.util.ControladorJuego;
import autonoma.AventuraMagicaGame.util.GestorNivel;
import autonoma.AventuraMagicaGame.elements.Jugador;
import autonoma.AventuraMagicaGame.elements.Nivel;
import autonoma.AventuraMagicaGame.elements.NivelBase;
import autonoma.AventuraMagicaGame.exceptions.SonidoNoEncontradoException;
import autonoma.AventuraMagicaGame.thread.HiloJuego;
import autonoma.AventuraMagicaGame.thread.HiloProgresoNivel;
import autonoma.AventuraMagicaGame.util.ReproductorSonido;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class PanelJuego extends JPanel {
    private Image fondo;
    private HiloJuego hiloJuego;
    private HiloProgresoNivel hiloProgreso;
    private int puntaje;
    private Nivel nivel;
    private GestorNivel gestorNivel;
    private ControladorJuego controlador;
    private boolean pausa;
    
    public PanelJuego(NivelBase nivelBase) {
        this.gestorNivel = new GestorNivel();
        this.nivel = (Nivel) nivelBase;  // casteo seguro, asumiendo que NivelBase es base de Nivel
        this.controlador = new ControladorJuego((NivelBase) this.nivel);
        this.puntaje = 0;
        this.pausa = false;

        actualizarFondo();

        setFocusable(true);
        setPreferredSize(new Dimension(800, 600));
        configurarControles();

        addHierarchyListener(e -> {
            if ((e.getChangeFlags() & HierarchyEvent.SHOWING_CHANGED) != 0 && isShowing()) {
                requestFocusInWindow();
            }
        });

        iniciarHilos();
    }
    private void actualizarFondo() {
        if (nivel != null && nivel.getFondo() != null) {
            this.fondo = nivel.getFondo();
        } else {
            this.fondo = new ImageIcon(getClass().getResource("/autonoma/AventuraMagicaGame/images/Fondo.jpg")).getImage();
        }
    }

    private void iniciarHilos() {
        this.hiloJuego = new HiloJuego(this);
        this.hiloJuego.start();

        this.hiloProgreso = new HiloProgresoNivel(
            controlador.getJugador(),
            gestorNivel,
            this::cargarSiguienteNivel
        );
        this.hiloProgreso.start();
    }

    // Variables declaration - do not modify                     
    // End of variables declaration
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents
   private void configurarControles() {
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (pausa) return;

                int dx = 0, dy = 0;
                int velocidad = controlador.getJugador().getVelocidad();

                switch (e.getKeyCode()) {
                    case KeyEvent.VK_LEFT -> dx = -velocidad;
                    case KeyEvent.VK_RIGHT -> dx = velocidad;
                    case KeyEvent.VK_UP -> dy = -velocidad;
                    case KeyEvent.VK_DOWN -> dy = velocidad;
                    case KeyEvent.VK_P -> pausa = !pausa;
                    case KeyEvent.VK_ENTER -> verificarAvanceNivel();
                }

                controlador.getJugador().mover(dx, dy, getWidth(), getHeight());
                repaint();
            }
        });
    }

    public void actualizarJuego() throws SonidoNoEncontradoException {
        if (!pausa && controlador != null) {
            controlador.moverEnemigos();
            controlador.verificarColisiones();
            repaint();

            Jugador jugador = controlador.getJugador();
            if (jugador != null && jugador.getVidas() <= 0) {
                ReproductorSonido.reproducir("/autonoma/AventuraMagicaGame/sounds/GameOver.wav");
                mostrarPantallaGameOver();
            }
        }
    }

    private void verificarAvanceNivel() {
        if (controlador.getJugador().getBotellasRecolectadas() >= nivel.getArtefactosRequeridos()) {
            cargarSiguienteNivel();
        }
    }

    private void cargarSiguienteNivel() {
        JOptionPane.showMessageDialog(this,
            "¡Nivel " + gestorNivel.getNumeroNivel() + " completado!",
            "Nivel Completado",
            JOptionPane.INFORMATION_MESSAGE);

        if (gestorNivel.esUltimoNivel()) {
            mostrarPantallaVictoria();
            detenerJuego();
            return;
        }

        int vidas = controlador.getJugador().getVidas();
        int puntajeJugador = controlador.getJugador().getPuntaje();

        gestorNivel.avanzarNivel();
        this.nivel = gestorNivel.getNivelActual();
        this.controlador = new ControladorJuego((NivelBase) this.nivel);
        this.controlador.getJugador().setVidas(vidas);
        this.controlador.getJugador().setPuntaje(puntajeJugador);
        this.controlador.getJugador().resetearBotellas();
        actualizarFondo();
        repaint();
    }

    public void detenerJuego() {
        if (hiloJuego != null) hiloJuego.detener();
        if (hiloProgreso != null) hiloProgreso.detener();
    }

    private void mostrarPantallaGameOver() {
        JOptionPane.showMessageDialog(this,
            "¡Game Over! Te has quedado sin vidas.",
            "Fin del Juego",
            JOptionPane.INFORMATION_MESSAGE);
        System.exit(0);
    }

    private void mostrarPantallaVictoria() {
        SwingUtilities.invokeLater(() -> {
            JOptionPane.showMessageDialog(
                null,
                "¡Felicidades! Has ganado el juego 🎉",
                "Victoria",
                JOptionPane.INFORMATION_MESSAGE
            );
            System.exit(0);
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (fondo != null) {
            g.drawImage(fondo, 0, 0, getWidth(), getHeight(), this);
        }

        controlador.dibujarArtefactos(g);
        controlador.dibujarEnemigos(g);
        controlador.dibujarSimbolos(g);
        controlador.dibujarJugador(g);

        dibujarHUD(g);
        if (pausa) {
            dibujarMensajePausa(g);
        }
    }

    private void dibujarHUD(Graphics g) {
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 18));
        g.drawString("Nivel: " + gestorNivel.getNumeroNivel(), 20, 20);
        g.drawString("Puntaje: " + controlador.getJugador().getPuntaje(), 20, 45);
        g.drawString("Vidas: " + controlador.getJugador().getVidas(), 20, 70);
        g.drawString("Artefactos: " +
                controlador.getJugador().getBotellasRecolectadas() + "/" +
                nivel.getArtefactosRequeridos(), 20, 95);
    }

    private void dibujarMensajePausa(Graphics g) {
        g.setColor(new Color(0, 0, 0, 150));
        g.fillRect(0, 0, getWidth(), getHeight());

        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 48));
        String mensaje = "PAUSA";
        FontMetrics fm = g.getFontMetrics();
        int x = (getWidth() - fm.stringWidth(mensaje)) / 2;
        int y = (getHeight() - fm.getHeight()) / 2 + fm.getAscent();
        g.drawString(mensaje, x, y);
    }
}

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
