package geometries;

import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import static org.junit.jupiter.api.Assertions.*;

/***
 * Unit tests for geometries.Cylinder class
 * @author Tehila Shraga and Tova Tretiak
 */
class CylinderTests {
    /***
     * Test method for {@link geometries.Cylinder#getNormal(primitives.Point)}.
     */
    @Test
    void testGetNormal() {
        // ============ Equivalence Partitions Tests ==============
        //TC01: Test that the normal of the cylinder is correct
        Ray axis = new Ray(new Point(0, 0, 0), new Vector(0, 0, 1));
        Cylinder cylinder = new Cylinder(1, axis, 5);
        Point pointOnSurface = new Point(1, 0, 2);
        Vector normal = cylinder.getNormal(pointOnSurface);
        assertEquals(new Vector(1, 0, 0), normal, "The normal vector is incorrect");
    }
}