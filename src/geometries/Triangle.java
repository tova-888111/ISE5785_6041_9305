package geometries;

import primitives.*;
import java.util.List;
import static primitives.Util.alignZero;
import static primitives.Util.isZero;

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
        Point p0 = ray.getHead();
        Vector dir = ray.getDirection();

        Point v0 = vertices.get(0);
        Point v1 = vertices.get(1);
        Point v2 = vertices.get(2);

        Vector edge1 = v1.subtract(v0);
        Vector edge2 = v2.subtract(v0);

        Vector h = dir.crossProduct(edge2);
        double a = alignZero(edge1.dotProduct(h));

        // if the variable A is near 0, ray is parallel to the triangle
        if (isZero(a)) return null;

        double f = alignZero(1.0 / a);
        Vector s = p0.subtract(v0);
        double u = alignZero(f * s.dotProduct(h));
        if (u <= 0 || u >= 1) return null; // exclude edges

        Vector q = s.crossProduct(edge1);
        double v = alignZero(f * dir.dotProduct(q));
        if (v <= 0 || u + v >= 1) return null; // exclude edges

        double t = alignZero(f * edge2.dotProduct(q));

        // Check if intersection point is behind the ray's origin or at the origin
        if (t <= 0) return null;

        Point intersectionPoint = p0.add(dir.scale(t));

        // Reject intersection if it lies exactly on vertex or edge
        if (intersectionPoint.equals(p0) ||
                intersectionPoint.equals(v0) || intersectionPoint.equals(v1) || intersectionPoint.equals(v2)) {
            return null;
        }

        return List.of(intersectionPoint);
    }
}

