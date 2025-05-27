

package autonoma.AventuraMagicaGame.main;


import autonoma.AventuraMagicaGame.elements.NivelBase;
import autonoma.AventuraMagicaGame.elements.NivelFacil;
import autonoma.AventuraMagicaGame.ui.Ventana;
import javax.swing.SwingUtilities;

/**
 *
 * @author jgiugtiñut
 */
public class Main {


    public static void main(String[] args) {
    SwingUtilities.invokeLater(() -> {
        NivelBase nivelInicial = new NivelFacil();  // o el nivel que quieras
        new Ventana(nivelInicial);
    });
    }
}
    
