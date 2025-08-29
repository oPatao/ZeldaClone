package Graficos;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Spritesheets {

    private BufferedImage spritesheet;

    public Spritesheets(String path) {
        try {
            spritesheet = ImageIO.read(getClass().getResource(path));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public BufferedImage getSpritesheet(int x, int y, int width, int height) {
        return spritesheet.getSubimage(x, y, width, height);
    }
}
