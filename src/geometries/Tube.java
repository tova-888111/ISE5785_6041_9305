package geometries;
import static primitives.Util.isZero;
import primitives.*;

import java.util.List;

/**
 * The Tube class represents an infinite cylindrical tube in 3D space.
 * @author Tehila Shraga and Tova Tretiak
 */
public class Tube extends RadialGeometry{

    /** The central axis of the tube, represented as a ray */
    protected final Ray axis;

    /**
     * Constructor to initialize new Tube with the specified radius and central axis.
     * @param radius The radius of the tube (must be positive).
     * @param axis The central axis of the tube, represented as a {@link Ray}.
     */
    public Tube(double radius, Ray axis) {
        super(radius);
        this.axis = axis;
    }

    @Override
    public Vector getNormal(Point point) {
        double t=axis.getDirection().dotProduct(point.subtract(axis.getHead()));
        if (isZero(t))
            return point.subtract(axis.getHead()).normalize();
        return point.subtract(axis.getPoint(t)).normalize();
    }

    /***
     * Returns the central axis of the tube.
     * @return axis
     */
    public Ray getAxis() {
        return axis;
    }

    @Override
    public List<Point> findIntersections(Ray ray) {
        Point p = ray.getHead();
        Vector v = ray.getDirection();
        Point p0 = axis.getHead();
        Vector v0 = axis.getDirection();

        if (Math.abs(v0.dotProduct(v) - v0.length() * v.length()) < 1e-10) {
            return null;
        }

        // w = deltaP = p - p0
        Vector deltaP = p.subtract(p0);

        Vector vCrossV0 = v.crossProduct(v0);
        Vector deltaPCrossV0 = deltaP.crossProduct(v0);

        double a = vCrossV0.lengthSquared();
        double b = 2 * vCrossV0.dotProduct(deltaPCrossV0);
        double c = deltaPCrossV0.lengthSquared() - radius * radius * v0.lengthSquared();

        double discriminant = b * b - 4 * a * c;

        // No intersection
        if (isZero(discriminant) || discriminant < 0 || isZero(a)) {
            return null;
        }

        double sqrtDisc = Math.sqrt(discriminant);
        double denom = 2 * a;

        double t1 = (-b + sqrtDisc) / denom;
        double t2 = (-b - sqrtDisc) / denom;

        List<Point> result = new java.util.LinkedList<>();

        if (t1 > 0 && !isZero(t1)) {
            result.add(ray.getPoint(t1));
        }

        if (t2 > 0 && !isZero(t2)) {
            Point point2 = ray.getPoint(t2);
            // Avoid adding the same point (if very close numerically)
            if (result.isEmpty() || !result.getFirst().equals(point2)) {
                result.add(point2);
            }
        }

        return result.isEmpty() ? null : result;
    }
}
