package Entities;

import World.Camera;
import com.PatoGames.main.Game;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Tiro extends Entity {
    private int dx, dy, curLife = 0, life = 100;
    private int spd = 2;
    private BufferedImage spriteTiro = Entity.AMMO_EN;

    public Tiro(int x, int y, int width, int height, BufferedImage sprite, int dx, int dy) {
        super(x,y,width,height,sprite);
        this.dx = dx;
        this.dy = dy;
        spriteTiro = sprite;
    }

    public void tick() {
        x += dx*spd;
        y += dy*spd;

        curLife++;
        if(curLife >= life){
            Game.Tiros.remove(this);
            return;
        }

    }
    public void render(Graphics g) {
        g.drawImage(spriteTiro,this.getX() - Camera.x,this.getY() - Camera.y,width,height,null);
    }
}
