package geometries;

import java.util.List;
import static primitives.Util.*;
import primitives.*;

/**
 * Polygon class represents two-dimensional polygon in 3D Cartesian coordinate
 * system
 * @author Dan
 */
public class Polygon extends Geometry {
   /** List of polygon's vertices */
   protected final List<Point> vertices;
   /** Associated plane in which the polygon lays */
   protected final Plane       plane;
   /** The size of the polygon - the amount of the vertices in the polygon */
   private final int           size;

   /**
    * Polygon constructor based on vertices list. The list must be ordered by edge
    * path. The polygon must be convex.
    * @param  vertices                 list of vertices according to their order by
    *                                  edge path
    * @throws IllegalArgumentException in any case of illegal combination of
    *                                  vertices:
    *                                  <ul>
    *                                  <li>Less than 3 vertices</li>
    *                                  <li>Consequent vertices are in the same
    *                                  point
    *                                  <li>The vertices are not in the same
    *                                  plane</li>
    *                                  <li>The order of vertices is not according
    *                                  to edge path</li>
    *                                  <li>Three consequent vertices lay in the
    *                                  same line (180&#176; angle between two
    *                                  consequent edges)
    *                                  <li>The polygon is concave (not convex)</li>
    *                                  </ul>
    */
   public Polygon(Point... vertices) {
      if (vertices.length < 3)
         throw new IllegalArgumentException("A polygon can't have less than 3 vertices");
      this.vertices = List.of(vertices);
      size          = vertices.length;

      // Generate the plane according to the first three vertices and associate the
      // polygon with this plane.
      // The plane holds the invariant normal (orthogonal unit) vector to the polygon
      plane         = new Plane(vertices[0], vertices[1], vertices[2]);
      if (size == 3) return; // no need for more tests for a Triangle

      Vector  n        = plane.getNormal(null);
      // Subtracting any subsequent points will throw an IllegalArgumentException
      // because of Zero Vector if they are in the same point
      Vector  edge1    = vertices[size - 1].subtract(vertices[size - 2]);
      Vector  edge2    = vertices[0].subtract(vertices[size - 1]);

      // Cross Product of any subsequent edges will throw an IllegalArgumentException
      // because of Zero Vector if they connect three vertices that lay in the same
      // line.
      // Generate the direction of the polygon according to the angle between last and
      // first edge being less than 180deg. It is hold by the sign of its dot product
      // with the normal. If all the rest consequent edges will generate the same sign
      // - the polygon is convex ("kamur" in Hebrew).
      boolean positive = edge1.crossProduct(edge2).dotProduct(n) > 0;
      for (var i = 1; i < size; ++i) {
         // Test that the point is in the same plane as calculated originally
         if (!isZero(vertices[i].subtract(vertices[0]).dotProduct(n)))
            throw new IllegalArgumentException("All vertices of a polygon must lay in the same plane");
         // Test the consequent edges have
         edge1 = edge2;
         edge2 = vertices[i].subtract(vertices[i - 1]);
         if (positive != (edge1.crossProduct(edge2).dotProduct(n) > 0))
            throw new IllegalArgumentException("All vertices must be ordered and the polygon must be convex");
      }
   }

   @Override
   public Vector getNormal(Point point) { return plane.getNormal(null); }

   @Override
   public List<Point> findIntersections(Ray ray) {
      // Step 1: Intersect the ray with the polygon's plane
      List<Point> planeIntersections = plane.findIntersections(ray);
      if (planeIntersections == null) {
         return null; // No intersection with the plane
      }

      Point intersectionPoint = planeIntersections.get(0);
      Point p0 = ray.getHead();
      Vector dir = ray.getDirection();

      // Exclude the ray's origin point (head) if it's exactly on the polygon
      if (intersectionPoint.equals(p0)) {
         return null;
      }

      Vector v = intersectionPoint.subtract(p0);

      // Step 2: Check if the intersection point is inside the polygon
      Vector n = null;
      int size = vertices.size();
      boolean isPositive = false;

      for (int i = 0; i < size; i++) {
         Point vi = vertices.get(i);
         Point vi1 = vertices.get((i + 1) % size);

         Vector edge1 = vi.subtract(p0);
         Vector edge2 = vi1.subtract(p0);

         Vector cross = edge1.crossProduct(edge2);
         double dot = cross.dotProduct(v);

         // On the first iteration, determine the direction (positive/negative)
         if (i == 0) {
            if (isZero(dot)) return null; // Point is on the edge
            isPositive = dot > 0;
         } else {
            // If the sign differs from the initial sign — point is outside
            if (isZero(dot) || (dot > 0) != isPositive) return null;
         }
      }

      // If the point is inside, return it
      return List.of(intersectionPoint);
   }

}