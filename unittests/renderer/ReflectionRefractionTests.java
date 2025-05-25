package renderer;

import static java.awt.Color.*;

import org.junit.jupiter.api.Test;

import geometries.*;
import lighting.*;
import primitives.*;
import scene.Scene;

import java.util.List;

/**
 * Tests for reflection and transparency functionality, test for partial
 * shadows
 * (with transparency)
 * @author Dan Zilberstein
 */
class ReflectionRefractionTests {
   /** Default constructor to satisfy JavaDoc generator */
   ReflectionRefractionTests() { /* to satisfy JavaDoc generator */ }

   /** Scene for the tests */
   private final Scene          scene         = new Scene("Test scene");
   /** Camera builder for the tests with triangles */
   private final Camera.Builder cameraBuilder = Camera.getBuilder()     //
      .setRayTracer(scene, RayTracerType.SIMPLE);

   /** Produce a picture of a sphere lighted by a spotLight */
   @Test
   void twoSpheres() {
      scene.geometries.add( //
                           new Sphere(50d,new Point(0, 0, -50)).setEmission(new Color(BLUE)) //
                              .setMaterial(new Material().setKD(0.4).setKS(0.3).setShininess(100).setKT(0.3)), //
                           new Sphere(25d, new Point(0, 0, -50)).setEmission(new Color(RED)) //
                              .setMaterial(new Material().setKD(0.5).setKS(0.5).setShininess(100))); //
      scene.lights.add( //
                       new SpotLight(new Color(1000, 600, 0), new Point(-100, -100, 500), new Vector(-1, -1, -2)) //
                          .setKl(0.0004).setKq(0.0000006));

      cameraBuilder
         .setLocation(new Point(0, 0, 1000)) //
         .setDirection(Point.ZERO, Vector.AXIS_Y) //
         .setVpDistance(1000).setVpSize(150, 150) //
         .setResolution(500, 500) //
         .build() //
         .renderImage() //
         .writeToImage("refractionTwoSpheres");
   }

   /** Produce a picture of a sphere lighted by a spotLight */
   @Test
   void twoSpheresOnMirrors() {
      scene.geometries.add( //
                           new Sphere(400d, new Point(-950, -900, -1000)).setEmission(new Color(0, 50, 100)) //
                              .setMaterial(new Material().setKD(0.25).setKS(0.25).setShininess(20) //
                                 .setKT(new Double3(0.5, 0, 0))), //
                           new Sphere(200d, new Point(-950, -900, -1000)).setEmission(new Color(100, 50, 20)) //
                              .setMaterial(new Material().setKD(0.25).setKS(0.25).setShininess(20)), //
                           new Triangle(new Point(1500, -1500, -1500), new Point(-1500, 1500, -1500), //
                                        new Point(670, 670, 3000)) //
                              .setEmission(new Color(20, 20, 20)) //
                              .setMaterial(new Material().setKR(1)), //
                           new Triangle(new Point(1500, -1500, -1500), new Point(-1500, 1500, -1500), //
                                        new Point(-1500, -1500, -2000)) //
                              .setEmission(new Color(20, 20, 20)) //
                              .setMaterial(new Material().setKR(new Double3(0.5, 0, 0.4))));
      scene.setAmbientLight(new AmbientLight(new Color(26, 26, 26)));
      scene.lights.add(new SpotLight(new Color(1020, 400, 400), new Point(-750, -750, -150), new Vector(-1, -1, -4)) //
         .setKl(0.00001).setKq(0.000005));

      cameraBuilder
         .setLocation(new Point(0, 0, 10000)) //
         .setDirection(Point.ZERO, Vector.AXIS_Y) //
         .setVpDistance(10000).setVpSize(2500, 2500) //
         .setResolution(500, 500) //
         .build() //
         .renderImage() //
         .writeToImage("reflectionTwoSpheresMirrored");
   }

   /**
    * Produce a picture of two triangles lighted by a spotLight with a
    * partially
    * transparent Sphere producing partial shadow
    */
   @Test
   void trianglesTransparentSphere() {
      scene.geometries.add(
                           new Triangle(new Point(-150, -150, -115), new Point(150, -150, -135),
                                        new Point(75, 75, -150))
                              .setMaterial(new Material().setKD(0.5).setKS(0.5).setShininess(60)),
                           new Triangle(new Point(-150, -150, -115), new Point(-70, 70, -140), new Point(75, 75, -150))
                              .setMaterial(new Material().setKD(0.5).setKS(0.5).setShininess(60)),
                           new Sphere(30d,new Point(60, 50, -50)).setEmission(new Color(BLUE))
                              .setMaterial(new Material().setKD(0.2).setKS(0.2).setShininess(30).setKT(0.6)));
      scene.setAmbientLight(new AmbientLight(new Color(38, 38, 38)));
      scene.lights.add(
                       new SpotLight(new Color(700, 400, 400), new Point(60, 50, 0), new Vector(0, 0, -1))
                          .setKl(4E-5).setKq(2E-7));

      cameraBuilder
         .setLocation(new Point(0, 0, 1000)) //
         .setDirection(Point.ZERO, Vector.AXIS_Y) //
         .setVpDistance(1000).setVpSize(200, 200) //
         .setResolution(600, 600) //
         .build() //
         .renderImage() //
         .writeToImage("refractionShadow");
   }

   /**
    * Produce a picture of triangle, sphere, polygon lighted by a spotLight with a Sphere
    * producing a shading
    */
   @Test
   void colorfulGeometries() {
      Geometries geometries = new Geometries(
              // Sphere
              new Sphere(1.5, new Point(-2, 0, -10))
                      .setEmission(new Color(220, 20, 20))
                      .setMaterial(new Material()
                              .setKD(0.6).setKS(0.4).setShininess(100)
                              .setKT(0.1)
                      ),

              // Triangle
              new Triangle(
                      new Point(3.5, 2, -12),
                      new Point(1, -2, -12),
                      new Point(6, -2, -12)
              ).setEmission(new Color(255, 180, 0))
                      .setMaterial(new Material()
                              .setKD(0.7).setKS(0.3).setShininess(60)
                      ),

              // Rectangle
              new Polygon(
                      new Point(-30, -3, -40),
                      new Point(30, -3, -40),
                      new Point(30, -3, 10),
                      new Point(-30, -3, 10)
              ).setEmission(new Color(60, 60, 60))
                      .setMaterial(new Material()
                              .setKD(0.7).setKS(0.2).setShininess(100).setKR(0.5)
                      )
      );

      // Create the scene with a background color and ambient light
      Scene scene = new Scene("Colorful Geometries")
              .setBackground(new Color(150, 200, 255))
              .setAmbientLight(new AmbientLight(new Color(10, 10, 10)))
              .setGeometries(geometries)
              .setLights(List.of(
                      new PointLight(new Color(100, 60, 60), new Point(-10, 15, 10))
                              .setKl(0.01).setKq(0.001),
                      new SpotLight(new Color(255, 220, 100), new Point(10, 10, 5), new Vector(-2, -2, -3))
                              .setKl(0.01).setKq(0.001),
                      new DirectionalLight(new Color(80, 80, 80), new Vector(-1, -1, -1))
              ));

      // Create the camera with specified parameters
      Camera camera = Camera.getBuilder()
              .setLocation(new Point(0, 0, 0))
              .setDirection(new Point(0, 0, -1))
              .setVpSize(10, 10)
              .setVpDistance(10)
              .setResolution(800, 800)
              .setRayTracer(scene, RayTracerType.SIMPLE)
              .build();

      // Render the scene and save the image
      camera.renderImage().writeToImage("multiGeometries");
   }

}
