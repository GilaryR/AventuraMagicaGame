package autonoma.AventuraMagicaGame.elements;

import java.util.Arrays;
import java.util.List;

/**
 * Representa un acertijo con su pregunta y posibles respuestas válidas.
 * Permite verificar si una respuesta dada por el usuario es correcta.
 *@author Alejandra ortega 
 * @since 26-05-2025
 * @version 2.0
 */
public class Acertijo {
    /** La pregunta del acertijo */
    private String pregunta;
    
    /** Lista de respuestas válidas (puede haber varias formas correctas) */
    private List<String> respuestas;

    /**
     * Crea un nuevo acertijo con una pregunta y múltiples respuestas válidas.
     * @param pregunta El texto de la pregunta del acertijo
     * @param respuestas Varargs con las respuestas válidas (acepta múltiples)
     */
    public Acertijo(String pregunta, String... respuestas) {
        this.pregunta = pregunta;
        this.respuestas = Arrays.asList(respuestas);
    }

    /**
     * Crea un nuevo acertijo con una pregunta y exactamente dos respuestas válidas.
     * @param pregunta El texto de la pregunta del acertijo
     * @param respuesta1 Primera respuesta válida
     * @param respuesta2 Segunda respuesta válida
     */
    public Acertijo(String pregunta, String respuesta1, String respuesta2) {
        this.pregunta = pregunta;
        this.respuestas = Arrays.asList(respuesta1, respuesta2);
    }

    /**
     * Obtiene la pregunta del acertijo.
     * @return El texto de la pregunta
     */
    public String getPregunta() {
        return pregunta;
    }

    /**
     * Obtiene la lista de respuestas válidas.
     * @return Lista de strings con las respuestas aceptadas
     */
    public List<String> getRespuestas() {
        return respuestas;
    }

    /**
     * Verifica si la respuesta proporcionada por el usuario es correcta.
     * La comparación ignora mayúsculas/minúsculas y espacios alrededor.
     * @param respuestaUsuario La respuesta a verificar
     * @return true si la respuesta coincide con alguna de las respuestas válidas,
     *         false en caso contrario o si la respuesta es null
     */
    public boolean verificar(String respuestaUsuario) {
        if (respuestaUsuario == null) return false;
        String normalizada = respuestaUsuario.trim().toLowerCase();

        for (String r : respuestas) {
            if (r.trim().toLowerCase().equals(normalizada)) {
                return true;
            }
        }
        return false;
    }
}