/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package autonoma.AventuraMagicaGame.elements;

/**
 * Representa un enemigo de tipo Frailejón en el juego.
 * El Frailejón es un enemigo con movimiento horizontal a velocidad constante.
 * @author Gilary
 * @version 1.0
 * @since 26/05/2025
 */
public class Frailejon extends Enemigo {
    /** Ancho predeterminado del sprite del Frailejón en píxeles */
    private static final int ANCHO = 50;
    
    /** Alto predeterminado del sprite del Frailejón en píxeles */
    private static final int ALTO = 70;
    
    /** Ruta de la imagen del sprite del Frailejón */
    private static final String RUTA_IMAGEN = "/autonoma/AventuraMagicaGame/images/Frailejon.png";

    /**
     * Crea una nueva instancia de Frailejón en la posición especificada.
     * 
     * @param x Posición horizontal inicial (coordenada x)
     * @param y Posición vertical inicial (coordenada y)
     */
    public Frailejon(int x, int y) {
        super(x, y, RUTA_IMAGEN, ANCHO, ALTO);
        setVelocidad(2); // Velocidad horizontal de 2 píxeles por movimiento
    }
}