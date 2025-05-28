package autonoma.AventuraMagicaGame.elements;

import autonoma.AventuraMagicaGameBase.elements.Sprite;
/**
 * Representa al jugador principal del juego, heredando de Sprite para capacidades gráficas.
 * 
 * Esta clase gestiona todas las propiedades y comportamientos del jugador:
 * 
 * Movimiento y posición
 * Vida y sistema de daño
 * Recolección de objetos (botellas y esmeraldas)
 * Puntuación y progreso
 * @author Gilary Rugeles
 * @since 27/05/2025
 * @version  3.1
 */
public class Jugador extends Sprite {
    /** Puntaje actual del jugador */
    private int puntaje;
    
    /** Velocidad de movimiento en píxeles */
    private int velocidad = 5;
    
    /** Salud actual del jugador (0-100) */
    private int vida = 10;
    
    /** Botellas recolectadas en el nivel actual */
    private int botellasRecolectadas;
    
    /** Esmeraldas recolectadas en el nivel actual */
    private int esmeraldasRecolectadas;
    
    /** Ancho del sprite del jugador en píxeles */
    private static final int ANCHO_JUGADOR = 48;
    
    /** Alto del sprite del jugador en píxeles */
    private static final int ALTO_JUGADOR = 48;
    
    /** Ruta de la imagen del jugador */
    private static final String RUTA_IMAGEN = "/autonoma/AventuraMagicaGame/images/Jugador.png";

    /**
     * Crea una nueva instancia del jugador en la posición especificada.
     * 
     * @param x Posición horizontal inicial (coordenada x)
     * @param y Posición vertical inicial (coordenada y)
     */
    public Jugador(int x, int y) {
        super(x, y, RUTA_IMAGEN, ANCHO_JUGADOR, ALTO_JUGADOR);
        this.puntaje = 0;
        this.botellasRecolectadas = 0;
        this.esmeraldasRecolectadas = 0;
    }

    // Métodos de movimiento básicos
    /** Mueve al jugador hacia la izquierda según su velocidad actual */
    public void moverIzquierda() { this.x -= velocidad; }
    
    /** Mueve al jugador hacia la derecha según su velocidad actual */
    public void moverDerecha()   { this.x += velocidad; }
    
    /** Mueve al jugador hacia arriba según su velocidad actual */
    public void moverArriba()    { this.y -= velocidad; }
    
    /** Mueve al jugador hacia abajo según su velocidad actual */
    public void moverAbajo()     { this.y += velocidad; }

    /**
     * Mueve al jugador de forma controlada, verificando límites del área.
     * 
     * @param dx Desplazamiento horizontal deseado
     * @param dy Desplazamiento vertical deseado
     * @param width Ancho del área de movimiento
     * @param height Alto del área de movimiento
     */
    public void mover(int dx, int dy, int width, int height) {
        int nuevaX = this.x + dx;
        int nuevaY = this.y + dy;

        // Verificación de límites horizontales
        if (nuevaX < 0) nuevaX = 0;
        else if (nuevaX + getAncho() > width) nuevaX = width - getAncho();

        // Verificación de límites verticales
        if (nuevaY < 0) nuevaY = 0;
        else if (nuevaY + getAlto() > height) nuevaY = height - getAlto();

        this.x = nuevaX;
        this.y = nuevaY;
    }

    /**
     * Obtiene la vida actual del jugador.
     * 
     * @return Valor de vida actual (0-100)
     */
    public int getVida() {
        return vida;
    }

    /**
     * Obtiene la vida actual del jugador (alias de getVida()).
     * 
     * @return Valor de vida actual (0-100)
     */
    public int getVidas() {
        return vida;
    }

    /**
     * Establece el valor de vida del jugador.
     * 
     * @param vidas Nuevo valor de vida (será ajustado al rango 0-100)
     */
    public void setVidas(int vidas) {
        this.vida = Math.max(0, Math.min(vidas, 100));
    }

    /**
     * Aumenta la vida del jugador.
     * 
     * @param cantidad Valor a sumar a la vida actual (ajustado a máximo 100)
     */
    public void aumentarVida(int cantidad) {
        this.vida += cantidad;
        if (this.vida > 100) this.vida = 100;
        if (this.vida < 0) this.vida = 0;
    }

    /**
     * Reduce la vida del jugador.
     * 
     * @param cantidad Valor a restar de la vida actual (ajustado a mínimo 0)
     */
    public void reducirVida(int cantidad) {
        this.vida -= cantidad;
        if (this.vida < 0) this.vida = 0;
    }

    /**
     * Obtiene el puntaje actual del jugador.
     * 
     * @return Puntaje actual (siempre ≥ 0)
     */
    public int getPuntaje() {
        return puntaje;
    }

    /**
     * Establece el puntaje del jugador.
     * 
     * @param puntaje Nuevo valor de puntaje (ajustado a mínimo 0)
     */
    public void setPuntaje(int puntaje) {
        this.puntaje = Math.max(puntaje, 0);
    }

    /**
     * Incrementa el puntaje del jugador.
     * 
     * @param valor Cantidad a sumar al puntaje
     */
    public void incrementarPuntaje(int valor) {
        this.puntaje += valor;
    }

    /**
     * Disminuye el puntaje del jugador.
     * 
     * @param valor Cantidad a restar del puntaje (ajustado a mínimo 0)
     */
    public void disminuirPuntaje(int valor) {
        this.puntaje -= valor;
        if (this.puntaje < 0) this.puntaje = 0;
    }

    /**
     * Aumenta el puntaje del jugador (alias de incrementarPuntaje).
     * 
     * @param cantidad Valor a sumar al puntaje (ajustado a mínimo 0)
     */
    public void aumentarPuntaje(int cantidad) {
        this.puntaje += cantidad;
        if (this.puntaje < 0) this.puntaje = 0;
    }

    /**
     * Recolecta una botella, aumentando el contador y el puntaje.
     */
    public void recolectarBotella() {
        aumentarBotellasRecolectadas(1);
        aumentarPuntaje(5);
    }

    /**
     * Verifica si se ha alcanzado el objetivo de botellas requeridas.
     * 
     * @param botellasRequeridas Cantidad necesaria para completar el objetivo
     * @return true si se han recolectado suficientes botellas, false en caso contrario
     */
    public boolean objetivoBotellasAlcanzado(int botellasRequeridas) {
        return botellasRecolectadas >= botellasRequeridas;
    }

    /**
     * Recolecta una esmeralda, aumentando el contador y la vida del jugador.
     */
    public void recolectarEsmeralda() {
        aumentarEsmeraldasRecolectadas(1);
        aumentarVida(15);
    }

    /**
     * Obtiene la cantidad de botellas recolectadas en el nivel actual.
     * 
     * @return Número de botellas recolectadas
     */
    public int getBotellasRecolectadas() {
        return botellasRecolectadas;
    }

    /**
     * Establece la cantidad de botellas recolectadas.
     * 
     * @param cantidad Nuevo valor para botellas recolectadas
     */
    public void setBotellasRecolectadas(int cantidad) {
        this.botellasRecolectadas = cantidad;
    }

    /**
     * Obtiene la cantidad de esmeraldas recolectadas en el nivel actual.
     * 
     * @return Número de esmeraldas recolectadas
     */
    public int getEsmeraldasRecolectadas() {
        return esmeraldasRecolectadas;
    }

    /**
     * Establece la cantidad de esmeraldas recolectadas.
     * 
     * @param cantidad Nuevo valor para esmeraldas recolectadas
     */
    public void setEsmeraldasRecolectadas(int cantidad) {
        this.esmeraldasRecolectadas = cantidad;
    }

    /**
     * Aumenta el contador de botellas recolectadas.
     * 
     * @param cantidad Valor a sumar (ajustado a mínimo 0)
     */
    private void aumentarBotellasRecolectadas(int cantidad) {
        this.botellasRecolectadas = Math.max(0, this.botellasRecolectadas + cantidad);
    }

    /**
     * Aumenta el contador de esmeraldas recolectadas.
     * 
     * @param cantidad Valor a sumar (ajustado a mínimo 0)
     */
    private void aumentarEsmeraldasRecolectadas(int cantidad) {
        this.esmeraldasRecolectadas = Math.max(0, this.esmeraldasRecolectadas + cantidad);
    }

    /**
     * Reinicia el contador de botellas recolectadas (para nuevo nivel).
     */
    public void resetearBotellas() {
        this.botellasRecolectadas = 0;
    }

    /**
     * Obtiene el total acumulado de botellas recolectadas (actualmente no implementado).
     * 
     * @return Siempre 0 en esta implementación
     */
    public int getBotellasTotales() {
        return 0; // Puedes implementar si quieres llevar el total acumulado de botellas
    }

    /**
     * Obtiene la velocidad actual de movimiento del jugador.
     * 
     * @return Velocidad en píxeles por movimiento
     */
    public int getVelocidad() {
        return velocidad;
    }

    /**
     * Establece la velocidad de movimiento del jugador.
     * 
     * @param velocidad Nueva velocidad en píxeles por movimiento
     */
    public void setVelocidad(int velocidad) {
        this.velocidad = velocidad;
    }
}