
package autonoma.AventuraMagicaGameBase.elements;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import javax.swing.ImageIcon;

/**
 * Clase base para representar elementos gráficos (sprites) en el juego.
 * Proporciona funcionalidad básica para manejar posición, dimensiones,
 * visibilidad y representación gráfica de elementos del juego.
 * @author Gilary Rugeles 
 * @since 26-05-2025
 * @version 1.0
 */
public class Sprite {
    /** Posición horizontal (coordenada x) del sprite */
    protected int x;
    
    /** Posición vertical (coordenada y) del sprite */
    protected int y;
    
    /** Imagen asociada al sprite */
    protected Image imagen;
    
    /** Ancho del sprite en píxeles */
    protected int ancho;
    
    /** Alto del sprite en píxeles */
    protected int alto;
    
    /** Bandera que indica si el sprite debe ser dibujado */
    protected boolean visible;

    /**
     * Constructor que crea un nuevo sprite.
     * @param x Posición horizontal inicial
     * @param y Posición vertical inicial
     * @param rutaImagen Ruta del archivo de imagen
     * @param ancho Ancho deseado para el sprite
     * @param alto Alto deseado para el sprite
     */
    public Sprite(int x, int y, String rutaImagen, int ancho, int alto) {
        this.x = x;
        this.y = y;
        this.ancho = ancho;
        this.alto = alto;
        this.visible = true;
        cargarImagen(rutaImagen);
    }

    /**
     * Carga y escala la imagen del sprite.
     * @param rutaImagen Ruta del recurso de imagen
     */
    protected void cargarImagen(String rutaImagen) {
        try {
            ImageIcon icon = new ImageIcon(getClass().getResource(rutaImagen));
            Image imgOriginal = icon.getImage();
            this.imagen = imgOriginal.getScaledInstance(ancho, alto, Image.SCALE_SMOOTH);
        } catch (Exception e) {
            System.err.println("Error al cargar imagen: " + rutaImagen);
            e.printStackTrace();
            this.imagen = new BufferedImage(ancho, alto, BufferedImage.TYPE_INT_ARGB);
        }
    }

    /**
     * Dibuja el sprite con optimización de área visible (clipping).
     * @param g Contexto gráfico para dibujar
     */
    public void dibujar(Graphics g) {
        if (visible && imagen != null) {
            Rectangle clipBounds = g.getClipBounds();
            
            if (clipBounds != null) {
                Rectangle spriteBounds = new Rectangle(x, y, ancho, alto);
                if (clipBounds.intersects(spriteBounds)) {
                    Rectangle intersection = clipBounds.intersection(spriteBounds);
                    if (!intersection.isEmpty()) {
                        g.drawImage(imagen, x, y, null);
                    }
                }
            } else {
                g.drawImage(imagen, x, y, null);
            }
        }
    }

    /**
     * Dibuja el sprite verificando límites del panel.
     * @param g Contexto gráfico para dibujar
     * @param panelWidth Ancho máximo del área visible
     * @param panelHeight Alto máximo del área visible
     */
    public void dibujar(Graphics g, int panelWidth, int panelHeight) {
        if (visible && imagen != null) {
            if (x >= 0 && y >= 0 && x + ancho <= panelWidth && y + alto <= panelHeight) {
                g.drawImage(imagen, x, y, null);
            }
        }
    }

    /**
     * Verifica si el sprite está dentro de los límites especificados.
     * @param panelWidth Ancho máximo permitido
     * @param panelHeight Alto máximo permitido
     * @return true si el sprite está completamente visible
     */
    public boolean estaDentroDelimites(int panelWidth, int panelHeight) {
        return x >= 0 && y >= 0 && x + ancho <= panelWidth && y + alto <= panelHeight;
    }

    /**
     * Ajusta la posición para mantener el sprite dentro de los límites.
     * @param panelWidth Ancho máximo permitido
     * @param panelHeight Alto máximo permitido
     */
    public void forzarDentroLimites(int panelWidth, int panelHeight) {
        x = Math.max(0, Math.min(x, panelWidth - ancho));
        y = Math.max(0, Math.min(y, panelHeight - alto));
    }

    // Métodos de acceso (getters y setters) con documentación básica
    
    /** @return Posición horizontal actual */
    public int getX() { return x; }
    
    /** @param x Nueva posición horizontal */
    public void setX(int x) { this.x = x; }
    
    /**
     * Establece posición horizontal con control de límites.
     * @param x Nueva posición horizontal deseada
     * @param limiteAnchoPanel Ancho máximo permitido
     */
    public void setX(int x, int limiteAnchoPanel) {
        this.x = Math.max(0, Math.min(x, limiteAnchoPanel - ancho));
    }
    
    /** @return Posición vertical actual */
    public int getY() { return y; }
    
    /** @param y Nueva posición vertical */
    public void setY(int y) { this.y = y; }
    
    /**
     * Establece posición vertical con control de límites.
     * @param y Nueva posición vertical deseada
     * @param limiteAltoPanel Alto máximo permitido
     */
    public void setY(int y, int limiteAltoPanel) {
        this.y = Math.max(0, Math.min(y, limiteAltoPanel - alto));
    }
    
    /** @return Imagen actual del sprite */
    public Image getImagen() { return imagen; }
    
    /** @param imagen Nueva imagen para el sprite */
    public void setImagen(Image imagen) { this.imagen = imagen; }
    
    /** @return Ancho actual del sprite */
    public int getAncho() { return ancho; }
    
    /** @param ancho Nuevo ancho para el sprite */
    public void setAncho(int ancho) { this.ancho = ancho; }
    
    /** @return Alto actual del sprite */
    public int getAlto() { return alto; }
    
    /** @param alto Nuevo alto para el sprite */
    public void setAlto(int alto) { this.alto = alto; }
    
    /** @return Estado de visibilidad actual */
    public boolean isVisible() { return visible; }
    
    /** @param visible Nuevo estado de visibilidad */
    public void setVisible(boolean visible) { this.visible = visible; }
    
    /** 
     * Obtiene el rectángulo de colisión del sprite.
     * @return Rectángulo con posición y dimensiones actuales
     */
    public Rectangle getBounds() {
        return new Rectangle(x, y, ancho, alto);
    }
}