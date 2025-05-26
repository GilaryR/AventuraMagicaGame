package autonoma.AventuraMagicaGame.elements;

public class Esmeralda extends Artefacto {
    private static final int ANCHO = 40;
    private static final int ALTO = 40;
    private static final String RUTA = "/autonoma/AventuraMagica/images/esmeralda.png";

    public Esmeralda(int x, int y) {
        super(x, y, RUTA, ANCHO, ALTO);
    }

    @Override
    public void aplicarEfecto(Jugador jugador) {
        jugador.aumentarVida(15);
    }
}

