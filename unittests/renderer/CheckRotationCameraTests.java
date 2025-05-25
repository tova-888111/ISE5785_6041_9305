package renderer;

import org.junit.jupiter.api.Test;
import primitives.*;
import geometries.*;
import scene.Scene;

import static java.awt.Color.WHITE;

/**
 * Testing Camera Class for rotation and movement
 * @author Tehila Shraga and Tova Tretiak
 */
public class CheckRotationCameraTests {

    /**
     * Default constructor for the CheckRotationCameraTests class.
     */
    public CheckRotationCameraTests() {
        // Default constructor
    }

    /**The geometries of the scene*/
    Geometries geometries= new Geometries(new Triangle( new Point(-3, 0, -20), new Point(3, 0, -20), new Point(0, 2, -20)).setEmission(new Color(java.awt.Color.pink)));
    /**The scene of the camera*/
    Scene scene = new Scene("Test scene").setBackground( new Color(WHITE)).setGeometries(geometries);


    /** Create a camera with a specific location and direction*/
    Camera cam1 = Camera.getBuilder()
            .setLocation(new Point(0, 0, 0))
            .setDirection(new Point(0, 0, -1))
            .setVpSize(3, 3)
            .setVpDistance(10)
            .setResolution(500,500)
            .setRayTracer(scene, RayTracerType.SIMPLE)
            .build();

    /**
     * Test method for the regular camera.
     */
    @Test
    void testRegularCamera(){
        Camera camRegular = Camera.getBuilder(cam1)
                .build().renderImage().writeToImage("RegularCamera");
    }

    /**
     * Test method for the camera rotated around the Vto axis.
     */
    @Test
    void testRotateAroundVTo(){
        Camera camRotated = Camera.getBuilder(cam1)
                .rotateAroundVTo(45)
                .build().renderImage().writeToImage("RotateAroundVTo");
    }

    /**
     * Test method for the camera rotated p0 with a vector.
     */
    @Test
    void testMoveP0(){
        Camera camMoved = Camera.getBuilder(cam1)
                .moveP0(new Vector(0,0,-4))
                .build().renderImage().writeToImage("MoveP0");
    }
}
