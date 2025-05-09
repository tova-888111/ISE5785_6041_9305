package geometries;

import org.junit.jupiter.api.Test;
import primitives.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
/**
 * Unit tests for geometries.Geometries class
 * @author Tehila Shraga and Tova Tretiak
 */
class GeometriesTests {
    /**Sphere used in some tests*/
    Sphere sphere= new Sphere(4,new Point(2, 0, 0));
    /**Plane used in some tests*/
    Plane plane= new Plane(new Point(1, 2, 1), Vector.AXIS_Z);
    /**Triangle used in some tests*/
    Triangle triangle= new Triangle(new Point(0,1,0), new Point(1,0,0), Point.ZERO);

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
        // ============ Equivalence Partitions Tests ==============

        //TC01: Test that the ray intersects some of the  geometries
        Geometries geometries1 = new Geometries();
        geometries1.add(sphere, plane, triangle);
        Ray ray1 = new Ray(new Point(3,0,8), new Vector(0, 0, -1));
        List<Point> intersections1 = geometries1.findIntersections(ray1);
        assertEquals(3, intersections1.size(), "The number of intersection points is incorrect");

        // =============== Boundary Values Tests ==================

        //TC02: Test that the ray intersects all the geometries
        Ray ray2 = new Ray(new Point(0.5, 0.25 ,8), new Vector(0, 0, -1));
        List<Point> intersections2 = geometries1.findIntersections(ray2);
        assertEquals(4, intersections2.size(), "The number of intersection points is incorrect");

        //TC03: Test that the ray does not intersect any of the geometries
        Ray ray3 = new Ray(new Point(0, 0, 8), Vector.AXIS_Z);
        List<Point> intersections3 = geometries1.findIntersections(ray3);
        assertNull(intersections3, "The intersection point is incorrect");

        //TC04: Test that the ray intersects one of the geometries
        Ray ray4 = new Ray(new Point(10, 0, 8), new Vector(0, 0, -1));
        List<Point> intersections4 = geometries1.findIntersections(ray4);
        assertEquals(1, intersections4.size(), "The number of intersection points is incorrect");

        //TC05: Test that there is no intersection when the geometries list is empty
        Geometries geometries2 = new Geometries();
        Ray ray5 = new Ray(new Point(0, 0, 8), new Vector(0, 0, -1));
        List<Point> intersections5 = geometries2.findIntersections(ray5);
        assertNull(intersections5, "The intersection point is incorrect");
    }
}