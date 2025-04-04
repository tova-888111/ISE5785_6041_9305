package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;
import java.util.List;
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
        List<Point> intersectionPoints = plane.findIntersections(ray);

        //If the ray does not intersect with the plane, return null
        if (intersectionPoints == null) {
            return null;
        }
        Point p = intersectionPoints.getFirst();
        Point p0 = ray.getHead();
        Vector v = ray.getDirection();
        //If the intersection point is the same as the ray's head, return null
        if (p.equals(p0)) {
            return null;
        }

        Point p1 = vertices.get(0);
        Point p2 = vertices.get(1);
        Point p3 = vertices.get(2);

        Vector v1 = p1.subtract(p0);
        Vector v2 = p2.subtract(p0);
        Vector v3 = p3.subtract(p0);

        Vector n1 = v1.crossProduct(v2).normalize();
        Vector n2 = v2.crossProduct(v3).normalize();
        Vector n3 = v3.crossProduct(v1).normalize();

        double s1 = v.dotProduct(n1);
        double s2 = v.dotProduct(n2);
        double s3 = v.dotProduct(n3);

        if (isZero(s1)|| isZero(s2)|| isZero(s3))
            return null;
        // If the ray intersects the plane and is inside the triangle
        if ((s1 > 0 && s2 > 0 && s3 > 0) || (s1 < 0 && s2 < 0 && s3 < 0)) {
            return List.of(p);
        }
        // If the ray intersects the plane but is not inside triangle, return null
        return null;
    }
}

