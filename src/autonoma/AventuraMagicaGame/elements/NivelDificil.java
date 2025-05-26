package autonoma.AventuraMagicaGame.elements;

import java.util.Arrays;
import java.util.List;

public class NivelDificil extends NivelBase {

    @Override
    protected List<String> tiposEnemigos() {
        return Arrays.asList("Tucan", "Frailejon", "Cuy", "Capybara");
    }

    @Override
    protected List<String> tiposArtefactos() {
        return Arrays.asList("Botella", "Esmeralda");
    }

    @Override
    protected int cantidadDe(String tipo) {
        switch (tipo) {
            case "Tucan": return 40;        // doble del intermedio (20 * 2)
            case "Frailejon": return 40;    // igual
            case "Cuy": return 20;          // doble del intermedio (10 * 2)
            case "Capybara": return 10;     // nueva especie
            case "Botella": return 60;      // doble del intermedio
            case "Esmeralda": return 12;    // doble del intermedio
            default: return 0;
        }
    }

    @Override
    protected int artefactosRequeridos() {
        return 60;
    }

    @Override
    public int size() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
