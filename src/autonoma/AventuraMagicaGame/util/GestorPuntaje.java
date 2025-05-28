
package autonoma.AventuraMagicaGame.util;

import autonoma.AventuraMagicaGame.exceptions.EscrituraExcepcion;
import autonoma.AventuraMagicaGameBase.elements.EscritorTextoPlano;
import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javax.swing.JOptionPane;

/**
 * Gestor para manejar el guardado y lectura de puntajes del juego
 * Utiliza la arquitectura existente con EscritorTextoPlano
 * 
 * @author Gilary
 * @since 28/05/2025
 * @version 1.0
 */
public class GestorPuntaje {
    private static final String ARCHIVO_PUNTAJES = obtenerRutaPaquete() + "puntajes.txt";
    
    /**
     * Obtiene la ruta del paquete util donde se encuentra esta clase
     * @return Ruta del paquete util
     */
    private static String obtenerRutaPaquete() {
        return "src/autonoma/AventuraMagicaGame/util/";
    }
    private EscritorTextoPlano escritor;
    
    /**
     * Constructor que inicializa el escritor de texto plano
     */
    public GestorPuntaje() {
        this.escritor = new EscritorTextoPlano(ARCHIVO_PUNTAJES);
        verificarArchivo();
    }
    
    /**
     * Verifica si el archivo existe y muestra información sobre su ubicación
     */
    private void verificarArchivo() {
        File archivo = new File(ARCHIVO_PUNTAJES);
        System.out.println("Archivo de puntajes: " + archivo.getAbsolutePath());
    }
    
    /**
     * Solicita el nombre del jugador y guarda su puntaje final
     * 
     * @param puntajeFinal Puntaje total obtenido por el jugador
     * @param nivelAlcanzado Último nivel alcanzado por el jugador
     */
    public void solicitarYGuardarPuntaje(int puntajeFinal, int nivelAlcanzado) {
        // Solicitar nombre del jugador
        String nombreJugador = JOptionPane.showInputDialog(
            null,
            "¡Juego terminado!\n" +
            "Tu puntaje final: " + puntajeFinal + "\n" +
            "Nivel alcanzado: " + nivelAlcanzado + "\n\n" +
            "Ingresa tu nombre:",
            "Guardar Puntaje",
            JOptionPane.QUESTION_MESSAGE
        );
        
        // Validar nombre ingresado
        if (nombreJugador == null || nombreJugador.trim().isEmpty()) {
            nombreJugador = "Anónimo";
        }
        
        // Guardar el puntaje
        guardarPuntaje(nombreJugador.trim(), puntajeFinal, nivelAlcanzado);
    }
    
    /**
     * Guarda un puntaje en el archivo de texto
     * 
     * @param nombreJugador Nombre del jugador
     * @param puntaje Puntaje obtenido
     * @param nivel Nivel alcanzado
     */
    public void guardarPuntaje(String nombreJugador, int puntaje, int nivel) {
        try {
            // Generar timestamp
            LocalDateTime ahora = LocalDateTime.now();
            DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
            String fechaHora = ahora.format(formato);
            
            // Formatear línea de puntaje con separadores para facilitar lectura
            String lineaPuntaje = String.format("%-20s | %6d pts | Nivel %2d | %s",
                nombreJugador, puntaje, nivel, fechaHora);
            
            // Escribir usando tu EscritorTextoPlano
            escritor.escribir(lineaPuntaje);
            
        } catch (EscrituraExcepcion e) {
            System.err.println("✗ Error al guardar puntaje: " + e.getMessage());
            
            // Mostrar error al usuario
            JOptionPane.showMessageDialog(
                null,
                "No se pudo guardar el puntaje:\n" + e.getMessage(),
                "Error de Guardado",
                JOptionPane.ERROR_MESSAGE
            );
        }
    }
    
    /**
     * Método estático de conveniencia para usar sin instanciar
     * 
     * @param puntajeFinal Puntaje final del jugador
     * @param nivelAlcanzado Nivel alcanzado por el jugador
     */
    public static void guardarPuntajeFinal(int puntajeFinal, int nivelAlcanzado) {
        GestorPuntaje gestor = new GestorPuntaje();
        gestor.solicitarYGuardarPuntaje(puntajeFinal, nivelAlcanzado);
    }
    
    /**
     * Verifica si el archivo de puntajes existe
     * 
     * @return true si el archivo existe, false en caso contrario
     */
    public boolean archivoExiste() {
        File archivo = new File(ARCHIVO_PUNTAJES);
        return archivo.exists();
    }
    
    /**
     * Obtiene la ruta completa del archivo de puntajes
     * 
     * @return Ruta absoluta del archivo
     */
    public String obtenerRutaArchivo() {
        File archivo = new File(ARCHIVO_PUNTAJES);
        return archivo.getAbsolutePath();
    }
}