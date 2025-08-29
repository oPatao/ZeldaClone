package World;

public class Camera {
    public static int x, y;

    public static int clamp(int xAtual, int xmin, int xmax) {
        if (xAtual < xmin) {
            xAtual = xmin;
        }
        if(xAtual > xmax) {
            xAtual = xmax;
        }
        return xAtual;
    }
}
