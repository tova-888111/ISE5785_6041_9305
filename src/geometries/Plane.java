package geometries;

import primitives.*;

/**
 * The class represents a plane in 3D space.
 * A plane is defined by either a point and a normal vector or by three non-collinear points.
 * This class extends {@link Geometry}, which represents general geometric objects.
 * @author Naomi Ben Shabat and Tova Tretiak
 */
public class Plane extends Geometry {

    /** A point on the plane */
    private final Point q;

    /** The normal vector of the plane */
    private final Vector normal;

    /**
     * Constructor to initialize a plane given a point on the plane and a normal vector.
     * @param q a point on the plane
     * @param normal the normal vector to the plane (normalized in the constructor)
     */
    public Plane(Point q, Vector normal) {
        this.q = q;
        this.normal = normal.normalize();
    }

    /**
     * Constructs a plane given three non-collinear points.
     * @param q1 the first point
     * @param q2 the second point
     * @param q3 the third point
     */
    public Plane(Point q1,Point q2,Point q3) {
        this.q=q1;
        this.normal=null;
    }

    @Override
    public Vector getNormal(Point point) {

        return normal;
    }

    /**
     * Returns the normal vector of the plane.
     * @return the normal vector of the plane
     */
    public Vector getNormal() {

        return normal;
    }
}
