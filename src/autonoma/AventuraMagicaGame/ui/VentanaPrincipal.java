/*package autonoma.AventuraMagicaGame.ui;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class VentanaPrincipal extends javax.swing.JFrame {

    private PanelJuego panelJuego;

    public VentanaPrincipal() {
        initComponents();

        setTitle("Aventura Mágica");

        // Crear y añadir el panel de juego
        panelJuego = new PanelJuego();
        getContentPane().add(panelJuego);

        // Para que el panel pueda recibir el foco y escuchar teclas
        panelJuego.requestFocusInWindow();

        // Crear menú simple
        crearMenu();

        setSize(820, 640);
        setLocationRelativeTo(null);  // centra la ventana
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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

    private void initComponents() {
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        // No uses el GroupLayout automático vacío
        // Se maneja todo manualmente con el panelJuego agregado en el constructor
        // Por eso no configuramos layout aquí

        pack();
    }
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
*/
