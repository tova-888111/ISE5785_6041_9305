package geometries;
import primitives.*;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

/***
 * Unit tests for geometries.Plane class
 * @author Tehila Shraga and Tova Tretiak
 */
class PlaneTests {
    /***
     * Default constructor for the PlaneTests class.
     */
    public PlaneTests() {
    }

    /** Point (0,0,0) for testing */
    private final Point p1 = new Point(0, 0, 0);
    /** Point (2,0,0) for testing */
    private final Point p2 = new Point(2, 0, 0);
    /** Point (0,1,0) for testing */
    private final Point p3 = new Point(0, 1, 0);
    /** Point (4,0,0) for testing */
    private final Point p4 = new Point(3, 0, 0);
    /** Point (0,2,0) for testing */
    private final Point p5 = new Point(0, 2, 0);

    /**It is a value that shows the maximum allowed difference between the expected result and the actual result.*/
    private static final double ACCURACY = 0.000001;
    /***
     * Test method for {@link geometries.Plane#Plane(Point, Point, Point)}.
     * This test checks if the plane is created correctly.
     */
    @Test
    void testConstructor() {
        // ============ Equivalence Partitions Tests ==============
        //TC01: Test a plane with correct points
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
        // =============== Boundary Values Tests ==================
        //TC02: Test a plane with two identical points first and second
        assertThrows(IllegalArgumentException.class, () ->new Plane(p1,p1,p2),
                "Constructed a plane with two identical points");
        //TC03: Test a plane with two identical points second and third
        assertThrows(IllegalArgumentException.class, () ->new Plane(p1,p2,p2),
                "Constructed a plane with two identical points");
        //TC04: Test a plane with two identical points first and third
        assertThrows(IllegalArgumentException.class, () ->new Plane(p1,p2,p1),
                "Constructed a plane with two identical points");
        //TC05: Test a plane with three identical points
        assertThrows(IllegalArgumentException.class, () ->new Plane(p1,p1,p1),
                "Constructed a plane with three identical points");
        //TC06: Test a plane with three points that are on the same line
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
        //TC01: Test that the normal of the plane is correct
        Plane plane=new Plane(p1,p2,p3);
        //Two vectors on the plane
        Vector v1=p1.subtract(p2);
        Vector v2=p1.subtract(p3);
        //The normal of the plane
        Vector normal=plane.getNormal(p1);
        assertEquals(1,normal.length(),ACCURACY,"The length of the normal is not 1");
        //Test that the normal of the plane is orthogonal to two vectors on the plane
        assertEquals(0,v1.dotProduct(normal),ACCURACY, "The normal is not orthogonal to the first vector");
        assertEquals(0,v2.dotProduct(normal),ACCURACY, "The normal is not orthogonal to the second vector");
    }

    /***
     * Test method for {@link geometries.Plane#findIntersections(Ray)}.
     * This test checks if the intersection points of the plane and the ray are calculated correctly.
     */
    @Test
    void testFindIntersections() {
        Plane plane =new Plane (p2,p4,p5);
        // ============ Equivalence Partitions Tests ==============
        //Tests when the ray is not parallel or orthogonal to the plane
        //TC01: Test that the ray intersects the plane
        Ray ray1 = new Ray(new Point(0,0,4), new Vector(3,0,-4));
        assertEquals(List.of(p4), plane.findIntersections(ray1), "The ray does not intersect the plane when it is not parallel or orthogonal to the plane");
        //TC02: Test that the ray does not intersect the plane
        Ray ray2 = new Ray(new Point(0,0,4), new Vector(3,0,4));
        assertNull(plane.findIntersections(ray2), "The ray intersects the plane when it is not parallel or orthogonal to the plane");

        // =============== Boundary Values Tests ==================
        //Tests when the ray is parallel to the plane
        //TC03: Test that the ray does not intersect the plane when it is not on the plane
        Ray ray3 = new Ray(new Point(0,0,4), new Vector(3,0,0));
        assertNull(plane.findIntersections(ray3), "The ray intersects the plane when the ray is parallel and not on to the plane");
        //TC04: Test that the ray does not intersect the plane when it is on the plane
        Ray ray4 = new Ray(new Point(6,5,0), new Vector(3,0,0));
        assertNull(plane.findIntersections(ray4), "The ray intersects the plane when the ray is parallel and on to the plane");

        //Tests when the ray is orthogonal to the plane
        //TC05: Test that the ray intersects the plane when it starts in front of the plane
        Ray ray5 = new Ray(new Point(5,0,-7), new Vector(0,0,1));
        assertEquals(List.of(new Point(5,0,0)), plane.findIntersections(ray5), "The ray does not intersect the plane when it is orthogonal and starts in front of the plane");
        //TC06: Test that the ray  does not intersect the plane when it starts behind the plane
        Ray ray6 = new Ray(new Point(5,0,7), new Vector(0,0,1));
        assertNull(plane.findIntersections(ray6), "The ray intersects the plane when it is orthogonal and starts behind the plane");
        //TC07: Test that the ray  does not intersect the plane when it starts on the plane
        Ray ray7 = new Ray(new Point(5,0,0), new Vector(0,0,1));
        assertNull(plane.findIntersections(ray7), "The ray intersects the plane when it is orthogonal and starts on the plane");

        //Tests when the ray is not parallel or orthogonal to the plane
        //TC08: Test that the ray does not intersect the plane when the head of the ray presents the plane
        Ray ray8 = new Ray(new Point(2,0,0), new Vector(1,5,9));
        assertNull(plane.findIntersections(ray8), "The ray intersects the plane when the head of the ray presents the plane and the ray is not parallel or orthogonal to the plane");
        //TC09: Test that the ray does not intersect the plane when the head of the ray is on the plane
        Ray ray9 = new Ray(new Point(5,0,0), new Vector(1,0,9));
        assertNull(plane.findIntersections(ray9), "The ray intersects the plane when the head of the ray is on the plane and the ray is not parallel or orthogonal to the plane");
    }
}