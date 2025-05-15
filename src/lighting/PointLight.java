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
    /** kC - constant attenuation coefficient */
    private double kC=1;
    /** kL - linear attenuation coefficient */
    private double kL=0;
    /** kQ - quadratic attenuation coefficient */
    private double kQ=0;

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
     * @param kC The constant attenuation coefficient.
     * @return The current PointLight object, allowing for method chaining.
     */
    public PointLight setKc(double kC) {
        this.kC = kC;
        return this;
    }

    /**
     * Sets the linear attenuation coefficient for the point light source.
     * @param kL The linear attenuation coefficient.
     * @return The current PointLight object, allowing for method chaining.
     */
    public PointLight setKl(double kL) {
        this.kL = kL;
        return this;
    }

    /**
     * Sets the quadratic attenuation coefficient for the point light source.
     * @param kQ The quadratic attenuation coefficient.
     * @return The current PointLight object, allowing for method chaining.
     */
    public PointLight setKq(double kQ) {
        this.kQ = kQ;
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
        double factor=kC + kL * distance + kQ * distance * distance;
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
}
