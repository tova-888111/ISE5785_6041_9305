package geometries;

import lighting.LightSource;
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
     * Default constructor for the Intersectable class.
     */
    public Intersectable() {
    }

    /**
     * Finds the intersection points of a ray with the geometric object.
     * This method returns a list of intersection points, which may be empty if there are no intersections.
     * The list may contain multiple points if the ray intersects the object at multiple locations.
     *
     * @param ray the ray to check for intersections
     * @return a list of intersection points, or {@code null} if there are no intersections
     */
    public final List<Point> findIntersections(Ray ray) {
        var list = calculateIntersections(ray);
        return list == null ? null : list.stream().map(intersection -> intersection.point).toList();
    }

    /**
     * Finds the intersection points of a ray with the geometric object.
     * This method returns a list of Intersection objects, which contain both the geometry and the intersection point.
     * The list may contain multiple intersections if the ray intersects the object at multiple locations.
     *
     * @param ray the ray to check for intersections
     * @param maxDistance the maximum distance from the ray's origin to consider for intersections
     * @return a list of Intersection objects, or {@code null} if there are no intersections
     */
    protected abstract List<Intersection> calculateIntersectionsHelper(Ray ray, double maxDistance);

    /**
     * Finds the intersection points of a ray with the geometric object.
     * This method is a wrapper around the calculateIntersectionsHelper method,
     * providing a default maximum distance of positive infinity.
     *
     * @param ray the ray to check for intersections
     * @param maxDistance the maximum distance from the ray's origin to consider for intersections
     * @return a list of Intersection objects, or {@code null} if there are no intersections
     */
    public final List<Intersection> calculateIntersections(Ray ray, double maxDistance) {
        return calculateIntersectionsHelper(ray, maxDistance);
    }

    /**
     * Finds the intersection points of a ray with the geometric object.
     * This method is a wrapper around the calculateIntersectionsHelper method,
     * providing a default maximum distance of positive infinity.
     *
     * @param ray the ray to check for intersections
     * @return a list of Intersection objects, or {@code null} if there are no intersections
     */
    public final List<Intersection> calculateIntersections(Ray ray) {
        return calculateIntersections(ray, Double.POSITIVE_INFINITY);
    }

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
        /** The material of the intersected object */
        public final Material material;
        /** The normal vector at the intersection point */
        public Vector normal;
        /** The direction vector of the ray from the camera */
        public Vector v;
        /** The dot product of the ray direction and the normal vector */
        public double vNormal;
        /** The light source illuminating the intersection point */
        public LightSource light;
        /** The direction vector of the light source */
        public Vector l;
        /** The dot product of the light direction and the normal vector */
        public double lNormal;

        /**
         * Constructor to create an intersection object.
         * This constructor initializes the geometry, intersection point, and material.
         * @param geometry The geometry of the intersected object.
         * @param point The intersection point of the ray with the geometry.
         */
        public Intersection(Geometry geometry, Point point) {
            this.geometry = geometry;
            this.point = point;
            // Initialize the material of the intersected object, if the geometry is not null
            if (geometry!=null)
                this.material = geometry.getMaterial();
            else
                this.material = null;
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