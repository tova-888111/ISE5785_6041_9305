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
        assertEquals(new Vector(1, 0, 0), tube.getNormal(new Point(1, 0, 1)), "The normal vector is incorrect");
        // =============== Boundary Values Tests ==================
        //TC02: Test that the normal of the tube is correct when the connection between the point and the head of the ray forms a right angle with the ray.
        assertEquals(new Vector(1, 0, 0), tube.getNormal(new Point(1, 0, 0)), "The normal vector is incorrect");
    }
}