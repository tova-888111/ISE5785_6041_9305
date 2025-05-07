package primitives;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
/**
 * Unit tests for primitives.Ray class
 * @author Tehila Shraga and Tova Tretiak
 */
class RayTests {
    /** A ray (1,2,3) in the direction of (0,0,1) used in tests */
    private final Ray ray = new Ray(new Point(1, 2, 3), new Vector(0, 0, 1));
    /**
     * Constructor for the RayTests class.
     */
    public RayTests() {
    }
     /**
     * Test method for {@link primitives.Ray#Ray(primitives.Point, primitives.Vector)}.
     * Test method for constructor.
     */
    @Test
    void testGetPoint() {
        // ============ Equivalence Partitions Tests ==============
        //TC01: Test for positive distance
        assertEquals(new Point(1, 2, 6), ray.getPoint(3), "Ray getPoint() wrong result");
        //TC02: Test for large distance
        assertEquals(new Point(1,2,1003), ray.getPoint(1000), "Ray getPoint() wrong result");
        //TC03: Test for negative distance
        assertEquals(new Point(1, 2, 0), ray.getPoint(-3), "Ray getPoint() wrong result");
        // =============== Boundary Values Tests ==================
        //TC04: Test for zero distance
        assertEquals(new Point(1, 2, 3), ray.getPoint(0), "Ray getPoint() wrong result");

    }

    @Test
    void testFindClosestPoint(){

        // ============ Equivalence Partitions Tests ==============
        //TC01: Test when the closest point is in the middle of the list
        final List<Point> list1 = List.of(
                new Point(1, 2, 5),
                new Point(1,2,4.5),
                new Point(1, 2, 4),
                new Point(1, 2, 6),
                new Point(1, 2, 7)
        );
        assertEquals(new Point(1, 2, 4), ray.findClosestPoint(list1), "Ray findClosestPoint() wrong result when the closest point is in the middle of the list");

        // ============ Boundary Values Tests ==================

        //TC02: Test for empty list
        assertNull(ray.findClosestPoint(null), "Ray findClosestPoint() wrong result when the list is null");

        //TC03: Test when the closest point is the first point in the list
        List<Point> list3 = List.of(
                new Point(1, 2, 4),
                new Point(1, 2, 5),
                new Point(1, 2, 6),
                new Point(1, 2, 7)
        );
        assertEquals(new Point(1, 2, 4), ray.findClosestPoint(list3), "Ray findClosestPoint() wrong result when the closest point is the first point in the list");

        //TC04: Test when the closest point is the last point in the list
        List<Point> list4 = List.of(
                new Point(1, 2, -10),
                new Point(1, 2, 5),
                new Point(1, 2, 6),
                new Point(1, 2, 4)
        );
        assertEquals(new Point(1, 2, 4), ray.findClosestPoint(list4), "Ray findClosestPoint() wrong result when the closest point is the last point in the list");
    }
}