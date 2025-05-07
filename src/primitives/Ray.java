package primitives;

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
     * Constructor to initialize new ray with a given starting point and direction.
     * @param head The starting point of the ray.
     * @param direction The direction vector of the ray, which will be normalized.
     */
    public Ray(Point head, Vector direction) {
        this.head = head;
        this.direction = direction.normalize();
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

    public Point findClosestPoint(List<Point> points) {
       if (points==null){
           return null;
       }

       Point closesPoint = points.get(0);
       double minDistanceSquared = head.distanceSquared(closesPoint);
        for (int i = 1; i < points.size(); i++) {
            Point point = points.get(i);
            double distanceSquared = head.distanceSquared(point);
            if (distanceSquared < minDistanceSquared) {
                closesPoint = point;
                minDistanceSquared = distanceSquared;
            }
        }
            return closesPoint;
    }
}
