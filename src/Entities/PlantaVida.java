package Entities;

import World.Camera;
import com.PatoGames.main.Game;

import java.awt.*;
import java.awt.image.BufferedImage;

public class PlantaVida extends Entity {
    public BufferedImage plantaInteira = Game.spritesheets.getSpritesheet(80,16,16,16);
    public BufferedImage plantaComida = Game.spritesheets.getSpritesheet(80,32,16,16);
    public static boolean foiComida = false;

    public PlantaVida(int x, int y, int width, int height, BufferedImage sprite) {
        super(x,y,width,height,sprite);
        foiComida = false;
    }
    public void comida() {
        if (!foiComida) {
            Player.life += 15;
            this.foiComida = true;
            if (Player.life >= Player.maxLife) {
                Player.life = Player.maxLife;
            }

        }
    }

    public void render(Graphics g) {
        if(!foiComida){
            g.drawImage(plantaInteira,x - Camera.x,y - Camera.y,this.width,this.height ,null);
        } else if (foiComida) {
            g.drawImage(plantaComida,x - Camera.x,y - Camera.y,this.width,this.height ,null);
        }

    }

}
