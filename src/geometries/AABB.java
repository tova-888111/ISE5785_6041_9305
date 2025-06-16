package geometries;

import primitives.*;

/**
 * The AABB (Axis-Aligned Bounding Box) class represents a 3D bounding box
 * defined by two points: the minimum and maximum corners.
 * It provides methods to check for intersection with rays and to compute
 * the union of two AABBs.
 *
 * @author Tehila Shraga and Tova Tretiak
 */
public class AABB {
    /** The minimum corner of the bounding box */
    public final Point min;
    /** The maximum corner of the bounding box */
    public final Point max;

    /**
     * Constructs an AABB with the specified minimum and maximum points.
     * The minimum point represents one corner of the box, and the maximum point
     * represents the opposite corner.
     *
     * @param min the minimum corner of the bounding box
     * @param max the maximum corner of the bounding box
     */
    public AABB(Point min, Point max) {
        this.min = min;
        this.max = max;
    }

    /**
     * Checks if the AABB intersects with a given ray.
     * This method uses the slab method to determine if the ray intersects
     * the bounding box defined by the minimum and maximum points.
     *
     * @param ray the ray to check for intersection with the AABB
     * @return true if the ray intersects the AABB, false otherwise
     */
    public boolean intersects(Ray ray) {
        // Get the origin and direction of the ray
        Point origin = ray.getHead();
        Vector dir = ray.getDirection();

        // Initialize the tMin and tMax values for the intersection test
        double tMin = Double.NEGATIVE_INFINITY;
        double tMax = Double.POSITIVE_INFINITY;

        // Convert the points to arrays for easier manipulation
        double[] originCoords = {origin.getX(), origin.getY(), origin.getZ()};
        double[] dirCoords = {dir.getX(), dir.getY(), dir.getZ()};
        double[] minCoords = {min.getX(), min.getY(), min.getZ()};
        double[] maxCoords = {max.getX(), max.getY(), max.getZ()};

        for (int i = 0; i < 3; i++) {
            if (Math.abs(dirCoords[i]) < 1e-9) { // Check if the direction component is nearly zero
                if (originCoords[i] < minCoords[i] || originCoords[i] > maxCoords[i])
                    return false;
            } else {
                double invDir = 1.0 / dirCoords[i]; // Inverse of the direction component
                double t1 = (minCoords[i] - originCoords[i]) * invDir;
                double t2 = (maxCoords[i] - originCoords[i]) * invDir;

                // Ensure t1 is the smaller value and t2 is the larger value
                if (t1 > t2) {
                    double tmp = t1; t1 = t2; t2 = tmp;
                }

                // Update tMin and tMax based on the intersection with this slab
                tMin = Math.max(tMin, t1);
                tMax = Math.min(tMax, t2);

                // If tMin exceeds tMax, there is no intersection
                if (tMin > tMax) return false;
            }
        }
        // Check if the ray intersects the AABB in the positive direction
        return tMax >= 0;
    }


    /**
     * Computes the union of two AABBs.
     * The union is defined as the smallest AABB that contains both input AABBs.
     *
     * @param a the first AABB
     * @param b the second AABB
     * @return a new AABB that represents the union of the two input AABBs
     */
    public static AABB union(AABB a, AABB b) {
        // Ensure that the minimum and maximum points are correctly calculated
        Point min = new Point(
                Math.min(a.min.getX(), b.min.getX()),
                Math.min(a.min.getY(), b.min.getY()),
                Math.min(a.min.getZ(), b.min.getZ())
        );
        // Ensure that the maximum points are correctly calculated
        Point max = new Point(
                Math.max(a.max.getX(), b.max.getX()),
                Math.max(a.max.getY(), b.max.getY()),
                Math.max(a.max.getZ(), b.max.getZ())
        );
        return new AABB(min, max);
    }
}

