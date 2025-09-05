package Entities;

import World.Camera;
import com.PatoGames.main.Game;

import java.awt.*;
import java.awt.image.BufferedImage;

public class PlantaVida extends Entity {
    public BufferedImage plantaInteira = Game.spritesheets.getSpritesheet(80,16,16,16);
    public BufferedImage plantaComida = Game.spritesheets.getSpritesheet(80,32,16,16);
    public boolean foiComida = false;

    public PlantaVida(int x, int y, int width, int height, BufferedImage sprite) {
        super(x,y,width,height,sprite);
        this.foiComida = false;
    }
    public void comida() {
        if (!foiComida) {
            Game.player.life += 15;
            this.foiComida = true;
            if (Game.player.life >= Game.player.maxLife) {
                Game.player.life = Game.player.maxLife;
            }

        }
    }


    public void render(Graphics g) {
        if(!foiComida){
            g.drawImage(plantaInteira,x - Camera.x,y - Camera.y,this.width,this.height ,null);
        } else {
            g.drawImage(plantaComida,x - Camera.x,y - Camera.y,this.width,this.height ,null);
        }

    }

}
