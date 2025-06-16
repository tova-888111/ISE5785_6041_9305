package geometries;

import primitives.Ray;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class BVHNode extends Intersectable {
    private final AABB box;
    private final Intersectable left;
    private final Intersectable right;

    public BVHNode(List<Intersectable> geometries) {
        if (geometries.size() == 1) {
            left = geometries.get(0);
            right = null;
            box = left.getBoundingBox();
        } else if (geometries.size() == 2) {
            left = geometries.get(0);
            right = geometries.get(1);
            box = AABB.union(left.getBoundingBox(), right.getBoundingBox());
        } else {
            // מיון לפי ציר אקראי (לדוגמה: X)
            geometries.sort(Comparator.comparingDouble(g -> g.getBoundingBox().min.getX()));
            int mid = geometries.size() / 2;
            left = new BVHNode(geometries.subList(0, mid));
            right = new BVHNode(geometries.subList(mid, geometries.size()));
            box = AABB.union(left.getBoundingBox(), right.getBoundingBox());
        }
    }

    @Override
    protected List<Intersection> calculateIntersectionsHelper(Ray ray, double maxDistance) {
        if (!box.intersects(ray)) return null;

        List<Intersection> intersections = new ArrayList<>();
        if (left != null) {
            List<Intersection> leftHits = left.calculateIntersections(ray, maxDistance);
            if (leftHits != null) intersections.addAll(leftHits);
        }
        if (right != null) {
            List<Intersection> rightHits = right.calculateIntersections(ray, maxDistance);
            if (rightHits != null) intersections.addAll(rightHits);
        }
        return intersections.isEmpty() ? null : intersections;
    }

    @Override
    public AABB getBoundingBox() {
        return box;
    }
}

