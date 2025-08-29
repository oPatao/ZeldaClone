package Entities;

import World.Camera;
import com.PatoGames.main.Game;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Slime extends Entity{

    double speed = 1;

    private int frames;
    private int curAnimation;
    private int directions = 3;
    private final int RightDir=0,LeftDir=1;

    public static BufferedImage[] slimeDireita;
    public static BufferedImage[] slimeEsquerda;




    public Slime(int x, int y, int width, int height, BufferedImage sprite) {
        super(x,y,width,height,sprite);

        slimeDireita = new BufferedImage[4];
        for (int i = 0; i < 3; i++) {
            slimeDireita[i] = Game.spritesheets.getSpritesheet(112+(16*i),16, 16, 16);
        }
        slimeDireita[3] = slimeDireita[1];

        slimeEsquerda = new BufferedImage[4];
        for (int i = 0; i < 3; i++) {
            slimeEsquerda[i] = Game.spritesheets.getSpritesheet(112+(16*i),0, 16, 16);
        }
        slimeEsquerda[3] = slimeEsquerda[1];
    }

    public void tick(){
        boolean moved = false;
        speed = 1;
        int targetFrame = 10;


        if(x < Game.player.getX()){
            moved = true;
            x += speed;
            directions = RightDir;

        }else if(x > Game.player.getX()){
            moved = true;
            x -= speed;
            directions = LeftDir;
        }
        if(y > Game.player.getY()){
            moved = true;
            y -= speed;

        }else if(y < Game.player.getY()){
            moved = true;
            y += speed;

        }
        if(moved){
            frames++;
            if(frames >= targetFrame){
                frames = 0;
                curAnimation++;
                if(curAnimation > 3){
                    curAnimation = 0;
                }
            }
        }

    }
    public void render(Graphics g) {
        //g.drawImage(directions,this.getX(),this.getY(),null);
        if (directions == RightDir) {
            g.drawImage(slimeDireita[curAnimation], x - Camera.x,y - Camera.y, this.width , this.height, null);
        } else if (directions == LeftDir) {
            g.drawImage(slimeEsquerda[curAnimation], x - Camera.x,y - Camera.y, this.width, this.height, null);
        }
    }
}
