package geometries;

import org.junit.jupiter.api.Test;
import primitives.*;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

/***
 * Unit tests for geometries.Cylinder class
 * @author Tehila Shraga and Tova Tretiak
 */
class CylinderTests {
    /***
     * Default constructor for the CylinderTests class.
     */
    public CylinderTests() {
    }

    /***
     * Test method for {@link geometries.Cylinder#Cylinder(double, primitives.Ray, double)}.
     * Test method for constructor.
     */
    @Test
    void testConstructor() {
        // ============ Equivalence Partitions Tests ==============
        //TC01: Test that the cylinder is created correctly
        Cylinder cylinder = new Cylinder(5, new Ray(new Point(0, 0, 0), new Vector(0, 0, 7)), 5);
        assertEquals(5, cylinder.getRadius(), "The radius of the cylinder is incorrect");
        assertEquals(new Ray(new Point(0, 0, 0), new Vector(0, 0, 7)), cylinder.getAxis(), "The axis ray of the cylinder is incorrect");
        assertEquals(5, cylinder.getHeight(), "The height of the cylinder is incorrect");
        // =============== Boundary Values Tests ==================
        //TC02: Test that the cylinder is created with a negative radius
        assertThrows(IllegalArgumentException.class, () -> new Cylinder(-5, new Ray(new Point(0, 0, 0), new Vector(0, 0, 7)), 5),
                "Constructed a cylinder with a negative radius");
        //TC03: Test that the cylinder is created with a zero radius
        assertThrows(IllegalArgumentException.class, () -> new Cylinder(0, new Ray(new Point(0, 0, 0), new Vector(0, 0, 7)), 5),
                "Constructed a cylinder with a zero radius");
        //TC04: Test that the cylinder is created with a negative height
        assertThrows(IllegalArgumentException.class, () -> new Cylinder(5, new Ray(new Point(0, 0, 0), new Vector(0, 0, -7)), -5),
                "Constructed a cylinder with a negative height");
        //TC05: Test that the cylinder is created with a zero height
        assertThrows(IllegalArgumentException.class, () -> new Cylinder(5, new Ray(new Point(0, 0, 0), new Vector(0, 0, 7)), 0),
                "Constructed a cylinder with a zero height");
    }
    /***
     * Test method for {@link geometries.Cylinder#getNormal(primitives.Point)}.
     */
    @Test
    void testGetNormal() {
        Cylinder cylinder = new Cylinder(5, new Ray(new Point(0, 0, 0), new Vector(0, 0, 7)), 5);
        // ============ Equivalence Partitions Tests ==============
        //TC01: Test that the normal of the cylinder is correct when the point is on the side of the cylinder
        assertEquals(new Vector(1, 0, 0), cylinder.getNormal(new Point(5, 0, 2)), "The normal vector is incorrect");
        //TC02: Test that the normal of the cylinder is correct when the point is on the top base of the cylinder
        assertEquals(new Vector(0, 0, 1), cylinder.getNormal(new Point(1, 0, 5)), "The normal vector is incorrect");
        //TC03: Test that the normal of the cylinder is correct when the point is on the bottom base of the cylinder
        assertEquals(new Vector(0, 0, -1), cylinder.getNormal(new Point(1, 0, 0)), "The normal vector is incorrect");
        // =============== Boundary Values Tests ==================
        //TC04: Test that the normal of the cylinder is correct when the point is on the edge of the top base of the cylinder
        assertEquals(new Vector(0, 0, 1), cylinder.getNormal(new Point(5, 0, 5)), "The normal vector is incorrect");
        //TC05: Test that the normal of the cylinder is correct when the point is on the edge of the bottom base of the cylinder
        assertEquals(new Vector(0, 0, -1), cylinder.getNormal(new Point(5, 0, 0)), "The normal vector is incorrect");
        //TC06: Test that the normal of the cylinder is correct when the point is the center of the top base of the cylinder
        assertEquals(new Vector(0, 0, 1), cylinder.getNormal(new Point(0, 0, 5)), "The normal vector is incorrect");
        //TC07: Test that the normal of the cylinder is correct when the point is the center of the bottom base of the cylinder
        assertEquals(new Vector(0, 0, -1), cylinder.getNormal(new Point(0, 0, 0)), "The normal vector is incorrect");
    }
}