package geometries;

import primitives.*;

import java.util.List;

import static primitives.Util.isZero;

/**
 * The Cylinder class represents a finite cylindrical shape in 3D space.
 * @author Tehila Shraga and Tova Tretiak
 */
public class Cylinder extends Tube{

    /** The height of the cylinder */
    private final double height;

    /**
     * Constructs a new Cylinder with the specified radius, central axis, and height.
     * @param radius The radius of the cylinder (must be positive).
     * @param axis The central axis of the cylinder, represented as a {@link Ray}.
     * @param height The height of the cylinder (must be positive).
     */
    public Cylinder(double radius, Ray axis, double height) {
        super(radius, axis);
        if (isZero(height)||height<0)
            throw new IllegalArgumentException("height must be positive");
        this.height = height;
    }

    @Override
    public List<Point> findIntersections(Ray ray) {
        return null; // The Cylinder class does not implement intersection logic
    }

    @Override
    public Vector getNormal(Point point) {

        Point p0 = axis.getHead(); // Base center
        Vector vector = axis.getDirection(); // Axis direction
        if (p0.equals(point)) {
            // Point is on the bottom base center
            return vector.scale(-1);
        }
        if (p0.add(vector.scale(height)).equals(point)) {
            // Point is on the top base center
            return vector;
        }
        // Projection of P onto the axis
        double t = point.subtract(p0).dotProduct(vector);
        if (isZero(t)) {
            // Point is on the bottom base center
            return vector.scale(-1);
        } else if (isZero(t - height)) {
            // Point is on the top base center
            return vector;
        } else {
            // Point is on the lateral surface
            Point o = p0.add(vector.scale(t)); // Closest point on axis
            return point.subtract(o).normalize();
        }
    }

    /***
     * Returns the height of the cylinder.
     * @return height
     */
    public double getHeight() {
        return height;
    }
}
