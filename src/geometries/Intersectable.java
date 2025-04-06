package geometries;

import primitives.*;
import java.util.List;
/**
 * The Intersectable interface represents a geometric object that can be intersected by a ray.
 * @author Tehila Shraga and Tova Tretiak
 */
public interface Intersectable {
    /**
     * Finds the intersections of a ray with the geometric object.
     * @param ray the ray to check for intersections
     * @return a list of intersection points, or null if there are no intersections
     */
    List<Point> findIntersections(Ray ray);
}
