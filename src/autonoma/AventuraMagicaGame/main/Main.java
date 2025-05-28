package autonoma.AventuraMagicaGame.main;

import autonoma.AventuraMagicaGame.elements.NivelBase;
import autonoma.AventuraMagicaGame.elements.NivelFacil;
import autonoma.AventuraMagicaGame.ui.Ventana;
import javax.swing.SwingUtilities;

/**
 * Clase principal del juego Aventura Mágica.
 * 
 * Esta clase contiene el método {@code main}, que es el punto de entrada del programa.
 * Se encarga de inicializar el primer nivel del juego y lanzar la ventana principal
 * dentro del hilo de eventos de Swing para mantener la interfaz gráfica fluida.
 * 
 * @author Gilary Rugeles
 * @version 2.0
 * @since 28/05/2025
 */
public class Main {

    /**
     * Método principal que inicia la ejecución del juego.
     * 
     * Se ejecuta en el hilo de eventos de Swing utilizando {@code SwingUtilities.invokeLater}
     * para garantizar que todos los componentes gráficos se creen de forma segura.
     * 
     * Aquí se instancia el nivel inicial del juego (por defecto, {@link NivelFacil})
     * y se lanza la ventana principal del juego.
     * 
     * @param args Argumentos de línea de comandos (no utilizados).
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            NivelBase nivelInicial = new NivelFacil();  // Nivel inicial del juego
            new Ventana(nivelInicial);                  // Crear y mostrar la ventana del juego
        });
    }
}
