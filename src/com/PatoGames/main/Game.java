package com.PatoGames.main;

import Entities.*;
import Graficos.Spritesheets;
import Graficos.UI;
import World.World;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class Game extends Canvas implements Runnable, KeyListener, MouseListener {

    public static JFrame frame;
    public static final int WIDTH = 240,HEIGHT = 128;

    public final int SCALE = 4;
    private Thread thread;
    private boolean running = true;
    private BufferStrategy bs;

    public static Random rand;

    private final BufferedImage background;

    public static World world;
    public static List<Entity> entities;
    public static List<PlantaVida> plantaVidas;
    public static List<Tiro> Tiros;
    public static Spritesheets spritesheets;
    public static Player player;

    public UI ui;

    public Game() throws IOException {
        rand = new Random();
        addKeyListener(this);
        setPreferredSize(new Dimension(WIDTH*SCALE, HEIGHT*SCALE));
        initFrame();
        ui = new UI();

        background = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
        entities = new ArrayList<Entity>();
        plantaVidas = new ArrayList<PlantaVida>();
        Tiros = new ArrayList<Tiro>();
        spritesheets = new Spritesheets("/[SPRITESHEET]zeldacolne.png");

        player = new Player(0,0,16,16,spritesheets.getSpritesheet(48,0,16,16));
        entities.add(player);


        world = new World("/mapa.png");
    }


    public void initFrame() {

        frame = new JFrame("ZELDATO");
        frame.add(this);
        frame.setResizable(false);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }



    public synchronized void start() {
        thread = new Thread(this);
        thread.start();
        running = true;



    }
    public synchronized void stop() throws InterruptedException {
        thread.join();
        running = false;
    }
    public static void main(String[] args) throws IOException {
        Game game = new Game();
        game.start();
    }

    public void tick(){

        for (int i = 0; i < entities.size(); i++) {
            Entity e = entities.get(i);
            e.tick();
        }
        for (int i = 0; i < Tiros.size(); i++) {
            Tiros.get(i).tick();

        }

    }


    public void render(){
        BufferStrategy bs = this.getBufferStrategy();
        if(bs == null){
            this.createBufferStrategy(3);
            return;
        }
        Graphics g = background.getGraphics();
        g.setColor(Color.BLACK);
        g.fillRect(0, 0,WIDTH,HEIGHT);


        //Graphics2D g2d = (Graphics2D) g;
        world.render(g);
        for (Entity e : entities) {
            e.render(g);
        }
        player.render(g);
        for (int i = 0; i < Tiros.size(); i++) {
            Tiros.get(i).render(g);
        }

        ui.render(g);

        g.dispose();
        g = bs.getDrawGraphics();
        g.drawImage(background, 0, 0,WIDTH*SCALE, HEIGHT*SCALE, null);
        g.setColor(Color.white);
        g.setFont(new Font("Arial", Font.BOLD, 18));
        g.drawString(player.getAmmo() + " AMMO", 62*SCALE, 11*SCALE);

        bs.show();
    }

    @Override
    public void run() {
        long lastTime = System.nanoTime();
        double amountOfTicks = 60.0;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;
        int frames = 0;
        double timer = System.currentTimeMillis();
        requestFocus();

        while (running) {
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            if (delta >= 1) {
                tick();
                render();
                frames++;
                delta--;

            }
            if(System.currentTimeMillis() - timer >= 1000) {
                System.out.println("FPS: " + frames);
                frames = 0;
                timer += 1000;
            }

        }
        try {
            stop();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_W){
            player.up = true;
        }else if(e.getKeyCode() == KeyEvent.VK_S){
            player.down = true;
        }
        if(e.getKeyCode() == KeyEvent.VK_A){
            player.left = true;
        }else if(e.getKeyCode() == KeyEvent.VK_D){
            player.right = true;
        }

        if(e.getKeyCode() == KeyEvent.VK_SHIFT){
            player.shift = true;
        }
        if (e.getKeyCode() == KeyEvent.VK_SPACE){
            player.atirando = true;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_W){
            player.up = false;
        }else if(e.getKeyCode() == KeyEvent.VK_S){
            player.down = false;
        }
        if(e.getKeyCode() == KeyEvent.VK_A){
            player.left = false;
        }else if(e.getKeyCode() == KeyEvent.VK_D){
            player.right = false;
        }
        if(e.getKeyCode() == KeyEvent.VK_SHIFT){
            player.shift = false;
        }

    }


    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}