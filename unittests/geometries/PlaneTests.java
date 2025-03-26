package geometries;
import primitives.Point;
import primitives.Vector;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PlaneTests {
     Point p1 = new Point(0, 0, 0);
     Point p2 = new Point(1, 0, 0);
     Point p3 = new Point(0, 1, 0);
     Point p4 = new Point(2, 0, 0);
    @Test
    void testConstructor() {
        // ============ Equivalence Partitions Tests ==============
        // TC01: Test that the constructor throws an exception when the points are on the same line
        assertThrows(IllegalArgumentException.class, () -> new Plane(p1,p2,p4), "Constructed a plane with points on the same line");

    }
    @Test
    void testGetNormal() {
        // ============ Equivalence Partitions Tests ==============
        // TC01: Test that the normal of a plane is calculated correctly
        Plane plane1 = new Plane(new Point(0, 0, 0), new Vector(1, 0, 0));
        assertEquals(new Vector(1, 0, 0), plane1.getNormal(new Point(0,0,0)), "getNormal() wrong result");
        Plane plane2 = new Plane(new Point(0, 0, 0), new Point(1, 0, 0), new Point(0,1,0));
        assertEquals(new Vector(0, 0, 1), plane2.getNormal(new Point(0,0,0)), "getNormal() wrong result");
    }
}