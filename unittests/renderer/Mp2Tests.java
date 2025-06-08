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
            ).setEmission(new Color(210, 180, 140))
                    .setMaterial(new Material().setKd(0.2).setKs(0.2).setShininess(20)),

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

            //Field
            new Polygon(new Point(-7, -7, -16),
                    new Point(-6.5, -7, -16),
                    new Point(-6.5, -20, -16),
                    new Point(-7, -20, -16))
            .setEmission(new Color( java.awt.Color.GREEN))
            .setMaterial(new Material().setKd(0.8).setKs(0.2).setShininess(20)),

            new Polygon(new Point(-15, -2, -30),
                    new Point(-14.5, -2, -30),
                    new Point(-14.5, -20, -30),
                    new Point(-15, -20, -30))
                    .setEmission(new Color( java.awt.Color.GREEN))
                    .setMaterial(new Material().setKd(0.8).setKs(0.2).setShininess(20)),

            new Polygon(new Point(-23, -2, -27),
                    new Point(-22.5, -2, -27),
                    new Point(-22.5, -20, -27),
                    new Point(-23, -20, -27))
            .setEmission(new Color( java.awt.Color.GREEN))
            .setMaterial(new Material().setKd(0.8).setKs(0.2).setShininess(20)),

            new Polygon(new Point(15, -2, -30),
                    new Point(14.5, -2, -30),
                    new Point(14.5, -20, -30),
                    new Point(15, -20, -30))
            .setEmission(new Color( java.awt.Color.GREEN))
            .setMaterial(new Material().setKd(0.8).setKs(0.2).setShininess(20)),


            new Polygon(new Point(10, -5, -18),
                    new Point(10.3, -5, -18),
                    new Point(10.3, -20, -18),
                    new Point(10, -20, -18))
                    .setEmission(new Color(java.awt.Color.GREEN))
                    .setMaterial(new Material().setKd(0.8).setKs(0.2).setShininess(20)),

            new Polygon(new Point(5, -10, -24),
                    new Point(5.3, -10, -24),
                    new Point(5.3, -20, -24),
                    new Point(5, -20, -24))
                    .setEmission(new Color(java.awt.Color.GREEN))
                    .setMaterial(new Material().setKd(0.8).setKs(0.2).setShininess(20)),

            new Polygon(new Point(-10, -3, -19),
                    new Point(-9.7, -3, -19),
                    new Point(-9.7, -20, -19),
                    new Point(-10, -20, -19))
                    .setEmission(new Color(java.awt.Color.GREEN))
                    .setMaterial(new Material().setKd(0.8).setKs(0.2).setShininess(20)),

            new Polygon(new Point(0, -6, -29),
                    new Point(0.3, -6, -29),
                    new Point(0.3, -20, -29),
                    new Point(0, -20, -29))
                    .setEmission(new Color(java.awt.Color.GREEN))
                    .setMaterial(new Material().setKd(0.8).setKs(0.2).setShininess(20)),

            new Polygon(new Point(-12, -6, -18),
                    new Point(-11.7, -6, -18),
                    new Point(-11.7, -20, -18),
                    new Point(-12, -20, -18))
                    .setEmission(new Color(java.awt.Color.GREEN))
                    .setMaterial(new Material().setKd(0.8).setKs(0.2).setShininess(20)),

            new Polygon(new Point(8, -4, -26),
                    new Point(8.4, -4, -26),
                    new Point(8.4, -20, -26),
                    new Point(8, -20, -26))
                    .setEmission(new Color(java.awt.Color.GREEN))
                    .setMaterial(new Material().setKd(0.8).setKs(0.2).setShininess(20)),

            new Polygon(new Point(2, -8, -22),
                    new Point(2.3, -8, -22),
                    new Point(2.3, -20, -22),
                    new Point(2, -20, -22))
                    .setEmission(new Color(java.awt.Color.GREEN))
                    .setMaterial(new Material().setKd(0.8).setKs(0.2).setShininess(20)),

            new Polygon(new Point(-6, -2, -25),
                    new Point(-5.6, -2, -25),
                    new Point(-5.6, -20, -25),
                    new Point(-6, -20, -25))
                    .setEmission(new Color(java.awt.Color.GREEN))
                    .setMaterial(new Material().setKd(0.8).setKs(0.2).setShininess(20)),

            new Polygon(new Point(13, -5, -20),
                    new Point(13.4, -5, -20),
                    new Point(13.4, -20, -20),
                    new Point(13, -20, -20))
                    .setEmission(new Color(java.awt.Color.GREEN))
                    .setMaterial(new Material().setKd(0.8).setKs(0.2).setShininess(20)),

            new Polygon(new Point(-17, -3, -26),
                    new Point(-16.7, -3, -26),
                    new Point(-16.7, -20, -26),
                    new Point(-17, -20, -26))
                    .setEmission(new Color(java.awt.Color.GREEN))
                    .setMaterial(new Material().setKd(0.8).setKs(0.2).setShininess(20)),

            new Polygon(new Point(-10, -6, -22),
                    new Point(-9.7, -6, -22),
                    new Point(-9.7, -20, -22),
                    new Point(-10, -20, -22))
                    .setEmission(new Color(java.awt.Color.GREEN))
                    .setMaterial(new Material().setKd(0.8).setKs(0.2).setShininess(20)),

            new Polygon(new Point(-5, -4, -19),
                    new Point(-4.7, -4, -19),
                    new Point(-4.7, -20, -19), new Point(-5, -20, -19))
                    .setEmission(new Color(java.awt.Color.GREEN))
                    .setMaterial(new Material().setKd(0.8).setKs(0.2).setShininess(20)),

            new Polygon(new Point(3, -7, -28),
                    new Point(3.3, -7, -28),
                    new Point(3.3, -20, -28), new Point(3, -20, -28))
                    .setEmission(new Color(java.awt.Color.GREEN))
                    .setMaterial(new Material().setKd(0.8).setKs(0.2).setShininess(20)),

            new Polygon(new Point(8, -5, -24),
                    new Point(8.3, -5, -24),
                    new Point(8.3, -20, -24),
                    new Point(8, -20, -24))
                    .setEmission(new Color(java.awt.Color.GREEN))
                    .setMaterial(new Material().setKd(0.8).setKs(0.2).setShininess(20)),

            new Polygon(new Point(14, -2, -30),
                    new Point(14.3, -2, -30),
                    new Point(14.3, -20, -30),
                    new Point(14, -20, -30))
                    .setEmission(new Color(java.awt.Color.GREEN))
                    .setMaterial(new Material().setKd(0.8).setKs(0.2).setShininess(20)),

            new Polygon(new Point(17, -6, -21),
                    new Point(17.3, -6, -21),
                    new Point(17.3, -20, -21),
                    new Point(17, -20, -21))
                    .setEmission(new Color(java.awt.Color.GREEN))
                    .setMaterial(new Material().setKd(0.8).setKs(0.2).setShininess(20)),

            new Polygon(new Point(-20, -2, -29),
                    new Point(-19.7, -2, -29),
                    new Point(-19.7, -20, -29),
                    new Point(-20, -20, -29))
                    .setEmission(new Color(java.awt.Color.GREEN))
                    .setMaterial(new Material().setKd(0.8).setKs(0.2).setShininess(20)),

            new Polygon(new Point(-12, -8, -23),
                    new Point(-11.7, -8, -23),
                    new Point(-11.7, -20, -23),
                    new Point(-12, -20, -23))
                    .setEmission(new Color(java.awt.Color.GREEN))
                    .setMaterial(new Material().setKd(0.8).setKs(0.2).setShininess(20)),

            new Polygon(new Point(-8, -5, -27),
                    new Point(-7.7, -5, -27),
                    new Point(-7.7, -20, -27),
                    new Point(-8, -20, -27))
                    .setEmission(new Color(java.awt.Color.GREEN))
                    .setMaterial(new Material().setKd(0.8).setKs(0.2).setShininess(20)),

            new Polygon(new Point(6, -6, -17),
                    new Point(6.3, -6, -17),
                    new Point(6.3, -20, -17),
                    new Point(6, -20, -17))
                    .setEmission(new Color(java.awt.Color.GREEN))
                    .setMaterial(new Material().setKd(0.8).setKs(0.2).setShininess(20)),

            new Polygon(new Point(10, -3, -26),
                    new Point(10.3, -3, -26),
                    new Point(10.3, -20, -26),
                    new Point(10, -20, -26))
                    .setEmission(new Color(java.awt.Color.GREEN))
                    .setMaterial(new Material().setKd(0.8).setKs(0.2).setShininess(20)),

            new Polygon(new Point(0, -8, -25),
                    new Point(0.3, -8, -25),
                    new Point(0.3, -20, -25),
                    new Point(0, -20, -25))
                    .setEmission(new Color(java.awt.Color.GREEN))
                    .setMaterial(new Material().setKd(0.8).setKs(0.2).setShininess(20)),

            new Polygon(new Point(-3, -4, -18),
                    new Point(-2.7, -4, -18),
                    new Point(-2.7, -20, -18),
                    new Point(-3, -20, -18))
                    .setEmission(new Color(java.awt.Color.GREEN))
                    .setMaterial(new Material().setKd(0.8).setKs(0.2).setShininess(20)),

            new Polygon(new Point(12, -2, -27),
                    new Point(12.3, -2, -27),
                    new Point(12.3, -20, -27),
                    new Point(12, -20, -27))
                    .setEmission(new Color(java.awt.Color.GREEN))
                    .setMaterial(new Material().setKd(0.8).setKs(0.2).setShininess(20)),

            new Polygon(new Point(-4, -6, -12),
                    new Point(-3.7, -6, -12),
                    new Point(-3.7, -20, -12),
                    new Point(-4, -20, -12))
                    .setEmission(new Color(java.awt.Color.GREEN))
                    .setMaterial(new Material().setKd(0.8).setKs(0.2).setShininess(20)),

            new Polygon(new Point(2, -8, -14),
                    new Point(2.3, -8, -14),
                    new Point(2.3, -20, -14),
                    new Point(2, -20, -14))
                    .setEmission(new Color(java.awt.Color.GREEN))
                    .setMaterial(new Material().setKd(0.8).setKs(0.2).setShininess(20)),

            new Polygon(new Point(5, -5, -15),
                    new Point(5.3, -5, -15),
                    new Point(5.3, -20, -15),
                    new Point(5, -20, -15))
                    .setEmission(new Color(java.awt.Color.GREEN))
                    .setMaterial(new Material().setKd(0.8).setKs(0.2).setShininess(20)),

            new Polygon(new Point(-6, -4, -13),
                    new Point(-5.6, -4, -13),
                    new Point(-5.6, -20, -13),
                    new Point(-6, -20, -13))
                    .setEmission(new Color(java.awt.Color.GREEN))
                    .setMaterial(new Material().setKd(0.8).setKs(0.2).setShininess(20)),

            new Polygon(new Point(0, -6, -16),
                    new Point(0.3, -6, -16),
                    new Point(0.3, -20, -16),
                    new Point(0, -20, -16))
                    .setEmission(new Color(java.awt.Color.GREEN))
                    .setMaterial(new Material().setKd(0.8).setKs(0.2).setShininess(20))

    );

    /**The scene of the camera
     The scene is created with a background color and multiple light sources */
    Scene scene = new Scene("Test scene").setBackground( new Color(176, 226, 255)).setGeometries(geometries)
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
