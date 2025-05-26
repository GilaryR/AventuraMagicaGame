
package autonoma.AventuraMagicaGameBase.elements;

import java.util.ArrayList;

/**
 *
 * @author jgiugtiñut
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