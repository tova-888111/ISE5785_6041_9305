package geometries;
import static primitives.Util.alignZero;
import static primitives.Util.isZero;
import primitives.*;

import java.util.AbstractMap;
import java.util.Comparator;
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

        Vector deltaP;
        try {
            deltaP = p.subtract(p0);
        } catch (IllegalArgumentException e) {
            if (isZero(v.dotProduct(v0))) {
                return List.of(ray.getPoint(radius));
            }
            return null;
        }

        try {
            Vector cross = deltaP.crossProduct(v0);
            if (isZero(cross.length())) {
                if (isZero(v.dotProduct(v0))) {
                    return List.of(ray.getPoint(radius));
                }
                return null;
            }
        } catch (IllegalArgumentException e) {
            if (isZero(v.dotProduct(v0))) {
                return List.of(ray.getPoint(radius));
            }
            return null;
        }

        if (Math.abs(v0.dotProduct(v)) > v0.length() * v.length() - 1e-10) {
            return null;
        }

        Vector vCrossV0 = v.crossProduct(v0);
        Vector deltaPCrossV0 = deltaP.crossProduct(v0);

        double a = alignZero(vCrossV0.lengthSquared());
        double b = alignZero(2 * vCrossV0.dotProduct(deltaPCrossV0));
        double c = alignZero(deltaPCrossV0.lengthSquared() - radius * radius * v0.lengthSquared());

        double discriminant = alignZero(b * b - 4 * a * c);

        if (isZero(discriminant) || discriminant < 0 || isZero(a)) {
            return null;
        }

        double sqrtDisc = alignZero(Math.sqrt(discriminant));
        double denom = alignZero(2 * a);

        double t1 = alignZero((-b + sqrtDisc) / denom);
        double t2 = alignZero((-b - sqrtDisc) / denom);

        List<AbstractMap.SimpleEntry<Double, Point>> temp = new java.util.LinkedList<>();
        final double EPSILON = 1e-10;

        if (t1 > EPSILON) {
            temp.add(new AbstractMap.SimpleEntry<>(t1, ray.getPoint(t1)));
        }

        if (t2 > EPSILON) {
            Point point2 = ray.getPoint(t2);
            boolean alreadyExists = false;
            for (AbstractMap.SimpleEntry<Double, Point> entry : temp) {
                if (entry.getValue().distance(point2) < EPSILON) {
                    alreadyExists = true;
                    break;
                }
            }
            if (!alreadyExists) {
                temp.add(new AbstractMap.SimpleEntry<>(t2, point2));
            }
        }

        if (temp.isEmpty()) {
            return null;
        }

        temp.sort(Comparator.comparingDouble(AbstractMap.SimpleEntry::getKey));

        List<Point> result = new java.util.LinkedList<>();
        for (AbstractMap.SimpleEntry<Double, Point> entry : temp) {
            result.add(entry.getValue());
        }

        return result;
    }
}
