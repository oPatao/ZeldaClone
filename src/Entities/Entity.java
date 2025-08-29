package entities;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Entity {
    private int x;
    private int y;
    private int width;
    private int height;

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

    public void render(Graphics g) {
        g.drawImage(this.sprites, this.getX(), this.getY(),this.getWidth(),this.getHeight(),null);
    }
}
