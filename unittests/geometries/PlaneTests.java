package geometries;
import primitives.Point;
import primitives.Vector;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/***
 * Unit tests for geometries.Plane class
 * @author Tehila Shraga and Tova Tretiak
 */
class PlaneTests {
    /***
     * Points for the tests
     */
     Point p1 = new Point(0, 0, 0);
     Point p2 = new Point(2, 0, 0);
     Point p3 = new Point(0, 1, 0);
     Point p4 = new Point(4, 0, 0);
    //It is a value that shows the maximum allowed difference between the expected result and the actual result.
    private static final double ACCURACY = 0.0001;
    /***
     * Test method for {@link geometries.Plane#Plane(Point, Point, Point)}.
     */
    @Test
    void testConstructor() {
        // ============ Equivalence Partitions Tests ==============
        //Test a plane with correct points
        testConstructor1();
        // =============== Boundary Values Tests ==================
        //Test a plane with two identical points
        testConstructor2();
        testConstructor3();
        testConstructor4();
        //Test a plane with three identical points
        testConstructor5();
        //Test a plane with three points that are on the same line
        testConstructor6();
    }

    /***
     * Test a plane with correct points
     */
    @Test
    void testConstructor1() {
        Plane plane1=new Plane(p1,p2,p3);
        //Two vectors on the plane
        Vector v1=p1.subtract(p2);
        Vector v2=p1.subtract(p3);
        //The normal of the plane
        Vector normal=plane1.getNormal(p1);
        assertEquals(1,normal.length(),ACCURACY,"The length of the normal is not 1");
        //Test that the normal of the plane is orthogonal to two vectors on the plane
        assertEquals(0,v1.dotProduct(normal),ACCURACY, "The normal is not orthogonal to the first vector");
        assertEquals(0,v2.dotProduct(normal),ACCURACY, "The normal is not orthogonal to the second vector");
    }
    /***
     * Test a plane with two identical points
     */
    @Test
    void testConstructor2() {
        assertThrows(IllegalArgumentException.class, () ->new Plane(p1,p1,p2),
                "Constructed a plane with two identical points");
    }
    /***
     * Test a plane with two identical points
     */
    @Test
    void testConstructor3() {
        assertThrows(IllegalArgumentException.class, () ->new Plane(p1,p2,p2),
                "Constructed a plane with two identical points");
    }
    /***
     * Test a plane with three identical points
     */
    @Test
    void testConstructor4() {
        assertThrows(IllegalArgumentException.class, () ->new Plane(p1,p1,p1),
                "Constructed a plane with two identical points");
    }
    /***
     * Test a plane with three identical points
     */
    @Test
    void testConstructor5() {
        assertThrows(IllegalArgumentException.class, () ->new Plane(p1,p1,p1),
                "Constructed a plane with three identical points");
    }
    /***
     * Test a plane with three points that are on the same line
     */
    @Test
    void testConstructor6() {
        assertThrows(IllegalArgumentException.class, () ->new Plane(p1,p2,p4),
                "Constructed a plane with three points that are on the same line");
    }

    /***
     * Test method for {@link geometries.Plane#getNormal(Point)}.
     * This test checks if the normal of the plane is calculated correctly.
     */
    @Test
    void testGetNormal() {
        // ============ Equivalence Partitions Tests ==============
        Plane plane1=new Plane(p1,p2,p3);
        //Test that the normal of the plane is correct
        assertEquals(new Vector(0,0,1),plane1.getNormal(p1), "The normal is not correct");
    }
}