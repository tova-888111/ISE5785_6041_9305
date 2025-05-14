package lighting;

import primitives.Color;
import primitives.*;

/**
 * This class represents a spotlight in a scene.
 * A spotLight is a type of point light that emits light in a specific direction.
 * The intensity of the light decreases with distance and is affected by the angle between the direction of the light and the direction to the point being illuminated.
 * The spotLight can be used to create focused lighting effects in the scene.
 *
 * @author Tehila Shraga and Tova Tretiak
 */
public class SpotLight extends PointLight{

    /** The direction of the light beam. */
    private final Vector direction;

    /**
     * Constructor for the SpotLight class.
     * @param intensity The intensity of the light source, represented by a Color object.
     * @param position The position of the point light source in 3D space, represented by a Point object.
     * @param direction The direction of the light beam, represented by a Vector object.
     */
    public SpotLight(Color intensity, Point position, Vector direction) {
        super(intensity, position);
        this.direction = direction.normalize();
    }

    /**
     * Sets the constant attenuation coefficient for the spotLight source.
     * @param kC The constant attenuation coefficient.
     * @return The current SpotLight object, allowing for method chaining.
     */
    public SpotLight setKc(double kC) {
        super.setKc(kC);
        return this;
    }

    /**
     * Sets the linear attenuation coefficient for the spotLight source.
     * @param kL The linear attenuation coefficient.
     * @return The current SpotLight object, allowing for method chaining.
     */
    public SpotLight setKl(double kL) {
        super.setKl(kL);
        return this;
    }

    /**
     * Sets the quadratic attenuation coefficient for the spotLight source.
     * @param kQ The quadratic attenuation coefficient.
     * @return The current SpotLight object, allowing for method chaining.
     */
    public SpotLight setKq(double kQ) {
        super.setKq(kQ);
        return this;
    }

    /**
     * Calculates the intensity of the light at a specific point in the scene.
     * The intensity is affected by the angle between the direction of the light and the direction to the point being illuminated.
     * @param p - The point in the scene where the intensity is being calculated.
     * @return The intensity of the light at the specified point, represented by a Color object.
     */
    @Override
    public Color getIntensity(Point p) {
        Color intensityPoint = super.getIntensity(p);
        double factor = Math.max(0, direction.dotProduct(getL(p)));
        return intensityPoint.scale(factor);
    }

    /**
     * Calculates the direction of the light from a specific point in the scene.
     * @param p - The point in the scene from which the direction of the light is being calculated.
     * @return The direction of the light from the specified point, represented by a Vector object.
     */
    @Override
    public Vector getL(Point p) {
        return super.getL(p);
    }
}
