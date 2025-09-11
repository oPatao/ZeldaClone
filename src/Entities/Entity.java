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
    public static BufferedImage ESTILINGUEFrenteCostas = Game.spritesheets.getSpritesheet(32,64,3,4);
    public static BufferedImage ESTILINGUELADOS = Game.spritesheets.getSpritesheet(35,64,3,4);

    protected int x;
    protected int y;
    protected int width;
    protected int height;
    protected int maskX, maskY, maskWidth, maskHeight;

    private BufferedImage sprites;

    public Entity(int x, int y, int width, int height, BufferedImage sprites) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.sprites = sprites;

        this.maskX = 0;
        this.maskY = 0;
        this.maskWidth = width;
        this.maskHeight = height;
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

    public void setMask(int maskX, int maskY, int maskWidth, int maskHeight) {
        this.maskX = maskX;
        this.maskY = maskY;
        this.maskWidth = maskWidth;
        this.maskHeight = maskHeight;
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
    public static boolean isCollide(Entity e1, Entity e2) {
        Rectangle e1Mask = new Rectangle(e1.getX(), e1.getY(), e1.getWidth(), e1.getHeight());
        Rectangle e2Mask = new Rectangle(e2.getX(), e2.getY(), e2.getWidth(), e2.getHeight());

        return e1Mask.intersects(e2Mask);
    }

    public void render(Graphics g) {
        g.drawImage(this.sprites, this.getX() - Camera.x, this.getY() - Camera.y,this.getWidth(),this.getHeight(),null);
       /* g.setColor(Color.RED);
        g.fillRect(this.getX() + maskX - Camera.x, this.getY() + maskY - Camera.y,this.maskWidth,this.maskHeight);*/
    }
}
