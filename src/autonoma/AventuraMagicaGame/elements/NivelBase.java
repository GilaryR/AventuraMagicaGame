
package autonoma.AventuraMagicaGame.elements;

import autonoma.AventuraMagicaGameBase.elements.Sprite;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * Clase abstracta base que implementa la interfaz Nivel y proporciona la funcionalidad
 * común para todos los niveles del juego Aventura Mágica.
 * Esta clase maneja la generación y gestión de enemigos, artefactos y símbolos de pregunta
 * en el nivel, asegurando una colocación aleatoria sin colisiones.
 * 
 * @author Gilary
 * @version 2.0
 * @Since 26/05/2025
 */

public abstract class NivelBase implements Nivel {
    /** Lista de enemigos presentes en el nivel */
    protected List<Enemigo> enemigos;
    
    /** Lista de artefactos coleccionables en el nivel */
    protected List<Artefacto> artefactos;
    
    /** Lista de símbolos de pregunta que contienen acertijos */
    protected List<SimboloPregunta> simbolos;
    
    /** Referencia al jugador que interactúa con el nivel */
    protected Jugador jugador;
    
    /** Ancho de la pantalla del juego en píxeles */
    private static final int ANCHO_PANTALLA = 800;
    
    /** Alto de la pantalla del juego en píxeles */
    private static final int ALTO_PANTALLA = 600;
    
    /** Margen utilizado para evitar colocar elementos en los bordes */
    private static final int MARGEN = 50;
    
    /** Generador de números aleatorios para la colocación de elementos */
    private static final Random rand = new Random();

    /**
     * Constructor que inicializa las listas de elementos del nivel.
     */
    public NivelBase() {
        enemigos = new ArrayList<>();
        artefactos = new ArrayList<>();
        simbolos = new ArrayList<>();
    }

    /**
     * Establece el jugador que interactuará con este nivel.
     * 
     * @param jugador El objeto Jugador que participa en el nivel
     */
    public void setJugador(Jugador jugador) {
        this.jugador = jugador;
    }

    /**
     * Método abstracto que debe devolver los tipos de enemigos para este nivel.
     * 
     * @return Lista de identificadores de tipos de enemigos
     */
    protected abstract List<String> tiposEnemigos();
    
    /**
     * Método abstracto que debe devolver los tipos de artefactos para este nivel.
     * 
     * @return Lista de identificadores de tipos de artefactos
     */
    protected abstract List<String> tiposArtefactos();
    
    /**
     * Método abstracto que indica la cantidad de elementos requeridos de un tipo.
     * 
     * @param tipo El tipo de elemento (enemigo o artefacto)
     * @return Cantidad requerida de ese tipo
     */
    protected abstract int cantidadDe(String tipo);
    
    /**
     * Método abstracto que indica cuántos artefactos se necesitan para completar el nivel.
     * 
     * @return Número de artefactos requeridos
     */
    protected abstract int artefactosRequeridos();

    /**
     * Genera y coloca aleatoriamente los elementos del nivel (enemigos y artefactos),
     * evitando colisiones entre ellos y con el jugador.
     */
    public void generarElementos() {
        List<String> mezcla = new ArrayList<>();

        // Mezcla los tipos de elementos según las cantidades requeridas
        for (String tipo : tiposEnemigos()) {
            int cantidad = cantidadDe(tipo);
            for (int i = 0; i < cantidad; i++) mezcla.add(tipo);
        }

        for (String tipo : tiposArtefactos()) {
            int cantidad = cantidadDe(tipo);
            for (int i = 0; i < cantidad; i++) mezcla.add(tipo);
        }

        Collections.shuffle(mezcla, rand);

        // Coloca cada elemento en una posición aleatoria sin colisiones
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

    /**
     * Verifica si un nuevo sprite colisiona con elementos existentes en el nivel.
     * 
     * @param nuevo El sprite que se quiere colocar
     * @return true si hay colisión, false en caso contrario
     */
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

    /**
     * Genera símbolos de pregunta con acertijos en posiciones aleatorias del nivel.
     */
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

    /**
     * Obtiene la lista de enemigos del nivel.
     * 
     * @return Lista de enemigos
     */
    @Override
    public List<Enemigo> getEnemigos() {
        return enemigos;
    }

    /**
     * Obtiene la lista de artefactos del nivel.
     * 
     * @return Lista de artefactos
     */
    @Override
    public List<Artefacto> getArtefactos() {
        return artefactos;
    }

    /**
     * Obtiene la lista de símbolos de pregunta del nivel.
     * 
     * @return Lista de símbolos de pregunta
     */
    @Override
    public List<SimboloPregunta> getSimbolosPregunta() {
        return simbolos;
    }

    /**
     * Obtiene el número de artefactos requeridos para completar el nivel.
     * 
     * @return Número de artefactos requeridos
     */
    @Override
    public int getArtefactosRequeridos() {
        return artefactosRequeridos();
    }
}