package Entities;

import World.Camera;
import World.World;
import com.PatoGames.main.Game;

import java.awt.*;
import java.awt.image.BufferedImage;

import static World.World.isFree;

public class Player extends Entity {

    public static boolean right, left, up, down,shift;
    double speed = 1;

    private int frames, curAnimation,targetFrame = 10;
    private int directions = 3, RightDir=0, LeftDir=1, UpDir = 2, DownDir= 3;
    private BufferedImage[] playerRight, playerLeft, playerUp, playerDown;

    private boolean moved = false;
    public static double life = 100, maxLife = 100;
    private int ammo = 0;


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
        speed = 1;
        targetFrame = 10;

        if(shift){
            speed +=1 ;
            targetFrame /= 2;
        }
        if(right && isFree(this.getX()+ (int)(speed), this.getY())){
            moved = true;
            x += speed;
            directions = RightDir;

        }else if(left && isFree(this.getX() - (int)(speed), this.getY())){
            moved = true;
            x -= speed;
            directions = LeftDir;
        }
        if(up && isFree(this.getX(), this.getY() - (int)(speed))){
            moved = true;
            y -= speed;
            directions = UpDir;
        }else if(down && isFree(this.getX(),this.getY() + (int)(speed))){
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
        }else{
            curAnimation = 1;
        }
        checkPlanta();
        checkMunicao();
        Camera.x =Camera.clamp( this.getX() -  (Game.WIDTH/2), 0, World.WIDTH*16 - Game.WIDTH );
        Camera.y =Camera.clamp( this.getY() - (Game.HEIGHT/2), 0, World.HEIGHT*16 - Game.HEIGHT );


    }
    public void checkPlanta(){
        for (int i = 0; i < Game.plantaVidas.size(); i++) {
            PlantaVida e = Game.plantaVidas.get(i);
                if(Entity.isCollide(this,e)) {
                    e.comida();
                    return;
                }
        }

    }
    public int getAmmo() {
        return ammo;
    }
    public void checkMunicao(){
        for (int i = 0; i < Game.entities.size(); i++) {
            Entity e = Game.entities.get(i);
            if(e instanceof Municao) {
                if (Entity.isCollide(this, e)) {
                    ammo+=5;
                    Game.entities.remove(e);
                }
            }
        }
    }

    /*public boolean isColliding(){

    }*/
    public void render(Graphics g){
        //g.drawImage(directions,this.getX(),this.getY(),null);
        if(directions == RightDir){
            g.drawImage(playerRight[curAnimation],x - Camera.x,y - Camera.y,this.width,this.height ,null);
        }else if(directions == LeftDir){
            g.drawImage(playerLeft[curAnimation],x - Camera.x,y - Camera.y,this.width,this.height,null);
        }
        if(directions == UpDir){
            g.drawImage(playerUp[curAnimation],x - Camera.x,y - Camera.y,this.width ,this.height ,null);
        }else if(directions == DownDir){
            g.drawImage(playerDown[curAnimation],x - Camera.x,y - Camera.y,this.width,this.height ,null);
        }
    }
}
