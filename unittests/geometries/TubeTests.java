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
    Vector v001 = new Vector(0, 0, 1);
    /** A vector used in some tests */
    Vector v01 = new Vector(0, 0, -1);
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
        Tube tube = new Tube(1, new Ray(p100, v001));

        // ============ Equivalence Partitions Tests ==============

        //TC01: Test that the ray intersects the tube (two points)
        assertEquals(List.of(new Point(0,0,5),new Point(1,-1,7)),tube.findIntersections(new Ray(new Point(-1,1,3),new Vector(1,-1,2))),"The intersection point is incorrect");

        //TC02: Test that the ray does not intersect the tube, if we change the direction of the ray it will intersect
        assertNull(tube.findIntersections(new Ray(new Point(-1, 0, 5), new Vector(0,-1,3))), "There is intersection point");

        //TC03: Test that the ray does not intersect the tube,it will never intersect the tube
        assertNull(tube.findIntersections(new Ray(new Point(-2,0,3), new Vector(1,1,0))),"The ray does not intersect the tube,it will never intersect the tube");

        //TC04: Test that the ray intersects the tube one point when it starts inside the tube
        // assertEquals(List.of(new Point()),tube.findIntersections(new Ray(new Point(1.1,0.2,0), new Vector(1,1,0))),"The ray does not intersect the tube one point when it starts inside the tube");



        // =============== Boundary Values Tests ==================

        // Tests for the ray that is parallel to the tube

        //TC05: Test that the ray does not intersect the tube, when the ray is parallel to the tube out of the tube with its center ray direction
        assertNull(tube.findIntersections(new Ray(new Point(-1, 0, 5), v001 )), "There is  intersection points when the ray is parallel to the tube but out of the tube with its center ray direction");

        //TC06: Test that the ray does not intersect the tube, when the ray is parallel to the tube out of the tube with its opposite center ray direction
        assertNull(tube.findIntersections(new Ray(new Point(-1, 0, 5),v01  )), "There is  intersection points when the ray is parallel to the tube but out of the tube with its opposite center ray direction");

        //TC07: Test that the ray does not intersect the tube, when the ray is parallel to the tube and inside the tube, not on its center ray with its direction
        assertNull(tube.findIntersections(new Ray(new Point(0.5, 0, 5), v001 )), "There is intersection points when the ray is parallel to the tube but inside the tube not on its center ray with its direction");

        //TC08: Test that the ray does not intersect the tube, when the ray is parallel to the tube and inside the tube, not on its center ray with its opposite direction
        assertNull(tube.findIntersections(new Ray(new Point(0.5, 0, 5), v01 )), "There is intersection points when the ray is parallel to the tube but inside the tube not on its center ray with its opposite direction");

        //TC09: Test that the ray does not intersect the tube, when the ray is parallel to the tube and inside the tube, on its center ray with its direction
        assertNull(tube.findIntersections(new Ray(new Point(1, 0, 5), v001 )), "There is intersection points when the ray is parallel to the tube but inside the tube on its center ray with its direction");

        //TC10: Test that the ray does not intersect the tube, when the ray is parallel to the tube and inside the tube, on its center ray with its opposite direction
        assertNull(tube.findIntersections(new Ray(new Point(1, 0, 5), v01 )), "There is intersection points when the ray is parallel to the tube but inside the tube on its center ray with its opposite direction");

        //TC11: Test that the ray does not intersect the tube, when the ray is parallel to the tube on its surface with its center ray direction
        assertNull(tube.findIntersections(new Ray(new Point(2, 0, 5), v001 )), "There is intersection points when the ray is parallel to the tube but on its surface with its center ray direction");

        //TC12: Test that the ray does not intersect the tube, when the ray is parallel to the tube on its surface with its opposite center ray direction
        assertNull(tube.findIntersections(new Ray(new Point(2, 0, 5), v01 )), "There is intersection points when the ray is parallel to the tube but on its surface with its opposite center ray direction");

        //Tests when the ray or its continue go through the head of the ray center of the tube

        //TC13: Test that the ray intersects the tube one point, when the ray orthogonal ot its ray and starts on the head of its ray
        assertEquals(List.of(new Point(0,1,0)), tube.findIntersections(new Ray(p100, new Vector(0, 1, 0))),
                "The intersection point is incorrect when the ray orthogonal to the tube and starts on the head of its ray");

        //TC14: Test that the ray intersects the tube two points when the ray orthogonal to its ray and starts before the tube
        assertEquals(List.of(new Point(0,0,0),new Point(2,0,0)), tube.findIntersections(new Ray(new Point(-1, 0, 0), v100)),
                "The intersection point is incorrect when the ray intersects the tube inside the tube and it is orthogonal to the tube");

        //TC15: Test that the ray intersects the tube one point when the ray orthogonal to its ray and the ray starts on the tube
        assertEquals(List.of(new Point(2,0,0)), tube.findIntersections(new Ray(new Point(0, 0, 0), v100)),
                "The intersection point is incorrect when the ray intersects the tube, the ray starts on the tube and it is orthogonal to the tube");

        //TC16: Test that the ray intersects the tube one point when the ray orthogonal to its ray and the ray starts inside the tube in front of its center ray
        assertEquals(List.of(new Point(2,0,0)), tube.findIntersections(new Ray(new Point(0.5, 0, 0), v100)),
                "The intersection point is incorrect when the ray intersects the tube, the ray starts inside the tube in front of its center ray and it is orthogonal to the tube");

        //TC17: Test that the ray intersects the tube one point when the ray orthogonal to its ray and the ray starts inside the tube behind its center ray
        assertEquals(List.of(new Point(2,0,0)), tube.findIntersections(new Ray(new Point(1.5, 0, 0), v100)),
                "The intersection point is incorrect when the ray intersects the tube, the ray starts inside the tube behind its center ray and it is orthogonal to the tube");

        //TC18: Test that the ray  does not intersect the tube when the ray orthogonal to its center ray and starts behind the tube with opposite direction
        assertNull(tube.findIntersections(new Ray(new Point(-1, 0, 0), new Vector(-1,0, 0))),
                "There is intersection points when the ray orthogonal to its center ray and starts behind the tube with opposite direction");

        //TC19: Test that the ray does not intersect the tube when the ray orthogonal to its center ray and starts on the tube with opposite direction
        assertNull(tube.findIntersections(new Ray(new Point(0, 0, 0), new Vector(-1,0, 0))),
                "There is intersection points when the ray orthogonal to its center ray and starts on the tube with opposite direction");

        //Tests when the ray is orthogonal to the tube and its center ray

        //TC20: Test that the ray intersects the tube when the ray orthogonal to its center ray and starts at continue of the center ray
        assertEquals(List.of(new Point(2,0,3)), tube.findIntersections(new Ray(new Point(1, 0, 3), new Vector(1,0, 0))),
                "The intersection point is incorrect when the ray orthogonal to its center ray and starts at continue of the center ray");

        //TC21: Test that the ray intersects the tube when the ray orthogonal to its center ray and starts in front of continue of the center ray inside the tube
        assertEquals(List.of(new Point(2,0,3)), tube.findIntersections(new Ray(new Point(0.5, 0, 3), new Vector(1,0, 0))),
                "The intersection point is incorrect when the ray orthogonal to its center ray and starts in front of continue of the center ray inside the tube");

        //TC22: Test that the ray intersects the tube when the ray orthogonal to its center ray and starts behind continue of the center ray inside the tube
        assertEquals(List.of(new Point(2,0,3)), tube.findIntersections(new Ray(new Point(1.5, 0, 3), new Vector(1,0, 0))),
                "The intersection point is incorrect when the ray orthogonal to its center ray and starts behind continue of the center ray inside the tube");

        //TC23: Test that the ray intersects the tube when the ray orthogonal to its center ray and starts on the surface of the tube
        assertEquals(List.of(new Point(0,0,3)),tube.findIntersections(new Ray(new Point(2, 0, 3), new Vector(-1,0, 0))),
                "There is intersection points when the ray orthogonal to its center ray and starts on the surface of the tube");

        //TC24: Test that the ray does not intersect the tube when the ray orthogonal to its center ray and starts on its surface with opposite direction
        assertNull(tube.findIntersections(new Ray(new Point(2, 0, 3), new Vector(1,0, 0))),
                "There is intersection points when the ray orthogonal to its center ray and starts on its surface with opposite direction");

        //Tests when the ray is askew to the tube

        //TC25: Test that the ray does not intersect the tube, when the ray is askew to the tube
        assertNull(tube.findIntersections(new Ray(new Point(-5, 0, 5), new Vector(-1,-1,3))), "There is intersection points when the ray is askew to the tube");


        //Tests when the ray or its continue is tangent to the tube

        //TC26: Test that the ray does not intersect the tube when the ray is tangent to the tube and starts on the tube
        assertNull(tube.findIntersections(new Ray(new Point(2, 0, 5), new Vector(0,1, 0))),
                "There is intersection points when the ray is tangent to the tube and starts on the tube");

        //TC27: Test that the ray intersects the tube when the ray is tangent to the tube and starts before the tube
        assertEquals(List.of( new Point(2,0,5)),tube.findIntersections( new Ray(new Point(2,-1,5), new Vector(0,1,0))),"The intersection point is incorrect when the ray is tangent to the tube and starts before the tube");

        //TC28: Test that the ray does not intersect the tube when the ray is tangent to the tube and starts after the tube
        assertNull(tube.findIntersections(new Ray(new Point(2,1,5), new Vector(0,1,0))),"The ray intersects the tube when the ray is tangent to the tube and starts after the tube");

        //Tests when the rau starts on the tube



        //Other tests












    }
}