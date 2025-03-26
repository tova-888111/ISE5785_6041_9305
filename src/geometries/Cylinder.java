package geometries;

import primitives.*;

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
    public Vector getNormal(Point point) {

        return null;
    }

}
