package World;

import com.PatoGames.main.Game;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Tile {
    public static BufferedImage TILE_GRAMA_SIMPLES = Game.spritesheets.getSpritesheet(0,16,16,16);
    public static BufferedImage TILE_GRAMA_FLOR = Game.spritesheets.getSpritesheet(0,0,16,16);
    public static BufferedImage TILE_PEDRA = Game.spritesheets.getSpritesheet(16,0,16,16);

    private BufferedImage sprite;
    private int x, y;

    public Tile(BufferedImage sprite, int x, int y) {
        this.sprite = sprite;
        this.x = x;
        this.y = y;
    }

    public void render(Graphics g) {
        g.drawImage(sprite,x - Camera.x,y - Camera.y,null);
    }
}
