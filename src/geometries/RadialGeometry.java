package geometries;

import static primitives.Util.isZero;

/**
 * Abstract class representing geometric shapes that have a radius.
 * This class serves as a base for all radial geometries, such as spheres, tubes, and cylinders.
 * It provides a common property (radius) and ensures that the radius is valid (positive and non-zero).
 *
 *@author Tehila Shraga and Tova Tretiak
 */
public abstract class RadialGeometry extends Geometry {

    /**
     * The radius of the geometric shape.
     * This value must be positive and non-zero.
     */
    protected final double radius;

    /**
     * Constructor to initialize a RadialGeometry object with the given radius.
     * Ensures that the radius is valid (positive and non-zero).
     *
     * @param radius the radius of the geometric shape
     * @throws IllegalArgumentException if the radius is zero or negative
     */
    public RadialGeometry(double radius) {
        // Validate that the radius is positive and non-zero
        if (isZero(radius) || radius < 0) {
            throw new IllegalArgumentException("radius must be positive");
        }
        this.radius = radius;
    }

    /**
     * Returns the radius of the geometric shape.
     * This method provides access to the radius property for subclasses or external use.
     *
     * @return the radius of the geometric shape
     */
    public double getRadius() {
        return radius;
    }
}
