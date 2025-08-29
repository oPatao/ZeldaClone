package Entities;

import com.PatoGames.main.Game;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Player extends Entity {

    public static boolean right, left, up, down,shift;
    double speed = 2.0;

    private int frames, curAnimation,targetFrame = 10;
    private int directions = 3, RightDir=0, LeftDir=1, UpDir = 2, DownDir= 3;
    private BufferedImage[] playerRight, playerLeft, playerUp, playerDown;

    private boolean moved = false;


    public Player(int x, int y, int width, int height, BufferedImage sprite) {
        super(x,y,width,height,sprite);

        playerDown = new BufferedImage[4];
        for (int i = 0; i < 3; i++) {
            playerDown[i] = Game.spritesheets.getSpritesheet(32+(16*i),0, 16, 16);
        }
        playerDown[3] = playerDown[1];

        playerLeft = new BufferedImage[4];
        for (int i = 0; i < 3; i++) {
            playerLeft[i] = Game.spritesheets.getSpritesheet(32+(16*i),16, 16, 16);
        }
        playerLeft[3] = playerLeft[1];

        playerRight = new BufferedImage[4];
        for (int i = 0; i < 3; i++) {
            playerRight[i] = Game.spritesheets.getSpritesheet(32+(16*i),32, 16, 16);
        }
        playerRight[3] = playerRight[1];

        playerUp = new BufferedImage[4];
        for (int i = 0; i < 3; i++) {
            playerUp[i] = Game.spritesheets.getSpritesheet(32+(16*i),48, 16, 16);
        }
        playerUp[3] = playerUp[1];

    }
    public void tick(){
        moved = false;
        speed = 2.0;
        targetFrame = 10;

        if(shift){
            speed +=speed;
            targetFrame /= 2;
        }
        if(right){
            moved = true;
            x += speed;
            directions = RightDir;

        }else if(left){
            moved = true;
            x -= speed;
            directions = LeftDir;
        }
        if(up){
            moved = true;
            y -= speed;
            directions = UpDir;
        }else if(down){
            moved = true;
            y += speed;
            directions = DownDir;
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
    public void render(Graphics g){
        //g.drawImage(directions,this.getX(),this.getY(),null);
        if(directions == RightDir){
            g.drawImage(playerRight[curAnimation],x,y,this.width,this.height,null);
        }else if(directions == LeftDir){
            g.drawImage(playerLeft[curAnimation],x,y,this.width,this.height,null);
        }
        if(directions == UpDir){
            g.drawImage(playerUp[curAnimation],x,y,this.width,this.height,null);
        }else if(directions == DownDir){
            g.drawImage(playerDown[curAnimation],x,y,this.width,this.height,null);
        }
    }
}
