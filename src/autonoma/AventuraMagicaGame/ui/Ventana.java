package autonoma.AventuraMagicaGame.ui;

import autonoma.AventuraMagicaGame.elements.Nivel;
import autonoma.AventuraMagicaGame.elements.NivelBase;
import autonoma.AventuraMagicaGame.util.GestorNivel;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JOptionPane;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.AudioInputStream;
import java.io.IOException;
import java.net.URL;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

/**
 * Clase que representa la ventana principal del juego Aventura Mágica.
 * Esta clase extiende de JFrame y maneja la interfaz gráfica del menú principal
 * y la transición al panel de juego.
 * 
 * <p>La ventana incluye:
 * <ul>
 *   <li>Un menú principal con botones para jugar, ver instrucciones y salir</li>
 *   <li>Reproducción de música de fondo</li>
 *   <li>Transición al panel de juego cuando se presiona el botón "Jugar"</li>
 * </ul>
 * 
 * @author jgiugtiñut
 * @since 27-05-2025
 * @version 2.0.0
 */
public class Ventana extends javax.swing.JFrame {
    
    /**
     * Panel donde se renderiza el juego.
     */
    private PanelJuego panelJuego;
    
    /**
     * Clip de audio para la música de fondo.
     */
    private Clip clipMusica;

    /**
     * Constructor que inicializa la ventana principal.
     * 
     * @param nivelBase El nivel base del juego que se cargará inicialmente
     */
    public Ventana(NivelBase nivelBase) {
        initComponents();

        setSize(800, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        reproducirMusicaMenu();
        // NO creamos ni ponemos el panelJuego todavía
        // Así que el botón sigue visible al iniciar
        setVisible(true);
    }

    /**
     * Reproduce la música del menú principal en loop continuo.
     * La música se carga desde el archivo "/autonoma/AventuraMagicaGame/sounds/MusicaMenu.wav".
     */
    private void reproducirMusicaMenu() {
        try {
            if (clipMusica != null && clipMusica.isRunning()) {
                clipMusica.stop();
            }
            URL sonidoURL = getClass().getResource("/autonoma/AventuraMagicaGame/sounds/MusicaMenu.wav");
            AudioInputStream audioInput = AudioSystem.getAudioInputStream(sonidoURL);
            clipMusica = AudioSystem.getClip();
            clipMusica.open(audioInput);
            clipMusica.loop(Clip.LOOP_CONTINUOUSLY);
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            System.out.println("Error al reproducir música: " + e.getMessage());
        }
    }

    /**
     * Detiene la reproducción de la música de fondo y libera los recursos.
     */
    private void detenerMusica() {
        if (clipMusica != null && clipMusica.isRunning()) {
            clipMusica.stop();
            clipMusica.close();
        }
    }

    /**
     * Método que se ejecuta al hacer clic en el botón Salir.
     * Muestra un mensaje de despedida y cierra la aplicación.
     * 
     * @param evt Evento de acción del botón
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        Jugarbtn = new javax.swing.JButton();
        ComoJugarbtn1 = new javax.swing.JButton();
        Salirbtn = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(118, 153, 26));
        jPanel1.setBorder(javax.swing.BorderFactory.createMatteBorder(10, 10, 10, 10, new java.awt.Color(153, 93, 26)));
        jPanel1.setFocusable(false);

        Jugarbtn.setBackground(new java.awt.Color(255, 255, 102));
        Jugarbtn.setFont(new java.awt.Font("Lucida Fax", 3, 18)); // NOI18N
        Jugarbtn.setForeground(new java.awt.Color(51, 51, 0));
        Jugarbtn.setText("Jugar");
        Jugarbtn.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 0), 10));
        Jugarbtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                JugarbtnMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                JugarbtnMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                JugarbtnMouseExited(evt);
            }
        });
        Jugarbtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JugarbtnActionPerformed(evt);
            }
        });

        ComoJugarbtn1.setBackground(new java.awt.Color(255, 255, 102));
        ComoJugarbtn1.setFont(new java.awt.Font("Lucida Fax", 1, 18)); // NOI18N
        ComoJugarbtn1.setForeground(new java.awt.Color(51, 51, 0));
        ComoJugarbtn1.setText("¿Como Jugar?");
        ComoJugarbtn1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 0), 10));
        ComoJugarbtn1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ComoJugarbtn1MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                ComoJugarbtn1MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                ComoJugarbtn1MouseExited(evt);
            }
        });
        ComoJugarbtn1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ComoJugarbtn1ActionPerformed(evt);
            }
        });

        Salirbtn.setBackground(new java.awt.Color(255, 255, 102));
        Salirbtn.setFont(new java.awt.Font("Lucida Fax", 1, 18)); // NOI18N
        Salirbtn.setText("Salir");
        Salirbtn.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 0), 10));
        Salirbtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                SalirbtnMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                SalirbtnMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                SalirbtnMouseExited(evt);
            }
        });
        Salirbtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SalirbtnActionPerformed(evt);
            }
        });

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/autonoma/AventuraMagicaGame/images/FondoMenu.png"))); // NOI18N
        jLabel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 0), 10));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 578, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(Jugarbtn, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(32, 32, 32)
                        .addComponent(Salirbtn, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(ComoJugarbtn1)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(54, 54, 54)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(Jugarbtn, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(91, 91, 91)
                        .addComponent(ComoJugarbtn1, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(Salirbtn, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 399, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(47, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void SalirbtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SalirbtnActionPerformed
    JOptionPane.showMessageDialog(this, "Gracias por jugar Aventura Mágica 💫\n!", "Hasta pronto", JOptionPane.INFORMATION_MESSAGE);
    detenerMusica();
    System.exit(0);
    }//GEN-LAST:event_SalirbtnActionPerformed

    private void SalirbtnMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_SalirbtnMouseExited
    Salirbtn.setBackground(Color.YELLOW);
    }//GEN-LAST:event_SalirbtnMouseExited

    private void SalirbtnMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_SalirbtnMouseEntered
    Salirbtn.setBackground(Color.ORANGE);
    }//GEN-LAST:event_SalirbtnMouseEntered

    private void SalirbtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_SalirbtnMouseClicked

    }//GEN-LAST:event_SalirbtnMouseClicked
 /**
     * Método que se ejecuta al hacer clic en el botón "¿Cómo Jugar?".
     * Muestra un diálogo con las instrucciones del juego.
     * 
     * @param evt Evento de acción del botón
     */
    private void ComoJugarbtn1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ComoJugarbtn1ActionPerformed
        JOptionPane.showMessageDialog(this,
            "Bienvenido a Aventura Mágica\n\n" +
            "🕹️ Usa las teclas para mover a tu personaje\n" +
            "🎯 Atrapa objetos buenos y evita los peligros\n" +
            "❓ Recoge los símbolos de pregunta para obtener puntos\n\n" +
            "¡Diviértete y alcanza la máxima puntuación!",
            "¿Cómo jugar?", JOptionPane.INFORMATION_MESSAGE);
    }//GEN-LAST:event_ComoJugarbtn1ActionPerformed

    private void ComoJugarbtn1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ComoJugarbtn1MouseExited
        ComoJugarbtn1.setBackground(Color.YELLOW);
    }//GEN-LAST:event_ComoJugarbtn1MouseExited

    private void ComoJugarbtn1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ComoJugarbtn1MouseEntered
        ComoJugarbtn1.setBackground(Color.GRAY);
    }//GEN-LAST:event_ComoJugarbtn1MouseEntered

    private void ComoJugarbtn1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ComoJugarbtn1MouseClicked

    }//GEN-LAST:event_ComoJugarbtn1MouseClicked

    private void JugarbtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JugarbtnActionPerformed
        try {
            GestorNivel gestor = new GestorNivel();
            Nivel nivelActual = gestor.getNivelActual();

            panelJuego = new PanelJuego((NivelBase) nivelActual);
            panelJuego.setPreferredSize(new Dimension(800, 600));

            // Reemplazamos todo el contenido actual (el botón) por el panelJuego
            getContentPane().removeAll();
            setLayout(new BorderLayout());
            add(panelJuego, BorderLayout.CENTER);

            revalidate();
            repaint();

            panelJuego.requestFocusInWindow();
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error al iniciar el juego: " + e.getMessage());
        }
    }//GEN-LAST:event_JugarbtnActionPerformed

    private void JugarbtnMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JugarbtnMouseExited
        Jugarbtn.setBackground(Color.YELLOW);
    }//GEN-LAST:event_JugarbtnMouseExited

    private void JugarbtnMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JugarbtnMouseEntered
        Jugarbtn.setBackground(Color.GRAY);
    }//GEN-LAST:event_JugarbtnMouseEntered

    private void JugarbtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JugarbtnMouseClicked

    }//GEN-LAST:event_JugarbtnMouseClicked

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton ComoJugarbtn1;
    private javax.swing.JButton Jugarbtn;
    private javax.swing.JButton Salirbtn;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables
}
