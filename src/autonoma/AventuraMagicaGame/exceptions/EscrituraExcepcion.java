package autonoma.AventuraMagicaGame.exceptions;

/**
 * Excepción lanzada cuando ocurre un error al intentar escribir datos,
 * como al guardar información en un archivo de texto plano.
 * 
 * Esta excepción permite capturar y manejar fallos relacionados con la 
 * escritura de datos de manera más específica.
 * 
 * @author Luisa Fernanda Henao Posada
 * @version 1.0
 * @since 28/05/2025
 */
public class EscrituraExcepcion extends Exception {

    /**
     * Crea una nueva excepción de tipo {@code EscrituraExcepcion} con un mensaje detallado.
     * 
     * @param mensaje Descripción del error ocurrido durante la escritura.
     */
    public EscrituraExcepcion(String mensaje) {
        super(mensaje);
    }
}
