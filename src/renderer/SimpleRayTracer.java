package renderer;

import geometries.Intersectable.Intersection;
import lighting.LightSource;
import primitives.Color;
import primitives.Double3;
import primitives.Ray;
import primitives.Vector;
import scene.Scene;
import java.util.List;
import static primitives.Util.alignZero;

/**
 * This class implements a simple ray tracing algorithm.
 * It extends the RayTracerBase class and provides an implementation for the traceRay method.
 * The traceRay method is responsible for tracing a ray through the scene and returning the color at the intersection point.
 *
 * @author Tehila Shraga and Tova Tretiak
 */
public class SimpleRayTracer extends RayTracerBase {

    /**
     * Constructor to initialize the ray tracer with a given scene.
     *
     * @param scene The scene to be rendered.
     */
    public SimpleRayTracer(Scene scene) {
        super(scene);
    }

    /**
     * A small value used to avoid floating-point precision issues.
     * This value is used to check if two floating-point numbers are equal.
     */
    private static final double DELTA = 0.1;

    /**
     * Traces a ray through the scene and returns the color at the intersection point.
     * This method checks if the ray intersects with any geometries in the scene.
     * If there are no intersections, it returns the background color of the scene.
     * If there are intersections, it calculates the closest intersection point and returns the color at that point.
     *
     * @param ray The ray to be traced.
     * @return The color at the intersection point of the ray with the scene.
     */
    @Override
    public Color traceRay(Ray ray) {
        // Check if the ray intersects with any geometries in the scene
        List<Intersection> intersections = scene.geometries.calculateIntersections(ray);
        // If there are no intersections, return the background color of the scene
        if (intersections == null || intersections.isEmpty()) {
            return scene.backgroundColor;
        }
        // If there are intersections, calculate the closest intersection point
        Intersection closestPoint = ray.findClosestIntersection(intersections);
        // Return the color at the closest intersection point
        return calcColor(closestPoint, ray);
    }

    /**
     * Calculates the color at a given point in the scene.
     * This method uses the ambient light intensity and the material properties of the geometry
     * to calculate the final color at the intersection point.
     *
     * @param intersection- point and geometry at which to calculate the color.
     * @param ray           - the ray that intersects with the geometry
     * @return The color at the given point.
     */
    private Color calcColor(Intersection intersection, Ray ray) {
        // Check if the intersection and geometry are not null
        if (!preprocessIntersection(intersection, ray.getDirection())) {
            // If the intersection is null or the geometry is null, return black color
            return new Color(java.awt.Color.BLACK);
        }
        // Return the color at the intersection point
        return scene.ambientLight.getIntensity().scale(intersection.geometry.getMaterial().kA).add(calcColorLocalEffects(intersection));
    }

    /**
     * Preprocesses the intersection data for further calculations.
     * This method calculates the normal vector at the intersection point
     * This method calculates the dot product of the ray vector and the normal vector.
     * It also checks if the dot product is zero, indicating that the ray is parallel to the surface.
     * If the dot product is zero, it returns false
     * else it returns true.
     *
     * @param intersection - the intersection object containing the geometry and point of intersection
     * @param vector       - the direction vector of the ray
     * @return true if the dot product is not zero, false otherwise
     */
    private boolean preprocessIntersection(Intersection intersection, Vector vector) {
        // Initialize the normal vector
        intersection.v = vector;
        // Calculate the normal vector at the intersection point
        intersection.normal = intersection.geometry.getNormal(intersection.point);
        // Calculate the dot product of the ray vector and the normal vector
        intersection.vNormal = alignZero(intersection.v.dotProduct(intersection.normal));
        // Check if the dot product is zero, indicating that the ray is parallel to the surface
        return intersection.vNormal != 0;
    }

    /**
     * Sets the light source for the intersection point.
     * This method calculates the direction vector of the light source
     * and the dot product of the light direction and the normal vector.
     * It also checks if the dot product is zero, indicating that the light source is parallel to the surface.
     * If the dot product is zero, it returns false
     * else it returns true.
     *
     * @param intersection - the intersection object containing the geometry and point of intersection
     * @param lightSource  - the light source illuminating the intersection point
     * @return true if the dot product is not zero, false otherwise
     */
    private boolean setLightSource(Intersection intersection, LightSource lightSource) {
        // Initialize the light source
        intersection.light = lightSource;
        // Calculate the direction vector of the light source
        intersection.l = lightSource.getL(intersection.point);
        // Calculate the dot product of the light direction and the normal vector
        intersection.lNormal = alignZero(intersection.l.dotProduct(intersection.normal));
        // Check if the dot product is zero, indicating that the light source is parallel to the surface
        return (alignZero(intersection.lNormal * intersection.vNormal) > 0);
    }

    /**
     * Calculates the color at the intersection point based on local effects.
     * This method iterates through all the light sources in the scene
     * and calculates the diffuse and specular components of the color
     * at the intersection point.
     *
     * @param intersection - the intersection object containing the geometry and point of intersection
     * @return The color at the intersection point based on local effects.
     */
    private Color calcColorLocalEffects(Intersection intersection) {
        // Check if the intersection and geometry are not null
        Color color = intersection.geometry.getEmission();
        // Iterate through all the light sources in the scene
        for (LightSource lightSource : scene.lights) {
            // Set the light source for the intersection point
            // Check also if the intersection point is unshaded
            if (setLightSource(intersection, lightSource)&&unshaded(intersection)) {
                // Calculate the diffuse and specular components of the color
                Color iL = lightSource.getIntensity(intersection.point);
                // Add the color contributions from the light source
                color = color.add(iL.scale(calcDiffuse(intersection))).add(iL.scale(calcSpecular(intersection)));
            }
        }
        return color;
    }

    /**
     * Calculates the specular component of the color at the intersection point.
     * This method scales the specular coefficient of the material by the dot product of the reflection vector and the view vector.
     * This is raised to the power of the shininess coefficient of the material.
     *
     * @param intersection - the intersection object containing the geometry and point of intersection
     * @return The specular component of the color at the intersection point.
     */
    private Double3 calcSpecular(Intersection intersection) {
        // Check if the intersection and material are not null
        if (intersection == null || intersection.material == null)
            throw new IllegalArgumentException("intersection or material is null");
        // Calculate the reflection vector
        Vector r = intersection.l.add(intersection.normal.scale(-2 * intersection.lNormal)).normalize();
        // Calculate the dot product of the reflection vector and the view vector
        double vr = r.dotProduct(intersection.v) * (-1);
        // Check if the dot product is less than or equal to zero
        return intersection.material.kS.scale(Math.max(0, Math.pow(vr, intersection.material.nShininess)));
    }

    /**
     * Calculates the diffuse component of the color at the intersection point.
     * This method scales the diffuse coefficient of the material by the dot product of the light direction and the normal vector.
     *
     * @param intersection - the intersection object containing the geometry and point of intersection
     * @return The diffuse component of the color at the intersection point.
     */
    private Double3 calcDiffuse(Intersection intersection) {
        // Check if the intersection and material are not null
        if (intersection == null || intersection.material == null)
            throw new IllegalArgumentException("intersection or material is null");
        // Calculate the diffuse component based on the light direction and normal vector
        if (intersection.lNormal > 0) {
            return intersection.material.kD.scale(intersection.lNormal);
        }
        // If the light direction is opposite to the normal vector, return the negative diffuse component
        return intersection.material.kD.scale(intersection.lNormal * (-1));
    }

    /**
     * Checks if the intersection point is unshaded by any geometries in the scene.
     * This method calculates the ray from the intersection point to the light source
     * and checks if there are any intersections with other geometries in the scene.
     *
     * @param intersection - the intersection object containing the geometry and point of intersection
     * @return true if the intersection point is unshaded, false otherwise
     */
    private boolean unshaded(Intersection intersection) {
        // Calculate the ray from the intersection point to the light source
        Vector pointToLight = intersection.l.scale(-1);
        Vector delta= intersection.normal.scale(intersection.lNormal<0? DELTA : -DELTA);
        Ray shadowRay = new Ray(intersection.point.add(delta), pointToLight);
        // Check if the shadow ray intersects with any geometries in the scene
        List<Intersection> intersections = scene.geometries.calculateIntersections(shadowRay);
        // If there are no intersections, return true (the point is unshaded)
        if (intersections == null || intersections.isEmpty()) {
            return true;
        }
        // If there are intersections, check if the light source is blocked
        // Calculate the distance from the light source to the intersection point
        double distanceLight= intersection.light.getDistance(intersection.point);
        // A variable to store the distance between the head of the ray and the intersection point
        double distance;
        // Iterate through all the intersections
        for (Intersection i : intersections) {
            // Check if the intersection point is closer to the light source than the original intersection point
            distance= shadowRay.getHead().distance(i.point);
            if (alignZero( distanceLight-distance)>0){
                return false;
            }
        }
        return true;
    }
}
