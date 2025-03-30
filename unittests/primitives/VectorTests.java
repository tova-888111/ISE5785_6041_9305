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
    Vector v1 = new Vector(1, 2, 3);//First vector
    Vector v2 = new Vector(4, 5, 6);//Second vector
    Vector v3=new Vector (-3,0,1);//Third vector is orthogonal to v1
    Vector v4=new Vector(-1,-2,-3);//Fourth vector is the opposite of v1
    private static final double ACCURACY = 0.000001;//It is a value that shows the maximum allowed difference between the expected result and the actual result.
    /***
     * Test method for {@link primitives.Vector#Vector(double, double, double)}.
     * This test checks if the vector is created correctly.
     */
    @Test
    void testVector() {
        // ============ Equivalence Partitions Tests ==============
        //TC01: Test that the vector is created correctly
        assertEquals(1, v1.xyz.d1(), "Vector constructor wrong result");
        assertEquals(2, v1.xyz.d2(), "Vector constructor wrong result");
        assertEquals(3, v1.xyz.d3(), "Vector constructor wrong result");
        // =============== Boundary Values Tests ==================
        //TC02: Test that the vector with zero length throws an exception
        assertThrows(IllegalArgumentException.class, () -> new Vector(0, 0, 0),
                "Vector constructor for zero length does not throw an exception");
    }
    /**
     * Test method for {@link primitives.Vector#lengthSquared()}.
     * This test checks if the squared length of the vector is calculated correctly.
     */
    @Test
    void testLengthSquared() {
        // ============ Equivalence Partitions Tests ==============
        //TC01: Test that the squared distance of v1 is correct
        assertEquals(14, v1.lengthSquared(),ACCURACY, "LengthSquared() wrong result");
    }

    /**
     * Test method for {@link primitives.Vector#length()}.
     * This test checks if the length of the vector is calculated correctly.
     */
    @Test
    void testLength() {
        //============ Equivalence Partitions Tests ==============
        //TC01: Test that the distance between v1 is correct
        assertEquals(Math.sqrt(14),v1.length(),ACCURACY, "Length() wrong result");
    }

    /**
     * Test method for {@link primitives.Vector#normalize()}.
     * This test checks if the vector is normalized correctly.
     */
    @Test
    void testNormalize() {
        //============ Equivalence Partitions Tests ==============
        //TC01: Test that the normalized vector of v1 is correct
        assertEquals(new Vector(1 / Math.sqrt(14), 2 / Math.sqrt(14), 3 / Math.sqrt(14)),v1.normalize(), "Normalize() wrong result");
    }

    /**
     * Test method for {@link primitives.Vector#crossProduct(primitives.Vector)}.
     * This test checks if the cross product of two vectors is calculated correctly.
     */
    @Test
    void testCrossProduct() {
        //============ Equivalence Partitions Tests ==============
        //TC01: Test that the cross product of v1 and v2 is correct
        assertEquals(new Vector(-3, 6, -3),v1.crossProduct(v2), "CrossProduct() wrong result");
        // =============== Boundary Values Tests ==================
        //TC02: Test that cross product of two parallel vectors throw an exception
        assertThrows(IllegalArgumentException.class, () -> v1.crossProduct(v1),
                "crossProduct() for parallel vectors does not throw an exception");
}

    /**
     * Test method for {@link primitives.Vector#dotProduct(primitives.Vector)}.
     * This test checks if the dot product of two vectors is calculated correctly.
     */
    @Test
    void testDotProduct() {
        //============ Equivalence Partitions Tests ==============
        //TC01: Test that the dot product of v1 and v2 is correct
        assertEquals(32,v1.dotProduct(v2),ACCURACY, "DotProduct() wrong result");
        // =============== Boundary Values Tests ==================
        //TC02: Test that the dot product of two orthogonal vectors is zero
        assertEquals(0,v1.dotProduct(v3),ACCURACY, "DotProduct() for orthogonal vectors is not zero");

    }

    /**
     * Test method for {@link primitives.Vector#add(primitives.Vector)}.
     * This test checks if two vectors are added correctly.
     */
    @Test
    void testAdd() {
        //============ Equivalence Partitions Tests ==============
        //TC01: Test that add of v1 and v2 is correct
        assertEquals(new Vector(5, 7, 9),v1.add(v2), "Add() wrong result");
        // =============== Boundary Values Tests ==================
        //TC02: Test that add of two opposite vectors is correct
        assertThrows(IllegalArgumentException.class, () -> v1.add(v4),
                "Add() for opposite vectors does not throw an exception");
    }

    /**
     * Test method for {@link Vector#scale(double)}.
     * This test checks if the vector is scaled correctly.
     */
    @Test
    void testScale() {
        //============ Equivalence Partitions Tests ==============
        //TC01: Test if the scale between v1 and 2 is correct
        assertEquals(new Vector(2, 4, 6),v1.scale(2), "Scale() wrong result");
        // =============== Boundary Values Tests ==================
        //TC02: Test if the scale between v1 and 0 throw an exception
        assertThrows(IllegalArgumentException.class, () -> v1.scale(0),
                "Scale() vector with zero does not throw an exception");
    }

    /***
     * Test method for {@link primitives.Vector#subtract(primitives.Point)}.
     * This test checks if the subtraction of two vectors is calculated correctly.
     */
    @Test
    void testSubtract() {
        //============ Equivalence Partitions Tests ==============
        //TC01: Test that subtract between v2 and v1 is correct
        assertEquals(new Vector(3, 3, 3), v2.subtract(v1), "Subtract() wrong result");
        // =============== Boundary Values Tests ==================
        //TC02: Test that abstract between v1 and itself throw an exception
        assertThrows(IllegalArgumentException.class, () -> v1.subtract(v1),
                "Subtract() for same vector does not throw an exception");
    }
}