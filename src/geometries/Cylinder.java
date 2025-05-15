package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;
import java.util.List;
import static primitives.Util.isZero;

/**
 * The Cylinder class represents a finite cylindrical shape in 3D space.
 * It extends the {@link Tube} class, adding a height property to define the finite length of the cylinder.
 * The cylinder is defined by its radius, central axis (as a {@link Ray}), and height.
 *
 * @author Tehila Shraga and Tova Tretiak
 */
public class Cylinder extends Tube {

    /** The height of the cylinder. This value must be positive. */
    private final double height;

    /**
     * Constructs a new Cylinder with the specified radius, central axis, and height.
     * Validates that the height is positive and non-zero.
     *
     * @param radius The radius of the cylinder (must be positive).
     * @param axis   The central axis of the cylinder, represented as a {@link Ray}.
     * @param height The height of the cylinder (must be positive).
     * @throws IllegalArgumentException if the height is zero or negative.
     */
    public Cylinder(double radius, Ray axis, double height) {
        // Call the constructor of the Tube class
        super(radius, axis);
        // Validate the height
        if (isZero(height) || height < 0) {
            throw new IllegalArgumentException("height must be positive");
        }
        this.height = height;
    }

    /**
     * Computes the normal vector to the cylinder at a given point.
     * The normal vector is perpendicular to the surface of the cylinder at the specified point.
     * The method handles three cases:
     * 1. If the point is on the bottom base center, the normal vector points in the opposite direction of the axis.
     * 2. If the point is on the top base center, the normal vector points in the same direction as the axis.
     * 3. If the point is on the lateral surface, the normal vector is calculated as the difference between the point and the projection of the point onto the axis.
     *
     * @return A normalized vector representing the normal to the cylinder at the given point.
     */
    @Override
    public Vector getNormal(Point point) {
        // Base center
        Point p0 = axis.getHead();
        // Axis direction
        Vector vector = axis.getDirection();

        // Check if the point is on the bottom base center
        if (p0.equals(point)) {
            return vector.scale(-1);
        }

        // Check if the point is on the top base center
        if (axis.getPoint(height).equals(point)) {
            return vector;
        }

        // Projection of the point onto the axis
        double t = axis.getDirection().dotProduct(point.subtract(axis.getHead()));
        // Calculate the projection point on the axis
        // Point is on the bottom base center
        if (isZero(t)) {
            return vector.scale(-1);
            // Point is on the top base center
        } else if (isZero(t - height)) {
            return vector;
        } else {
            // Point is on the lateral surface
            return point.subtract(axis.getPoint(t)).normalize();
        }
    }

    /**
     * Returns the height of the cylinder.
     * Provides access to the height property for external use.
     *
     * @return The height of the cylinder.
     */
    public double getHeight() {
        return height;
    }

    /**
     * Finds the intersection points of a ray with the cylinder.
     * This method is not implemented in the current version of the class.
     *
     * @param ray The ray to check for intersections.
     * @return {@code null} as the intersection logic is not implemented.
     */
    @Override
    public List<Intersection> calculateIntersectionsHelper(Ray ray) {
        return null; // The Cylinder class does not implement intersection logic
    }
}
