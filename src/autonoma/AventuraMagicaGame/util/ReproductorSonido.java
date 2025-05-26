package autonoma.AventuraMagicaGame.util;

import javax.sound.sampled.*;
import java.io.File;

public class ReproductorSonido {
    public static void reproducir(String ruta) {
        try {
            File archivoSonido = new File(ruta);
            if (!archivoSonido.exists()) {
                System.err.println("Archivo de sonido no encontrado: " + ruta);
                return;
            }

            AudioInputStream audioStream = AudioSystem.getAudioInputStream(archivoSonido);
            Clip clip = AudioSystem.getClip();
            clip.open(audioStream);
            clip.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
