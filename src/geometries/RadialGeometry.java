package geometries;

import static primitives.Util.isZero;

/**
 * Abstract class representing geometric shapes that have a radius.
 * This class extends {@link Geometry} and serves as a base
 * for all radial geometric objects such as spheres, cylinders and tubes.
 * @author Naomi Ben Shabat and Tova Tretiak
 */
public abstract class RadialGeometry extends Geometry {

    /**
     * The radius of the geometric shape.
     */
    protected final double radius;

    /**
     * Constructor to initialize RadialGeometry object with the given radius.
     * @param radius the radius of the geometric shape
     */
    public RadialGeometry(double radius) {
        if (isZero(radius)||radius<0)
            throw new IllegalArgumentException("radius must be positive");
        this.radius = radius;
    }

}
