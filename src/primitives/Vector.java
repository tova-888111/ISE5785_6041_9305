package primitives;

/**
 * This class represents a vector in 3D space.
 * It extends the Point class and provides additional functionality for vector operations.
 * Vectors are used to represent directions and magnitudes in 3D space.
 * This class includes methods for vector arithmetic, normalization, and other vector operations.
 *
 * @author Tehila Shraga and Tova Tretiak
 */
public class Vector extends Point{

    /** The unit vector along the Z-axis */
    public static final Vector AXIS_Z = new Vector(0,0,1) ;
    /** The unit vector along the Y-axis */
    public static final Vector AXIS_Y = new Vector(0,1,0) ;
    /** The unit vector along the X-axis */
    public static final Vector AXIS_X = new Vector(1,0,0) ;

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
        if (this==obj) return true;
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
    public Vector add(Vector vector){
        return new Vector(xyz.add(vector.xyz));
    }

    /**
     * Scales the vector by a scalar factor.
     * @param scalar the scale factor
     * @return a new vector that is scaled by the given factor
     */
    public Vector scale(double scalar) {
        return new Vector(xyz.scale(scalar));
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
