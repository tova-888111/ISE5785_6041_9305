package renderer;

import org.junit.jupiter.api.Test;
import primitives.Color;

/**
 * This class contains unit tests for the ImageWriter class.
 * It tests the functionality of writing an image to a file.
 * @author Tehila Shraga and Tova Tretiak
 */
public class ImageWriterTests {

    /**
     * Default constructor for the ImageWriterTests class.
     */
    public ImageWriterTests(){
    }

    /**
     * Test method for {@link renderer.ImageWriter#writeToImage(String)}.
     * This test creates a grid image and saves it to a file.
     * It uses a yellow background and red lines for the grid.
     * The image is saved with the name "test_grid_image2".
     * The image size is 800x500 pixels, with 16 columns and 10 rows.
     */
    @Test
    void writeImageTest(){

        // Create an instance of ImageWriter with the desired image size
        // The image size is set to 800x500 pixels
        int width = 800;
        int height = 500;

        // Set the number of columns and rows for the grid
        int columns = 16;
        int rows = 10;

        // Create an instance of ImageWriter
        ImageWriter writer = new ImageWriter(width, height);

        // Set the background color to yellow
        Color backgroundColor = new Color(java.awt.Color.YELLOW);

        // Set the line color to red
        Color lineColor = new Color(java.awt.Color.RED);

        // Calculate the width and height of each cell in the grid
        int cellWidth = width / columns;
        int cellHeight = height / rows;

        // Loop through each pixel in the image
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                if (x % cellWidth == 0 || y % cellHeight == 0)
                    writer.writePixel(x, y, lineColor);
                else
                    writer.writePixel(x, y, backgroundColor);
            }
        }

        // Save the image to a file
        writer.writeToImage("test_grid_image2");
    }
}
