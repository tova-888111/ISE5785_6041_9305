package geometries;

import primitives.Point;
import primitives.Ray;

public class AABB {
    public final Point min;
    public final Point max;

    public AABB(Point min, Point max) {
        this.min = min;
        this.max = max;
    }

    public boolean intersects(Ray ray) {
        // Basic slab method
        double tMin = (min.getX() - ray.getHead().getX()) / ray.getDirection().getX();
        double tMax = (max.getX() - ray.getHead().getX()) / ray.getDirection().getX();
        if (tMin > tMax) { double temp = tMin; tMin = tMax; tMax = temp; }

        double tyMin = (min.getY() - ray.getHead().getY()) / ray.getDirection().getY();
        double tyMax = (max.getY() - ray.getHead().getY()) / ray.getDirection().getY();
        if (tyMin > tyMax) { double temp = tyMin; tyMin = tyMax; tyMax = temp; }

        if ((tMin > tyMax) || (tyMin > tMax)) return false;
        if (tyMin > tMin) tMin = tyMin;
        if (tyMax < tMax) tMax = tyMax;

        double tzMin = (min.getZ() - ray.getHead().getZ()) / ray.getDirection().getZ();
        double tzMax = (max.getZ() - ray.getHead().getZ()) / ray.getDirection().getZ();
        if (tzMin > tzMax) { double temp = tzMin; tzMin = tzMax; tzMax = temp; }

        return !((tMin > tzMax) || (tzMin > tMax));
    }

    public static AABB union(AABB a, AABB b) {
        Point min = new Point(
                Math.min(a.min.getX(), b.min.getX()),
                Math.min(a.min.getY(), b.min.getY()),
                Math.min(a.min.getZ(), b.min.getZ())
        );
        Point max = new Point(
                Math.max(a.max.getX(), b.max.getX()),
                Math.max(a.max.getY(), b.max.getY()),
                Math.max(a.max.getZ(), b.max.getZ())
        );
        return new AABB(min, max);
    }
}

