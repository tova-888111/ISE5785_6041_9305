package renderer;

import primitives.*;
import scene.Scene;

import java.util.List;
import geometries.Intersectable. Intersection;

/**
 * This class implements a simple ray tracing algorithm.
 * It extends the RayTracerBase class and provides an implementation for the traceRay method.
 * The traceRay method is responsible for tracing a ray through the scene and returning the color at the intersection point.
 * @author Tehila Shraga and Tova Tretiak
 */
public class SimpleRayTracer extends RayTracerBase {

    /**
     * Constructor to initialize the ray tracer with a given scene.
     * @param scene The scene to be rendered.
     */
    public SimpleRayTracer(Scene scene) {
        super(scene);
    }

    /**
     * Traces a ray through the scene and returns the color at the intersection point.
     * This method checks if the ray intersects with any geometries in the scene.
     * If there are no intersections, it returns the background color of the scene.
     * If there are intersections, it calculates the closest intersection point and returns the color at that point.
     * @param ray The ray to be traced.
     * @return The color at the intersection point of the ray with the scene.
     */
    @Override
    public Color traceRay(Ray ray) {
        // Check if the ray intersects with any geometries in the scene
        List<Intersection> intersections = scene.geometries.calculateIntersectionsHelper(ray);

        // If there are no intersections, return the background color of the scene
        if (intersections == null || intersections.isEmpty()) {
            return scene.backgroundColor;
        }

        // If there are intersections, calculate the closest intersection point
        Intersection closestPoint = ray.findClosestIntersection(intersections);

        // Return the color at the closest intersection point
        return calcColor(closestPoint);
    }

    /**
     * Calculates the color at a given point in the scene.
     * This method uses the ambient light intensity and the material properties of the geometry
     * to calculate the final color at the intersection point.
     * @param intersection- point and geometry at which to calculate the color.
     * @return The color at the given point.
     */
    private Color calcColor(Intersection intersection){
        return scene.ambientLight.getIntensity().scale(intersection.geometry.getMaterial().kA).add(intersection.geometry.getEmission());
    }
}
