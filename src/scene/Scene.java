package scene;

import geometries.Geometries;
import lighting.AmbientLight;
import lighting.LightSource;
import primitives.Color;

import java.util.LinkedList;
import java.util.List;

/**
 * This class represents a scene in a 3D rendering system.
 * It contains properties such as the name of the scene, background color, ambient light, and geometries.
 * The class provides methods to set these properties.
 * This class is PDS
 * This class includes the design pattern to chaining setters.
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
    /** The list of light sources in the scene. */
    List<LightSource> lights= new LinkedList<>();

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

    /**
     * Sets the light sources of the scene.
     * @param lights The list of light sources to set.
     * @return The current Scene object for method chaining.
     */
    public Scene setLights(List<LightSource> lights) {
        this.lights = lights;
        return this;
    }
}
