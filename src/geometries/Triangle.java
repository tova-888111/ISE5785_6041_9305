package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

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

    @Override
    public List<Point> findIntersections(Ray ray) {
        //Barycentric coordinates method
        Vector edge1 = vertices.get(1).subtract(vertices.get(0));
        Vector edge2 = vertices.get(2).subtract(vertices.get(0));
        Vector h = ray.getDirection().crossProduct(edge2);
        double a = edge1.dotProduct(h);
        if (a > -0.0000001 && a < 0.0000001) {
            return null; // This ray is parallel to this triangle.
        }
        double f = 1.0 / a;
        Vector s = ray.getHead().subtract(vertices.get(0));
        double u = f * s.dotProduct(h);
        if (u < 0.0 || u > 1.0) {
            return null;
        }
        Vector q = s.crossProduct(edge1);
        double v = f * ray.getDirection().dotProduct(q);
        if (v < 0.0 || u + v > 1.0) {
            return null;
        }
        // At this point we can compute t to find out where the ray intersects the plane
        double t = f * edge2.dotProduct(q);
        if (t > 0.0000001) { // ray intersection
            return List.of(ray.getHead().add(ray.getDirection().scale(t)));
        } // This means that there is a line intersection but not a ray intersection.
            return null;
        }
}
