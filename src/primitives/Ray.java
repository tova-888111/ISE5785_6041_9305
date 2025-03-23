package primitives;

/**
 * Represents a ray in 3D space, defined by a starting point (head) and a direction vector.
 * The direction vector is always normalized upon initialization.
 * @author Naomi Ben Shabat and Tova Tretiak
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
        return "Ray{" + "head=" + head.toString() + ", direction=" + direction.toString() + '}'; //ray{head=,direction=}
    }

    @Override
    public final boolean equals(Object obj) {
        if (!(obj instanceof Ray ray))
            return false;
        return head.equals(ray.head) && direction.equals(ray.direction);
    }


}
