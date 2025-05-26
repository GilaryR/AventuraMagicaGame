
package autonoma.AventuraMagicaGame.elements;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 *
 * @author jgiugtiñut
 */

public abstract class NivelBase implements Nivel {
    protected List<Enemigo> enemigos;
    protected List<Artefacto> artefactos;
    protected List<SimboloPregunta> simbolos;

    private static final int ANCHO_PANTALLA = 800;
    private static final int ALTO_PANTALLA = 600;
    private static final int MARGEN = 50;
    private static final Random rand = new Random();

    public NivelBase() {
        enemigos = new ArrayList<>();
        artefactos = new ArrayList<>();
        simbolos = new ArrayList<>();
        generarElementos();
        generarSimbolosPregunta();
    }

    // Métodos abstractos para definir cantidades y tipos en cada nivel
    protected abstract List<String> tiposEnemigos();
    protected abstract List<String> tiposArtefactos();
    protected abstract int cantidadDe(String tipo);  // devuelve cantidad para un tipo dado
    protected abstract int artefactosRequeridos();

    private void generarElementos() {
        List<String> mezcla = new ArrayList<>();

        // Agrega tipos de enemigos en la mezcla con sus cantidades
        for (String tipo : tiposEnemigos()) {
            int cantidad = cantidadDe(tipo);
            for (int i = 0; i < cantidad; i++) mezcla.add(tipo);
        }

        // Agrega tipos de artefactos en la mezcla con sus cantidades
        for (String tipo : tiposArtefactos()) {
            int cantidad = cantidadDe(tipo);
            for (int i = 0; i < cantidad; i++) mezcla.add(tipo);
        }

        // Mezclamos la lista para que enemigos y artefactos estén mezclados
        Collections.shuffle(mezcla, rand);

        for (String tipo : mezcla) {
            int x = MARGEN + rand.nextInt(ANCHO_PANTALLA - 2 * MARGEN);
            int y = MARGEN + rand.nextInt(ALTO_PANTALLA - 2 * MARGEN);

            switch (tipo) {
                case "Tucan": enemigos.add(new Tucan(x, y)); break;
                case "Frailejon": enemigos.add(new Frailejon(x, y)); break;
                case "Cuy": enemigos.add(new Cuy(x, y)); break;
                case "Capybara": enemigos.add(new Capybara(x, y)); break;
                case "Botella": artefactos.add(new Botella(x, y)); break;
                case "Esmeralda": artefactos.add(new Esmeralda(x, y)); break;
            }
        }
    }

    private void generarSimbolosPregunta() {
        for (int i = 0; i < 3; i++) {
            int x = MARGEN + rand.nextInt(ANCHO_PANTALLA - 2 * MARGEN);
            int y = MARGEN + rand.nextInt(ALTO_PANTALLA - 2 * MARGEN);

            List<Acertijo> acertijos = new ArrayList<>();
            acertijos.add(new Acertijo("¿Capital de Colombia?", "Bogotá"));
            acertijos.add(new Acertijo("2 + 2 * 2 =", "6"));

            simbolos.add(new SimboloPregunta(x, y, acertijos));
        }
    }

    @Override
    public List<Enemigo> getEnemigos() {
        return enemigos;
    }

    @Override
    public List<Artefacto> getArtefactos() {
        return artefactos;
    }

    @Override
    public List<SimboloPregunta> getSimbolosPregunta() {
        return simbolos;
    }

    @Override
    public int getArtefactosRequeridos() {
        return artefactosRequeridos();
    }
}