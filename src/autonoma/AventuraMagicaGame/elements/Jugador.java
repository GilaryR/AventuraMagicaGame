package autonoma.AventuraMagica.elements;

import autonoma.AventuraMagicaBase.elements.Sprite;

public class Jugador extends Sprite {
    private int puntaje;
    private int velocidad = 5;
    private int vida = 10;  // vida inicial

    private int botellasRecolectadas;
    private int esmeraldasRecolectadas;

    private static final int ANCHO_JUGADOR = 48;
    private static final int ALTO_JUGADOR = 48;
    private static final String RUTA_IMAGEN = "/autonoma/AventuraMagica/images/jugador.png";

    public Jugador(int x, int y) {
        super(x, y, RUTA_IMAGEN, ANCHO_JUGADOR, ALTO_JUGADOR);
        this.puntaje = 0;
        this.botellasRecolectadas = 0;
        this.esmeraldasRecolectadas = 0;
    }

    public void moverIzquierda() {
        this.x -= velocidad;
    }

    public void moverDerecha() {
        this.x += velocidad;
    }

    public void moverArriba() {
        this.y -= velocidad;
    }

    public void moverAbajo() {
        this.y += velocidad;
    }

    public void incrementarPuntaje(int valor) {
        this.puntaje += valor;
    }

    public void disminuirPuntaje(int valor) {
        this.puntaje -= valor;
        if (puntaje < 0) puntaje = 0;
    }

    public int getPuntaje() {
        return puntaje;
    }

    public void setPuntaje(int puntaje) {
        this.puntaje = puntaje;
    }

    public int getVelocidad() {
        return velocidad;
    }

    public void setVelocidad(int velocidad) {
        this.velocidad = velocidad;
    }

    public int getVida() {
        return vida;
    }

    /**
     * Devuelve la vida actual como entero (para lógica y comparaciones).
     */
    public int getVidas() {
        return this.vida;
    }

    /**
     * Mueve al jugador dentro de los límites dados (width x height).
     */
    public void mover(int dx, int dy, int width, int height) {
        int nuevaX = this.x + dx;
        int nuevaY = this.y + dy;

        if (nuevaX < 0) {
            nuevaX = 0;
        } else if (nuevaX + getAncho() > width) {
            nuevaX = width - getAncho();
        }

        if (nuevaY < 0) {
            nuevaY = 0;
        } else if (nuevaY + getAlto() > height) {
            nuevaY = height - getAlto();
        }

        this.x = nuevaX;
        this.y = nuevaY;
    }

    /**
     * Cuando el jugador recolecta una botella:
     * - aumenta contador botellas
     * - aumenta puntaje
     */
    public void recolectarBotella() {
        aumentarBotellasRecolectadas(1);
        aumentarPuntaje(5);    // puntaje por botella
    }

    /**
     * Cuando el jugador recolecta una esmeralda:
     * - aumenta contador esmeraldas
     * - aumenta vida
     * - aumenta puntaje
     */
    public void recolectarEsmeralda() {
        aumentarEsmeraldasRecolectadas(1);
        aumentarVida(15);      // cantidad de vida al recoger esmeralda
    }

    public int getBotellasRecolectadas() {
        return botellasRecolectadas;
    }

    public void setBotellasRecolectadas(int cantidad) {
        this.botellasRecolectadas = cantidad;
    }

    public int getEsmeraldasRecolectadas() {
        return esmeraldasRecolectadas;
    }

    public void setEsmeraldasRecolectadas(int cantidad) {
        this.esmeraldasRecolectadas = cantidad;
    }

    // MÉTODOS PRIVADOS PARA MANIPULAR ESTADOS INTERNOS

    private void aumentarBotellasRecolectadas(int cantidad) {
        this.botellasRecolectadas += cantidad;
        if (this.botellasRecolectadas < 0) {
            this.botellasRecolectadas = 0;
        }
    }

    private void aumentarEsmeraldasRecolectadas(int cantidad) {
        this.esmeraldasRecolectadas += cantidad;
        if (this.esmeraldasRecolectadas < 0) {
            this.esmeraldasRecolectadas = 0;
        }
    }

    void aumentarVida(int cantidad) {
        this.vida += cantidad;
        if (this.vida > 100) {
            this.vida = 100;
        } else if (this.vida < 0) {
            this.vida = 0;
        }
    }

    void aumentarPuntaje(int cantidad) {
        this.puntaje += cantidad;
        if (this.puntaje < 0) {
            this.puntaje = 0;
        }
    }

    public void reducirVida(int cantidad) {
        this.vida -= cantidad;
        if (this.vida < 0) {
            this.vida = 0;
        }
    }

    public void resetearParaNuevoNivel() {
        this.botellasRecolectadas = 0;
    }

    public void resetearBotellas() {
        this.botellasRecolectadas = 0;
    }

    public int getBotellasTotales() {
        return 0;
    }

    // El método setVidas se puede eliminar o implementar si es necesario:
    public void setVidas(int vidas) {
        // Puedes implementar si quieres convertir String a int y asignar a vida
        // Por ejemplo:
        // this.vida = Integer.parseInt(vidas);
    }
}
