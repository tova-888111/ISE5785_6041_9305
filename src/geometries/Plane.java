package geometries;

import primitives.*;

/**
 * The class represents a plane in 3D space.
 * @author Tehila Shraga and Tova Tretiak
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
        try{
            Vector v1=q2.subtract(q1);
            Vector v2=q3.subtract(q1);
            v1.crossProduct(v2);
            this.normal=v1.crossProduct(v2).normalize();
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("The points are collinear");
        }

    }

    @Override
    public Vector getNormal(Point point) {
        return normal;
    }
}
