package autonoma.AventuraMagicaGame.elements;

import java.util.Arrays;
import java.util.List;

public class NivelIntermedio extends NivelBase {

    /**
     * @return Lista de tipos de enemigos para este nivel
     */
    @Override
    protected List<String> tiposEnemigos() {
        return Arrays.asList("Tucan", "Frailejon", "Cuy");
    }

    /**
     * @return Lista de tipos de artefactos para este nivel
     */
    @Override
    protected List<String> tiposArtefactos() {
        return Arrays.asList("Botella", "Esmeralda");
    }

    /**
     * @param tipo El tipo de entidad a consultar
     * @return La cantidad de entidades del tipo especificado
     */
    @Override
    protected int cantidadDe(String tipo) {
        switch (tipo) {
            case "Tucan": return 20;
            case "Frailejon": return 20;
            case "Cuy": return 10;
            case "Botella": return 40;
            case "Esmeralda": return 6;
            default: return 0;
        }
    }

    /**
     * @return La cantidad de artefactos requeridos para completar el nivel
     */
    @Override
    protected int artefactosRequeridos() {
        return 40;
    }

    /**
     * @return El tamaño del nivel (no implementado)
     * @throws UnsupportedOperationException Si el método no está implementado
     */
    @Override
    public int size() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
