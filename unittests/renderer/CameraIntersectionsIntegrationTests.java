package renderer;

import geometries.*;
import org.junit.jupiter.api.Test;
import primitives.*;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
/**
 * Integration tests for the Camera class and its intersection with geometries
 * @author Tehila Shraga and Tova Tretiak
 */
public class CameraIntersectionsIntegrationTests {
    /**
     * The  builder camera used for testing
     * It is a camera with a view plane size of 3x3, resolution of 3x3, and a distance of 1 from the camera to the view plane.
     */
    Camera.Builder builder = Camera.getBuilder()
            .setVpSize(3,3)
            .setVpDistance(1);
    /**
     * The first camera used for testing
     * It is a camera with a location at (0,0,0)
     */
    Camera camera1 = builder.build();
    /**
     * The second camera used for testing
     * It is a camera with a location at (0,0,0.5)
     */
    Camera camera2 = builder.setLocation( new Point(0,0,0.5)).build();

    /**
     * This method checks the number of intersection points between a given intersectable object and a camera.
     * It constructs rays from the camera to the view plane and counts the number of intersection points.
     *
     * @param intersectable The intersectable object (e.g., sphere, plane, triangle) to check for intersections.
     * @param camera The camera used to construct rays.
     * @param expectedSize The expected number of intersection points.
     */
    void checkNumOfIntersections(Intersectable intersectable, Camera camera, int expectedSize,String kindOfGeometry) {
        List<Point> intersections;
        int size=0;
        // Iterate over the view plane pixels
        // The view plane is divided into 3x3 pixels
        for (int i = 0; i<3; i++) {
            for (int j=0; j < 3; j++) {
                Ray ray = camera.constructRay(3,3,j,i);
                intersections = intersectable.findIntersections(ray);
                if (intersections != null) {
                    size += intersections.size();
                }
            }
        }
        // Check the number of intersection points
        assertEquals(expectedSize, size, "Wrong number of intersection points with " + kindOfGeometry);
    }
    /**
     * Default constructor for the CameraIntersectionsIntegrationTests class.
     */
    public CameraIntersectionsIntegrationTests() {
    }

    // Test cases for camera intersections with different geometries

    /**
     * Test method for camera intersections with spheres.
     * It checks the number of intersection points between the camera and different spheres.
     */
    @Test
    void testCameraIntersectionsWithSphere() {
        // Test with sphere at (0,0,-3), radius 1. we expect 2 intersection points
        Sphere sphere1 = new Sphere(1, new Point(0,0,-3));
        // Test with camera at (0,0,0)
        checkNumOfIntersections(sphere1, camera1, 2, "sphere1");

        // Test with sphere at (0,0,-2.5), radius 2.5. we expect 18 intersection points
        Sphere sphere2 = new Sphere(2.5, new Point(0,0,-2.5));
        // Test with camera at (0,0,0.5)
        checkNumOfIntersections(sphere2, camera2, 18, "sphere2");

        // Test with sphere at (0,0,-2), radius 2. we expect 10 intersection points
        Sphere sphere3 = new Sphere(2, new Point(0,0,-2));
        // Test with camera at (0,0,0.5)
        checkNumOfIntersections(sphere3, camera2, 10, "sphere3");

        // Test with sphere at (0,0,-1), radius 4. we expect 9 intersection points
        Sphere sphere4 = new Sphere(4, new Point(0,0,-1));
        // Test with camera at (0,0,0)
        checkNumOfIntersections(sphere4, camera1, 9, "sphere4");

        // Test with sphere at (0,0,1), radius 0.5. we expect 0 intersection points
        Sphere sphere5 = new Sphere(0.5, new Point(0,0,1));
        // Test with camera at (0,0,0)
        checkNumOfIntersections(sphere5, camera1, 0, "sphere5");
    }

    /**
     * Test method for camera intersections with planes.
     * It checks the number of intersection points between the camera and different planes.
     */
    @Test
    void testCameraIntersectionsWithPlane() {

        // Test with plane at z=-5. we expect 9 intersection points
        Plane plane1 = new Plane(new Point(0,0,-5), new Point(1,0,-5), new Point(0,1,-5));
        // Test with camera at (0,0,0)
        checkNumOfIntersections(plane1, camera1, 9, "plane1");

        // Test with plane at (0,1,-3), (0,2,-2.5), (1,1,-3). we expect 9 intersection points
        Plane plane2 = new Plane(new Point(0,1,-3), new Point(0,2,-2.5), new Point(1,1,-3));
        // Test with camera at (0,0,0)
        checkNumOfIntersections(plane2, camera2, 9, "plane2");

        // Test with plane at (0,0,-5), (1,0,-5), (0,1,-4). we expect 6 intersection points
        Plane plane3 = new Plane(new Point(0,0,-5), new Point(1,0,-5), new Point(0,1,-4));
        // Test with camera at (0,0,0)
        checkNumOfIntersections(plane3, camera1, 6, "plane3");
    }

    /**
     * Test method for camera intersections with triangles.
     * It checks the number of intersection points between the camera and different triangles.
     */
    @Test
    void testCameraIntersectionsWithTriangle() {

        // Test with triangle at (0,1,-2), (1,-1,-2), (-1,-1,-2). we expect 1 intersection point
        Triangle triangle1 = new Triangle(new Point(0,1,-2), new Point(1,-1,-2), new Point(-1,-1,-2));
        // Test with camera at (0,0,0)
        checkNumOfIntersections(triangle1, camera1, 1, "triangle1");

        // Test with triangle at (0,20,-2), (1,-1,-2), (-1,-1,-2). we expect 2 intersection points
        Triangle triangle2 = new Triangle(new Point(0,20,-2), new Point(1,-1,-2), new Point(-1,-1,-2));
        // Test with camera at (0,0,0)
        checkNumOfIntersections(triangle2, camera1, 2, "triangle2");
    }
}
