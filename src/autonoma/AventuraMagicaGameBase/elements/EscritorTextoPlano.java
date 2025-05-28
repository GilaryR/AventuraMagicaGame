
package autonoma.AventuraMagicaGameBase.elements;

import autonoma.AventuraMagicaGame.exceptions.EscrituraExcepcion;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
/**
 * Implementación de la interfaz Escritor que permite escribir mensajes en un archivo de texto plano.
 * Los mensajes se escriben en modo append (añadiendo al final del archivo) con un salto de línea después de cada mensaje.
 * 
 * @author Gilary Rugeles
 * @version 1.0
 * @see Escritor
 */
public class EscritorTextoPlano implements Escritor {
    /**
     * La ruta del archivo donde se escribirán los mensajes.
     */
    private String ruta;

    /**
     * Constructor que inicializa el escritor con la ruta del archivo destino.
     * 
     * @param ruta La ruta del archivo donde se escribirán los mensajes.
     */
    public EscritorTextoPlano(String ruta) {
        this.ruta = ruta;
    }

    /**
     * Escribe un mensaje en el archivo de texto plano especificado en la ruta.
     * El mensaje se añade al final del archivo seguido de un salto de línea.
     * 
     * @param mensaje El texto que se escribirá en el archivo.
     * @throws EscrituraExcepcion Si ocurre un error de I/O al intentar escribir en el archivo.
     */
    @Override
    public void escribir(String mensaje) throws EscrituraExcepcion {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(ruta, true))) {
            writer.write(mensaje);
            writer.newLine();
        } catch (IOException e) {
            throw new EscrituraExcepcion("No se pudo escribir en el archivo: " + ruta);
        }
    }
}