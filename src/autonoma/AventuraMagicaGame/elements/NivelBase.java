package autonoma.AventuraMagicaGame.elements;

import autonoma.AventuraMagicaGameBase.elements.Sprite;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.awt.Image;
import javax.swing.ImageIcon;


/**
 * Clase abstracta base que implementa la interfaz Nivel y proporciona la funcionalidad
 * común para todos los niveles del juego Aventura Mágica.
 * Esta clase maneja la generación y gestión de enemigos, artefactos y símbolos de pregunta
 * en el nivel, asegurando una colocación aleatoria sin colisiones.
 * 
 * @author Gilary
 * @version 2.1
 * @since 26/05/2025
 *@author Alejandra Ortega
 * @version 2.2
 * @since 26/05/2025
 */

public abstract class NivelBase implements Nivel {

    private List<Enemigo> enemigos;
    private List<Artefacto> artefactos;
    private List<SimboloPregunta> simbolos;
    private Jugador jugador;
    private Image fondo;

    private int anchoPantalla = 800;
    private int altoPantalla = 600;
    private static final int MARGEN = 50;
    private static final Random rand = new Random();

    public NivelBase() {
        enemigos = new ArrayList<>();
        artefactos = new ArrayList<>();
        simbolos = new ArrayList<>();
    }

    public void actualizarDimensiones(int ancho, int alto) {
        this.anchoPantalla = ancho;
        this.altoPantalla = alto;
    }

    public void setFondo(String rutaImagen) {
        this.fondo = new ImageIcon(getClass().getResource(rutaImagen)).getImage();
    }

    public Image getFondo() {
        return fondo;
    }

    public void setJugador(Jugador jugador) {
        this.jugador = jugador;
    }

    protected abstract List<String> tiposEnemigos();
    protected abstract List<String> tiposArtefactos();
    protected abstract int cantidadDe(String tipo);
    protected abstract int artefactosRequeridos();

    protected List<Acertijo> obtenerAcertijos() {
        List<Acertijo> lista = new ArrayList<>();
        lista.add(new Acertijo("¿Animal símbolo de Colombia?", "Cóndor", "condor"));
        lista.add(new Acertijo("¿En qué año fue la independencia de Colombia?", "1810", "mil ochocientos diez"));
        lista.add(new Acertijo("¿Qué significa 'parcero' en Colombia?", "Amigo", "Parce"));
        lista.add(new Acertijo("¿Cuál es el parque natural más grande de Colombia?", "Serranía de Chiribiquete", "Chiribiquete"));
        lista.add(new Acertijo("¿Qué animal es típico de los Llanos orientales?", "Capibara", "Chigüiro"));
        lista.add(new Acertijo("¿Colombia tiene acceso a cuántos océanos?", "2", "dos"));
        lista.add(new Acertijo("¿Cómo se llama la flor nacional de Colombia?", "Orquídea", "orquidea"));
        lista.add(new Acertijo("¿Cuál es la ciudad conocida como 'La ciudad amurallada'?", "Cartagena", "Cartagena de Indias"));
        lista.add(new Acertijo("¿Qué famoso escritor colombiano ganó el Premio Nobel de Literatura?", "Gabriel García Márquez", "Gabo"));
        lista.add(new Acertijo("¿Cuál es el plato típico más representativo de Antioquia?", "Bandeja paisa", "Bandeja"));
        lista.add(new Acertijo("¿Qué río es el más largo de Colombia?", "Magdalena", "Río Magdalena"));
        lista.add(new Acertijo("¿En qué ciudad se celebra la Feria de las Flores?", "Medellín", "Medallo"));
        lista.add(new Acertijo("¿Cuál es el nombre del sombrero típico de la costa Caribe?", "Sombrero vueltiao", "Vueltiao"));
        lista.add(new Acertijo("¿Qué ciudad es conocida como la 'Sucursal del cielo'?", "Cali", "Santiago de Cali"));
        lista.add(new Acertijo("¿Cómo se llama la moneda oficial de Colombia?", "Peso colombiano", "Peso"));
        return lista;
    }

    public void generarElementos() {
        List<String> mezcla = new ArrayList<>();

        for (String tipo : tiposEnemigos()) {
            int cantidad = cantidadDe(tipo);
            for (int i = 0; i < cantidad; i++) {
                mezcla.add(tipo);
            }
        }

        for (String tipo : tiposArtefactos()) {
            int cantidad = cantidadDe(tipo);
            for (int i = 0; i < cantidad; i++) {
                mezcla.add(tipo);
            }
        }

        Collections.shuffle(mezcla, rand);

        for (String tipo : mezcla) {
            Sprite nuevo = null;
            boolean colocado = false;
            int intentos = 0;

            while (!colocado && intentos < 100) {
                int x = MARGEN + rand.nextInt(Math.max(1, anchoPantalla - 2 * MARGEN - 50));
                int y = MARGEN + rand.nextInt(Math.max(1, altoPantalla - 2 * MARGEN - 50));

                switch (tipo) {
                    case "Tucan": nuevo = new Tucan(x, y); break;
                    case "Frailejon": nuevo = new Frailejon(x, y); break;
                    case "Cuy": nuevo = new Cuy(x, y); break;
                    case "Capybara": nuevo = new Capybara(x, y); break;
                    case "Botella": nuevo = new Botella(x, y); break;
                    case "Esmeralda": nuevo = new Esmeralda(x, y); break;
                    default: break;
                }

                if (nuevo != null) {
                    int maxX = anchoPantalla - nuevo.getBounds().width - MARGEN;
                    int maxY = altoPantalla - nuevo.getBounds().height - MARGEN;

                    if (nuevo.getX() > maxX) nuevo.setX(Math.max(MARGEN, maxX));
                    if (nuevo.getY() > maxY) nuevo.setY(Math.max(MARGEN, maxY));

                    if (!colisionaConExistentes(nuevo)) {
                        if (nuevo instanceof Enemigo) enemigos.add((Enemigo) nuevo);
                        else if (nuevo instanceof Artefacto) artefactos.add((Artefacto) nuevo);
                        colocado = true;
                    }
                }

                intentos++;
            }
        }
    }

    private boolean colisionaConExistentes(Sprite nuevo) {
        if (jugador != null && nuevo.getBounds().intersects(jugador.getBounds())) return true;
        for (Enemigo e : enemigos) if (nuevo.getBounds().intersects(e.getBounds())) return true;
        for (Artefacto a : artefactos) if (nuevo.getBounds().intersects(a.getBounds())) return true;
        for (SimboloPregunta s : simbolos) if (nuevo.getBounds().intersects(s.getBounds())) return true;
        return false;
    }

    public void generarSimbolosPregunta() {
        List<Acertijo> acertijos = obtenerAcertijos();

        for (int i = 0; i < 3; i++) {
            boolean colocado = false;
            int intentos = 0;

            while (!colocado && intentos < 100) {
                int x = MARGEN + rand.nextInt(Math.max(1, anchoPantalla - 2 * MARGEN - 50));
                int y = MARGEN + rand.nextInt(Math.max(1, altoPantalla - 2 * MARGEN - 50));

                SimboloPregunta simbolo = new SimboloPregunta(x, y, acertijos);

                int maxX = anchoPantalla - simbolo.getBounds().width - MARGEN;
                int maxY = altoPantalla - simbolo.getBounds().height - MARGEN;

                if (simbolo.getX() > maxX) simbolo.setX(Math.max(MARGEN, maxX));
                if (simbolo.getY() > maxY) simbolo.setY(Math.max(MARGEN, maxY));

                if (!colisionaConExistentes(simbolo)) {
                    simbolos.add(simbolo);
                    colocado = true;
                }

                intentos++;
            }
        }
    }

    @Override public List<Enemigo> getEnemigos() { return enemigos; }
    @Override public List<Artefacto> getArtefactos() { return artefactos; }
    @Override public List<SimboloPregunta> getSimbolosPregunta() { return simbolos; }
    @Override public int getArtefactosRequeridos() { return artefactosRequeridos(); }
    @Override public int size() { return enemigos.size() + artefactos.size(); }
}

    