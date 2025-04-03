package geometries;

import org.junit.jupiter.api.Test;
import primitives.*;
import geometries.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
/**
 * Unit tests for geometries.Geometries class
 * @author Tehila Shraga and Tova Tretiak
 */
class GeometriesTests {
    Sphere sphere= new Sphere(4,new Point(0,0,1));
    Plane plane= new Plane(new Point(1, 2, 3), new Vector(0, 0, 1));
    Triangle triangle= new Triangle(new Point(0,1,0), new Point(1,0,0), new Point(0,0,0));

    /**
     * Default constructor for the GeometriesTests class.
     */
    public GeometriesTests() {
    }

    /**
     * Test method for {@link geometries.Geometries#add(geometries.Intersectable...)}.
     * Test method for add.
     */
    @Test
    void testAdd() {
        // ============ Equivalence Partitions Tests ==============
        //TC01: Test that the geometries are added correctly
        Geometries geometries = new Geometries();
        geometries.add(sphere, plane, triangle);
        assertEquals(3, geometries.getGeometries().size(), "The number of geometries is incorrect");
        // =============== Boundary Values Tests ==================
        //TC02: Test that the geometries are added correctly when the list is empty
        Geometries emptyGeometries = new Geometries();
        emptyGeometries.add();
        assertEquals(0, emptyGeometries.getGeometries().size(), "The number of geometries is incorrect");
    }

    /**
     * Test method for {@link geometries.Geometries#findIntersections(primitives.Ray)}.
     * Test method for findIntersections.
     */
    @Test
    void testFindIntersections() {
    }
}