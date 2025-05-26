package autonoma.AventuraMagicaGame.elements;

import autonoma.AventuraMagicaGame.elements.NivelBase;
import java.util.Arrays;
import java.util.List;

public class NivelFacil extends NivelBase {

    /**
     * @return Lista de tipos de enemigos para este nivel
     */
    @Override
    protected List<String> tiposEnemigos() {
        return Arrays.asList("Tucan", "Frailejon");
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
            case "Tucan": return 10;
            case "Frailejon": return 10;
            case "Botella": return 20;
            case "Esmeralda": return 3;
            default: return 0;
        }
    }

    /**
     * @return La cantidad de artefactos requeridos para completar el nivel
     */
    @Override
    protected int artefactosRequeridos() {
        return 20;
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
