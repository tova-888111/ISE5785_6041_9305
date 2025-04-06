package geometries;

import org.junit.jupiter.api.Test;
import primitives.*;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
/***
 * Unit tests for geometries.Sphere class
 * @author Tehila Shraga and Tova Tretiak
 */
class SphereTests {
    // Points and Vectors used many times in the tests
    /** A point used in some tests */
    private final Point  p100 = new Point(1, 0, 0);
    /** A Point used in some tests */
    private final Point p000 = new Point(0, 0, 0);
    /** A vector used in some tests */
    private final Point p01    = new Point(-1, 0, 0);
    /** A vector used in some tests */
    private final Point p02 = new Point(0.5, 0, 0);
    /** A point used in some tests */
    private final Point p03 = new Point(1, 1, 0);
    /** A point used in some tests */
    private final Point  p04 = new Point(1, -1, 0);
    /** A vector used in some tests */
    private final Vector v310   = new Vector(3, 1, 0);
    /** A vector used in some tests */
    private final Vector v110   = new Vector(1, 1, 0);
    /** A vector used in some tests */
    private final Vector v100   = new Vector(1, 0, 0);
    /** A vector used in some tests */
    private final Vector v010 = new Vector(0,1,0);

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
        Sphere sphere = new Sphere(2, p000);
        Point pointOnSurface = new Point(0, 0, 2);
        assertEquals(new Vector(0, 0, 1),sphere.getNormal(pointOnSurface), "The normal vector is incorrect");
    }


    /**
     * Test method for {@link geometries.Sphere#findIntersections(primitives.Ray)}.
     */
    @Test
    public void testFindIntersections() {
        //The sphere used in the tests
        Sphere sphere = new Sphere( 1d,p100);

        // ============ Equivalence Partitions Tests ==============

        // TC01: Ray's line is outside the sphere (0 points)
        assertNull(sphere.findIntersections(new Ray(p01, v110)), "Ray's line out of sphere");

        // TC02: Ray starts before and crosses the sphere (2 points)
        final Point  gp1    = new Point(0.0651530771650466, 0.355051025721682, 0);
        final Point  gp2    = new Point(1.53484692283495, 0.844948974278318, 0);
        final var    exp    = List.of(gp1, gp2);
        final var result1 = sphere.findIntersections(new Ray(p01, v310));
        assertNotNull(result1, "Can't be empty list");
        assertEquals(2, result1.size(), "Wrong number of points");
        assertEquals(exp, result1, "Ray crosses sphere");
        // TC03: Ray starts inside the sphere and goes outside (1 point)
        assertEquals(List.of(new Point(1.411437827766148, 0.911437827766148, 0.0)), sphere.findIntersections(new Ray(new Point(1, 0.5, 0),new Vector(1, 1, 0))), "Ray inside sphere, not toward center, exits at one point");

        // TC04: Ray starts after the sphere (0 points)
        assertNull(sphere.findIntersections(new Ray(new Point(0.5,2,0),v010 )), "Ray's line out of sphere");

        // =============== Boundary Values Tests ==================

        // **** Group 1: Ray's line crosses the sphere (but not the center)

        // TC11: Ray starts at sphere and goes inside (1 Points)
        assertEquals(List.of(new Point(0.5,-0.86602540378443864676,0)),sphere.findIntersections(new Ray(new Point(0.5, 0.86602540378443864676,0), new Vector (0,-1,0))), "Ray's line inside sphere");

        // TC12: Ray starts at sphere and goes outside (0 points)
        assertNull(sphere.findIntersections(new Ray(new Point(0.5, 0.86602540378443864676,0), v010)), "Ray's line out of sphere");

        // **** Group 2: Ray's line goes through the center

        // TC21: Ray starts before the sphere (2 points)
        assertEquals(List.of(p03, p04),sphere.findIntersections(new Ray(new Point(1,2,0), new Vector(0,-1,0))), "Ray's line inside sphere");

        // TC22: Ray starts at sphere and goes inside (1 Points)
        assertEquals(List.of(p04),sphere.findIntersections(new Ray(p03, new Vector(0,-1,0))), "Ray's line inside sphere");

        // TC23: Ray starts inside (1 Points)
        assertEquals(List.of(p04),sphere.findIntersections(new Ray(new Point(1,0.5,0), new Vector(0,-1,0))), "Ray's line inside sphere");


        // TC24: Ray starts at the center (1 Points)
        assertEquals(List.of(new Point(1,1,0)),sphere.findIntersections(new Ray(p100, v010)), "Ray's line inside sphere");

        // TC25: Ray starts at sphere and goes outside (0 points)
        assertNull(sphere.findIntersections(new Ray(p03, v010)), "Ray's line out of sphere");

        // TC26: Ray starts after sphere (0 points)
        assertNull(sphere.findIntersections(new Ray(new Point(1,2,0), v010)), "Ray's line out of sphere");

        // **** Group 3: Ray's line is tangent to the sphere (all tests 0 points)

        // TC31: Ray starts before the tangent point
        assertNull(sphere.findIntersections(new Ray(new Point(0.5,1,0), v100)), "Ray's line out of sphere");

        // TC32: Ray starts at the tangent point
        assertNull(sphere.findIntersections(new Ray(new Point(1,1,0), v100)), "Ray's line out of sphere");

        // TC33: Ray starts after the tangent point
        assertNull(sphere.findIntersections(new Ray(new Point(2,1,0), v100)), "Ray's line out of sphere");

        // **** Group 4: Special cases

        // TC41: Ray's line is outside sphere, ray is orthogonal to ray start to sphere's center line (0 points)
        assertNull(sphere.findIntersections(new Ray(new Point(1,2,0), v100)), "Ray's line out of sphere");

        // TC42: Ray's starts inside, ray is orthogonal to ray start to sphere's center line (1 point)
        assertEquals(List.of(new Point(1.866025403784439,0.5,0)),sphere.findIntersections(new Ray(new Point(1,0.5,0), v100)), "Ray's line inside sphere");

    }
}