package lighting;

import primitives.Color;
import primitives.*;

/**
 * This interface represents a light source in a scene.
 * It contains methods to get the intensity of the light at a specific point
 * and to get the direction of the light from that point.
 * The intensity of the light can be used to calculate the color of objects illuminated by the light source.
 * This interface is implemented by various types of light sources, such as point lights and spotlights.
 *
 * @author Tehila Shraga and Tova Tretiak
 */
public interface LightSource {

    /**
     * This method returns the intensity of the light at a specific point in the scene.
     * @param p - The point in the scene where the intensity is being calculated.
     * @return The intensity of the light at the specified point, represented by a Color object.
     */
    Color getIntensity(Point p);

    /**
     * This method returns the direction of the light from a specific point in the scene.
     * @param p - The point in the scene from which the direction of the light is being calculated.
     * @return The direction of the light from the specified point, represented by a Vector object.
     */
    Vector getL(Point p);

}
