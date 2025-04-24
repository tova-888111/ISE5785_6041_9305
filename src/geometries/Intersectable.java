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
     *
     * <p>Algorithm:</p>
     * <ol>
     *   <li>Each implementing class defines its own logic to calculate intersections.</li>
     *   <li>The method returns a list of points where the ray intersects the object.</li>
     *   <li>If there are no intersections, the method returns {@code null}.</li>
     * </ol>
     *
     * @param ray the ray to check for intersections
     *            <ul>
     *              <li>The ray is defined by an origin point and a direction vector.</li>
     *              <li>It is used to calculate potential intersection points with the object.</li>
     *            </ul>
     * @return a list of intersection points, or {@code null} if there are no intersections
     *         <ul>
     *           <li>The list contains {@link Point} objects representing the intersection points.</li>
     *           <li>If the ray does not intersect the object, the method returns {@code null}.</li>
     *         </ul>
     */
    List<Point> findIntersections(Ray ray);
}
