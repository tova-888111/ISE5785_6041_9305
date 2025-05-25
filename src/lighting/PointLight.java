package lighting;

import primitives.Color;
import primitives.Point;
import primitives.Vector;

import static primitives.Util.isZero;

/**
 * This class represents a point light source in a scene.
 * Point light sources emit light in all directions from a single point in space.
 * They can create soft shadows and are often used to simulate natural light sources, such as the sun or a light bulb.
 * The intensity of the light can be adjusted based on the distance from the light source to the point being illuminated.
 * This class includes position, intensity, and attenuation coefficients (kC, kL, kQ) to control the light's behavior.
 *
 * @author Tehila Shraga and Tova Tretiak
 */
public class PointLight extends Light implements LightSource{

    /** The position of the point light source in 3D space. */
    private final Point position;

    /** The attenuation coefficients for the point light source. */
    /** kc - constant attenuation coefficient */
    private double kc=1;
    /** kl - linear attenuation coefficient */
    private double kl=0;
    /** kq - quadratic attenuation coefficient */
    private double kq=0;

    /**
     * Constructor for the PointLight class.
     * @param intensity The intensity of the light source, represented by a Color object.
     * @param position The position of the point light source in 3D space, represented by a Point object.
     */
    public PointLight(Color intensity, Point position) {
        super(intensity);
        this.position = position;
    }

    /**
     * Sets the constant attenuation coefficient for the point light source.
     * @param kc The constant attenuation coefficient.
     * @return The current PointLight object, allowing for method chaining.
     */
    public PointLight setKc(double kc) {
        this.kc = kc;
        return this;
    }

    /**
     * Sets the linear attenuation coefficient for the point light source.
     * @param kl The linear attenuation coefficient.
     * @return The current PointLight object, allowing for method chaining.
     */
    public PointLight setKl(double kl) {
        this.kl = kl;
        return this;
    }

    /**
     * Sets the quadratic attenuation coefficient for the point light source.
     * @param kq The quadratic attenuation coefficient.
     * @return The current PointLight object, allowing for method chaining.
     */
    public PointLight setKq(double kq) {
        this.kq = kq;
        return this;
    }

    /**
     * Calculates the intensity of the light at a specific point in the scene.
     * @param p - The point in the scene where the intensity is being calculated.
     * @return The intensity of the light at the specified point, represented by a Color object.
     */
    @Override
    public Color getIntensity(Point p) {
        double distance = position.distance(p);
        double factor=kc + kl * distance + kq * distance * distance;
        if (isZero(factor)) {
            throw new IllegalArgumentException("Cannot divide by zero");
        }
        return intensity.scale(1/factor);
    }

    /**
     * Calculates the direction of the light from a specific point in the scene.
     * @param p - The point in the scene from which the direction of the light is being calculated.
     * @return The direction of the light from the specified point, represented by a Vector object.
     */
    @Override
    public Vector getL(Point p) {
        return p.subtract(position).normalize();
    }

    /**
     * Calculates the distance from the light source to a specific point in the scene.
     * @param point - The point in the scene from which the distance to the light source is being calculated.
     * @return The distance from the light source to the specified point, represented by a double value.
     */
    @Override
    public double getDistance(Point point) {
        return position.distance(point);
    }
}
