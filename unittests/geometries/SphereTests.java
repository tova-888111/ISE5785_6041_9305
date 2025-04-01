package geometries;

import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Vector;

import static org.junit.jupiter.api.Assertions.*;
/***
 * Unit tests for geometries.Sphere class
 * @author Tehila Shraga and Tova Tretiak
 */
class SphereTests {
    /***
     * Default constructor for the SphereTests class.
     */
    public SphereTests() {
    }

    /***
     * Test method for {@link geometries.Sphere#Sphere(double, primitives.Point)}.
     * Test method for constructor.
     */
    @Test
    void testConstructor() {
        // ============ Equivalence Partitions Tests ==============
        //TC01: Test that the sphere is created correctly
        Point center = new Point(0, 0, 0);
        Sphere sphere = new Sphere(2, center);
        assertEquals(2, sphere.getRadius(), "The radius of the sphere is incorrect");
        assertEquals(center, sphere.getCenter(), "The center point of the sphere is incorrect");
        // =============== Boundary Values Tests ==================
        //TC02: Test that the sphere is created with a negative radius
        assertThrows(IllegalArgumentException.class, () -> new Sphere(-2, center), "Constructed a sphere with a negative radius");
        //TC03: Test that the sphere is created with a zero radius
        assertThrows(IllegalArgumentException.class, () -> new Sphere(0, center), "Constructed a sphere with a zero radius");
    }
    /***
     * Test method for {@link geometries.Sphere#getNormal(primitives.Point)}.
     * Test method for getNormal() method.
     */
    @Test
    void testGetNormal() {
        // ============ Equivalence Partitions Tests ==============
        //TC01: Test that the normal of the sphere is correct
        Point center = new Point(0, 0, 0);
        Sphere sphere = new Sphere(2, center);
        Point pointOnSurface = new Point(0, 0, 2);
        assertEquals(new Vector(0, 0, 1),sphere.getNormal(pointOnSurface), "The normal vector is incorrect");
        // =============== Boundary Values Tests ==================
        //TC02: Test that the normal of the sphere is correct when the point is on the opposite side of the sphere
        Point oppositePoint = new Point(0, 0, -2);
        assertEquals(new Vector(0, 0, -1),sphere.getNormal(oppositePoint), "The normal vector is incorrect");
        }
    }