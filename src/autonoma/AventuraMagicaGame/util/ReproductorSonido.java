package autonoma.AventuraMagicaGame.util;

import javax.sound.sampled.*;
import java.io.InputStream;
import java.io.BufferedInputStream;
import java.net.URL;

public class ReproductorSonido {
private Clip clipMusica;

    public static void reproducir(String rutaRecurso) {
        try {
            // Cargar el recurso de sonido desde el classpath
            InputStream input = ReproductorSonido.class.getResourceAsStream(rutaRecurso);
            if (input == null) {
                System.err.println("No se encontró el archivo de sonido: " + rutaRecurso);
                return;
            }

            // Mejor rendimiento al envolver en BufferedInputStream
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
    private Clip clipNivel;

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
        volumen.setValue(-10.0f); // Ejemplo: más bajo
    } catch (Exception e) {
        e.printStackTrace();
        System.out.println("Error al reproducir música del nivel: " + e.getMessage());
    }
}
   private void detenerMusica() {
        if (clipMusica != null && clipMusica.isRunning()) {
            clipMusica.stop();
            clipMusica.close();
        }
    }

}
