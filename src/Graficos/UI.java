package Graficos;

import Entities.Player;

import java.awt.*;
import java.awt.image.BufferedImage;

public class UI {
    private int x = 10,y = 8;

    public void render(Graphics g) {
        g.setColor(Color.black);
        g.fillRect(x - 1,y - 1,50+2,5);
        g.setColor(Color.red);
        g.fillRect(x,y,50,3);
        g.setColor(Color.green);
        g.fillRect(x,y,(int)((Player.life/Player.maxLife)*50),3);

    }
}
