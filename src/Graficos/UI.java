package Graficos;

import Entities.Player;
import com.PatoGames.main.Game;

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
        g.fillRect(x,y,(int)((Game.player.life/Game.player.maxLife)*50),3);

    }
}
