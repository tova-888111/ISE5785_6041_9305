package geometries;

import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Vector;

import static org.junit.jupiter.api.Assertions.*;

class SphereTests {

    @Test
    void testGetNormal() {
        // Arrange
        Point center = new Point(0, 0, 0);
        Sphere sphere = new Sphere(1, center);
        Point pointOnSurface = new Point(0, 0, 1);

        // Act
        Vector normal = sphere.getNormal(pointOnSurface);

        // Assert
        assertEquals(new Vector(0, 0, 1), normal, "The normal vector is incorrect");
    }
}