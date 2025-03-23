package primitives;

/**
 * The Vector class represents a three-dimensional mathematical vector.
 * @author Naomi Ben Shabat and Tova Tretiak
 */
public class Vector extends Point{

    /**
     * Constructor to initialize vector from three double values.
     * @param x the x-coordinate
     * @param y the y-coordinate
     * @param z the z-coordinate
     * @throws IllegalArgumentException if the vector is (0,0,0)
     */
    public Vector(double x, double y, double z) {
        this(new Double3(x, y, z));
    }

    /**
     * Constructor to initialize vector from a Double3 instance.
     * @param xyz the Double3 instance representing the vector components
     * @throws IllegalArgumentException if the vector is (0,0,0)
     */
    public Vector(Double3 xyz) {
        super(xyz);
        if (xyz.equals(Double3.ZERO))
            throw new IllegalArgumentException("Vector cannot be (0,0,0)");
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Vector vector))
            return false;
        return xyz.equals(vector.xyz);
    }

    /**
     * Computes the squared length of the vector.
     * @return the squared length of the vector
     */
    public double lengthSquared() {
        return xyz.d1() * xyz.d1()
                + xyz.d2() * xyz.d2()
                + xyz.d3() * xyz.d3();
    }

    /**
     * Computes the length (magnitude) of the vector.
     * @return the length of the vector
     */
    public double length() {
        return Math.sqrt(lengthSquared());
    }

    /**
     * Normalizes the vector to have a length of 1.
     * @return a new normalized vector
     */
    public Vector normalize() {
        double length = length();
        return new Vector(xyz.scale(1.0 / length));
    }

    /**
     * Computes the cross product of this vector with another vector.
     * @param vector the other vector
     * @return a new vector that is the cross product of this and the given vector
     */
    public Vector crossProduct(Vector vector) {
        double x = xyz.d2() * vector.xyz.d3() - xyz.d3() * vector.xyz.d2();
        double y = xyz.d3() * vector.xyz.d1() - xyz.d1() * vector.xyz.d3();
        double z = xyz.d1() * vector.xyz.d2() - xyz.d2() * vector.xyz.d1();
        return new Vector(x, y, z);
    }

    /**
     * Computes the dot product of this vector with another vector.
     * @param vector the other vector
     * @return the dot product of the two vectors
     */
    public double dotProduct(Vector vector) {
        return xyz.d1() * vector.xyz.d1()
                + xyz.d2() * vector.xyz.d2()
                + xyz.d3() * vector.xyz.d3();
    }

    /**
     * Adds another vector to this vector.
     * @param vector the vector to add
     * @return a new vector representing the sum
     */
    public Vector add(Vector vector)
    {
        return new Vector(xyz.add(vector.xyz));
    }

    /**
     * Scales the vector by a scalar factor.
     * @param scalar the scale factor
     * @return a new vector that is scaled by the given factor
     */
    public Vector scale (double scalar) {

        return new Vector(xyz.scale(scalar));
    }

    @Override
    public String toString() {

        return super.toString();
    }
}
