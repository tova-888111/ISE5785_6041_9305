package geometries;

import static primitives.Util.alignZero;
import static primitives.Util.isZero;
import primitives.*;

import java.util.AbstractMap;
import java.util.Comparator;
import java.util.List;

/**
 * The Tube class represents an infinite cylindrical tube in 3D space.
 * It is defined by a central axis (a ray) and a radius.
 * This class provides methods to calculate the normal vector at a given point
 * on the tube's surface and to find intersection points of a ray with the tube.
 *
 * @author Tehila Shraga and Tova Tretiak
 */
public class Tube extends RadialGeometry {

    /** The central axis of the tube, represented as a ray */
    protected final Ray axis;

    /**
     * Constructor to initialize a new Tube with the specified radius and central axis.
     *
     * @param radius The radius of the tube (must be positive).
     * @param axis The central axis of the tube, represented as a {@link Ray}.
     */
    public Tube(double radius, Ray axis) {
        super(radius);
        this.axis = axis;
    }
    /**
     * Calculates the normal vector to the tube at a given point on its surface.
     *
     * <p>Algorithm:</p>
     * <ol>
     *   <li>Project the vector from the tube's axis head to the given point onto the axis direction.</li>
     *   <li>If the projection length is zero, the point is perpendicular to the axis head.</li>
     *   <li>Otherwise, calculate the projection point on the axis and subtract it from the given point.</li>
     *   <li>Normalize the resulting vector to get the normal.</li>
     * </ol>
     *
     * @param point The point on the tube's surface.
     * @return The normalized normal vector at the given point.
     */
    @Override
    public Vector getNormal(Point point) {
        // Calculate the projection length of the vector from the axis head to the point
        double t = axis.getDirection().dotProduct(point.subtract(axis.getHead()));

        // If the projection length is zero, the point is perpendicular to the axis head
        if (isZero(t)) {
            return point.subtract(axis.getHead()).normalize();
        }

        // Calculate the projection point on the axis and subtract it from the given point
        return point.subtract(axis.getPoint(t)).normalize();
    }

    /**
     * Returns the central axis of the tube.
     *
     * @return The axis of the tube.
     */
    public Ray getAxis() {
        return axis;
    }

    /**
     * Finds the intersection points of a ray with the tube.
     *
     * <p>Algorithm:</p>
     * <ol>
     *   <li>Calculate the vector from the ray's origin to the tube's axis head.</li>
     *   <li>Check if the ray is parallel to the tube's axis or lies on it.</li>
     *   <li>Use the quadratic equation to find intersection points based on the tube's geometry.</li>
     *   <li>Filter out points that are behind the ray's origin or duplicate points.</li>
     *   <li>Sort the intersection points by distance from the ray's origin.</li>
     * </ol>
     *
     * @param ray The ray to check for intersections.
     * @return A list of intersection points, or {@code null} if there are no intersections.
     */
    @Override
    public List<Point> findIntersections(Ray ray) {
        Point p = ray.getHead(); // Ray's origin
        Vector v = ray.getDirection(); // Ray's direction
        Point p0 = axis.getHead(); // Tube's axis head
        Vector v0 = axis.getDirection(); // Tube's axis direction

        // Calculate the vector from the ray's origin to the tube's axis head
        Vector deltaP;
        try {
            deltaP = p.subtract(p0);
        } catch (IllegalArgumentException e) {
            // Special case: ray starts at the axis head
            if (isZero(v.dotProduct(v0))) {
                return List.of(ray.getPoint(radius));
            }
            return null;
        }

        // Check if the ray is parallel to the tube's axis
        try {
            Vector cross = deltaP.crossProduct(v0);
            if (isZero(cross.length())) {
                if (isZero(v.dotProduct(v0))) {
                    return List.of(ray.getPoint(radius));
                }
                return null;
            }
        } catch (IllegalArgumentException e) {
            // Special case: deltaP is parallel to v0
            if (isZero(v.dotProduct(v0))) {
                return List.of(ray.getPoint(radius));
            }
            return null;
        }

        // Check if the ray is parallel to the tube's axis
        if (Math.abs(v0.dotProduct(v)) > v0.length() * v.length() - 1e-10) {
            return null;
        }

        // Calculate coefficients for the quadratic equation
        Vector vCrossV0 = v.crossProduct(v0);
        Vector deltaPCrossV0 = deltaP.crossProduct(v0);

        double a = alignZero(vCrossV0.lengthSquared());
        double b = alignZero(2 * vCrossV0.dotProduct(deltaPCrossV0));
        double c = alignZero(deltaPCrossV0.lengthSquared() - radius * radius * v0.lengthSquared());

        // Calculate the discriminant of the quadratic equation
        double discriminant = alignZero(b * b - 4 * a * c);

        // If the discriminant is zero or negative, there are no intersections
        if (isZero(discriminant) || discriminant < 0 || isZero(a)) {
            return null;
        }

        // Calculate the two solutions of the quadratic equation
        double sqrtDisc = alignZero(Math.sqrt(discriminant));
        double denom = alignZero(2 * a);

        double t1 = alignZero((-b + sqrtDisc) / denom);
        double t2 = alignZero((-b - sqrtDisc) / denom);

        // Filter out points that are behind the ray's origin or duplicate points
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

        // If no valid intersection points are found, return null
        if (temp.isEmpty()) {
            return null;
        }

        // Sort the intersection points by distance from the ray's origin
        temp.sort(Comparator.comparingDouble(AbstractMap.SimpleEntry::getKey));

        // Extract the points from the sorted list
        List<Point> result = new java.util.LinkedList<>();
        for (AbstractMap.SimpleEntry<Double, Point> entry : temp) {
            result.add(entry.getValue());
        }

        return result;
    }
}