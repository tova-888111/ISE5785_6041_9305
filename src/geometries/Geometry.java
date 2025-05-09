package geometries;

import primitives.*;

/**
 * An abstract class representing a geometric shape in 3D space.
 * This class serves as a base for all geometric objects and provides a common interface for intersection calculations.
 * It implements the {@link Intersectable} interface, ensuring that all geometric shapes can calculate ray intersections.
 *
 *@author Tehila Shraga and Tova Tretiak
 */
public abstract class Geometry implements Intersectable {

    /**
     * Default constructor for the Geometry class.
     * This constructor is used by subclasses to initialize a geometric object.
     */
    public Geometry() {
    }

    /**
     * Computes the normal vector to the geometric shape at a given point.
     * The normal vector is perpendicular to the surface of the shape at the specified point.
     * Each subclass must provide its own implementation of this method based on its specific geometry.
     *
     * @param point the point at which the normal is calculated
     */
    public abstract Vector getNormal(Point point);

}
