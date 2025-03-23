package geometries;

import primitives.*;

import static primitives.Util.isZero;

/**
 * The Cylinder class represents a finite cylindrical shape in 3D space.
 * A cylinder is defined by a central axis (represented as a {@link Ray}), a radius, and a height.
 * This class extends {@link Tube}, which represents an infinite tube.
 * Unlike a tube, a cylinder has two circular bases that cap its top and bottom at a fixed height.
 * @author Naomi Ben Shabat and Tova Tretiak
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
        return super.getNormal(point);
    }

}
