/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package autonoma.AventuraMagicaGameBase.elements;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import javax.swing.ImageIcon;

/**
 *
 * @author jgiugtiñut
 */

public class Sprite {
    protected int x, y;
    protected Image imagen;
    protected int ancho, alto;
    protected boolean visible;

    public Sprite(int x, int y, String rutaImagen, int ancho, int alto) {
        this.x = x;
        this.y = y;
        this.ancho = ancho;
        this.alto = alto;
        this.visible = true;
        cargarImagen(rutaImagen);
    }

    protected void cargarImagen(String rutaImagen) {
        try {
            ImageIcon icon = new ImageIcon(getClass().getResource(rutaImagen));
            Image imgOriginal = icon.getImage();
            
            // Escalar la imagen al tamaño deseado
            this.imagen = imgOriginal.getScaledInstance(
                ancho, 
                alto, 
                Image.SCALE_SMOOTH);
        } catch (Exception e) {
            System.err.println("Error al cargar imagen: " + rutaImagen);
            e.printStackTrace();
            // Crear imagen de respaldo
            this.imagen = new BufferedImage(ancho, alto, BufferedImage.TYPE_INT_ARGB);
        }
    }

    public void dibujar(Graphics g) {
        if (visible && imagen != null) {
            g.drawImage(imagen, x, y, null);
        }
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Image getImagen() {
        return imagen;
    }

    public void setImagen(Image imagen) {
        this.imagen = imagen;
    }

    public int getAncho() {
        return ancho;
    }

    public void setAncho(int ancho) {
        this.ancho = ancho;
    }

    public int getAlto() {
        return alto;
    }

    public void setAlto(int alto) {
        this.alto = alto;
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }
    public Rectangle getBounds() {
        return new Rectangle(x, y, ancho, alto);

    }
}