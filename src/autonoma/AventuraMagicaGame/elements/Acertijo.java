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
  private String pregunta;
    private String respuestaCorrecta;
    private String respuestaAlternativa;

    public Acertijo(String pregunta, String respuestaCorrecta, String respuestaAlternativa) {
        this.pregunta = pregunta;
        this.respuestaCorrecta = respuestaCorrecta;
        this.respuestaAlternativa = respuestaAlternativa;
    }

    public String getPregunta() {
        return pregunta;
    }

    public String getRespuesta() {
        return respuestaCorrecta;
    }

    public String getRespuestaAlternativa() {
        return respuestaAlternativa;
    }
}