package autonoma.AventuraMagicaGame.elements;

import java.util.Arrays;
import java.util.List;

public class Acertijo {
    private String pregunta;
    private List<String> respuestas;

    public Acertijo(String pregunta, String... respuestas) {
        this.pregunta = pregunta;
        this.respuestas = Arrays.asList(respuestas);
    }

    public Acertijo(String pregunta, String respuesta1, String respuesta2) {
        this.pregunta = pregunta;
        this.respuestas = Arrays.asList(respuesta1, respuesta2);
    }

    public String getPregunta() {
        return pregunta;
    }

    public List<String> getRespuestas() {
        return respuestas;
    }

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