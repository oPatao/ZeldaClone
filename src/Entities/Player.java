package Entities;

import Graficos.Spritesheets;
import World.Camera;
import World.World;
import com.PatoGames.main.Game;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

import static World.World.isFree;

public class Player extends Entity {

    public static boolean right, left, up, down,shift, atirando;
    double speed = 1;

    private int frames, curAnimation,targetFrame = 10,dmgFrames = 0;
    private int directions = 3, RightDir=0, LeftDir=1, UpDir = 2, DownDir= 3;
    private BufferedImage[] playerRight, playerLeft, playerUp, playerDown;
    private BufferedImage playerDmg = Game.spritesheets.getSpritesheet(48,64,16,16);

    public boolean isDamaged = false;

    private boolean armado = false;
    private int ammo = 0;


    private boolean moved = false;
    public double life = 100, maxLife = 100;



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

        if (isDamaged) {
            this.dmgFrames++;
            if(dmgFrames >= 15){
                dmgFrames = 0;
                isDamaged = false;
            }
        }

        checkPlanta();
        checkMunicao();
        if (!armado){
            checkEstilingue();
        }
        if (armado){
            if (atirando && ammo >= 1 ) {
                int dx = 0, dy = 0;
                if (directions == RightDir) {
                    dx = 1;
                } else if (directions == LeftDir) {
                    dx = -1;
                }
                if (directions == UpDir) {
                    dy = -1;
                } else if (directions == DownDir) {
                    dy = 1;
                }
                Tiro tiros = new Tiro(this.getX(), this.getY(), 8, 8, Entity.AMMO_EN, dx, dy);
                Game.Tiros.add(tiros);
                ammo--;
                atirando = false;
            }
        }

        Camera.x =Camera.clamp( this.getX() -  (Game.WIDTH/2), 0, World.WIDTH*16 - Game.WIDTH );
        Camera.y =Camera.clamp( this.getY() - (Game.HEIGHT/2), 0, World.HEIGHT*16 - Game.HEIGHT );

        if (life <= 0) {
            Game.entities.clear();
            Game.entities = new ArrayList<Entity>();
            Game.plantaVidas = new ArrayList<PlantaVida>();
            Game.spritesheets = new Spritesheets("/[SPRITESHEET]zeldacolne.png");

            Game.player = new Player(0,0,16,16,Game.spritesheets.getSpritesheet(48,0,16,16));
            Game.entities.add(Game.player);


            try {
                Game.world = new World("/mapa.png");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            return;
        }
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
    public void checkEstilingue(){
        for (int i = 0; i < Game.entities.size(); i++) {
            Entity e = Game.entities.get(i);
            if(e instanceof Estilingue) {
                if (Entity.isCollide(this, e)) {
                    armado = true;
                    Game.entities.remove(e);
                }
            }
        }
    }

    /*public boolean isColliding(){

    }*/
    public void render(Graphics g) {
        //g.drawImage(directions,this.getX(),this.getY(),null);
        if (!isDamaged) {
            if (directions == RightDir) {
                g.drawImage(playerRight[curAnimation], x - Camera.x, y - Camera.y, this.width, this.height, null);
                if (armado){
                    g.drawImage(Entity.ESTILINGUELADOS, 14 + x - Camera.x, 8 + y - Camera.y, 3,4, null);

                }
            } else if (directions == LeftDir) {
                g.drawImage(playerLeft[curAnimation], x - Camera.x, y - Camera.y, this.width, this.height, null);
                if (armado){
                    g.drawImage(Entity.ESTILINGUELADOS,x - Camera.x, 8 + y - Camera.y, 3,4, null);
                }
            }
            if (directions == UpDir) {
                g.drawImage(playerUp[curAnimation], x - Camera.x, y - Camera.y, this.width, this.height, null);
                if (armado){
                    g.drawImage(Entity.ESTILINGUEFrenteCostas, 14 + x - Camera.x, 9 + y - Camera.y, 3,4, null);
                }
            } else if (directions == DownDir) {
                g.drawImage(playerDown[curAnimation], x - Camera.x, y - Camera.y, this.width, this.height, null);
                if (armado){
                    g.drawImage(Entity.ESTILINGUEFrenteCostas,x - Camera.x, 9 + y - Camera.y, 3,4, null);
                }
            }
        }else{
            g.drawImage(playerDmg, x - Camera.x, y - Camera.y, this.width, this.height, null);
        }
    }
}
