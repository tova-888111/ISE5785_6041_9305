package geometries;

import primitives.*;
import java.util.List;

/**
 * The Intersectable interface represents a geometric object that can be intersected by a ray.
 * It provides a method to find the intersection points of a ray with the object.
 * This interface is implemented by all geometric shapes that support ray intersection calculations.
 *
 * @author Tehila Shraga and Tova Tretiak
 */
public interface Intersectable {

    /**
     * Finds the intersection points of a ray with the geometric object.
     * This method returns a list of intersection points, which may be empty if there are no intersections.
     * The list may contain multiple points if the ray intersects the object at multiple locations.
     */
    List<Point> findIntersections(Ray ray);
}
