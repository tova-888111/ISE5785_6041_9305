package geometries;

import org.junit.jupiter.api.Test;

import primitives.*;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

/***
 * Unit tests for geometries.Triangle class
 * @author Tehila Shraga and Tova Tretiak
 */
class TriangleTests {
    /**Point (0,0,0) for tests*/
    private final Point point1=(new Point(0,0,0));
    /**Point (4,0,0) for tests*/
    private final Point point2=(new Point(4,0,0));
    /**Point (0,3,0) for tests*/
    private final Point point3=(new Point(0,3,0));
    /**Point (0,0,1) for tests*/
    private final Point point4=(new Point(1,0,0));
    /**Point (3,0,0) for tests*/
    private final Point point5=(new Point(3,0,0));

    /***
     * Default constructor for the TriangleTests class.
     */
    public TriangleTests() {
    }

    /***
     * Test method for {@link geometries.Triangle#getNormal(primitives.Point)}.
     */
    @Test
    void testGetNormal() {
        // ============ Equivalence Partitions Tests ==============
        //TC01: Test that the normal of the triangle is correct
        Triangle triangle=new Triangle(point1,point2,point3);
        Vector normal = triangle.getNormal(point1);
        //Test that the length of the triangle's normal is 1
        assertEquals(1, normal.length(), "The length of the normal is not 1");
        //Test that the normal of the triangle is orthogonal to two vectors on the triangle
        assertEquals(0, (point1.subtract(point2)).dotProduct(normal), "The normal is not orthogonal to the first vector");
        assertEquals(0, (point1.subtract(point3)).dotProduct(normal), "The normal is not orthogonal to the second vector");
    }
    /***
     * Test method for {@link geometries.Triangle#Triangle(primitives.Point, primitives.Point, primitives.Point)}.
     * Test for findIntersections method.
     */
    @Test
    void testFindIntersections() {
        Triangle triangle=new Triangle(point2,point3,point4);
        // ============ Equivalence Partitions Tests ==============
        //TC01: Test that the ray intersects the triangle, the intersection point is inside the triangle
        Ray ray1=new Ray(new Point(3,0,5),new Vector(0,0,-1));
        assertEquals(List.of(point5), triangle.findIntersections(ray1), "The intersection point is incorrect");
        //TC02: Test that the ray does not intersect the triangle, the ray hits the plane of the triangle but not the triangle near its vertices
        Ray ray2=new Ray(new Point(1,-1,5),new Vector(0,0,-2));
        assertNull(triangle.findIntersections(ray2), "The intersection point is incorrect");
        //TC03: Test that the ray does not intersect the triangle, the ray hits the plane of the triangle but not the triangle near its edges
        Ray ray3=new Ray(new Point(5,2,5),new Vector(0,0,-2));
        assertNull(triangle.findIntersections(ray3), "The intersection point is incorrect");
        // ================== Boundary Values Tests ==================
        Ray ray4=new Ray(new Point(2,0,5),new Vector(0,0,-2));
        //TC04: Test that the ray does not intersect the triangle, the intersection point is on the edge of the triangle
        assertNull(triangle.findIntersections(ray4), "The intersection point is incorrect");
        //TC05: Test that the ray does not intersect the triangle, the intersection point is on the vertex of the triangle
        Ray ray5=new Ray(new Point(4,0,4),new Vector(0,0,-2));
        assertNull(triangle.findIntersections(ray5), "The intersection point is incorrect");
        //TC06: Test that the ray does not intersect the triangle, the intersection point is at the continuation of the edge of the triangle
        Ray ray6=new Ray(new Point(-3,0,0),new Vector(0,0,-2));
        assertNull(triangle.findIntersections(ray6), "The intersection point is incorrect");
    }
}