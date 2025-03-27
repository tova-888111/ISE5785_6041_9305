package primitives;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/***
 * Unit tests for primitives.Point class
 * @author Tehila Shraga and Tova Tretiak
 */
class PointTests {
    /***
     * Points and Vectors for testing
     */
    Point p1 = new Point(1, 2, 3);
    Point p2 = new Point(2, 4, 6);
    Vector v1 = new Vector(1, 2, 3);
    //It is a value that shows the maximum allowed difference between the expected result and the actual result.
    private static final double ACCURACY = 0.0001;
    /***
     * Test method for {@link primitives.Point#subtract(primitives.Point)}.
     * This test checks if subtract between two points is calculated correctly.
     */
    @Test
    void testSubtract() {
        // ============ Equivalence Partitions Tests ==============
        // Test that subtracting p1 from p2 results is the correct vector
        assertEquals(v1, p2.subtract(p1), "Subtract() wrong result");
        // =============== Boundary Values Tests ==================
        // Test that subtracting a point from itself throws an IllegalArgumentException
        assertThrows(IllegalArgumentException.class, () -> p1.subtract(p1),
                "Subtract() for same point does not throw an exception");
    }
    /***
     * Test method for {@link primitives.Point#add(primitives.Vector)}.
     * This test checks if add between two points is calculated correctly.
     */
    @Test
    void testAdd() {
        // ============ Equivalence Partitions Tests ==============
        // Test that adding vector v1 to point p1 results is the correct point
        assertEquals(p2, p1.add(v1), "Add() wrong result");
    }
    /***
     * Test method for {@link primitives.Point#distanceSquared(primitives.Point)}.
     * This test checks if the  distance squared between two points is calculated correctly.
     */
    @Test
    void testDistanceSquared() {
        // ============ Equivalence Partitions Tests ==============
        // Test that the squared distance between p1 and p2 is correct
        assertEquals(14, p1.distanceSquared(p2),ACCURACY, "DistanceSquared() wrong result");
    }
    /***
     * Test method for {@link primitives.Point#distance(primitives.Point)}.
     * This test checks if the distance between two points is calculated correctly.
     */
    @Test
    void testDistance() {
        // ============ Equivalence Partitions Tests ==============
        // Test that the distance between p1 and p2 is correct
        assertEquals(Math.sqrt(14), p1.distance(p2),ACCURACY, "Distance() wrong result");
    }
}