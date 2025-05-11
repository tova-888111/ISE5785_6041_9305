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
public abstract class Intersectable {

    /**
     * Finds the intersection points of a ray with the geometric object.
     * This method returns a list of intersection points, which may be empty if there are no intersections.
     * The list may contain multiple points if the ray intersects the object at multiple locations.
     *
     * @param ray the ray to check for intersections
     * @return a list of intersection points, or {@code null} if there are no intersections
     */
    public abstract List<Point> findIntersections(Ray ray);


    /**
     * An inner class representing an intersection between a ray and a geometric object.
     * It contains the geometry of the object and the intersection point.
     * This class is used to encapsulate the result of an intersection calculation.
     */
    public static class Intersection {
        /** The geometry of the intersected object */
        public final Geometry geometry;
        /** The intersection point of the ray with the geometry */
        public final Point point;

        /**
         * Constructor to create an intersection object.
         * @param geometry The geometry of the intersected object.
         * @param point The intersection point of the ray with the geometry.
         */
        public Intersection(Geometry geometry, Point point) {
            this.geometry = geometry;
            this.point = point;
        }

        /**
         * Checks if two intersection objects are equal.
         * @param obj The object to compare with this intersection.
         * @return true if the two intersections are equal, false otherwise.
         */
        @Override
        public boolean equals(Object obj) {
            // Check if the object is the same instance
            if (this == obj) return true;
            // Check if the object is null or of a different class
            if (!(obj instanceof Intersection intersection))
                return false;
            // Check if the geometry and point are equal
            return this.geometry==intersection.geometry && this.point.equals(intersection.point);
        }

        /**
         * An override to toString method to provide a string representation of the intersection object.
         * @return A string representation of the intersection object.
         */
        @Override
        public String toString() {
            return "Intersection{" +
                    "geometry=" + geometry +
                    ", point=" + point +
                    '}';
        }
    }
}
