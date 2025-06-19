package renderer;

import geometries.*;
import lighting.*;
import org.junit.jupiter.api.Test;
import primitives.*;
import scene.Scene;
import java.util.List;

/**
 * This class contains unit tests for rendering a scene with a flower, a vase, and a bird.
 * It uses the Camera class to create a camera that captures the scene from different angles.
 * The scene includes various geometries such as polygons, spheres, and triangles,
 * and is illuminated by multiple light sources.
 */
class Mp2Tests {

    /**
     * This is the constructor for the Mp2Tests class.
     */
    public Mp2Tests() {
    }

    /**The geometries of the scene*/
    Geometries geometries = new Geometries(
            //The stalk
            // Front side
            new Polygon(new Point(-0.25, 0, -17),
                    new Point(0.25, 0, -17),
                    new Point(0.25, -20, -17),
                    new Point(-0.25, -20, -17))
                    .setEmission(new Color( java.awt.Color.GREEN))
                    .setMaterial(new Material().setKd(0.8).setKs(0.2).setShininess(20)),
            // Back side
            new Polygon(new Point(-0.25, 0, -17.5),
                    new Point(0.25, 0, -17.5),
                    new Point(0.25, -20, -17.5),
                    new Point(-0.25, -20, -17.5))
                    .setEmission(new Color( java.awt.Color.GREEN))
                    .setMaterial(new Material().setKd(0.8).setKs(0.2).setShininess(20)),
            // The left side
            new Polygon(new Point(-0.25, 0, -17),
                    new Point(-0.25, 0, -17.5),
                    new Point(-0.25, -20, -17.5),
                    new Point(-0.25, -20, -17))
                    .setEmission(new Color( java.awt.Color.GREEN))
                    .setMaterial(new Material().setKd(0.8).setKs(0.2).setShininess(20)),
            // The right side
            new Polygon(new Point(0.25, 0, -17),
                    new Point(0.25, 0, -17.5),
                    new Point(0.25, -20, -17.5),
                    new Point(0.25, -20, -17))
                    .setEmission(new Color( java.awt.Color.GREEN))
                    .setMaterial(new Material().setKd(0.8).setKs(0.2).setShininess(20)),

            //The leave
            //The right leave
            new Triangle( new Point(1, -9, -17), new Point(1.5, -11, -17), new Point(3, -10, -17))
                    .setEmission(new Color(java.awt.Color.GREEN))
                    .setMaterial(new Material().setKd(0.8).setKs(0.2).setShininess(20)),
            new Sphere( 1,new Point(1, -10, -17))
                    .setEmission(new Color(java.awt.Color.GREEN))
                    .setMaterial(new Material().setKd(0.8).setKs(0.2).setShininess(20)),
            //The left leave
            new Triangle( new Point(-1, -7, -17), new Point(-1.5, -9, -17), new Point(-3, -8, -17))
                    .setEmission(new Color(java.awt.Color.GREEN))
                    .setMaterial(new Material().setKd(0.8).setKs(0.2).setShininess(20)),
            new Sphere( 1,new Point(-1, -8, -17))
                    .setEmission(new Color(java.awt.Color.GREEN))
                    .setMaterial(new Material().setKd(0.8).setKs(0.2).setShininess(20)),

            //The Vase
            //The front side
            new Polygon(new Point(-2, -18, -14.25),
                    new Point(2, -18, -14.25),
                    new Point(5.5, -14, -14.25),
                    new Point(-5.5, -14, -14.25))
                    .setEmission(new Color( java.awt.Color.DARK_GRAY)),
            //The back side
            new Polygon(new Point(-2, -18, -20.25),
                    new Point(2, -18, -20.25),
                    new Point(5.5, -14, -20.25),
                    new Point(-5.5, -14, -20.25))
                    .setEmission(new Color( java.awt.Color.DARK_GRAY)),
            //The left side
            new Polygon(new Point(-2, -18, -20.25),
                    new Point(-2, -18, -14.25),
                    new Point(-5.5, -14, -14.25),
                    new Point(-5.5, -14, -20.25))
                    .setEmission(new Color( java.awt.Color.DARK_GRAY)),
            //The right side
            new Polygon(new Point(2, -18, -20.25),
                    new Point(2, -18, -14.25),
                    new Point(5.5, -14, -14.25),
                    new Point(5.5, -14, -20.25))
                    .setEmission(new Color( java.awt.Color.DARK_GRAY)),
            //The top
            new Polygon(new Point(-4.5, -15, -20.25),
                    new Point(4.5, -15, -20.25),
                    new Point(4.5, -15, -14.25),
                    new Point(-4.5, -15, -14.25))
                    .setEmission(new Color( 101, 67, 33)),

            //The Window
            new Polygon(new Point(-24, 22, -29),
                    new Point(-24, -20, -29),
                    new Point(24, -20, -29),
                    new Point(24, 22, -29))
                    .setEmission(new Color( 0,0,0))
                    .setMaterial(new Material().setKd(0.4).setKs(0.4).setShininess(20).setKt(0.9).setKr(0.01)),

            //The frame of the window
            //The Top of the window
            new Polygon(new Point(-24, 22, -28.75),
                    new Point(24, 22, -28.75),
                    new Point(24, 24, -28.75),
                    new Point(-24, 24, -28.75))
                    .setEmission(new Color( 255, 255, 255)),

            //The left side of the window
            new Polygon(new Point(-23, 24, -28.75),
                    new Point(-23, -20, -28.75),
                    new Point(-25, -20, -28.75),
                    new Point(-25, 24, -28.75))
                    .setEmission(new Color( 255, 255, 255)),

            //The right side of the window
            new Polygon(new Point(23, 24, -28.75),
                    new Point(23, -20, -28.75),
                    new Point(25, -20, -28.75),
                    new Point(25, 24, -28.75))
                    .setEmission(new Color( 255, 255, 255)),
            //The Bottom of the window
            new Polygon(new Point(25, -20, -28.75),
                    new Point(25, -18, -28.75),
                    new Point(-25, -18, -28.75),
                    new Point(-25, -20, -28.75))
                    .setEmission(new Color( 255, 255, 255)),
             //The middle left to right
            new Polygon(new Point(-25, 1, -28.75),
                    new Point(-25, 3, -28.75),
                    new Point(25, 3, -28.75),
                    new Point(25, 1, -28.75))
                    .setEmission(new Color( 255, 255, 255)),
            //The middle up to down
            new Polygon(new Point(-1, 22, -28.75),
                    new Point(1, 22, -28.75),
                    new Point(1, -20, -28.75),
                    new Point(-1, -20, -28.75))
                    .setEmission(new Color( 255, 255, 255)),

             //The wall at the front side of the window
            //The Top of the window
            new Polygon(new Point(-32, 22, -29),
                    new Point(32, 22, -29),
                    new Point(32, 37, -29),
                    new Point(-32, 37, -29))
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

            //the walls at the sides of the window
            //The wall
            new Polygon( new Point(30, 50, -29),
                    new Point(30, 50, -5),
                    new Point(30, -40, -5),
                    new Point(30, -40, -29))
                    .setEmission(new Color(160, 140, 110)),
            new Polygon( new Point(-30, 50, -29),
                    new Point(-30, 50, -5),
                    new Point(-30, -40, -5),
                    new Point(-30, -40, -29))
                    .setEmission(new Color(160, 140, 110)),
            //The base
            new Polygon(new Point(-26, -20, 2),
                    new Point(28, -20, 2),
                    new Point(28, -20, -29),
                    new Point(-26, -20, -29))
                    .setEmission(new Color(205, 170, 125))
                    .setMaterial(new Material().setKd(0.3).setKs(0.3).setShininess(30)),

            //The flower
            //Up-Yellow
            new Triangle( new Point(-2, 4.5, -17), new Point(2, 4.5, -17), new Point(0, 7.5, -16))
                    .setEmission(new Color(java.awt.Color.YELLOW))
                    .setMaterial(new Material().setKd(0.8).setKs(0.2).setShininess(30)),
            new Sphere( 2.5,new Point(0, 3.5, -18))
                    .setEmission(new Color(java.awt.Color.YELLOW))
                    .setMaterial(new Material().setKd(0.8).setKs(0.2).setShininess(30)),

            //Down-Yellow
            new Triangle( new Point(-2, -4.5, -17), new Point(2, -4.5, -17), new Point(0, -7.5, -16))
                    .setEmission(new Color(java.awt.Color.YELLOW))
                    .setMaterial(new Material().setKd(0.8).setKs(0.2).setShininess(30)),
            new Sphere( 2.5,new Point(0, -3.5, -18))
                    .setEmission(new Color(java.awt.Color.YELLOW))
                    .setMaterial(new Material().setKd(0.8).setKs(0.2).setShininess(30)),

            //Left-Yellow
            new Triangle( new Point(-4.5, 2, -17), new Point(-4.5, -2, -17), new Point(-7.5, 0, -16))
                    .setEmission(new Color(java.awt.Color.YELLOW))
                   .setMaterial(new Material().setKd(0.8).setKs(0.2).setShininess(30)),
            new Sphere( 2.5,new Point(-3.5, 0, -18))
                    .setEmission(new Color(java.awt.Color.YELLOW))
                    .setMaterial(new Material().setKd(0.8).setKs(0.2).setShininess(30)),

            //Right-Yellow
            new Triangle( new Point(4.5, 2, -17), new Point(4.5, -2, -17), new Point(7.5, 0, -16))
                    .setEmission(new Color(java.awt.Color.YELLOW))
                    .setMaterial(new Material().setKd(0.8).setKs(0.2).setShininess(30)),
            new Sphere( 2.5,new Point(3.5 , 0, -18))
                    .setEmission(new Color(java.awt.Color.YELLOW))
                    .setMaterial(new Material().setKd(0.8).setKs(0.2).setShininess(30)),

            //Center
            new Sphere( 2.5,new Point(0, 0, -16.75))
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
            new Triangle( new Point(-26, 24, -38), new Point(-38, 24, -38), new Point(-22, 22, -38))
                    .setEmission(new Color(192, 192, 192))
                    .setMaterial(new Material().setKd(0.2).setKs(0.3).setShininess(40)),
            new Triangle( new Point(-23, 21, -34.8), new Point(-19, 21, -34.8), new Point(-15, 17, -33))
                    .setEmission(new Color(192, 192, 192))
                    .setMaterial(new Material().setKd(0.2).setKs(0.3).setShininess(40)),
            //The tail
            new Triangle( new Point(-21, 19, -36), new Point(-19, 21, -38), new Point(-17, 18, -38))
                    .setEmission(new Color(java.awt.Color.GRAY))
                    .setMaterial(new Material().setKd(0.2).setKs(0.3).setShininess(40)),

            //The sun
            new Sphere( 10,new Point(26, 26, -44))
                    .setEmission(new Color(java.awt.Color.ORANGE))
                    .setMaterial(new Material().setKd(1).setKs(1).setShininess(60)),

            //The field
            new Polygon( new Point(-40, -15, -37),
                    new Point(40, -15, -37),
                    new Point(40, -40, -37),
                    new Point(-40, -40, -37))
                    .setEmission(new Color(0, 100, 0)),

            //The purple butterfly
            new Polygon( new Point(10, 5, -10),
                    new Point(9.7, 5, -11),
                    new Point(9.7, 3, -11),
                    new Point(10, 3, -10))
                    .setEmission(new Color(75, 0, 130)),
            new Sphere( 0.8,new Point(10.8, 4.5, -10))
                    .setEmission(new Color(200, 160, 255))
                    .setMaterial(new Material().setKd(0.1).setKs(0.1).setShininess(20)),
            new Sphere( 0.8,new Point(8.9, 4.5, -11))
                    .setEmission(new Color(200, 160, 255))
                    .setMaterial( new Material().setKd(0.1).setKs(0.1).setShininess(20)),
            new Sphere(0.5,new Point(10.4, 3.3, -10))
                    .setEmission(new Color(200, 160, 255))
                    .setMaterial( new Material().setKd(0.1).setKs(0.1).setShininess(20)),
            new Sphere( 0.5,new Point(9.3, 3.3, -11))
                    .setEmission(new Color(200, 160, 255))
            .setMaterial( new Material().setKd(0.1).setKs(0.1).setShininess(20)),

            //The orange butterfly
             new Polygon( new Point(5, 4, -10),
                    new Point(4.7, 4, -11),
                    new Point(4.7, 2, -11),
                    new Point(5, 2, -10))
                    .setEmission(new Color(255, 140, 0)),
            new Sphere( 0.8,new Point(5.8, 3.5, -10))
                    .setEmission(new Color(255, 200, 100))
                    .setMaterial(new Material().setKd(0.1).setKs(0.1).setShininess(20)),
            new Sphere( 0.8,new Point(3.9, 3.5, -11))
                    .setEmission(new Color(255, 200, 100))
                    .setMaterial(new Material().setKd(0.1).setKs(0.1).setShininess(20)),
            new Sphere(0.5,new Point(5.4, 2.3, -10))
                    .setEmission(new Color(255, 200, 100))
                    .setMaterial(new Material().setKd(0.1).setKs(0.1).setShininess(20)),
            new Sphere( 0.5,new Point(4.3, 2.3, -11))
                    .setEmission(new Color(255, 200, 100))
                    .setMaterial(new Material().setKd(0.1).setKs(0.1).setShininess(20)),

            //The pink butterfly
            new Polygon( new Point(9, 2, -10),
                    new Point(8.7, 2, -11),
                    new Point(8.7, 0, -11),
                    new Point(9, 0, -10))
                    .setEmission(new Color(231, 84, 128)),
            new Sphere( 0.8,new Point(9.8, 1.5, -10))
                    .setEmission(new Color(255, 182, 193))
                    .setMaterial(new Material().setKd(0.1).setKs(0.1).setShininess(20)),
            new Sphere( 0.8,new Point(7.9, 1.5, -11))
                    .setEmission(new Color(255, 182, 193))
                    .setMaterial(new Material().setKd(0.1).setKs(0.1).setShininess(20)),
            new Sphere(0.5,new Point(9.4, 0.3, -10))
                    .setEmission(new Color(255, 182, 193))
                    .setMaterial(new Material().setKd(0.1).setKs(0.1).setShininess(20)),
            new Sphere( 0.5,new Point(8.3, 0.3, -11))
                    .setEmission(new Color(255, 182, 193))
                    .setMaterial(new Material().setKd(0.1).setKs(0.1).setShininess(20)),

            //The blue butterfly
            new Polygon( new Point(-8, 4, -11),
                    new Point(-7.7, 4, -12),
                    new Point(-7.7, 2, -12),
                    new Point(-8, 2, -11))
                    .setEmission(new Color(0, 51, 102)),
            new Sphere( 0.8,new Point(-8.8, 3.5, -11))
                    .setEmission(new Color(153, 204, 255))
                    .setMaterial(new Material().setKd(0.1).setKs(0.1).setShininess(20)),
            new Sphere( 0.8,new Point(-6.9, 3.5, -12))
                    .setEmission(new Color(153, 204, 255))
                    .setMaterial(new Material().setKd(0.1).setKs(0.1).setShininess(20)),
            new Sphere(0.5,new Point(-8.4, 2.3, -11))
                    .setEmission(new Color(153, 204, 255))
                    .setMaterial(new Material().setKd(0.1).setKs(0.1).setShininess(20)),
            new Sphere( 0.5,new Point(-7.3, 2.3, -12))
                    .setEmission(new Color(153, 204, 255))
                    .setMaterial(new Material().setKd(0.1).setKs(0.1).setShininess(20)),

            //The  green butterfly
            new Polygon( new Point(-9, 1, -11),
                    new Point(-8.7, 1, -12),
                    new Point(-8.7, -1, -12),
                    new Point(-9, -1, -11))
                    .setEmission(new Color(0, 100, 0)),
            new Sphere( 0.8,new Point(-9.8, 0.5, -11))
                    .setEmission(new Color(144, 238, 144))
                    .setMaterial(new Material().setKd(0.1).setKs(0.1).setShininess(20)),
            new Sphere( 0.8,new Point(-7.9, 0.5, -12))
                    .setEmission(new Color(144, 238, 144))
                    .setMaterial(new Material().setKd(0.1).setKs(0.1).setShininess(20)),
            new Sphere(0.5,new Point(-9.4, -0.7, -11))
                    .setEmission(new Color(144, 238, 144))
                    .setMaterial(new Material().setKd(0.1).setKs(0.1).setShininess(20)),
            new Sphere( 0.5,new Point(-8.3, -0.7, -12))
                    .setEmission(new Color(144, 238, 144))
                    .setMaterial(new Material().setKd(0.1).setKs(0.1).setShininess(20)),

            //The red butterfly
            new Polygon( new Point(10, -4, -7),
                    new Point(9.7, -4, -8),
                    new Point(9.7, -6, -8),
                    new Point(10, -6, -7))
                    .setEmission(new Color(204, 0, 0)),
            new Sphere( 0.8,new Point(10.8, -4.5, -7))
                    .setEmission(new Color(255, 102, 102))
                    .setMaterial(new Material().setKd(0.1).setKs(0.1).setShininess(20)),
            new Sphere( 0.8,new Point(8.9, -4.5, -8))
                    .setEmission(new Color(255, 102, 102))
                    .setMaterial(new Material().setKd(0.1).setKs(0.1).setShininess(20)),
            new Sphere(0.5,new Point(10.4, -5.7, -7))
                    .setEmission(new Color(255, 102, 102))
                    .setMaterial(new Material().setKd(0.1).setKs(0.1).setShininess(20)),
            new Sphere( 0.5,new Point(9.3, -5.7, -8))
                    .setEmission(new Color(255, 102, 102))
                    .setMaterial(new Material().setKd(0.1).setKs(0.1).setShininess(20)),

            //The turkiz  butterfly
            new Polygon( new Point(8, -6.5, -7),
                    new Point(7.7, -6.5, -8),
                    new Point(7.7, -8.5, -8),
                    new Point(8, -8.5, -7))
                    .setEmission(new Color(0, 128, 128)),
            new Sphere( 0.8,new Point(8.8, -7, -7))
                    .setEmission(new Color(175, 238, 238))
                    .setMaterial(new Material().setKd(0.1).setKs(0.1).setShininess(20)),
            new Sphere( 0.8,new Point(6.9, -7, -8))
                    .setEmission(new Color(175, 238, 238))
                    .setMaterial(new Material().setKd(0.1).setKs(0.1).setShininess(20)),
            new Sphere(0.5,new Point(8.4, -8.2, -7))
                    .setEmission(new Color(175, 238, 238))
                    .setMaterial(new Material().setKd(0.1).setKs(0.1).setShininess(20)),
            new Sphere( 0.5,new Point(7.3, -8.2, -8))
                    .setEmission(new Color(175, 238, 238))
                    .setMaterial(new Material().setKd(0.1).setKs(0.1).setShininess(20))
    );

    /**The light sources of the scene
     * The light sources are created with different colors and positions
     * They are used to illuminate the scene and create shadows */
    List<LightSource> lights= List.of(
            new PointLight(new Color(80, 60, 60), new Point(-8, 12, 8))
            .setKl(0.012).setKq(0.0015),
                    new SpotLight(new Color(160, 125, 80), new Point(8, 8, 6), new Vector(-1, -1, -2))
            .setKl(0.012).setKq(0.0015),
                    new DirectionalLight(new Color(70, 70, 70), new Vector(-0.3, -0.5, -1)),
            new DirectionalLight(new Color(70, 70, 70), new Vector(0, -1, 0)),
            new DirectionalLight(new Color(70, 70, 70), new Vector(-1, 0, 1)),
            new DirectionalLight(new Color(70, 70, 70), new Vector(-1, 0, 0)),
            new PointLight(new Color(80, 60, 60), new Point(26, 26, 0))
            .setKl(0.012).setKq(0.0015)
                    );

    /**
     * The scene
     */
    Scene scene = new Scene("Test scene").setBackground( new Color(180, 210, 235)).setGeometries(geometries)
            .setLights(lights);


    /**
     * Test method that renders a simple flower scene with a camera.
     * It uses multithreading to speed up the rendering process.
     * It uses BVH for optimization.
     */
    @Test
    void test1(){//Time: 40 sec 586 ms
        // Create a camera with a specific location and direction
        Camera cam1 = Camera.getBuilder()
                .setLocation(new Point(-1, 0.5, 5))
                .setDirection(new Point(0, 0, -1))
                .setVpSize(20, 20)
                .setVpDistance(10)
                .setResolution(700,700)
                .setRayTracer(scene, RayTracerType.SIMPLE)
                .setDofRays(50)
                .setAperture(0.5)
                .setFocalDistance(23)
                .setMultithreading(-2) // Use all available cores for multithreading
                .enableBVH()// Enable BVH for optimization
                .build();
        cam1.renderImage().writeToImage("FinalPicture1");
    }


    /**
     * Test method that renders a simple flower scene with a camera.
     * It  doesn't use multithreading to speed up the rendering process.
     * It uses BVH for optimization.
     */
    @Test
    void test2(){//Time: 2 min 55 sec
        // Create a camera with a specific location and direction
        Camera cam1 = Camera.getBuilder()
                .setLocation(new Point(-1, 0.5, 5))
                .setDirection(new Point(0, 0, -1))
                .setVpSize(20, 20)
                .setVpDistance(10)
                .setResolution(700,700)
                .setRayTracer(scene, RayTracerType.SIMPLE)
                .setDofRays(50)
                .setAperture(0.5)
                .setFocalDistance(23)
                .enableBVH()// Enable BVH for optimization
                .build();
        cam1.renderImage().writeToImage("FinalPicture2");
    }

    /**
     * Test method that renders a simple flower scene with a camera.
     * It uses multithreading to speed up the rendering process.
     * It doesn't use BVH for optimization.
     */
    @Test
    void test3(){//Time: 1 min 5 sec
        // Create a camera with a specific location and direction
        Camera cam1 = Camera.getBuilder()
                .setLocation(new Point(-1, 0.5, 5))
                .setDirection(new Point(0, 0, -1))
                .setVpSize(20, 20)
                .setVpDistance(10)
                .setResolution(700,700)
                .setRayTracer(scene, RayTracerType.SIMPLE)
                .setDofRays(50)
                .setAperture(0.5)
                .setFocalDistance(23)
                .setMultithreading(-2) // Use all available cores for multithreading
                .build();
        cam1.renderImage().writeToImage("FinalPicture3");
    }

    /**
     * Test method that renders a simple flower scene with a camera.
     * It doesn't use multithreading to speed up the rendering process.
     * It doesn't use BVH for optimization.
     */
    @Test
    void test4(){//Time: 4 min 51 sec
        // Create a camera with a specific location and direction
        Camera cam1 = Camera.getBuilder()
                .setLocation(new Point(-1, 0.5, 5))
                .setDirection(new Point(0, 0, -1))
                .setVpSize(20, 20)
                .setVpDistance(10)
                .setResolution(700,700)
                .setRayTracer(scene, RayTracerType.SIMPLE)
                .setDofRays(50)
                .setAperture(0.5)
                .setFocalDistance(23)
                .build();
        cam1.renderImage().writeToImage("FinalPicture4");
    }

    /**
     * This test renders a more complex flower scene with a camera.
     * It creates a camera with a specific location and direction,
     * sets the viewport size and distance,
     * sets the resolution,
     * uses a simple ray tracer,
     * and applies depth of field (DoF) settings.
     * The rendered image is saved with the filename "FinalPicture2".
     * This test is designed to showcase the camera's ability to capture a scene with depth of field effects.
     */
    @Test
    void test5(){//Time: 22 sec 932 ms
        // Create a camera with a specific location and direction
        Camera cam1 = Camera.getBuilder()
                .setLocation(new Point(0, 13, -10))
                .setDirection(new Vector(0, -1, -1), new Vector(0, 1, -1))
                .setVpSize(20, 20)
                .setVpDistance(10)
                .setResolution(500,500)
                .setRayTracer(scene, RayTracerType.SIMPLE)
                .setDofRays(50)
                .setAperture(0.5)
                .setFocalDistance(14)
                .setMultithreading(-2) // Use all available cores for multithreading
                .enableBVH()// Enable BVH for optimization
                .build();
        cam1.renderImage().writeToImage("FinalPicture5");
    }
}
