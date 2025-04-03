package geometries;
import primitives.*;

/**
 * Abstract class representing a geometric shape in 3D space.
 * This class serves as a base for all geometric objects.
 * @author Tehila Shraga and Tova Tretiak
 */
public abstract class Geometry implements Intersectable {

    /**
     * Default constructor for the Geometry class.
     */
    public Geometry() {
    }
    /**
     * Computes the normal vector to the geometric shape at a given point.
     * @param point the point at which the normal is calculated
     * @return a normalized vector representing the normal to the shape at the given point
     */
    public abstract Vector getNormal(Point point);

}
