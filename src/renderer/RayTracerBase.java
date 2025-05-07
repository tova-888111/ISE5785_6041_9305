package renderer;

import primitives.*;
import scene.Scene;

/**
 * This abstract class serves as a base for ray tracing algorithms.
 * It contains a reference to the scene being rendered and provides an abstract method for tracing rays.
 * @author Tehila Shraga and Tova Tretiak
 */
public abstract class RayTracerBase {

    /** The scene being rendered. */
    protected final Scene scene;

    /**
     * Constructor to initialize the ray tracer with a given scene.
     * @param scene The scene to be rendered.
     */
    public RayTracerBase(Scene scene) {
        this.scene = scene;
    }

    /**
     * Abstract method to trace a ray and return the color at the intersection point.
     * @param ray The ray to be traced.
     * @return The color at the intersection point of the ray with the scene.
     */
    public abstract Color traceRay(Ray ray);
}
