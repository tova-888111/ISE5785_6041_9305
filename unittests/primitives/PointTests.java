package primitives;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
class PointTests {
    Point p1=new Point(1,2,3);
    Point p2=new Point(2,4,6);
    Vector v1= new Vector(1,2,3);
    @Test
    void testSubtract() {
        testSubtract1();
        testSubtract2();
    }
    @Test
    void testSubtract1() {
       assertEquals(v1,p2.subtract(p1),"Subtract() wrong result");
    }
    @Test
    void testSubtract2() {
        try {
            p1.subtract(p1);
            fail("Subtract() for same point does not throw an exception");
        }catch(IllegalArgumentException e){

        }

    }

    @Test
    void testAdd() {
        assertEquals(p2,p1.add(v1),"Add() wrong result");
    }

    @Test
    void testDistanceSquared() {
        assertEquals(14,p1.distanceSquared(p2),"DistanceSquared() wrong result");
    }

    @Test
    void testDistance() {
    }
}