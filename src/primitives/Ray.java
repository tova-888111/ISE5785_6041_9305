package primitives;

import geometries.Intersectable.Intersection;

import java.util.List;

import static primitives.Util.isZero;

/**
 * This class represents a ray in 3D space.
 * @author Tehila Shraga and Tova Tretiak
 */
public class Ray {

    /**
     * The starting point of the ray.
     */
    final private Point head;

    /**
     * The direction vector of the ray (always normalized).
     */
    final private Vector direction;

    /**
     * A small constant used to create a delta vector for shadow rays.
     * This is used to avoid self-intersection in shadow calculations.
     */
    private static final double DELTA = 0.1;
    /**
     * Constructor to initialize new ray with a given starting point and direction.
     * @param head The starting point of the ray.
     * @param direction The direction vector of the ray, which will be normalized.
     */
    public Ray(Point head, Vector direction) {
        this.head = head;
        this.direction = direction.normalize();
    }

    /**
     * Constructor to initialize a new ray with a given starting point, direction, and normal vector.
     * This constructor is used to create shadow rays.
     * @param head The starting point of the ray.
     * @param direction The direction vector of the ray, which will be normalized.
     * @param normal The normal vector at the point of intersection.
     */
    public Ray(Point head, Vector direction, Vector normal) {
        // Normalize the direction vector
        this.direction = direction.normalize();
        // Calculate the delta vector based on the normal vector and the direction
        // If the dot product of the normal and direction is positive, use DELTA
        Vector delta= normal.scale(normal.dotProduct(direction)>0? DELTA : -DELTA);
        this.head=head.add(delta);
    }

    @Override
    public String toString() {
        return "Ray{" + "head=" + head.toString() + ", direction=" + direction.toString() + '}';
    }

    @Override
    public final boolean equals(Object obj) {
        if (this==obj) return true;
        if (!(obj instanceof Ray ray))
            return false;
        return head.equals(ray.head) && direction.equals(ray.direction);
    }

    /***
     * Getter for the head of the ray.
     * @return the head of the ray
     */
    public Point getHead() {
        return head;
    }

    /***
     * Getter for the direction of the ray.
     * @return the direction of the ray
     */
    public Vector getDirection() {
        return direction;
    }

    /***
     * Returns a point on the ray at a distance t from the head.
     * @param distance The distance from the head to the point on the ray.
     * @return A new point located at distance t from the head of the ray.
     */
    public Point getPoint(double distance) {
        if (isZero(distance)) {
            return head;
        }
        return head.add(direction.scale(distance));
    }

    /***
     * Finds the closest point to the head of the ray from a list of points.
     * @param points The list of points to search for the closest point.
     * @return The closest point to the head of the ray, or null if the list is empty.
     */
    public Point findClosestPoint(List<Point> points) {
        return points == null ? null
                : findClosestIntersection(points.stream().map(p -> new Intersection(null, p)).toList()).point;
    }

    /**
     * Finds the closest intersection point from a list of intersections.
     * This method iterates through the list of intersections
     * and finds the one that is closest to the head of the ray.
     * @param intersections The list of intersections to search for the closest intersection.
     * @return The closest intersection to the head of the ray, or null if the list is empty.
     */
    public Intersection findClosestIntersection(List<Intersection> intersections) {
        // Check if the list of points is empty
       if (intersections==null){
           return null;
       }

        // closest point is the first point in the list
        // This variable will be used to store the closest point found so far
       Intersection closesPoint = intersections.getFirst();

        // Calculate the distance squared from the head to the first point
        // This variable will be used to store the minimum distance squared found so far
       double minDistanceSquared = head.distanceSquared(closesPoint.point);

       // Variable to store the distance squared from the head to the current point
       double distanceSquared;

       // Iterate through the list of points starting from the second point
        for (int i = 1; i < intersections.size(); i++) {

            // Get the current point from the list
            Intersection point = intersections.get(i);

            // Calculate the distance squared from the head to the current point
            distanceSquared = head.distanceSquared(point.point);

            // Check if the distance squared from the head to the current point is less than the minimum distance squared found so far
            if (distanceSquared < minDistanceSquared) {
                closesPoint = point;
                minDistanceSquared = distanceSquared;
            }
        }
            return closesPoint;
    }
}
