package gamejam.model;

import gamejam.model.interfaces.Drawable;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Background implements Drawable {

    private int[][] backgroundImage;
    private double width;
    private double height;
    private double canvasWidth;
    private double canvasHeight;

    public Background(Image img, double width, double height, double canvasWidth, double canvasHeight) {
        this.width = width;
        this.height = height;
        this.backgroundImage = new int[(int) height + 1][(int) width + 1];

        this.canvasWidth = canvasWidth;
        this.canvasHeight = canvasHeight;

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                int color = img.getPixelReader().getArgb(i, j);
                backgroundImage[i][j] = color;
            }
        }
    }

    @Override
    public <T> void draw(T... obj) {
        GraphicsContext graphicsContext = (GraphicsContext) obj[0];

        for (int i = 0; i < canvasHeight / height; i++) {
            for (int j = 0; j < canvasWidth / width; j++) {
                graphicsContext.getPixelWriter().setArgb(i, j, backgroundImage[i][j]);
            }
        }
    }
}
