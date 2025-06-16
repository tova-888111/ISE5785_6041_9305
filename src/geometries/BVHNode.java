package geometries;

import primitives.Ray;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * The BVHNode class represents a node in a Bounding Volume Hierarchy (BVH).
 * It is used for spatial acceleration of ray intersection tests.
 * Each node contains a bounding box and references to left and right child nodes.
 * The BVH is built from a list of intersectable geometries.
 *
 * @author Tehila Shraga and Tova Tretiak
 */
public class BVHNode extends Intersectable {
    /** The bounding box of this BVH node */
    private final AABB box;
    /** The left child node, which may be another BVHNode or an intersectable geometry */
    private final Intersectable left;
    /** The right child node, which may be another BVHNode or an intersectable geometry */
    private final Intersectable right;

    /**
     * Constructs a BVHNode from a list of intersectable geometries.
     * If the list contains one geometry, it becomes a leaf node.
     * If it contains two geometries, they become the left and right children.
     * For more than two geometries, the list is split into two halves recursively.
     *
     * @param geometries the list of intersectable geometries to build the BVH from
     */
    public BVHNode(List<Intersectable> geometries) {
        // Ensure the list is not empty
        if (geometries.size() == 1) {
            // If there's only one geometry, it becomes a leaf node
            left = geometries.get(0);
            right = null;
            box = left.getBoundingBox();
            // If there's only one geometry, it becomes a leaf node
        } else if (geometries.size() == 2) {
            left = geometries.get(0);
            right = geometries.get(1);
            box = AABB.union(left.getBoundingBox(), right.getBoundingBox());
            // If there are two geometries, they become the left and right children
        } else {
            // More than two geometries, we need to split them
            AABB totalBox = geometries.get(0).getBoundingBox();
            for (int i = 1; i < geometries.size(); i++) {
                totalBox = AABB.union(totalBox, geometries.get(i).getBoundingBox());
            }

            // Sort the geometries based on their bounding box extents
            double xExtent = totalBox.max.getX() - totalBox.min.getX();
            double yExtent = totalBox.max.getY() - totalBox.min.getY();
            double zExtent = totalBox.max.getZ() - totalBox.min.getZ();

            // Sort geometries based on the largest extent
            if (xExtent >= yExtent && xExtent >= zExtent) {
                geometries.sort(Comparator.comparingDouble(g -> g.getBoundingBox().min.getX()));
            } else if (yExtent >= zExtent) {
                geometries.sort(Comparator.comparingDouble(g -> g.getBoundingBox().min.getY()));
            } else {
                geometries.sort(Comparator.comparingDouble(g -> g.getBoundingBox().min.getZ()));
            }

            // Split the list into two halves
            int mid = geometries.size() / 2;
            // Create left and right child nodes recursively
            left = new BVHNode(geometries.subList(0, mid));
            // Create right child node with the remaining geometries
            right = new BVHNode(geometries.subList(mid, geometries.size()));
            // Calculate the bounding box for this node
            box = AABB.union(left.getBoundingBox(), right.getBoundingBox());
        }
    }

    /**
     * Finds the intersection points of a ray with the BVH node.
     * This method checks if the ray intersects the bounding box of this node.
     * If it does, it recursively checks the left and right child nodes for intersections.
     *
     * @param ray the ray to check for intersections
     * @param maxDistance the maximum distance from the ray's origin to consider for intersections
     * @return a list of Intersection objects, or {@code null} if there are no intersections
     */
    @Override
    protected List<Intersection> calculateIntersectionsHelper(Ray ray, double maxDistance) {
        if (!box.intersects(ray)) return null;

        // Check if the ray intersects the bounding box of this node
        List<Intersection> intersections = new ArrayList<>();
        double currentMaxDistance = maxDistance;

        // Check intersections in the left child node
        if (left != null) {
            List<Intersection> leftHits = left.calculateIntersections(ray, currentMaxDistance);
            if (leftHits != null) {
                intersections.addAll(leftHits);
                // Update the current maximum distance based on the closest intersection found in the left subtree
                for (Intersection hit : leftHits) {
                    currentMaxDistance = Math.min(currentMaxDistance, hit.point.distance(ray.getHead()));
                }
            }
        }

        // Check intersections in the right child node
        if (right != null) {
            List<Intersection> rightHits = right.calculateIntersections(ray, currentMaxDistance);
            if (rightHits != null) {
                intersections.addAll(rightHits);
            }
        }

        // If no intersections were found, return null
        return intersections.isEmpty() ? null : intersections;
    }

    /**
     * Returns the axis-aligned bounding box (AABB) of this BVH node.
     * The bounding box is defined by the minimum and maximum coordinates
     * of the geometries contained in this node.
     *
     * @return the bounding box of this BVH node
     */
    @Override
    public AABB getBoundingBox() {
        return box;
    }
}

