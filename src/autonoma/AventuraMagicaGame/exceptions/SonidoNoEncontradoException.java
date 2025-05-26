package autonoma.AventuraMagicaGame.exceptions;


/**
 *
 * @author Luisa Fernanda Henao
 */
/**
 * Excepción lanzada cuando no se encuentra un archivo de sonido
 */
public class SonidoNoEncontradoException extends Exception {
    private String nombreSonido;

    public SonidoNoEncontradoException(String nombreSonido) {
        super("No se encontró el archivo de sonido: " + nombreSonido);
        this.nombreSonido = nombreSonido;
    }

    public String getNombreSonido() {
        return nombreSonido;
    }
}