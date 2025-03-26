package primitives;

/**
 * This class represents a ray in 3D space.
 * @author Tehila Shraga and Tova Tretiak
 */
public class Ray {

    /**
     * The starting point of the ray.
     */
    final public Point head;

    /**
     * The direction vector of the ray (always normalized).
     */
    final public Vector direction;

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


}
