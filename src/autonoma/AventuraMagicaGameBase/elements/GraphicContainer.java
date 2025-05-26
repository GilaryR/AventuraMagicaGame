
package autonoma.AventuraMagicaGameBase.elements;

import java.awt.Graphics;
import javax.swing.JPanel;

/**
 *
 * @author jgiugtiñut
 */
public class GraphicContainer extends JPanel {
    private SpriteContainer spriteContainer;

    public GraphicContainer(SpriteContainer spriteContainer) {
        this.spriteContainer = spriteContainer;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (Sprite s : spriteContainer.getSprites()) {
           
        }
    }
}