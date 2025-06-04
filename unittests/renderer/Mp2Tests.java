package renderer;

import geometries.Geometries;
import geometries.Polygon;
import geometries.Sphere;
import geometries.Triangle;
import lighting.DirectionalLight;
import lighting.PointLight;
import lighting.SpotLight;
import org.junit.jupiter.api.Test;
import primitives.Color;
import primitives.Material;
import primitives.Point;
import primitives.*;
import scene.Scene;
import java.util.List;


class Mp2Tests {

    /**
     * This is the constructor for the Mp2Tests class.
     */
    public Mp2Tests() {
    }

    /**The geometries of the scene*/
    Geometries geometries= new Geometries(
            //The land
            new Polygon(
                    new Point(-30, -20, -5),
                    new Point(-60, -20, -50),
                    new Point(60, -20, -50),
                    new Point(30, -20, -5)
            ).setEmission(new Color(210, 180, 140)),

            //The stalk
            // Front side
            new Polygon(new Point(-0.5, 0, -21),
                    new Point(0.5, 0, -21),
                    new Point(0.5, -20, -21),
                    new Point(-0.5, -20, -21))
                    .setEmission(new Color( java.awt.Color.GREEN))
                    .setMaterial(new Material().setKd(0.8).setKs(0.2).setShininess(20)),
            // Back side
            new Polygon(new Point(-0.5, 0, -21.5),
                    new Point(0.5, 0, -21.5),
                    new Point(0.5, -20, -21.5),
                    new Point(-0.5, -20, -21.5))
                    .setEmission(new Color( java.awt.Color.GREEN))
                    .setMaterial(new Material().setKd(0.8).setKs(0.2).setShininess(20)),
            // The left side
            new Polygon(new Point(-0.5, 0, -21),
                    new Point(-0.5, 0, -21.5),
                    new Point(-0.5, -20, -21.5),
                    new Point(-0.5, -20, -21))
                    .setEmission(new Color( java.awt.Color.GREEN))
                    .setMaterial(new Material().setKd(0.8).setKs(0.2).setShininess(20)),
            // The right side
            new Polygon(new Point(0.5, 0, -21),
                    new Point(0.5, 0, -21.5),
                    new Point(0.5, -20, -21.5),
                    new Point(0.5, -20, -21))
                    .setEmission(new Color( java.awt.Color.GREEN))
                    .setMaterial(new Material().setKd(0.8).setKs(0.2).setShininess(20)),

            //The leave
            new Triangle( new Point(3.5, -13, -21), new Point(2, -10.5, -21), new Point(4.7, -11, -21))
                    .setEmission(new Color(java.awt.Color.GREEN))
                    .setMaterial(new Material().setKd(0.8).setKs(0.2).setShininess(20)),
            new Sphere( 1.5,new Point(2, -12, -21))
                    .setEmission(new Color(java.awt.Color.GREEN))
                    .setMaterial(new Material().setKd(0.8).setKs(0.2).setShininess(20)),

            //The flower
            //The center of the flower
            //Center
            new Sphere( 2.5,new Point(0, 0, -21.25))
                   .setEmission(new Color(java.awt.Color.BLACK))
                    .setMaterial(new Material().setKd(0.7).setKs(0.3).setShininess(50))

            );

    /**The scene of the camera
     The scene is created with a background color and multiple light sources */
    Scene scene = new Scene("Test scene").setBackground( new Color(176, 226, 255)).setGeometries(geometries)
            .setLights(List.of(
                    new PointLight(new Color(80, 60, 60), new Point(-8, 12, 8))
                            .setKl(0.012).setKq(0.0015),
                    new SpotLight(new Color(160, 125, 80), new Point(8, 8, 6), new Vector(-1, -1, -2))
                            .setKl(0.012).setKq(0.0015),
                    new DirectionalLight(new Color(70, 70, 70), new Vector(-0.3, -0.5, -1))
            ));


    /**
     * This test renders a basic flower scene with a camera.
     * It creates a camera with a specific location and direction,
     * sets the viewport size and distance,
     * sets the resolution,
     * and uses a simple ray tracer.
     * The rendered image is saved with the filename "BasicFlower".
     */
    @Test
    void test1(){
        // Create a camera with a specific location and direction
        Camera cam1 = Camera.getBuilder()
                .setLocation(new Point(0, 0, 0))
                .setDirection(new Point(0, 0, -1))
                .setVpSize(20, 20)
                .setVpDistance(10)
                .setResolution(500,500)
                .setRayTracer(scene, RayTracerType.SIMPLE)
                .build();
        cam1.renderImage().writeToImage("FinalPicture1");
    }

    @Test
    void test2(){
        // Create a camera with a specific location and direction
        Camera cam1 = Camera.getBuilder()
                .setLocation(new Point(0, 13, -10))
                .setDirection(new Vector(0, -1, -1), new Vector(0, 1, -1))
                .setVpSize(20, 20)
                .setVpDistance(10)
                .setResolution(500,500)
                .setRayTracer(scene, RayTracerType.SIMPLE)
                .build();
        cam1.renderImage().writeToImage("FinalPicture2");
    }
}
