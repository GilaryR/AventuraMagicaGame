package autonoma.AventuraMagicaGame.util;

import javax.sound.sampled.*;
import java.io.InputStream;
import java.io.BufferedInputStream;

public class ReproductorSonido {

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
}
