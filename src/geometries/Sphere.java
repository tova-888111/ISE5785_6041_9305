package geometries;

import primitives.*;

import java.util.List;

import static primitives.Util.alignZero;

/**
 * Represents a three-dimensional sphere in 3D space.
 * A sphere is defined by its center point and radius.
 * This class extends the {@link RadialGeometry} class.
 * It provides methods to calculate the normal vector at a given point on the sphere
 * and to find intersection points of a ray with the sphere.
 *
 * @author Tehila Shraga and Tova Tretiak
 */
public class Sphere extends RadialGeometry {

    /** The center point of the sphere in 3D space */
    private final Point center;

    /**
     * Constructs a sphere with a specified radius and center point.
     *
     * @param radius the radius of the sphere (must be positive)
     * @param center the center point of the sphere
     * @throws IllegalArgumentException if the radius is not positive
     */
    public Sphere(double radius, Point center) {
        super(radius);
        this.center = center;
    }

    /**
     * Calculates the normal vector to the sphere at a given point on its surface.
     * The normal vector is the vector from the center of the sphere to the given point,
     * normalized to have a length of 1.
     *
     * @param point the point on the surface of the sphere
     * @return the normalized normal vector at the given point
     * @throws IllegalArgumentException if the point is not on the surface of the sphere
     */
    @Override
    public Vector getNormal(Point point) {
        return point.subtract(center).normalize();
    }

    /**
     * Retrieves the center point of the sphere.
     *
     * @return the center point of the sphere
     */
    public Point getCenter() {
        return center;
    }

    /**
     * Finds the intersection points of a ray with the sphere.
     * The method calculates the points where the given ray intersects the sphere.
     *
     * @param ray the ray to check for intersections
     * @return a list of intersection points, or {@code null} if there are no intersections
     */
    @Override
    public List<Intersection> calculateIntersectionsHelper(Ray ray) {
        Vector v = ray.getDirection();
        Point p0 = ray.getHead();

        // Special case: if the ray starts at the center of the sphere
        if (p0.equals(center)) {
            return List.of(new Intersection(this,ray.getPoint(radius)));
        }

        // Calculate the vector from the ray's origin to the sphere's center
        Vector u = center.subtract(p0);

        // Project the vector onto the ray's direction
        double tm = alignZero(v.dotProduct(u));

        // Calculate the squared distance from the sphere's center to the ray
        double d2 = alignZero(u.lengthSquared() - tm * tm);

        // Calculate the squared radius of the sphere
        double r2 = alignZero(radius * radius);

        // If the distance is greater than the radius, there are no intersections
        if (alignZero(d2 - r2) > 0) {
            return null;
        }

        // Calculate the distance from the projection point to the intersection points
        double th = alignZero(Math.sqrt(r2 - d2));

        // If th is zero, the ray is tangent to the sphere, so no intersection
        if (alignZero(th) == 0) {
            return null;
        }

        // Calculate the distances to the intersection points along the ray
        double t1 = alignZero(tm - th);
        double t2 = alignZero(tm + th);

        // Return the intersection points that are in front of the ray's origin
        if (t1 <= 0 && t2 <= 0) return null;
        if (t1 > 0 && t2 > 0) return List.of(new Intersection(this,ray.getPoint(t1)), new Intersection(this,ray.getPoint(t2)));
        if (t1 > 0) return List.of(new Intersection(this,ray.getPoint(t1)));
        if (t2 > 0) return List.of(new Intersection(this,ray.getPoint(t2)));

        return null;
    }
}