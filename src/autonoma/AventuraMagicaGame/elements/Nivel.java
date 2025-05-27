
package autonoma.AventuraMagicaGame.elements;

import java.awt.Image;
import java.util.List;

/**
 *
 * @author jgiugtiñut
 */

public interface Nivel {
    List<Enemigo> getEnemigos();
    List<Artefacto> getArtefactos();
    List<SimboloPregunta> getSimbolosPregunta();

    public int getArtefactosRequeridos();

    public int size();

    public Image getFondo();
}
