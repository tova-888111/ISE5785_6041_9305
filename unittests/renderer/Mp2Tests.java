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
            //The stalk
            // Front side
            new Polygon(new Point(-0.25, 0, -21),
                    new Point(0.25, 0, -21),
                    new Point(0.25, -20, -21),
                    new Point(-0.25, -20, -21))
                    .setEmission(new Color( java.awt.Color.GREEN))
                    .setMaterial(new Material().setKd(0.8).setKs(0.2).setShininess(20)),
            // Back side
            new Polygon(new Point(-0.25, 0, -21.5),
                    new Point(0.25, 0, -21.5),
                    new Point(0.25, -20, -21.5),
                    new Point(-0.25, -20, -21.5))
                    .setEmission(new Color( java.awt.Color.GREEN))
                    .setMaterial(new Material().setKd(0.8).setKs(0.2).setShininess(20)),
            // The left side
            new Polygon(new Point(-0.25, 0, -21),
                    new Point(-0.25, 0, -21.5),
                    new Point(-0.25, -20, -21.5),
                    new Point(-0.25, -20, -21))
                    .setEmission(new Color( java.awt.Color.GREEN))
                    .setMaterial(new Material().setKd(0.8).setKs(0.2).setShininess(20)),
            // The right side
            new Polygon(new Point(0.25, 0, -21),
                    new Point(0.25, 0, -21.5),
                    new Point(0.25, -20, -21.5),
                    new Point(0.25, -20, -21))
                    .setEmission(new Color( java.awt.Color.GREEN))
                    .setMaterial(new Material().setKd(0.8).setKs(0.2).setShininess(20)),

            //The leave
            //The right leave
            new Triangle( new Point(1, -9, -21), new Point(1.5, -11, -21), new Point(3, -10, -21))
                    .setEmission(new Color(java.awt.Color.GREEN))
                    .setMaterial(new Material().setKd(0.8).setKs(0.2).setShininess(20)),
            new Sphere( 1,new Point(1, -10, -21))
                    .setEmission(new Color(java.awt.Color.GREEN))
                    .setMaterial(new Material().setKd(0.8).setKs(0.2).setShininess(20)),
            //The left leave
            new Triangle( new Point(-1, -7, -21), new Point(-1.5, -9, -21), new Point(-3, -8, -21))
                    .setEmission(new Color(java.awt.Color.GREEN))
                    .setMaterial(new Material().setKd(0.8).setKs(0.2).setShininess(20)),
            new Sphere( 1,new Point(-1, -8, -21))
                    .setEmission(new Color(java.awt.Color.GREEN))
                    .setMaterial(new Material().setKd(0.8).setKs(0.2).setShininess(20)),

            //The Vase
            //The front side
            new Polygon(new Point(-2, -18, -18.25),
                    new Point(2, -18, -18.25),
                    new Point(5.5, -14, -18.25),
                    new Point(-5.5, -14, -18.25))
                    .setEmission(new Color( java.awt.Color.DARK_GRAY)),
            //The back side
            new Polygon(new Point(-2, -18, -24.25),
                    new Point(2, -18, -24.25),
                    new Point(5.5, -14, -24.25),
                    new Point(-5.5, -14, -24.25))
                    .setEmission(new Color( java.awt.Color.DARK_GRAY)),
            //The left side
            new Polygon(new Point(-2, -18, -24.25),
                    new Point(-2, -18, -18.25),
                    new Point(-5.5, -14, -18.25),
                    new Point(-5.5, -14, -24.25))
                    .setEmission(new Color( java.awt.Color.DARK_GRAY)),
            //The right side
            new Polygon(new Point(2, -18, -24.25),
                    new Point(2, -18, -18.25),
                    new Point(5.5, -14, -18.25),
                    new Point(5.5, -14, -24.25))
                    .setEmission(new Color( java.awt.Color.DARK_GRAY)),
            //The top
            new Polygon(new Point(-4.5, -15, -24.25),
                    new Point(4.5, -15, -24.25),
                    new Point(4.5, -15, -18.25),
                    new Point(-4.5, -15, -18.25))
                    .setEmission(new Color( 101, 67, 33)),

            //The Window
            new Polygon(new Point(-24, 22, -29),
                    new Point(-24, -20, -29),
                    new Point(24, -20, -29),
                    new Point(24, 22, -29))
                    .setEmission(new Color( 0,0,0))
                    .setMaterial(new Material().setKd(0.1).setKs(0.2).setShininess(3).setKt(0.9)),
            //The Top of the window
            new Polygon(new Point(-32, 22, -29),
                    new Point(32, 22, -29),
                    new Point(32, 32, -29),
                    new Point(-32, 32, -29))
                    .setEmission(new Color( 160, 140, 110)),
            //The Bottom of the window
            new Polygon(new Point(-32, -20, -29),
                    new Point(32, -20, -29),
                    new Point(32, -30, -29),
                    new Point(-32, -30, -29))
                    .setEmission(new Color( 160, 140, 110)),
            //The left side of the window
            new Polygon(new Point(-32, 22, -29),
                    new Point(-32, -20, -29),
                    new Point(-24, -20, -29),
                    new Point(-24, 22, -29))
                    .setEmission(new Color( 160, 140, 110)),
            //The right side of the window
            new Polygon(new Point(32, 22, -29),
                    new Point(32, -20, -29),
                    new Point(24, -20, -29),
                    new Point(24, 22, -29))
                    .setEmission(new Color( 160, 140, 110)),
            //The base
            new Polygon(new Point(-26, -20, 5),
                    new Point(26, -20, 5),
                    new Point(26, -20, -29),
                    new Point(-26, -20, -29))
                    .setEmission(new Color(205, 170, 125)),

            //The flower
            //Up-Yellow
            new Triangle( new Point(-1.5, 2, -26), new Point(1.5, 2, -26), new Point(0, 5, -28))
            .setEmission(new Color(java.awt.Color.YELLOW))
            .setMaterial(new Material().setKd(0.8).setKs(0.2).setShininess(30)),
            new Sphere( 2.5,new Point(0, 1, -24))
            .setEmission(new Color(java.awt.Color.YELLOW))
            .setMaterial(new Material().setKd(0.8).setKs(0.2).setShininess(30)),

            //Down-Yellow
            new Triangle( new Point(-1.5, -3, -16.5), new Point(1.5, -3, -16.5), new Point(0, -5, -15))
            .setEmission(new Color(java.awt.Color.YELLOW))
            .setMaterial(new Material().setKd(0.8).setKs(0.2).setShininess(30)),
            new Sphere( 2.5,new Point(0, -3, -18.5))
           .setEmission(new Color(java.awt.Color.YELLOW))
            .setMaterial(new Material().setKd(0.8).setKs(0.2).setShininess(30)),

            //Left-Yellow
            new Triangle( new Point(-5, -2.5, -20), new Point(-5, -2.5, -25.5), new Point(-8, 0, -21.25))
            .setEmission(new Color(java.awt.Color.YELLOW))
            .setMaterial(new Material().setKd(0.8).setKs(0.2).setShininess(30)),
            new Sphere( 2.5,new Point(-4, -2.5, -22.25))
            .setEmission(new Color(java.awt.Color.YELLOW))
            .setMaterial(new Material().setKd(0.8).setKs(0.2).setShininess(30)),

            //Right-Yellow
            new Triangle( new Point(5, -2.5, -20), new Point(5, -2.5, -25.5), new Point(8, 0, -21.25))
            .setEmission(new Color(java.awt.Color.YELLOW))
            .setMaterial(new Material().setKd(0.8).setKs(0.2).setShininess(30)),
            new Sphere( 2.5,new Point(4, -2.5, -22.25))
            .setEmission(new Color(java.awt.Color.YELLOW))
            .setMaterial(new Material().setKd(0.8).setKs(0.2).setShininess(30)),

            //Center
            new Sphere( 2.5,new Point(0, 0, -21.25))
            .setEmission(new Color(java.awt.Color.BLACK))
            .setMaterial(new Material().setKd(0.7).setKs(0.3).setShininess(50)),

            //The bird - Enhanced flying bird

            //The body (main body)
            new Sphere( 3.2,new Point(-22, 22, -38))
                    .setEmission(new Color(java.awt.Color.GRAY))
                    .setMaterial(new Material().setKd(0.7).setKs(0.3).setShininess(40)),
            //The head
            new Sphere( 1.8,new Point(-23.5, 23, -35))
                    .setEmission(new Color(java.awt.Color.DARK_GRAY))
                    .setMaterial(new Material().setKd(0.7).setKs(0.3).setShininess(40)),
            //Small details - eye
            new Sphere( 0.3,new Point(-22.5, 22.5, -32.5))
                    .setEmission(new Color(java.awt.Color.WHITE))
                    .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(100)),
            ////Small details - beak
            new Triangle( new Point(-25, 24.5, -35), new Point(-25, 23, -35), new Point(-28, 23.5, -35))
                    .setEmission(new Color(java.awt.Color.ORANGE)),
            //The wings
            new Triangle( new Point(-22, 25.2, -38), new Point(-20, 24, -38), new Point(-21, 30, -38))
                    .setEmission(new Color(192, 192, 192))
                    .setMaterial(new Material().setKd(0.2).setKs(0.3).setShininess(40)),
            new Triangle( new Point(-22, 25.2, -38), new Point(-19.5, 23.5, -38), new Point(-17, 27.5, -37))
                    .setEmission(new Color(192, 192, 192))
                    .setMaterial(new Material().setKd(0.2).setKs(0.3).setShininess(40)),
            //The sun
            new Sphere( 8,new Point(26, 26, -37))
                    .setEmission(new Color(java.awt.Color.ORANGE))
                    .setMaterial(new Material().setKd(0.8).setKs(0.2).setShininess(30))
            //The field

    );

    /**The scene of the camera
     The scene is created with a background color and multiple light sources */
    Scene scene = new Scene("Test scene").setBackground( new Color(180, 210, 235)).setGeometries(geometries)
            .setLights(List.of(
                    new PointLight(new Color(80, 60, 60), new Point(-8, 12, 8))
                            .setKl(0.012).setKq(0.0015),
                    new SpotLight(new Color(160, 125, 80), new Point(8, 8, 6), new Vector(-1, -1, -2))
                            .setKl(0.012).setKq(0.0015),
                    new DirectionalLight(new Color(70, 70, 70), new Vector(-0.3, -0.5, -1)),
                    new DirectionalLight(new Color(70, 70, 70), new Vector(0, -1, 0))
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
                .setLocation(new Point(0, 0, 2))
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
