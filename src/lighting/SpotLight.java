package lighting;

import primitives.Color;
import primitives.*;

import static primitives.Util.alignZero;
import static primitives.Util.isZero;

/**
 * This class represents a spotlight in a scene.
 * A spotLight is a type of point light that emits light in a specific direction.
 * The intensity of the light decreases with distance and is affected by the angle between the direction of the light and the direction to the point being illuminated.
 * The spotLight can be used to create focused lighting effects in the scene.
 * This class includes position, intensity, and attenuation coefficients (kC, kL, kQ) to control the light's behavior.
 * This class uses the PointLight class as a base and extends its functionality to include directionality.
 * This class uses the design pattern of chaining setters to allow for easy configuration of the light's properties.
 *
 * @author Tehila Shraga and Tova Tretiak
 */
public class SpotLight extends PointLight{

    /** The direction of the light beam. */
    private final Vector direction;
    /** The narrowness of the beam, which affects the intensity of the light. */
    private double narrowBeam = 1;

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
     * Sets the narrowness of the beam for the spotLight source.
     * The narrow beam affects the intensity of the light.
     * @param narrowBeam The narrowness of the beam.
     * @return The current SpotLight object, allowing for method chaining.
     * @throws IllegalArgumentException if the narrowBeam is less than or equal to 0.
     */
    public SpotLight setNarrowBeam(double narrowBeam) {
        if (alignZero(narrowBeam) <= 0) {
            throw new IllegalArgumentException("Narrow beam must be greater than 0");
        }
        this.narrowBeam = narrowBeam;
        return this;
    }

    /**
     * Calculates the intensity of the light at a specific point in the scene.
     * The intensity is affected by the angle between the direction of the light and the direction to the point being illuminated.
     * The intensity is also affected by the distance from the light source to the point.
     * @param p - The point in the scene where the intensity is being calculated.
     * @return The intensity of the light at the specified point, represented by a Color object.
     */
    @Override
    public Color getIntensity(Point p) {
        Color intensityPoint = super.getIntensity(p);
        double factor = Math.max(0, alignZero(direction.dotProduct(getL(p))));
        if (isZero(factor)) {
            return Color.BLACK;
        }
        return intensityPoint.scale(Math.pow(factor, narrowBeam));
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
