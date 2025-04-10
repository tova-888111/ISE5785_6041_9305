package geometries;

import primitives.*;
import java.util.LinkedList;
import java.util.List;

/**
 * The Geometries class represents a collection of geometric shapes.
 * It implements the Intersectable interface, allowing for intersection calculations with rays.
 * @author Tehila Shraga and Tova Tretiak
 */
public class Geometries implements Intersectable {

    /** A list of intersectable geometries.*/
    private final List<Intersectable> geometries= new LinkedList<>();

    /**
     * Default constructor for the Geometries class.
     */
    public Geometries() {
        // No initialization needed
    }

    /**
     * Constructor for the Geometries class.
     * @param geometries the intersectable geometries to be added to the collection
     */
    public Geometries(Intersectable... geometries){
        add(geometries);
    }

    /**
     * Adds one or more intersectable geometries to the collection.
     * @param geometries the geometries to be added
     */
    public void add(Intersectable... geometries) {
        this.geometries.addAll(List.of(geometries));
    }

    @Override
    public List<Point> findIntersections(Ray ray) {
        List<Point> intersections = new LinkedList<>();

        for (Intersectable geometry : geometries) {
            List<Point> tempIntersections = geometry.findIntersections(ray);
            if (tempIntersections != null) {
                for (Point p : tempIntersections) {
                    if (!intersections.contains(p)) {
                        intersections.add(p);
                    }
                }
            }
        }

        return intersections.isEmpty() ? null : intersections;
    }

    /**
     * Returns the list of geometries in the collection.
     * @return the list of intersectable geometries
     */
    public List<Intersectable> getGeometries() {
        return geometries;
    }
}
