package renderer;

import org.junit.jupiter.api.Test;
import primitives.Color;


public class ImageWriterTests {

    /**
     * Default constructor for the ImageWriterTests class.
     */
    public ImageWriterTests(){
    }

    /**
     * Test method for {@link renderer.ImageWriter#writeToImage(String)}.
     * This test creates a grid image and saves it to a file.
     * It uses a light blue background and black lines for the grid.
     * The image is saved with the name "test_grid_image".
     * The image size is 800x500 pixels, with 16 columns and 10 rows.
     */
    @Test
    void writeImageTest(){

        int width = 800;
        int height = 500;
        int columns = 16;
        int rows = 10;

        ImageWriter writer = new ImageWriter(width, height);

        Color backgroundColor = new Color(173, 216, 230); // light blue

        Color lineColor = new Color(0, 0, 0); // black

        int cellWidth = width / columns;
        int cellHeight = height / rows;

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                if (x % cellWidth == 0 || y % cellHeight == 0)
                    writer.writePixel(x, y, lineColor);
                else
                    writer.writePixel(x, y, backgroundColor);
            }
        }

        writer.writeToImage("test_grid_image");
    }
}
