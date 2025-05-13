package geometries;

import primitives.*;
import java.util.LinkedList;
import java.util.List;

/**
 * The Geometries class represents a collection of geometric shapes.
 * It extends the {@link Intersectable}, allowing for intersection calculations with rays.
 * This class provides methods to add geometries to the collection and find intersection points of a ray with all geometries in the collection.
 *
 * @author Tehila Shraga and Tova Tretiak
 */
public class Geometries extends Intersectable {

    /** A list of intersectable geometries. */
    private final List<Intersectable> geometries = new LinkedList<>();

    /**
     * Default constructor for the Geometries class.
     * Initializes an empty collection of geometries.
     */
    public Geometries() {
        // No initialization needed as the list is already initialized
    }

    /**
     * Constructor for the Geometries class.
     * Allows initializing the collection with a set of geometries.
     *
     * @param geometries the intersectable geometries to be added to the collection
     */
    public Geometries(Intersectable... geometries) {
        add(geometries); // Add the provided geometries to the collection
    }

    /**
     * Adds one or more intersectable geometries to the collection.
     * This method allows dynamically adding geometries to the existing collection.
     *
     * @param geometries the geometries to be added
     */
    public void add(Intersectable... geometries) {
        this.geometries.addAll(List.of(geometries)); // Add all provided geometries to the list
    }

    /**
     * Finds the intersection points of a ray with all geometries in the collection.
     * Iterates through each geometry in the collection and collects all intersection points.
     * Ensures that duplicate points are not added to the result.
     *
     * @param ray the ray to check for intersections
     * @return a list of intersection points, or {@code null} if there are no intersections
     */
    @Override
    public List<Intersection> calculateIntersectionsHelper(Ray ray) {
        // List to store intersection points
        List<Intersection> intersections = null;

        // Iterate through each geometry in the collection
        for (Intersectable geometry : geometries) {
            // Find intersections of the ray with the current geometry
            List<Intersection> tempIntersections = geometry.calculateIntersectionsHelper(ray);

            // If intersections are found, add them to the result list
            if (tempIntersections != null) {
                if (intersections==null) {
                    intersections = new LinkedList<>();
                }
                intersections.addAll(tempIntersections);
            }
        }

        // Return null if no intersections are found, otherwise return the list of points
       return intersections;
    }

    /**
     * Returns the list of geometries in the collection.
     * Provides access to the internal list of geometries for external use.
     *
     * @return the list of intersectable geometries
     */
    public List<Intersectable> getGeometries() {
        return geometries;
    }
}