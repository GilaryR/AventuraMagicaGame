
package autonoma.AventuraMagicaGameBase.elements;

import java.util.ArrayList;

/**
 * @author Gilary 
 * @since 26/05/2025
 * @version 2.0
 */

public class SpriteContainer {
    private ArrayList<Sprite> sprites = new ArrayList<>();

    public void add(Sprite s) {
        sprites.add(s);
    }

    public ArrayList<Sprite> getSprites() {
        return sprites;
    }
}