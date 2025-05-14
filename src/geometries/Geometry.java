package geometries;

import primitives.*;

/**
 * An abstract class representing a geometric shape in 3D space.
 * This class serves as a base for all geometric objects and provides a common interface for intersection calculations.
 * It extends the {@link Intersectable}  ensuring that all geometric shapes can calculate ray intersections.
 *
 *@author Tehila Shraga and Tova Tretiak
 */
public abstract class Geometry extends Intersectable {

    /** The color of the geometry */
    protected Color emission = Color.BLACK; // Default emission color

    /** The material of the geometry */
    private Material material = new Material(); // Default material

    /**
     * Default constructor for the Geometry class.
     * This constructor is used by subclasses to initialize a geometric object.
     */
    public Geometry() {
    }

    /**
     * Sets the emission color of the geometry.
     * @param emission the color to set as the emission color
     */
    public Geometry setEmission(Color emission) {
        this.emission = emission;
        return this;
    }

    /**
     * Sets the material of the geometry.
     * @param material the material to set for the geometry
     */
    public Geometry setMaterial(Material material) {
        this.material = material;
        return this;
    }

    /**
     * @return the emission color of the geometry
     */
    public Color getEmission() {
        return emission;
    }

    /**
     * Gets the material of the geometry.
     * @return the material of the geometry
     */
    public Material getMaterial() {
        return material;
    }

    /**
     * Computes the normal vector to the geometric shape at a given point.
     * The normal vector is perpendicular to the surface of the shape at the specified point.
     * Each subclass must provide its own implementation of this method based on its specific geometry.
     *
     * @param point the point at which the normal is calculated
     */
    public abstract Vector getNormal(Point point);

}
