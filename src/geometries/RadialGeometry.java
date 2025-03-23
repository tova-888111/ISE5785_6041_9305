package geometries;

import static primitives.Util.isZero;

/**
 * Abstract class representing geometric shapes that have a radius.
 * @author Tehila Shraga and Tova Tretiak
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
