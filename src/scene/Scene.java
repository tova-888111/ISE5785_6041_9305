package scene;

import geometries.Geometries;
import lighting.AmbientLight;
import primitives.Color;

/**
 * This class represents a scene in a 3D rendering system.
 * It contains properties such as the name of the scene, background color, ambient light, and geometries.
 * The class provides methods to set these properties.
 * @author Tehila Shraga and Tova Tretiak
 */
public class Scene {

    /** The name of the scene. */
    public String name;
    /** The background color of the scene. */
    public Color backgroundColor= Color.BLACK;
    /** The ambient light in the scene. */
    public AmbientLight ambientLight = AmbientLight.NONE;
    /** The geometries in the scene. */
    public Geometries geometries= new Geometries();

    /**
     * Constructor to initialize a new scene with a given name.
     * @param name The name of the scene.
     */
    public Scene(String name) {
        this.name = name;
    }

    /**
     * Sets the background color of the scene.
     * @param backgroundColor The background color to set.
     * @return The current Scene object for method chaining.
     */
    public Scene setBackground(Color backgroundColor) {
        this.backgroundColor = backgroundColor;
        return this;
    }

    /**
     * Sets the ambient light of the scene.
     * @param ambientLight The ambient light to set.
     * @return The current Scene object for method chaining.
     */
    public Scene setAmbientLight(AmbientLight ambientLight) {
        this.ambientLight = ambientLight;
        return this;
    }

    /**
     * Sets the geometries of the scene.
     * @param geometries The geometries to set.
     * @return The current Scene object for method chaining.
     */
    public Scene setGeometries(Geometries geometries) {
        this.geometries = geometries;
        return this;
    }
}
