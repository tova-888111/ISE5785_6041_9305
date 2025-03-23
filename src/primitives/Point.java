package primitives;

/**
 * This class represents a point in 3D space.
 * @author Tehila Shraga and Tova Tretiak
 */
public class Point {

    /**
     * Constant representing the origin point (0,0,0) in the coordinate system.
     */
    public static final Point ZERO = new Point(0, 0, 0);

    /**
     * A {@link Double3} object that stores the point's coordinates.
     */
    final protected Double3 xyz;

    /**
     * Constructor to initialize new point with the given x, y, and z coordinates.
     * @param x The x-coordinate of the point.
     * @param y The y-coordinate of the point.
     * @param z The z-coordinate of the point.
     */
    public Point(double x, double y, double z) {
        this(new Double3(x, y, z));
    }

    /**
     * Constructor to initialize new point from a Double3 object.
     * @param xyz The object containing the point's coordinates.
     */
    public Point(Double3 xyz) {
        this.xyz = xyz;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Point point))
            return false;
        return xyz.equals(point.xyz);
    }

    @Override
    public int hashCode() {

        return xyz.hashCode();
    }

    @Override
    public String toString() {

        return xyz.toString();
    }

    /**
     * Subtracts another point from this point to produce a vector.
     * @param p The point to subtract.
     * @return A Vector representing the difference between the two points.
     */
    public Vector subtract(Point p) {
        return new Vector(xyz.subtract(p.xyz));
    }

    /**
     * Adds a vector to this point to produce a new point.
     * @param vector The vector to add.
     * @return A new Point that is the result of the addition.
     */
    public Point add(Vector vector) {

        return new Point(xyz.add(vector.xyz));
    }

    /**
     * Computes the squared distance between this point and another point.
     * @param point The point to calculate the distance to.
     * @return The squared distance between the two points.
     */
    public double distanceSquared(Point point) {
      double dx = xyz.d1() - point.xyz.d1();
      double dy = xyz.d2() - point.xyz.d2();
      double dz = xyz.d3() - point.xyz.d3();

      return dx * dx + dy * dy + dz * dz;
    }

    /**
     * Computes the Euclidean distance between this point and another point.
     * @param point The point to calculate the distance to.
     * @return The distance between the two points.
     */
    public double distance(Point point) {

        return Math.sqrt(distanceSquared(point));
    }
}
