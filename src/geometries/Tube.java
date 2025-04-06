package geometries;
import static primitives.Util.isZero;
import primitives.*;

import java.util.List;

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
        double t=axis.getDirection().dotProduct(point.subtract(axis.getHead()));
        if (isZero(t))
            return point.subtract(axis.getHead()).normalize();
        return point.subtract(axis.getPoint(t)).normalize();
    }

    /***
     * Returns the central axis of the tube.
     * @return axis
     */
    public Ray getAxis() {
        return axis;
    }

    @Override
    public List<Point> findIntersections(Ray ray) {
        return null; // The Tube class does not implement intersection logic
    }
}
