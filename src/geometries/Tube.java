package geometries;
import static primitives.Util.isZero;
import primitives.*;

/**
 * The Tube class represents an infinite cylindrical tube in 3D space.
 * @author Tehila Shraga and Tova Tretiak
 */
public class Tube extends RadialGeometry{

    /** The central axis of the tube, represented as a ray */
    protected final Ray axis;

    /**
     * Constructor to initialize new Tube with the specified radius and central axis.
     * @param radius The radius of the tube (must be positive).
     * @param axis The central axis of the tube, represented as a {@link Ray}.
     */
    public Tube(double radius, Ray axis) {
        super(radius);
        this.axis = axis;
    }

    @Override
    public Vector getNormal(Point point) {
        double t=axis.direction.dotProduct(point.subtract(axis.head));
        Point o=axis.head;
        if (!isZero(t))
            o=o.add(axis.direction.scale(t));
        return point.subtract(o).normalize();
    }
}
