package Entities;

import World.Camera;
import com.PatoGames.main.Game;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Entity {
    public static BufferedImage PLANTAR_CURA_EN = Game.spritesheets.getSpritesheet(80,16,16,16);
    public static BufferedImage ESTILINGUE_EN = Game.spritesheets.getSpritesheet(80,0,16,16);
    public static BufferedImage AMMO_EN = Game.spritesheets.getSpritesheet(96,0,16,16);
    public static BufferedImage SLIME_EN = Game.spritesheets.getSpritesheet(112,0,16,16);

    protected int x;
    protected int y;
    protected int width;
    protected int height;

    private BufferedImage sprites;

    public Entity(int x, int y, int width, int height, BufferedImage sprites) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.sprites = sprites;
    }
    public int getX() {
        return this.x;
    }
    public int getY() {
        return this.y;
    }
    public int getWidth() {
        return this.width;
    }
    public int getHeight() {
        return this.height;
    }

    public int setX(int x) {
        this.x = x;
        return x;
    }
    public int setY(int y) {
        this.y = y;
        return y;
    }

    public void tick(){

    }

    public void render(Graphics g) {
        g.drawImage(this.sprites, this.getX() - Camera.x, this.getY() - Camera.y,this.getWidth(),this.getHeight(),null);
    }
}
