package lighting;

import primitives.Color;

/**
 * This class represents the ambient light in a scene.
 * Ambient light is a type of light that is scattered in all directions and illuminates all objects equally.
 * It does not have a specific source and does not cast shadows.
 * The intensity of the ambient light can be adjusted to create different lighting effects in the scene.
 * @author Tehila Shraga and Tova Tretiak
 */
public class AmbientLight extends Light {

    /** Default ambient light with intensity of black (no light).*/
    public final static AmbientLight NONE = new AmbientLight(Color.BLACK);

     /**
     * Constructor for AmbientLight.
     * @param iA the intensity of the ambient light
     */
    public AmbientLight(Color iA) {
        super(iA);
    }
}
