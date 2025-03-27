package geometries;

import org.junit.jupiter.api.Test;

import primitives.Point;
import primitives.Vector;

import static org.junit.jupiter.api.Assertions.*;

/***
 * Unit tests for geometries.Triangle class
 * @author Tehila Shraga and Tova Tretiak
 */
class TriangleTests {
    /***
     * Test method for {@link geometries.Triangle#getNormal(primitives.Point)}.
     */
    @Test
    void testGetNormal() {
        // ============ Equivalence Partitions Tests ==============
        Point point1=(new Point(0,0,0));
        Point point2=(new Point(4,0,0));
        Point point3=(new Point(0,3,0));
        Triangle triangle=new Triangle(point1,point2,point3);
        Vector normal = triangle.getNormal(point1);
        //Test that the normal of the triangle is correct
        assertEquals(1, normal.length(), "The length of the normal is not 1");
        //Test that the normal of the triangle is orthogonal to two vectors on the triangle
        assertEquals(0, (point1.subtract(point2)).dotProduct(normal), "The normal is not orthogonal to the first vector");
        assertEquals(0, (point1.subtract(point3)).dotProduct(normal), "The normal is not orthogonal to the second vector");
    }
}