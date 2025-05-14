package lighting;

import primitives.Color;
import primitives.Point;
import primitives.Vector;

/**
 * This class represents a directional light source in a scene.
 * A directional light emits light in a specific direction, and the intensity of the light is constant across the entire scene.
 * The direction of the light can be used to create shadows and highlights on objects in the scene.
 *
 * @author Tehila Shraga and Tova Tretiak
 */
public class DirectionalLight extends Light implements LightSource{

    /** The direction of the light beam. */
    private final Vector direction;

    /**
     * Constructor for the DirectionalLight class.
     * @param intensity The intensity of the light source, represented by a Color object.
     * @param direction The direction of the light beam, represented by a Vector object.
     */
    public DirectionalLight(Color intensity, Vector direction) {
        super(intensity);
        this.direction = direction.normalize();
    }

    /**
     * Gets the intensity of the light at a specific point in the scene.
     * @param p - The point in the scene where the intensity is being calculated.
     * @return The intensity of the light at the specified point, represented by a Color object.
     */
    @Override
    public Color getIntensity(Point p) {
        return intensity;
    }

    /**
     * Gets the direction of the light from a specific point in the scene.
     * @param p - The point in the scene from which the direction of the light is being calculated.
     * @return The direction of the light from the specified point, represented by a Vector object.
     */
    @Override
    public Vector getL(Point p) {
        return direction;
    }
}
