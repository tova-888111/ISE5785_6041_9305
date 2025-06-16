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
     * @param ray The ray to check for intersections.
     * @param maxDistance The maximum distance from the ray's origin to consider for intersections.
     * @return A list of intersection points, or {@code null} if there are no intersections.
     */
    @Override
    public List<Intersection> calculateIntersectionsHelper(Ray ray, double maxDistance) {
        Point p = ray.getHead(); // Ray's origin point
        Vector v = ray.getDirection(); // Ray's direction vector
        Point p0 = axis.getHead(); // Tube's axis origin
        Vector v0 = axis.getDirection(); // Tube's axis direction

        // Compute the vector from the ray's origin to the tube's axis origin
        Vector deltaP;
        try {
            deltaP = p.subtract(p0);
        } catch (IllegalArgumentException e) {
            // Special case: ray starts at the axis head
            if (isZero(v.dotProduct(v0))) {
                Point intersection = ray.getPoint(radius);
                if (intersection.distance(p) < maxDistance) {
                    return List.of(new Intersection(this, intersection));
                }
                return null;
            }
            return null;
        }

        // Check if the ray is perpendicular to the tube's axis and starts on the axis
        try {
            Vector cross = deltaP.crossProduct(v0);
            if (isZero(cross.length())) {
                if (isZero(v.dotProduct(v0))) {
                    Point intersection = ray.getPoint(radius);
                    if (intersection.distance(p) < maxDistance) {
                        return List.of(new Intersection(this, intersection));
                    }
                }
                return null;
            }
        } catch (IllegalArgumentException e) {
            // Special case: deltaP is parallel to v0
            if (isZero(v.dotProduct(v0))) {
                Point intersection = ray.getPoint(radius);
                if (intersection.distance(p) < maxDistance) {
                    return List.of(new Intersection(this, intersection));
                }
            }
            return null;
        }

        // If the ray is almost parallel to the tube's axis, no intersection
        if (Math.abs(v0.dotProduct(v)) > v0.length() * v.length() - 1e-10) {
            return null;
        }

        // Compute the quadratic equation coefficients for intersection
        Vector vCrossV0 = v.crossProduct(v0);
        Vector deltaPCrossV0 = deltaP.crossProduct(v0);

        double a = alignZero(vCrossV0.lengthSquared());
        double b = alignZero(2 * vCrossV0.dotProduct(deltaPCrossV0));
        double c = alignZero(deltaPCrossV0.lengthSquared() - radius * radius * v0.lengthSquared());

        // Calculate the discriminant
        double discriminant = alignZero(b * b - 4 * a * c);

        // If no real roots, then there is no intersection
        if (isZero(discriminant) || discriminant < 0 || isZero(a)) {
            return null;
        }

        // Solve the quadratic equation
        double sqrtDisc = alignZero(Math.sqrt(discriminant));
        double denom = alignZero(2 * a);

        double t1 = alignZero((-b + sqrtDisc) / denom);
        double t2 = alignZero((-b - sqrtDisc) / denom);

        // Collect valid intersection points within the maxDistance (excluding exactly equal)
        List<AbstractMap.SimpleEntry<Double, Point>> temp = new java.util.LinkedList<>();
        final double EPSILON = 1e-10;

        if (t1 > EPSILON && t1 < maxDistance) {
            temp.add(new AbstractMap.SimpleEntry<>(t1, ray.getPoint(t1)));
        }

        if (t2 > EPSILON && t2 < maxDistance) {
            Point point2 = ray.getPoint(t2);
            boolean alreadyExists = false;

            // Avoid adding duplicate intersection points
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

        // If no valid intersections found, return null
        if (temp.isEmpty()) {
            return null;
        }

        // Sort intersections by distance from the ray origin
        temp.sort(Comparator.comparingDouble(AbstractMap.SimpleEntry::getKey));

        // Convert to Intersection objects
        List<Intersection> result = new java.util.LinkedList<>();
        for (AbstractMap.SimpleEntry<Double, Point> entry : temp) {
            result.add(new Intersection(this, entry.getValue()));
        }

        return result;
    }

    /**
     * Returns the axis-aligned bounding box (AABB) of the tube.
     * The bounding box is defined by the minimum and maximum coordinates
     * of the tube's axis extended by its radius along each axis (X, Y, Z).
     *
     * @return the axis-aligned bounding box (AABB) of the tube
     */
    @Override
    public AABB getBoundingBox() {
        // Calculate the axis-aligned bounding box (AABB) of the tube
        final double EXTENT = Double.MAX_VALUE / 4; // A large value to extend the axis in both directions
        final double r = radius;// Tube's radius

        // Get the axis point and direction
        Point axisPoint = axis.getHead();
        Vector dir = axis.getDirection().normalize();

        // Calculate the far point along the axis in both directions
        Point farPoint = axis.getPoint(EXTENT);

        // Calculate the minimum and maximum coordinates for the bounding box
        double minX = Math.min(axisPoint.getX(), farPoint.getX()) - r;
        double maxX = Math.max(axisPoint.getX(), farPoint.getX()) + r;

        // Calculate the minimum and maximum coordinates for Y and Z
        double minY = Math.min(axisPoint.getY(), farPoint.getY()) - r;
        double maxY = Math.max(axisPoint.getY(), farPoint.getY()) + r;

        // Calculate the minimum and maximum coordinates for Z
        double minZ = Math.min(axisPoint.getZ(), farPoint.getZ()) - r;
        double maxZ = Math.max(axisPoint.getZ(), farPoint.getZ()) + r;

        // Create the minimum and maximum points of the bounding box
        Point min = new Point(minX, minY, minZ);
        Point max = new Point(maxX, maxY, maxZ);

        // Create and return the AABB using the min and max points
        return new AABB(min, max);
    }

}