package renderer;

import geometries.Geometries;
import geometries.*;
import geometries.Triangle;
import org.junit.jupiter.api.Test;
import primitives.Color;
import primitives.Point;
import scene.Scene;

import static java.awt.Color.WHITE;

public class FirstMpTests {



    /**The geometries of the scene*/
    Geometries geometries= new Geometries(
            //The flower
            //Up-Yellow
            new Triangle( new Point(-3, 5, -20), new Point(3, 5, -20), new Point(0, 9, -20)).setEmission(new Color(java.awt.Color.YELLOW)),
            new Sphere( 3,new Point(0, 4, -20)).setEmission(new Color(java.awt.Color.YELLOW)),

            //Down-Yellow
            new Triangle( new Point(-3, -5, -20), new Point(3, -5, -20), new Point(0, -9, -20)).setEmission(new Color(java.awt.Color.YELLOW)),
            new Sphere( 3,new Point(0, -4, -20)).setEmission(new Color(java.awt.Color.YELLOW)),

           //Left-Yellow
            new Triangle( new Point(-5, 3, -20), new Point(-5, -3, -20), new Point(-9, 0, -20)).setEmission(new Color(java.awt.Color.YELLOW)),
            new Sphere( 3,new Point(-4, 0, -20)).setEmission(new Color(java.awt.Color.YELLOW)),

            //Right-Yellow
            new Triangle( new Point(5, 3, -20), new Point(5, -3, -20), new Point(9, 0, -20)).setEmission(new Color(java.awt.Color.YELLOW)),
            new Sphere( 3,new Point(4, 0, -20)).setEmission(new Color(java.awt.Color.YELLOW)),

            //Center
            new Sphere( 2.5,new Point(0, 0, -15)).setEmission(new Color(java.awt.Color.BLACK)),

            //Up right-ORANGE
            new Triangle( new Point(6.5,3.5 , -23), new Point(3.5, 6.5, -23), new Point(7.5, 7.5, -23)).setEmission(new Color(255, 255, 150)),
            new Sphere( 2.5,new Point(4, 4, -23)).setEmission(new Color(255, 255, 150)),

            //Up left-ORANGE
            new Triangle( new Point(-6.5, 3.5, -23), new Point(-3.5, 6.5, -23), new Point(-7.5, 7.5, -23)).setEmission(new Color(255, 255, 150)),
            new Sphere( 2.5,new Point(-4, 4, -23)).setEmission(new Color(255, 255, 150)),

            //Down right-ORANGE
            new Triangle( new Point(6.5, -3.5, -23), new Point(3.5, -6.5, -23), new Point(7.5, -7.5, -23)).setEmission(new Color(255, 255, 150)),
            new Sphere( 2.5,new Point(4, -4, -23)).setEmission(new Color(255, 255, 150)),

            //Down left-ORANGE
            new Triangle( new Point(-6.5, -3.5, -23), new Point(-3.5, -6.5, -23), new Point(-7.5, -7.5, -23)).setEmission(new Color(255, 255, 150)),
            new Sphere( 2.5,new Point(-4, -4, -23)).setEmission(new Color(255, 255, 150)),

            //The land
            new Polygon(
                    new Point(-20, -20, -20),
                    new Point(-20, -15, -20),
                    new Point(20, -15, -20),
                    new Point(20, -20, -20)
            ).setEmission(new Color(210, 180, 140)),

            //The stalk
            new Polygon(new Point(-1, 0, -25),
                    new Point(1, 0, -25),
                    new Point(1, -20, -25),
                    new Point(-1, -20, -25)).setEmission(new Color( java.awt.Color.GREEN)),

            //The leave
            new Triangle( new Point(4.5, -15, -25), new Point(2.5, -12, -25), new Point(5.2, -13, -25)).setEmission(new Color(java.awt.Color.GREEN)),
            new Sphere( 2,new Point(2.5, -14, -25)).setEmission(new Color(java.awt.Color.GREEN))
            );

    /**The scene of the camera*/
    Scene scene = new Scene("Test scene").setBackground( new Color(176, 226, 255)).setGeometries(geometries);


    /** Create a camera with a specific location and direction*/
    Camera cam1 = Camera.getBuilder()
            .setLocation(new Point(0, 0, 0))
            .setDirection(new Point(0, 0, -1))
            .setVpSize(20, 20)
            .setVpDistance(10)
            .setResolution(500,500)
            .setRayTracer(scene, RayTracerType.SIMPLE)
            .build();

    @Test
    void test(){
        cam1.renderImage().writeToImage("Trying");
    }
}
