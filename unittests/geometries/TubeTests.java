package geometries;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/***
 * Unit tests for geometries.Tube class
 * @author Tehila Shraga and Tova Tretiak
 */
class TubeTests {
    /***
     * Test method for {@link geometries.Tube#getNormal(primitives.Point)}.
     */
    @Test
    void testGetNormal() {
        // ============ Equivalence Partitions Tests ==============
        Tube tube = new Tube(1, new Ray(new Point(0, 0, 0), new Vector(0, 0, 5)));
        //Test that the normal of the tube is correct
       assertEquals(new Vector(1, 0, 0), tube.getNormal(new Point(1, 0, 1)), "The normal vector is incorrect");
        // =============== Boundary Values Tests ==================
        assertEquals(new Vector(1, 0, 0), tube.getNormal(new Point(1, 0, 0)), "The normal vector is incorrect");
    }
}