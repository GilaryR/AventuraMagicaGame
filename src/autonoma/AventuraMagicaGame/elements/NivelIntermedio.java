package autonoma.AventuraMagicaGame.elements;

import java.util.Arrays;
import java.util.List;

public class NivelIntermedio extends NivelBase {

    public NivelIntermedio() {
        super();
        setFondo("/autonoma/AventuraMagicaGame/images/fondoIntermedio.jpg");  // Ruta del fondo para nivel intermedio
    }

    @Override
    protected List<String> tiposEnemigos() {
        return Arrays.asList("Tucan", "Frailejon", "Cuy");
    }

    @Override
    protected List<String> tiposArtefactos() {
        return Arrays.asList("Botella", "Esmeralda");
    }

    @Override
    protected int cantidadDe(String tipo) {
        switch (tipo) {
            case "Tucan": return 6;
            case "Frailejon": return 2;
            case "Cuy": return 5;
            case "Botella": return 40;
            case "Esmeralda": return 6;
            default: return 0;
        }
    }

    @Override
    protected int artefactosRequeridos() {
        return 40;
    }

}
