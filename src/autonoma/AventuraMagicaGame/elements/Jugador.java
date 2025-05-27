package autonoma.AventuraMagicaGame.elements;

import autonoma.AventuraMagicaGameBase.elements.Sprite;



public class Jugador extends Sprite {
  private int puntaje;
    private int velocidad = 5;
    private int vida = 10;

    private int botellasRecolectadas;
    private int esmeraldasRecolectadas;

    private static final int ANCHO_JUGADOR = 48;
    private static final int ALTO_JUGADOR = 48;
    private static final String RUTA_IMAGEN = "/autonoma/AventuraMagicaGame/images/Jugador.png";

    public Jugador(int x, int y) {
        super(x, y, RUTA_IMAGEN, ANCHO_JUGADOR, ALTO_JUGADOR);
        this.puntaje = 0;
        this.botellasRecolectadas = 0;
        this.esmeraldasRecolectadas = 0;
    }


    public void moverIzquierda() { this.x -= velocidad; }
    public void moverDerecha()   { this.x += velocidad; }
    public void moverArriba()    { this.y -= velocidad; }
    public void moverAbajo()     { this.y += velocidad; }

    public void mover(int dx, int dy, int width, int height) {
        int nuevaX = this.x + dx;
        int nuevaY = this.y + dy;

        if (nuevaX < 0) nuevaX = 0;
        else if (nuevaX + getAncho() > width) nuevaX = width - getAncho();

        if (nuevaY < 0) nuevaY = 0;
        else if (nuevaY + getAlto() > height) nuevaY = height - getAlto();

        this.x = nuevaX;
        this.y = nuevaY;
    }

    public int getVida() {
        return vida;
    }

    public int getVidas() {
        return vida;
    }

    public void setVidas(int vidas) {
        this.vida = Math.max(0, Math.min(vidas, 100));
    }

    public void aumentarVida(int cantidad) {
        this.vida += cantidad;
        if (this.vida > 100) this.vida = 100;
        if (this.vida < 0) this.vida = 0;
    }

    public void reducirVida(int cantidad) {
        this.vida -= cantidad;
        if (this.vida < 0) this.vida = 0;
    }

    public int getPuntaje() {
        return puntaje;
    }

    public void setPuntaje(int puntaje) {
        this.puntaje = Math.max(puntaje, 0);
    }

    public void incrementarPuntaje(int valor) {
        this.puntaje += valor;
    }

    public void disminuirPuntaje(int valor) {
        this.puntaje -= valor;
        if (this.puntaje < 0) this.puntaje = 0;
    }

    public void aumentarPuntaje(int cantidad) {
        this.puntaje += cantidad;
        if (this.puntaje < 0) this.puntaje = 0;
    }


    public void recolectarBotella() {
        aumentarBotellasRecolectadas(1);
        aumentarPuntaje(5);
    }
    public boolean objetivoBotellasAlcanzado(int botellasRequeridas) {
    return botellasRecolectadas >= botellasRequeridas;
    }

    public void recolectarEsmeralda() {
        aumentarEsmeraldasRecolectadas(1);
        aumentarVida(15);
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

    private void aumentarBotellasRecolectadas(int cantidad) {
        this.botellasRecolectadas = Math.max(0, this.botellasRecolectadas + cantidad);
    }

    private void aumentarEsmeraldasRecolectadas(int cantidad) {
        this.esmeraldasRecolectadas = Math.max(0, this.esmeraldasRecolectadas + cantidad);
    }

  
    public void resetearParaNuevoNivel() {
        this.botellasRecolectadas = 0;
    }

    public void resetearBotellas() {
        this.botellasRecolectadas = 0;
    }

    public int getBotellasTotales() {
        return 0; // ¿Planeas implementar esto?
    }

    public int getVelocidad() {
        return velocidad;
    }

    public void setVelocidad(int velocidad) {
        this.velocidad = velocidad;
    }
}