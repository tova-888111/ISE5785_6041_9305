package lighting;

import primitives.Color;

/**
 * This abstract class represents a light source in a scene.
 * It contains the intensity of the light, which is represented by a Color object.
 * The intensity of the light can be used to calculate the color of objects illuminated by the light source.
 * @author Tehila Shraga and Tova Tretiak
 */
public abstract class Light {

    /** The intensity of the light source. */
    protected final Color intensity;

    /**
     * Constructor for the Light class.
     * @param intensity The intensity of the light source, represented by a Color object.
     */
    protected Light(Color intensity) {
        this.intensity = intensity;
    }

    /**
     * Getter for the intensity of the light source.
     * @return The intensity of the light source, represented by a Color object.
     */
    public Color getIntensity() {
        return intensity;
    }
}
