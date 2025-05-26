package autonoma.AventuraMagicaGame.ui;

import autonoma.AventuraMagicaGame.ui.PanelJuego;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.SwingUtilities;

public class VentanaPrincipal extends javax.swing.JFrame {
    private PanelJuego panelJuego;

    public VentanaPrincipal() {
        setTitle("Aventura Mágica");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(820, 640);  // tamaño ventana
        setLocationRelativeTo(null);  // centra la ventana

        // Crear y añadir el panel de juego
        panelJuego = new PanelJuego();
        add(panelJuego);

        // Crear menú simple
        crearMenu();

        // Para que el panel pueda recibir el foco y escuchar teclas
        panelJuego.requestFocusInWindow();

        setVisible(true);
    }

    private void crearMenu() {
        JMenuBar menuBar = new JMenuBar();
        JMenu menuJuego = new JMenu("Juego");

        JMenuItem pausaItem = new JMenuItem("Pausar/Reanudar");
        pausaItem.addActionListener(e -> {
            panelJuego.pausa = !panelJuego.pausa;
            panelJuego.requestFocusInWindow();
            panelJuego.repaint();
        });

        JMenuItem salirItem = new JMenuItem("Salir");
        salirItem.addActionListener(e -> {
            panelJuego.detenerJuego();
            System.exit(0);
        });

        menuJuego.add(pausaItem);
        menuJuego.addSeparator();
        menuJuego.add(salirItem);

        menuBar.add(menuJuego);
        setJMenuBar(menuBar);
    }
   
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
