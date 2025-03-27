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
    }
}