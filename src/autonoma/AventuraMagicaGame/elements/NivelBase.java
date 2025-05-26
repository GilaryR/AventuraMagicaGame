
package autonoma.AventuraMagicaGame.elements;

import autonoma.AventuraMagicaGameBase.elements.Sprite;
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
    protected Jugador jugador;

    private static final int ANCHO_PANTALLA = 800;
    private static final int ALTO_PANTALLA = 600;
    private static final int MARGEN = 50;
    private static final Random rand = new Random();

    public NivelBase() {
        enemigos = new ArrayList<>();
        artefactos = new ArrayList<>();
        simbolos = new ArrayList<>();
        // Quitamos llamada a generarElementos() aquí
    }

    public void setJugador(Jugador jugador) {
        this.jugador = jugador;
    }

    protected abstract List<String> tiposEnemigos();
    protected abstract List<String> tiposArtefactos();
    protected abstract int cantidadDe(String tipo);
    protected abstract int artefactosRequeridos();

    public void generarElementos() {
        List<String> mezcla = new ArrayList<>();

        for (String tipo : tiposEnemigos()) {
            int cantidad = cantidadDe(tipo);
            for (int i = 0; i < cantidad; i++) mezcla.add(tipo);
        }

        for (String tipo : tiposArtefactos()) {
            int cantidad = cantidadDe(tipo);
            for (int i = 0; i < cantidad; i++) mezcla.add(tipo);
        }

        Collections.shuffle(mezcla, rand);

        for (String tipo : mezcla) {
            Sprite nuevo = null;
            boolean colocado = false;
            int intentos = 0;

            while (!colocado && intentos < 100) {
                int x = MARGEN + rand.nextInt(ANCHO_PANTALLA - 2 * MARGEN);
                int y = MARGEN + rand.nextInt(ALTO_PANTALLA - 2 * MARGEN);

                switch (tipo) {
                    case "Tucan": nuevo = new Tucan(x, y); break;
                    case "Frailejon": nuevo = new Frailejon(x, y); break;
                    case "Cuy": nuevo = new Cuy(x, y); break;
                    case "Capybara": nuevo = new Capybara(x, y); break;
                    case "Botella": nuevo = new Botella(x, y); break;
                    case "Esmeralda": nuevo = new Esmeralda(x, y); break;
                }

                if (!colisionaConExistentes(nuevo)) {
                    if (nuevo instanceof Enemigo) {
                        enemigos.add((Enemigo) nuevo);
                    } else if (nuevo instanceof Artefacto) {
                        artefactos.add((Artefacto) nuevo);
                    }
                    colocado = true;
                }

                intentos++;
            }
        }
    }

    private boolean colisionaConExistentes(Sprite nuevo) {
        if (jugador != null && nuevo.getBounds().intersects(jugador.getBounds())) {
            return true;
        }

        for (Enemigo e : enemigos) {
            if (nuevo.getBounds().intersects(e.getBounds())) return true;
        }
        for (Artefacto a : artefactos) {
            if (nuevo.getBounds().intersects(a.getBounds())) return true;
        }
        for (SimboloPregunta s : simbolos) {
            if (nuevo.getBounds().intersects(s.getBounds())) return true;
        }
        return false;
    }

    public void generarSimbolosPregunta() {
        for (int i = 0; i < 3; i++) {
            boolean colocado = false;
            int intentos = 0;
            while (!colocado && intentos < 100) {
                int x = MARGEN + rand.nextInt(ANCHO_PANTALLA - 2 * MARGEN);
                int y = MARGEN + rand.nextInt(ALTO_PANTALLA - 2 * MARGEN);

                List<Acertijo> acertijos = new ArrayList<>();
                acertijos.add(new Acertijo("¿Capital de Colombia?", "Bogotá"));
                acertijos.add(new Acertijo("2 + 2 * 2 =", "6"));

                SimboloPregunta simbolo = new SimboloPregunta(x, y, acertijos);
                if (!colisionaConExistentes(simbolo)) {
                    simbolos.add(simbolo);
                    colocado = true;
                }

                intentos++;
            }
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