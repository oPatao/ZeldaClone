package World;

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

                if(pixels[pixelAtual] == 0xFF6ABE30){
                    //grama simples
                    tiles[xx+ (yy*WIDTH)] = new GramaTile(Tile.TILE_GRAMA_SIMPLES, xx*16, yy*16);
                    
                } else if (pixels[pixelAtual] == 0xFF4B692F) {
                    //grama com flor
                    tiles[xx+ (yy*WIDTH)] = new GramaFlorTile(Tile.TILE_GRAMA_FLOR, xx*16, yy*16);
                    
                } else if (pixels[pixelAtual] == 0xFF000000) {
                    //pedra/parede
                    tiles[xx+ (yy*WIDTH)] = new PedraTile(Tile.TILE_PEDRA, xx*16, yy*16);

                }else if (pixels[pixelAtual] == 0xFF5FCDE4) {
                    //player e grama simples

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
