package geometries;

import primitives.*;

/**
 * The {@code Sphere} class represents a three-dimensional sphere in 3D space.
 * A sphere is defined by a central point and a radius.
 * This class extends {@link RadialGeometry}, which represents geometric shapes with a radius.
 * @author Naomi Ben Shabat and Tova Tretiak
 */
public class Sphere extends RadialGeometry{

    /** The center point of the sphere */
    private final Point center;

    /**
     * Constructor to initialize sphere with a given radius and center point.
     * @param radius the radius of the sphere
     * @param center the center point of the sphere
     */
    public Sphere(double radius, Point center) {
        super(radius);
        this.center = center;
    }

    @Override
    public Vector getNormal(Point point) {
        return null;
    }
}
