package geometries;

import primitives.*;

import java.util.LinkedList;
import java.util.List;

/**
 * The Geometries class represents a collection of geometric shapes.
 * It supports optional BVH acceleration for efficient ray intersection.
 */
public class Geometries extends Intersectable {

    /** A list of intersectable geometries */
    private final List<Intersectable> geometries = new LinkedList();
    /** The acceleration structure for ray intersection, if BVH is used */
    private Intersectable accelerationStructure = null;
    /** Whether to use BVH for acceleration */
    private final boolean useBVH;

    /**
     * Default constructor without BVH.
     */
    public Geometries() {
        this(false);
    }

    /**
     * Constructor with BVH control.
     * @param useBVH whether to use BVH optimization
     */
    public Geometries(boolean useBVH) {
        this.useBVH = useBVH;
    }

    /**
     * Constructor with geometries and optional BVH.
     * @param useBVH whether to use BVH optimization
     * @param geometries geometries to add
     */
    public Geometries(boolean useBVH, Intersectable... geometries) {
        this.useBVH = useBVH;
        add(geometries);
    }

    /**
     * Constructor with geometries without BVH.
     * @param geometries geometries to add
     */
    public Geometries(Intersectable... geometries) {
        this(false, geometries);
    }

    /**
     * Adds one or more intersectable geometries to the collection.
     * @param geometries the geometries to be added
     */
    public void add(Intersectable... geometries) {
        this.geometries.addAll(List.of(geometries));
        if (useBVH) {
            // Rebuild the BVH structure whenever geometries are added
            buildBVH();
        }
    }

    /**
     * Builds the BVH tree from current geometries.
     */
    public void buildBVH() {
        this.accelerationStructure = new BVHNode(geometries);
    }

    /**
     * Calculates ray intersections with all geometries using BVH if enabled.
     * @param ray the ray to test
     * @param maxDistance maximum allowed distance
     * @return list of intersections or null
     */
    @Override
    protected List<Intersection> calculateIntersectionsHelper(Ray ray, double maxDistance) {
        // If BVH is used, delegate to the acceleration structure
        if (useBVH && accelerationStructure != null) {
            return accelerationStructure.calculateIntersections(ray, maxDistance);
        }

        // If BVH is not used, iterate through all geometries directly
        List<Intersection> intersections = null;
        double currentMaxDistance = maxDistance;

        // Iterate through all geometries and calculate intersections
        for (Intersectable geometry : geometries) {
            List<Intersection> tempIntersections = geometry.calculateIntersections(ray, currentMaxDistance);
            if (tempIntersections != null) {
                if (intersections == null)
                    intersections = new LinkedList<>();
                intersections.addAll(tempIntersections);

                // Update the current maximum distance based on the closest intersection found
                for (Intersection hit : tempIntersections) {
                    double distance = hit.point.distance(ray.getHead());
                    currentMaxDistance = Math.min(currentMaxDistance, distance);
                }
            }
        }
        // If no intersections were found, return null
        return intersections;
    }

    /**
     * Returns the internal list of geometries.
     * @return list of geometries
     */
    public List<Intersectable> getGeometries() {
        return geometries;
    }

    /**
     * Computes a bounding box that encloses all geometries.
     * @return combined AABB
     */
    @Override
    public AABB getBoundingBox() {
        if (geometries.isEmpty()) return null;

        AABB bbox = geometries.getFirst().getBoundingBox();
        for (int i = 1; i < geometries.size(); i++) {
            bbox = AABB.union(bbox, geometries.get(i).getBoundingBox());
        }
        return bbox;
    }
}
