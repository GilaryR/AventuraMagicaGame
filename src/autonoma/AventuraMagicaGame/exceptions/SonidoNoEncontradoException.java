package autonoma.AventuraMagicaGame.exceptions;

/**
 * Excepción lanzada cuando no se encuentra un archivo de sonido en el sistema.
 * 
 * Esta clase extiende {@link Exception} y permite identificar errores específicos
 * relacionados con la carga de archivos de sonido, incluyendo el nombre del archivo faltante.
 * 
 * @author Luisa Fernanda Henao
 * @version 1.0
 * @since 28/05/2025
 */
public class SonidoNoEncontradoException extends Exception {

    /**
     * Nombre del archivo de sonido que no fue encontrado.
     */
    private String nombreSonido;

    /**
     * Crea una nueva instancia de {@code SonidoNoEncontradoException} con el nombre del sonido faltante.
     *
     * @param nombreSonido El nombre del archivo de sonido que no se pudo encontrar.
     */
    public SonidoNoEncontradoException(String nombreSonido) {
        super("No se encontró el archivo de sonido: " + nombreSonido);
        this.nombreSonido = nombreSonido;
    }

    /**
     * Obtiene el nombre del archivo de sonido que no fue encontrado.
     *
     * @return El nombre del sonido.
     */
    public String getNombreSonido() {
        return nombreSonido;
    }
}
