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
 *
 * @author jgiugtiñut
 */
public class Ventana extends javax.swing.JFrame {
 private PanelJuego panelJuego;
 private Clip clipMusica;


    /**
     * Creates new form Ventana
     * @param nivelBase
     */
    public Ventana(NivelBase nivelBase) {
        initComponents();

        setSize(800, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // NO creamos ni ponemos el panelJuego todavía
        // Así que el botón sigue visible al iniciar
        setVisible(true);
    }
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        Jugarbtn = new javax.swing.JButton();
        ComoJugarbtn1 = new javax.swing.JButton();
        Salirbtn = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(118, 153, 26));
        jPanel1.setBorder(javax.swing.BorderFactory.createMatteBorder(10, 10, 10, 10, new java.awt.Color(153, 93, 26)));

        Jugarbtn.setBackground(new java.awt.Color(255, 255, 102));
        Jugarbtn.setFont(new java.awt.Font("Lucida Fax", 3, 18)); // NOI18N
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
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 577, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(ComoJugarbtn1))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(Jugarbtn, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addComponent(Salirbtn, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(54, 54, 54)
                        .addComponent(Jugarbtn)
                        .addGap(59, 59, 59)
                        .addComponent(ComoJugarbtn1, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(52, 52, 52)
                        .addComponent(Salirbtn))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel1)))
                .addContainerGap(19, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void SalirbtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SalirbtnActionPerformed
        JOptionPane.showMessageDialog(this, "Gracias por jugar Aventura Mágica 💫\n!", "Hasta pronto", JOptionPane.INFORMATION_MESSAGE);
        detenerMusica();
        System.exit(0);
    }//GEN-LAST:event_SalirbtnActionPerformed

    private void SalirbtnMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_SalirbtnMouseExited
        Salirbtn.setBackground(Color.CYAN);
    }//GEN-LAST:event_SalirbtnMouseExited

    private void SalirbtnMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_SalirbtnMouseEntered
        Salirbtn.setBackground(Color.CYAN);
    }//GEN-LAST:event_SalirbtnMouseEntered

    private void SalirbtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_SalirbtnMouseClicked

    }//GEN-LAST:event_SalirbtnMouseClicked

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
        ComoJugarbtn1.setBackground(Color.CYAN);
    }//GEN-LAST:event_ComoJugarbtn1MouseExited

    private void ComoJugarbtn1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ComoJugarbtn1MouseEntered
        ComoJugarbtn1.setBackground(Color.CYAN);
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
        Jugarbtn.setBackground(Color.CYAN);
    }//GEN-LAST:event_JugarbtnMouseExited

    private void JugarbtnMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JugarbtnMouseEntered
        Jugarbtn.setBackground(Color.CYAN);
    }//GEN-LAST:event_JugarbtnMouseEntered

    private void JugarbtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JugarbtnMouseClicked

    }//GEN-LAST:event_JugarbtnMouseClicked
private void reproducirMusicaMenu() {
    try {
        if (clipMusica != null && clipMusica.isRunning()) {
            clipMusica.stop();
        }
        URL sonidoURL = getClass().getResource("/autonoma/AventuraMagicaGame/sounds/musica_menu.wav");
        AudioInputStream audioInput = AudioSystem.getAudioInputStream(sonidoURL);
        clipMusica = AudioSystem.getClip();
        clipMusica.open(audioInput);
        clipMusica.loop(Clip.LOOP_CONTINUOUSLY);
    } catch (IOException | LineUnavailableException | UnsupportedAudioFileException e) {
        System.out.println("Error al reproducir música: " + e.getMessage());
    }
}

private void detenerMusica() {
    if (clipMusica != null && clipMusica.isRunning()) {
        clipMusica.stop();
        clipMusica.close();
    }
}
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton ComoJugarbtn1;
    private javax.swing.JButton Jugarbtn;
    private javax.swing.JButton Salirbtn;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables
}
