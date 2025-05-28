package renderer;

import geometries.Geometries;
import geometries.Sphere;
import geometries.Triangle;
import org.junit.jupiter.api.Test;
import primitives.Color;
import primitives.Point;
import scene.Scene;

import static java.awt.Color.WHITE;

public class TryingTests {

    /**The geometries of the scene*/
    Geometries geometries= new Geometries(new Triangle( new Point(-3, 4, -20), new Point(3, 4, -20), new Point(0, 7, -20)).setEmission(new Color(java.awt.Color.YELLOW)),
            new Sphere( 4,new Point(0, 1, -20)).setEmission(new Color(java.awt.Color.YELLOW)));
    /**The scene of the camera*/
    Scene scene = new Scene("Test scene").setBackground( new Color(WHITE)).setGeometries(geometries);


    /** Create a camera with a specific location and direction*/
    Camera cam1 = Camera.getBuilder()
            .setLocation(new Point(0, 0, 0))
            .setDirection(new Point(0, 0, -1))
            .setVpSize(10, 10)
            .setVpDistance(10)
            .setResolution(500,500)
            .setRayTracer(scene, RayTracerType.SIMPLE)
            .build();

    @Test
    void test(){
        cam1.renderImage().writeToImage("Trying");
    }
}
