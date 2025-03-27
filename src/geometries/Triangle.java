package geometries;

import primitives.Point;

/**
 * This class represents a triangle in 3D space.
 * @author Tehila Shraga and Tova Tretiak
 */
public class Triangle extends Polygon {
    /***
     * Constructor to initialize a triangle with three given points.
     * @param point1 -first point of the triangle.
     * @param point2 -second point of the triangle.
     * @param point3- third point of the triangle.
     */
    public Triangle(Point point1, Point point2, Point point3) {
        super(point1, point2, point3);
    }
}
