package primitives;

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

}
