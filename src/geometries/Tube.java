package geometries;

import primitives.*;

/**
 * The Tube class represents an infinite cylindrical tube in 3D space.
 * A tube is defined by a central axis (represented as a {@link Ray}) and a radius.
 * This class extends {@link RadialGeometry}, which represents geometric shapes with a radius.
 * A tube does not have finite length, meaning it extends infinitely along its axis.
 * @author Naomi Ben Shabat and Tova Tretiak
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
        return null;
    }
}
