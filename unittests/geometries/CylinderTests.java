package geometries;

import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import static org.junit.jupiter.api.Assertions.*;

class CylinderTests {

    @Test
    void testGetNormal() {
        // Arrange
        Ray axis = new Ray(new Point(0, 0, 0), new Vector(0, 0, 1));
        Cylinder cylinder = new Cylinder(1, axis, 5);
        Point pointOnSurface = new Point(1, 0, 2);

        // Act
        Vector normal = cylinder.getNormal(pointOnSurface);

        // Assert
        assertEquals(new Vector(1, 0, 0), normal, "The normal vector is incorrect");
    }
}