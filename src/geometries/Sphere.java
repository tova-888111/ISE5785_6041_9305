package geometries;

import primitives.*;

import java.util.List;

import static primitives.Util.alignZero;
import static primitives.Util.isZero;

/**
 * This class represents a three-dimensional sphere in 3D space.
 * @author Tehila Shraga and Tova Tretiak
 */
public class Sphere extends RadialGeometry{

    /** The center point of the sphere */
    private final Point center;

    /**
     * Constructor to initialize sphere with a given radius and center point.
     * @param radius the radius of the sphere
     * @param center the center point of the sphere
     */
    public Sphere(double radius, Point center) {
        super(radius);
        this.center = center;
    }

    @Override
    public Vector getNormal(Point point) {
        return point.subtract(center).normalize();
    }

    /***
     * Getter for the center of the sphere.
     * @return center
     */
    public Point getCenter() {
        return center;
    }

    @Override
    public List<Point> findIntersections(Ray ray) {
        Vector v = ray.getDirection();
        Point p0 = ray.getHead();
        // The vector from the center of the sphere to the ray's head
        if (p0.equals(center)) {
            return List.of(p0.add(v.scale(radius)));
        }
            Vector u = center.subtract(p0);
            double tm = alignZero(v.dotProduct(u));
            double d2 = alignZero(u.lengthSquared() - tm * tm);
            if (alignZero(d2 - radius * radius) > 0) {
                return null; // No intersection
            }
            double th = alignZero(Math.sqrt(radius * radius - d2));
            double t1 = alignZero(tm - th);
            double t2 = alignZero(tm + th);

        if (t1 <= 0 && t2 <= 0) {
            return null; // No intersection
        }

        if (t1 > 0 && t2<=0) {
            return List.of(ray.getPoint(t1));
        }

        if (t2 > 0 && t1<=0) {
            return List.of(ray.getPoint(t2));
        }

        if (t1 > 0 && t2 > 0) {
            return List.of(ray.getPoint(t1), ray.getPoint(t2));
        }
        return null;//No intersections
        }
}
