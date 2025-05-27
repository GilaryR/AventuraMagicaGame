
package autonoma.AventuraMagicaGame.elements;

/**
 * Representa un acertijo o pregunta con su respuesta correspondiente.
 * Permite verificar si una respuesta dada coincide con la respuesta correcta.
 * 
 * @author Gilary Rugeles
 * @since 26-05-2025
 * @version 2.0
 */
public class Acertijo {
    private String pregunta;
    private String respuesta;

    /**
     * Crea un nuevo acertijo con la pregunta y respuesta especificadas.
     * 
     * @param pregunta El texto de la pregunta o acertijo
     * @param respuesta La respuesta correcta para este acertijo
     */
    public Acertijo(String pregunta, String respuesta) {
        this.pregunta = pregunta;
        this.respuesta = respuesta;
    }

    /**
     * Verifica si la respuesta proporcionada coincide con la respuesta correcta.
     * La comparación no distingue entre mayúsculas y minúsculas y elimina espacios
     * en blanco al inicio y final de la respuesta proporcionada.
     * 
     * @param r La respuesta a verificar
     * @return true si la respuesta coincide (ignorando mayúsculas/minúsculas), false en caso contrario
     */
    public boolean verificar(String r) {
        return respuesta.equalsIgnoreCase(r.trim());
    }

    /**
     * Obtiene el texto de la pregunta.
     * 
     * @return El texto de la pregunta
     */
    public String getPregunta() { return pregunta; }
}