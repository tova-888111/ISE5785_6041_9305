package primitives;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
/**
 * Unit tests for primitives.Ray class
 * @author Tehila Shraga and Tova Tretiak
 */
class RayTests {
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
        //TC02: Test for negative distance
        assertEquals(new Point(1, 2, 0), ray.getPoint(-3), "Ray getPoint() wrong result");
        // =============== Boundary Values Tests ==================
        //TC03: Test for zero distance
        assertEquals(new Point(1, 2, 3), ray.getPoint(0), "Ray getPoint() wrong result");

    }
}