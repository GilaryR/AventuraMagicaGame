/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package autonoma.AventuraMagicaGame.elements;

/**
 *
 * @author jgiugtiñut
 */

public class Frailejon extends Enemigo {
    private static final int ANCHO = 50;
    private static final int ALTO = 70;
    private static final String RUTA_IMAGEN = "/autonoma/AventuraMagicaGame/images/Frailejon.png";

    public Frailejon(int x, int y) {
        super(x, y, RUTA_IMAGEN, ANCHO, ALTO);
        setVelocidad(2); // Esto ajusta la velocidad horizontal a 2 px por movimiento
    }
}
