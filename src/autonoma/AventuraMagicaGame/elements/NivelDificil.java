package autonoma.AventuraMagicaGame.elements;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Nivel difícil del juego con enemigos, artefactos y un fondo personalizado.
 * 
 * @author Gilary
 * @version 1.0
 * @since 26/05/2025
 */
public class NivelDificil extends NivelBase {

    public NivelDificil() {
        super();
        setFondo("/autonoma/AventuraMagicaGame/images/fondoDificil.jpg"); // Asegúrate de tener esta imagen en tu carpeta resources
    }

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
            case "Tucan": return 4;
            case "Frailejon": return 3;
            case "Cuy": return 4;
            case "Capybara": return 3;
            case "Botella": return 5;
            case "Esmeralda": return 4;
            default: return 0;
        }
    }

    @Override
    protected int artefactosRequeridos() {
        return 7;
    }

    @Override
    protected List<Acertijo> obtenerAcertijos() {
        List<Acertijo> lista = new ArrayList<>();
        lista.add(new Acertijo("¿Cuál es el río más largo que recorre Colombia?", "Magdalena", "Río Magdalena"));
        lista.add(new Acertijo("¿Qué escritor colombiano ganó el Nobel de Literatura?", "Gabriel García Márquez", "Gabo"));
        lista.add(new Acertijo("¿Cuál es la capital del departamento del Amazonas?", "Leticia", "leticia"));
        lista.add(new Acertijo("¿Nombre del volcán activo en el sur de Colombia?", "Galeras", "volcán Galeras"));
        lista.add(new Acertijo("¿En qué cordillera se ubica Bogotá?", "Oriental", "Cordillera Oriental"));
        lista.add(new Acertijo("¿Qué ciudad es conocida como la 'ciudad blanca'?", "Popayán", "popayan"));
        lista.add(new Acertijo("¿Qué fruta tropical también se llama maracuyá?", "Parchita", "passiflora"));
        return lista;
    }

    @Override
    public int size() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
