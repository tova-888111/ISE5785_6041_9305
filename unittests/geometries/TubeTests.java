package geometries;

import primitives.*;
import java.util.List;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/***
 * Unit tests for geometries.Tube class
 * @author Tehila Shraga and Tova Tretiak
 */
class TubeTests {

    // Points and Vectors used many times in the tests
    /** A vector used in some tests */
    Vector v100 = new Vector(1, 0, 0);
    /** A vector used in some tests */
    Point p100 = new Point(1, 0, 0);

    /***
     * Default constructor for the TubeTests class.
     */
    public TubeTests() {
    }

    /***
     * Test method for {@link geometries.Tube#Tube(double, primitives.Ray)}.
     * Test method for constructor.
     */
    @Test
    void testConstructor() {
        // ============ Equivalence Partitions Tests ==============
        //TC01: Test that the tube is created correctly
        Tube tube = new Tube(5, new Ray(new Point(0, 0, 0), new Vector(0, 0, 7)));
        assertEquals(5, tube.getRadius(), "The radius of the tube is incorrect");
        assertEquals(new Ray(new Point(0, 0, 0), new Vector(0, 0, 7)), tube.getAxis(), "The axis ray of the tube is incorrect");
        // =============== Boundary Values Tests ==================
        //TC02: Test that the tube is created with a negative radius
        assertThrows(IllegalArgumentException.class, () -> new Tube(-5, new Ray(new Point(0, 0, 0), new Vector(0, 0, 7))),
                "Constructed a tube with a negative radius");
        //TC03: Test that the tube is created with a zero radius
        assertThrows(IllegalArgumentException.class, () -> new Tube(0, new Ray(new Point(0, 0, 0), new Vector(0, 0, 7))),
                "Constructed a tube with a zero radius");
    }
    /***
     * Test method for {@link geometries.Tube#getNormal(primitives.Point)}.
     */
    @Test
    void testGetNormal() {
        // ============ Equivalence Partitions Tests ==============
        //TC01: Test that the normal of the tube is correct
        Tube tube = new Tube(1, new Ray(new Point(0, 0, 0), new Vector(0, 0, 5)));
        assertEquals(v100, tube.getNormal(new Point(1, 0, 1)), "The normal vector is incorrect");
        // =============== Boundary Values Tests ==================
        //TC02: Test that the normal of the tube is correct when the connection between the point and the head of the ray forms a right angle with the ray.
        assertEquals(v100, tube.getNormal(p100), "The normal vector is incorrect");
    }
    /***
     * Test method for {@link geometries.Tube#findIntersections(primitives.Ray)}.
     * Test method for finding intersections.
     */
    @Test
    void testFindIntersections() {
        /* Tube used for the test */
        Tube tube = new Tube(1, new Ray(p100, new Vector(0, 0, 5)));
        // ============ Equivalence Partitions Tests ==============
        //TC01: Test that the ray intersects the tube, the intersection point is inside the tube
        assertEquals(List.of(new Point(0,0,5),new Point(2,0,5)), tube.findIntersections(new Ray(new Point(-1, 0, 5), v100)),
                "The intersection point is incorrect when the ray intersects the tube inside the tube");
        //TC02: Test that the ray does not intersect the tube
        assertNull(tube.findIntersections(new Ray(new Point(-1, 0, 5), new Vector(-1,0,0) )), "There is intersection point when the ray does not intersect the tube");

    }
}