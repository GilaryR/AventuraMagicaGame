package autonoma.AventuraMagicaGame.util;

import javax.sound.sampled.*;
import java.io.InputStream;
import java.io.BufferedInputStream;
import java.net.URL;

/**
 * Clase utilitaria para manejar la reproducción de efectos de sonido y música
 * de fondo en el juego Aventura Mágica.
 * 
 * Permite reproducir efectos individuales desde archivos en el classpath y
 * reproducir música de fondo en bucle para los distintos niveles.
 * 
 * Maneja errores comunes como archivos no encontrados, formato no soportado o
 * líneas de audio no disponibles.
 * 
 * @author Gilary Rugeles
 * @version 3.0
 * @since 28/05/2025
 */
public class ReproductorSonido {

    /** Clip utilizado para reproducir música de fondo del nivel. */
    private Clip clipMusica;

    /** Clip actual utilizado para la música de fondo del nivel. */
    private Clip clipNivel;

    /**
     * Reproduce un efecto de sonido corto a partir de una ruta del classpath.
     *
     * @param rutaRecurso Ruta relativa del archivo de sonido en los recursos del proyecto.
     */
    public static void reproducir(String rutaRecurso) {
        try {
            InputStream input = ReproductorSonido.class.getResourceAsStream(rutaRecurso);
            if (input == null) {
                System.err.println("No se encontró el archivo de sonido: " + rutaRecurso);
                return;
            }

            AudioInputStream audioStream = AudioSystem.getAudioInputStream(new BufferedInputStream(input));
            Clip clip = AudioSystem.getClip();
            clip.open(audioStream);
            clip.start();
        } catch (UnsupportedAudioFileException e) {
            System.err.println("Formato de archivo no compatible: " + e.getMessage());
        } catch (LineUnavailableException e) {
            System.err.println("Línea de audio no disponible: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Error al reproducir sonido: " + e.getMessage());
        }
    }

    /**
     * Reproduce música de fondo en bucle para un nivel del juego.
     * Detiene la música anterior si estaba sonando.
     *
     * @param ruta Ruta del archivo de audio del nivel (relativa al classpath).
     */
    public void reproducirMusicaNivel(String ruta) {
        detenerMusica(); // Detiene cualquier música anterior

        try {
            URL url = getClass().getResource(ruta);
            AudioInputStream audioInput = AudioSystem.getAudioInputStream(url);
            clipNivel = AudioSystem.getClip();
            clipNivel.open(audioInput);
            clipNivel.loop(Clip.LOOP_CONTINUOUSLY);

            // Ajustar volumen (rango: -80.0f a 6.0f)
            FloatControl volumen = (FloatControl) clipNivel.getControl(FloatControl.Type.MASTER_GAIN);
            volumen.setValue(-10.0f); // Volumen moderadamente bajo
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error al reproducir música del nivel: " + e.getMessage());
        }
    }

    /**
     * Detiene la música actualmente en reproducción (si hay alguna).
     */
    private void detenerMusica() {
        if (clipMusica != null && clipMusica.isRunning()) {
            clipMusica.stop();
            clipMusica.close();
        }
    }
}
