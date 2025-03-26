package primitives;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the Vector class.
 * This class contains tests for various methods of the Vector class to ensure they work correctly.
 * @author Tehila Shraga and Tova Tretiak
 */
class VectorTests {
    /***
     * Vectors for testing
     */
    Vector v1 = new Vector(1, 2, 3);
    Vector v2 = new Vector(4, 5, 6);
    Vector v3=new Vector (-3,0,1);
    Vector v4=new Vector(-1,-2,-3);

    /**
     * Test method for {@link Vector#lengthSquared()}.
     * This test checks if the squared length of the vector is calculated correctly.
     */
    @Test
    void testLengthSquared() {
        // ============ Equivalence Partitions Tests ==============
        assertEquals(14, v1.lengthSquared(), "lengthSquared() wrong result");
    }

    /**
     * Test method for {@link Vector#length()}.
     * This test checks if the length of the vector is calculated correctly.
     */
    @Test
    void testLength() {
        //============ Equivalence Partitions Tests ==============
        assertEquals(Math.sqrt(14),v1.length(), "Length is incorrect");
    }

    /**
     * Test method for {@link Vector#normalize()}.
     * This test checks if the vector is normalized correctly.
     */
    @Test
    void testNormalize() {
        //============ Equivalence Partitions Tests ==============
        assertEquals(new Vector(1 / Math.sqrt(14), 2 / Math.sqrt(14), 3 / Math.sqrt(14)),v1.normalize(), "Normalize() wrong result");
    }

    /**
     * Test method for {@link Vector#crossProduct(Vector)}.
     * This test checks if the cross product of two vectors is calculated correctly.
     */
    @Test
    void testCrossProduct() {
        //============ Equivalence Partitions Tests ==============
        assertEquals(new Vector(-3, 6, -3),v1.crossProduct(v2), "CrossProduct() wrong result");
        // =============== Boundary Values Tests ==================
        assertEquals(new Vector(0, 0, 0),v1.crossProduct(v1), "CrossProduct() for same vector is not zero");
    }

    /**
     * Test method for {@link Vector#dotProduct(Vector)}.
     * This test checks if the dot product of two vectors is calculated correctly.
     */
    @Test
    void testDotProduct() {
        //============ Equivalence Partitions Tests ==============
        assertEquals(32,v1.dotProduct(v2), "DotProduct() wrong result");
        // =============== Boundary Values Tests ==================
        assertEquals(0,v1.dotProduct(v3), "DotProduct() for orthogonal vectors is not zero");

    }

    /**
     * Test method for {@link Vector#add(Vector)}.
     * This test checks if two vectors are added correctly.
     */
    @Test
    void testAdd() {
        //============ Equivalence Partitions Tests ==============
        assertEquals(new Vector(5, 7, 9),v1.add(v2), "Add() wrong result");
        // =============== Boundary Values Tests ==================
        try {
            v1.add(v4);
            fail("Add() for opposite vectors does not throw an exception");
        } catch (IllegalArgumentException e) {
        }
    }

    /**
     * Test method for {@link Vector#scale(double)}.
     * This test checks if the vector is scaled correctly.
     */
    @Test
    void testScale() {
        //============ Equivalence Partitions Tests ==============
        assertEquals(new Vector(2, 4, 6),v1.scale(2), "Scale() wrong result");
        // =============== Boundary Values Tests ==================
        try {
            v1.scale(0);
            fail("Scale() vector with zero does not throw an exception");
        } catch (IllegalArgumentException e) {
        }
    }

    /**
     * Test method for {@link Vector#subtract(Vector)}.
     * This test checks if the subtraction of two vectors is calculated correctly.
     */
    @Test
    void testSubtract() {
        //============ Equivalence Partitions Tests ==============
        assertEquals(new Vector(3, 3, 3),v2.subtract(v1), "Subtract() wrong result");
        // =============== Boundary Values Tests ==================
        try {
            v1.subtract(v1);
            fail("Subtract() for same vector does not throw an exception");
        } catch (IllegalArgumentException e) {
        }
    }
}