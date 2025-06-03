package renderer;

import geometries.Geometries;
import geometries.*;
import geometries.Triangle;
import lighting.*;
import lighting.SpotLight;
import org.junit.jupiter.api.Test;
import primitives.*;
import primitives.Point;
import scene.Scene;

import java.util.List;

import static java.awt.Color.WHITE;

public class FirstMpTests {
    /**The geometries of the scene*/
    Geometries geometries= new Geometries(
            //The flower
            //Up-Yellow
            new Triangle( new Point(-3, 5, -20), new Point(3, 5, -20), new Point(0, 9, -20))
                    .setEmission(new Color(java.awt.Color.YELLOW))
                    .setMaterial(new Material().setKd(0.8).setKs(0.2).setShininess(30)),
            new Sphere( 3,new Point(0, 4, -20))
                    .setEmission(new Color(java.awt.Color.YELLOW))
                    .setMaterial(new Material().setKd(0.8).setKs(0.2).setShininess(30)),

            //Down-Yellow
            new Triangle( new Point(-3, -5, -20), new Point(3, -5, -20), new Point(0, -9, -20))
                    .setEmission(new Color(java.awt.Color.YELLOW))
                    .setMaterial(new Material().setKd(0.8).setKs(0.2).setShininess(30)),
            new Sphere( 3,new Point(0, -4, -20))
                    .setEmission(new Color(java.awt.Color.YELLOW))
                    .setMaterial(new Material().setKd(0.8).setKs(0.2).setShininess(30)),

            //Left-Yellow
            new Triangle( new Point(-5, 3, -20), new Point(-5, -3, -20), new Point(-9, 0, -20))
                    .setEmission(new Color(java.awt.Color.YELLOW))
                    .setMaterial(new Material().setKd(0.8).setKs(0.2).setShininess(30)),
            new Sphere( 3,new Point(-4, 0, -20))
                    .setEmission(new Color(java.awt.Color.YELLOW))
                    .setMaterial(new Material().setKd(0.8).setKs(0.2).setShininess(30)),

            //Right-Yellow
            new Triangle( new Point(5, 3, -20), new Point(5, -3, -20), new Point(9, 0, -20))
                    .setEmission(new Color(java.awt.Color.YELLOW))
                    .setMaterial(new Material().setKd(0.8).setKs(0.2).setShininess(30)),
            new Sphere( 3,new Point(4, 0, -20))
                    .setEmission(new Color(java.awt.Color.YELLOW))
                    .setMaterial(new Material().setKd(0.8).setKs(0.2).setShininess(30)),

            //Center
            new Sphere( 2.5,new Point(0, 0, -15))
                    .setEmission(new Color(java.awt.Color.BLACK))
                    .setMaterial(new Material().setKd(0.7).setKs(0.3).setShininess(50)),

            //Up right-ORANGE
            new Triangle( new Point(6.5,3.5 , -23), new Point(3.5, 6.5, -23), new Point(7.5, 7.5, -23))
                    .setEmission(new Color(255, 255, 150))
                    .setMaterial(new Material().setKd(0.2).setKs(0.2).setShininess(30)),
            new Sphere( 2.5,new Point(4, 4, -23))
                    .setEmission(new Color(255, 255, 150))
                    .setMaterial(new Material().setKd(0.2).setKs(0.2).setShininess(30)),

            //Up left-ORANGE
            new Triangle( new Point(-6.5, 3.5, -23), new Point(-3.5, 6.5, -23), new Point(-7.5, 7.5, -23))
                    .setEmission(new Color(255, 255, 150))
                    .setMaterial(new Material().setKd(0.2).setKs(0.2).setShininess(30)),
            new Sphere( 2.5,new Point(-4, 4, -23))
                    .setEmission(new Color(255, 255, 150))
                    .setMaterial(new Material().setKd(0.2).setKs(0.2).setShininess(30)),

            //Down right-ORANGE
            new Triangle( new Point(6.5, -3.5, -23), new Point(3.5, -6.5, -23), new Point(7.5, -7.5, -23))
                    .setEmission(new Color(255, 255, 150))
                    .setMaterial(new Material().setKd(0.2).setKs(0.2).setShininess(30)),
            new Sphere( 2.5,new Point(4, -4, -23))
                    .setEmission(new Color(255, 255, 150))
                    .setMaterial(new Material().setKd(0.2).setKs(0.2).setShininess(30)),

            //Down left-ORANGE
            new Triangle( new Point(-6.5, -3.5, -23), new Point(-3.5, -6.5, -23), new Point(-7.5, -7.5, -23))
                    .setEmission(new Color(255, 255, 150))
                    .setMaterial(new Material().setKd(0.2).setKs(0.2).setShininess(30)),
            new Sphere( 2.5,new Point(-4, -4, -23))
                    .setEmission(new Color(255, 255, 150))
                    .setMaterial(new Material().setKd(0.2).setKs(0.2).setShininess(30)),

            //The land
            new Polygon(
                    new Point(-20, -20, -17),
                    new Point(-20, -14, -17),
                    new Point(20, -14, -17),
                    new Point(20, -20, -17)
            ).setEmission(new Color(210, 180, 140)),

            //The stalk
            new Polygon(new Point(-1, 0, -21),
                    new Point(1, 0, -21),
                    new Point(1, -20, -21),
                    new Point(-1, -20, -21))
                    .setEmission(new Color( java.awt.Color.GREEN))
                    .setMaterial(new Material().setKd(0.8).setKs(0.2).setShininess(20)),

            //The leave
            new Triangle( new Point(4.5, -15, -21), new Point(2.5, -12, -21), new Point(5.2, -13, -21))
                    .setEmission(new Color(java.awt.Color.GREEN))
                    .setMaterial(new Material().setKd(0.8).setKs(0.2).setShininess(20)),
            new Sphere( 2,new Point(2.5, -14, -21))
                    .setEmission(new Color(java.awt.Color.GREEN))
                    .setMaterial(new Material().setKd(0.8).setKs(0.2).setShininess(20)),

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
                    .setMaterial(new Material().setKd(0.2).setKs(0.3).setShininess(40))
    );

    /**The scene of the camera*/
    Scene scene = new Scene("Test scene").setBackground( new Color(176, 226, 255)).setGeometries(geometries)
            .setLights(List.of(
                    new PointLight(new Color(80, 60, 60), new Point(-8, 12, 8))
                            .setKl(0.012).setKq(0.0015),
                    new SpotLight(new Color(160, 125, 80), new Point(8, 8, 6), new Vector(-1, -1, -2))
                            .setKl(0.012).setKq(0.0015),
                    new DirectionalLight(new Color(70, 70, 70), new Vector(-0.3, -0.5, -1))
            ));


    @Test
    void test1(){
        /** Create a camera with a specific location and direction*/
        Camera cam1 = Camera.getBuilder()
                .setLocation(new Point(0, 0, 0))
                .setDirection(new Point(0, 0, -1))
                .setVpSize(20, 20)
                .setVpDistance(10)
                .setResolution(500,500)
                .setRayTracer(scene, RayTracerType.SIMPLE)
                .build();
        cam1.renderImage().writeToImage("BasicFlower");
    }

    @Test
    void test2() {
        /** Create a camera with a specific location and direction*/
        Camera cam2 = Camera.getBuilder()
                .setLocation(new Point(0, 0, 0))
                .setDirection(new Point(0, 0, -1))
                .setVpSize(20, 20)
                .setVpDistance(10)
                .setResolution(500, 500)
                .setRayTracer(scene, RayTracerType.SIMPLE)
                .setDofRays(5)
                .setAperture(0.5)
                .setFocalDistance(17)
                .build();
        cam2.renderImage().writeToImage("ImprovedFlower");
    }
}
