package geometries;

import primitives.*;
import java.util.List;
import static primitives.Util.alignZero;
import static primitives.Util.isZero;

/**
 * The Plane class represents a plane in 3D space.
 * A plane is defined by a point on the plane and a normal vector.
 * This class provides methods to calculate the normal vector at a given point
 * and to find intersection points of a ray with the plane.
 *
 *@author Tehila Shraga and Tova Tretiak
 */
public class Plane extends Geometry {

    /** A point on the plane */
    private final Point q;

    /** The normal vector of the plane */
    private final Vector normal;

    /**
     * Constructor to initialize a plane given a point on the plane and a normal vector.
     * The normal vector is normalized during initialization.
     *
     * @param q a point on the plane
     * @param normal the normal vector to the plane (not necessarily normalized)
     */
    public Plane(Point q, Vector normal) {
        this.q = q;
        this.normal = normal.normalize(); // Ensure the normal vector is a unit vector
    }

    /**
     * Constructs a plane given three non-collinear points.
     * The normal vector is calculated as the cross product of two edges formed by the points.
     *
     * @param q1 the first point
     * @param q2 the second point
     * @param q3 the third point
     * @throws IllegalArgumentException if the points are collinear
     */
    public Plane(Point q1, Point q2, Point q3) {
        this.q = q1;
        try {
            // Calculate two vectors from the three points
            Vector v1 = q2.subtract(q1);
            Vector v2 = q3.subtract(q1);

            // Calculate the normal vector as the cross product of the two vectors
            this.normal = v1.crossProduct(v2).normalize();
        } catch (IllegalArgumentException e) {
            // If the cross product results in a zero vector, the points are collinear
            throw new IllegalArgumentException("The points are collinear");
        }
    }

    /**
     * Returns the normal vector to the plane.
     * Since the plane is flat, the normal vector is constant and does not depend on the point.
     *
     * @param point a point on the plane (not used in this implementation)
     * @return the normal vector to the plane
     */
    @Override
    public Vector getNormal(Point point) {
        return normal;
    }

    /**
     * Finds the intersection points of a ray with the plane.
     * @param ray the ray to check for intersections
     * @param maxDistance the maximum distance from the ray's origin to consider for intersections
     *
     * @return a list containing the intersection point, or {@code null} if there is no intersection
     */
    @Override
    public List<Intersection> calculateIntersectionsHelper(Ray ray, double maxDistance) {
        Point p0 = ray.getHead(); // Ray's origin
        Vector v = ray.getDirection(); // Ray's direction
        Vector n = normal; // Plane's normal vector

        // Calculate the dot product of the normal vector and the ray's direction
        double nv = alignZero(n.dotProduct(v));

        // Special case: if the ray starts exactly at the plane's reference point
        if (q.equals(p0)) {
            return null; // No intersection
        }

        // If the dot product is zero, the ray is parallel to the plane
        if (isZero(nv)) {
            return null; // No intersection
        }

        // Calculate the parameter t for the ray equation
        double t = alignZero(n.dotProduct(q.subtract(p0)) / nv);

        // If t is zero, the ray starts on the plane
        if (isZero(t)) {
            return null; // No intersection
        }

        // If t is negative, the intersection point is behind the ray's origin
        if (t < 0) {
            return null; // No intersection
        }

        // If t is greater than the maximum distance, the intersection point is too far
        if (alignZero( maxDistance-t)<=0) {
            return null; // No intersection
        }

        // Calculate the intersection point using the ray equation
        return List.of(new Intersection(this,ray.getPoint(t)));
    }

    /**
     * Returns the axis-aligned bounding box (AABB) of the plane.
     * This method calculates the bounding box based on the plane's normal vector
     * and the point on the plane.
     * The bounding box is defined by extending the plane's point in the direction of the normal vector
     * and a small thickness to ensure it has a volume in 3D space.
     * * The bounding box is used for spatial acceleration structures like BVH.
     *
     * @return the bounding box of the plane
     */
    @Override
    public AABB getBoundingBox() {
        // Calculate the axis-aligned bounding box (AABB) of the plane
        double min = -Double.MAX_VALUE;
        double max = Double.MAX_VALUE;

        // Determine the minimum and maximum extents based on the normal vector
        double nx = normal.getX();
        double ny = normal.getY();
        double nz = normal.getZ();

        // If the normal vector is zero in a dimension, we set the min and max to extreme values
        double thickness = 0.0001;

        // Calculate the min and max points based on the normal vector
        Point minPoint = new Point(
                nx == 1 || nx == -1 ? q.getX() : min,
                ny == 1 || ny == -1 ? q.getY() : min,
                nz == 1 || nz == -1 ? q.getZ() : min
        );

        Point maxPoint = new Point(
                nx == 1 || nx == -1 ? q.getX() : max,
                ny == 1 || ny == -1 ? q.getY() : max,
                nz == 1 || nz == -1 ? q.getZ() : max
        );

        // Adjust the min and max points based on the normal vector and thickness
        return new AABB(
                new Point(
                        minPoint.getX() - (nx != 0 ? thickness : 0),
                        minPoint.getY() - (ny != 0 ? thickness : 0),
                        minPoint.getZ() - (nz != 0 ? thickness : 0)
                ),
                new Point(
                        maxPoint.getX() + (nx != 0 ? thickness : 0),
                        maxPoint.getY() + (ny != 0 ? thickness : 0),
                        maxPoint.getZ() + (nz != 0 ? thickness : 0)
                )
        );
    }

}
