package World;

import Entities.Entity;
import Entities.Municao;
import Entities.PlantaVida;
import Entities.Slime;
import com.PatoGames.main.Game;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class World {

    private Tile[] tiles;
    public static int WIDTH, HEIGHT;

    public World(String path) throws IOException {
        BufferedImage mapa = ImageIO.read(getClass().getResource(path));

        int[] pixels = new int[mapa.getWidth()*mapa.getHeight()];
        WIDTH = mapa.getWidth();
        HEIGHT = mapa.getHeight();
        tiles = new Tile[mapa.getWidth()*mapa.getHeight()];

        mapa.getRGB(0, 0, mapa.getWidth(), mapa.getHeight(), pixels, 0, mapa.getWidth());

        for(int xx = 0; xx < mapa.getWidth(); xx++){

            for(int yy = 0; yy < mapa.getHeight(); yy++){

                int pixelAtual = pixels[xx + (mapa.getWidth()*yy)];

                if(pixelAtual == 0xFF6ABE30){
                    //grama simples
                    tiles[xx+ (yy*WIDTH)] = new GramaTile(Tile.TILE_GRAMA_SIMPLES, xx*16, yy*16);
                    
                } else if (pixelAtual == 0xFF4B692F) {
                    //grama com flor
                    tiles[xx+ (yy*WIDTH)] = new GramaFlorTile(Tile.TILE_GRAMA_FLOR, xx*16, yy*16);
                    
                } else if (pixelAtual == 0xFF000000) {
                    //pedra/parede
                    tiles[xx+ (yy*WIDTH)] = new PedraTile(Tile.TILE_PEDRA, xx*16, yy*16);

                }else if (pixelAtual == 0xFF5FCDE4) {

                    Game.player.setX(xx*16);
                    Game.player.setY(yy*16);
                    //player e grama simples
                    tiles[xx+ (yy*WIDTH)] = new GramaFlorTile(Tile.TILE_GRAMA_FLOR, xx*16, yy*16);

                } else if (pixelAtual == 0xFFdf7126) {
                    Game.entities.add(new Slime(xx*16,yy*16,16,16, Entity.SLIME_EN));
                    //inimigo
                    tiles[xx+ (yy*WIDTH)] = new GramaFlorTile(Tile.TILE_GRAMA_FLOR, xx*16, yy*16);
                } else if (pixelAtual == 0xFF8a6f30) {
                    //arma
                    tiles[xx+ (yy*WIDTH)] = new GramaFlorTile(Tile.TILE_GRAMA_FLOR, xx*16, yy*16);
                    Game.entities.add(new Slime(xx*16,yy*16,16,16, Entity.ESTILINGUE_EN));

                }else if (pixelAtual == 0xFF524b24) {
                    //munição
                    Game.entities.add(new Municao(xx*16,yy*16,16,16, Entity.AMMO_EN));
                    tiles[xx+ (yy*WIDTH)] = new GramaFlorTile(Tile.TILE_GRAMA_FLOR, xx*16, yy*16);

                }else if (pixelAtual == 0xFFac3232) {
                    //lifePlanta
                    Game.entities.add(new PlantaVida(xx*16,yy*16,16,16, Entity.PLANTAR_CURA_EN));
                    tiles[xx+ (yy*WIDTH)] = new GramaFlorTile(Tile.TILE_GRAMA_FLOR, xx*16, yy*16);
                }
                else {
                    tiles[xx+ (yy*WIDTH)] = new GramaTile(Tile.TILE_GRAMA_SIMPLES, xx*16, yy*16);

                }
            }

        }
    }

    public void render(Graphics g) {
        for(int xx = 0; xx < WIDTH; xx++){
            for(int yy = 0; yy < HEIGHT; yy++){
                Tile tile = tiles[xx + (WIDTH*yy)];
                tile.render(g);
            }
        }

    }
}
