package lighting;

import primitives.Color;

public class AmbientLight {

    /** The intensity of the ambient light.*/
    private final Color intensity;

    /**
     * Default ambient light with intensity of black (no light).
     */
    public final static AmbientLight NONE = new AmbientLight(Color.BLACK);

    /**
     * Constructor for AmbientLight.
     * @param IA the intensity of the ambient light
     */
    public AmbientLight(Color IA) {
        this.intensity = IA;
    }

    public Color getIntensity() {
        return intensity;
    }
}
