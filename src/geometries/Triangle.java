package geometries;

import primitives.*;
import java.util.List;
import static primitives.Util.alignZero;
import static primitives.Util.isZero;

/**
 * This class represents a triangle in 3D space.
 * A triangle is defined by three vertices and is a specific case of a polygon.
 * It extends the {@link Polygon} class.
 * This class provides methods to find intersection points of a ray with the triangle.
 *
 * @author Tehila Shraga and Tova Tretiak
 */
public class Triangle extends Polygon {

    /**
     * Constructor to initialize a triangle with three given points.
     *
     * @param point1 the first vertex of the triangle
     * @param point2 the second vertex of the triangle
     * @param point3 the third vertex of the triangle
     */
    public Triangle(Point point1, Point point2, Point point3) {
        super(point1, point2, point3);
    }

    /**
     * Finds the intersection points of a ray with the triangle.
     *
     * <p>Algorithm:</p>
     * <ol>
     *   <li>Calculate the plane intersection using the Möller-Trumbore algorithm.</li>
     *   <li>Check if the intersection point lies inside the triangle using barycentric coordinates.</li>
     *   <li>Exclude points on the edges or vertices of the triangle.</li>
     * </ol>
     *
     * @param ray the ray to check for intersections
     * @return a list of intersection points, or {@code null} if there are no intersections
     */
    @Override
    public List<Point> findIntersections(Ray ray) {
        // Ray's origin point and direction vector
        Point p0 = ray.getHead();
        Vector dir = ray.getDirection();

        // Vertices of the triangle
        Point v0 = vertices.get(0);
        Point v1 = vertices.get(1);
        Point v2 = vertices.get(2);

        // Calculate the edges of the triangle
        Vector edge1 = v1.subtract(v0);
        Vector edge2 = v2.subtract(v0);

        // Calculate the determinant to check if the ray is parallel to the triangle
        Vector h = dir.crossProduct(edge2); // Cross product of ray direction and edge2
        double a = alignZero(edge1.dotProduct(h)); // Determinant

        // If determinant is near zero, the ray is parallel to the triangle
        if (isZero(a)) return null;

        // Calculate the inverse of the determinant
        double f = alignZero(1.0 / a);

        // Calculate the vector from the ray's origin to the first vertex of the triangle
        Vector s = p0.subtract(v0);

        // Calculate the barycentric coordinate u
        double u = alignZero(f * s.dotProduct(h));
        // If u is outside the range [0, 1], the intersection is outside the triangle
        if (u <= 0 || u >= 1) return null;

        // Calculate the barycentric coordinate v
        Vector q = s.crossProduct(edge1);
        double v = alignZero(f * dir.dotProduct(q));
        // If v is outside the range [0, 1] or u + v >= 1, the intersection is outside the triangle
        if (v <= 0 || u + v >= 1) return null;

        // Calculate the distance t from the ray's origin to the intersection point
        double t = alignZero(f * edge2.dotProduct(q));

        // If t is less than or equal to zero, the intersection point is behind the ray's origin
        if (t <= 0) return null;

        // Calculate the intersection point
        Point intersectionPoint = ray.getPoint(t);

        // Reject the intersection if it lies exactly on a vertex or edge of the triangle
        if (intersectionPoint.equals(p0) ||
                intersectionPoint.equals(v0) || intersectionPoint.equals(v1) || intersectionPoint.equals(v2)) {
            return null;
        }

        // Return the intersection point as a list
        return List.of(intersectionPoint);
    }
}

