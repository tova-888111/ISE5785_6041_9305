package renderer;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.MissingResourceException;

import static primitives.Util.alignZero;
import static primitives.Util.isZero;

/**
 * The Camera class represents a virtual camera in 3D space.
 * It defines the position, orientation, and view plane of the camera.
 * The camera is used to construct rays through pixels on the view plane.
 * This class uses the Builder design pattern for flexible initialization.
 *
 * <p>Key components:
 * <ul>
 *   <li>p0: The position of the camera in 3D space.</li>
 *   <li>vTo: The forward direction vector of the camera.</li>
 *   <li>vUp: The upward direction vector of the camera.</li>
 *   <li>vRight: The rightward direction vector of the camera (calculated as the cross product of vTo and vUp).</li>
 *   <li>distance: The distance from the camera to the view plane.</li>
 *   <li>width and height: The dimensions of the view plane.</li>
 * </ul>
 * </p>
 *
 * <p>This class also provides a nested Builder class for constructing Camera objects.</p>
 *
 * @author Tehila Shraga and Tova Tretiak
 */
public class Camera implements Cloneable {
    // Camera properties
    private Point p0=null; // The position of the camera
    private Vector vTo=null; // The forward direction vector
    private Vector vUp=null; // The upward direction vector
    private Vector vRight=null; // The rightward direction vector
    private double distance = 0.0; // Distance from the camera to the view plane
    private double width = 0.0; // Width of the view plane
    private double height = 0.0; // Height of the view plane
    private Point pc=null; // The center of the view plane
    private int nx= 0; // Number of pixels in the x-direction
    private int ny= 0; // Number of pixels in the y-direction

    /**
     * Private constructor to prevent direct instantiation.
     * Use the Builder class to create Camera objects.
     */
    private Camera() {
    }

    /**
     * Returns a new Builder instance for constructing a Camera object.
     *
     * @return a new Builder instance
     */
    public static Builder getBuilder() {
        return new Builder();
    }

    /**
     * Constructs a ray through a specific pixel on the view plane.
     *
     * @param nX the number of pixels in the x-direction
     * @param nY the number of pixels in the y-direction
     * @param j the column index of the pixel
     * @param i the row index of the pixel
     * @return the constructed ray
     */
    public Ray constructRay(int nX, int nY, int j, int i) {
        // TODO: Implement the method to construct a ray through a pixel
        return null;
    }

    /**
     * Retrieves the position of the camera.
     *
     * @return the position of the camera
     */
    public Point getP0() {
        return p0;
    }

    /**
     * Retrieves the forward direction vector of the camera.
     *
     * @return the forward direction vector
     */
    public Vector getVTo() {
        return vTo;
    }

    /**
     * Retrieves the upward direction vector of the camera.
     *
     * @return the upward direction vector
     */
    public Vector getVUp() {
        return vUp;
    }

    /**
     * Retrieves the rightward direction vector of the camera.
     *
     * @return the rightward direction vector
     */
    public Vector getVRight() {
        return vRight;
    }

    /**
     * Retrieves the distance from the camera to the view plane.
     *
     * @return the distance to the view plane
     */
    public double getDistance() {
        return distance;
    }

    /**
     * Retrieves the width of the view plane.
     *
     * @return the width of the view plane
     */
    public double getWidth() {
        return width;
    }

    /**
     * Retrieves the height of the view plane.
     *
     * @return the height of the view plane
     */
    public double getHeight() {
        return height;
    }

    /**
     * Retrieves the center of the view plane.
     *
     * @return the center of the view plane
     */
    public Point getPc() {
        return pc;
    }

    /**
     * The Builder class is used to construct Camera objects.
     * It provides methods for setting the camera's properties and ensures
     * that the camera is properly initialized before use.
     */
    public static class Builder {
        //The Camera object being constructed
        private final Camera camera = new Camera();

        /**
         * Constructs a Builder instance with default camera properties.
         */
        public Builder() {
            this.camera.p0 = new Point(0, 0, 0); // Default position at the origin
            this.camera.vTo = new Vector(0, 0, -1); // Default forward direction
            this.camera.vUp = new Vector(0, 1, 0); // Default upward direction
            this.camera.vRight = new Vector(1, 0, 0); // Default rightward direction
        }

        /**
         * Constructs a Builder instance by copying the properties of an existing Camera object.
         *
         * @param camera the Camera object to copy
         */
        public Builder(Camera camera) {
            this.camera.p0 = camera.p0;
            this.camera.vTo = camera.vTo;
            this.camera.vUp = camera.vUp;
            this.camera.vRight = camera.vRight;
            this.camera.distance = camera.distance;
            this.camera.width = camera.width;
            this.camera.height = camera.height;
        }

        /**
         * Sets the position of the camera.
         *
         * @param p0 the position of the camera
         * @return the Builder instance
         */
        public Builder setLocation(Point p0) {
            camera.p0 = p0;
            return this;
        }

        /**
         * Sets the direction vectors of the camera.
         * Ensures that vTo and vUp are orthogonal and normalizes them.
         *
         * @param vTo the forward direction vector
         * @param vUp the upward direction vector
         * @return the Builder instance
         * @throws IllegalArgumentException if vTo and vUp are not orthogonal
         */
        public Builder setDirection(Vector vTo, Vector vUp) {
            // Ensure vTo and vUp are orthogonal
            if (!isZero(vTo.dotProduct(vUp))) {
                throw new IllegalArgumentException("vTo and vUp must be orthogonal");
            }
            // Normalize vTo and vUp
            camera.vTo = vTo.normalize();
            camera.vUp = vUp.normalize();
            // Calculate vRight as the cross product of vTo and vUp
            camera.vRight = vTo.crossProduct(vUp);
            return this;
        }

        /**
         * Sets the direction vectors of the camera using a target point and an up vector.
         * Calculates vTo, vRight, and vUp based on the target point and up vector.
         *
         * @param target the target point
         * @param vUp    the upward direction vector
         * @return the Builder instance
         */
        public Builder setDirection(Point target, Vector vUp) {
            // Ensure the target point is not the same as the camera position
            if (camera.p0.equals(target)) {
                throw new IllegalArgumentException("Target point cannot be the same as camera position");
            }
            //Calculate vTo as the normalized vector from camera position to target point
            camera.vTo= target.subtract(camera.p0).normalize();
            //Calculate vRight as the cross product of vTo and vUp
            camera.vRight= camera.vTo.crossProduct(vUp).normalize();
            // Calculate vUp as the cross product of vRight and vTo
            camera.vUp = camera.vRight.crossProduct(camera.vTo).normalize();
            return  this;
        }

        /**
         * Sets the direction vectors of the camera using a target point.
         * Calculates vTo, vRight, and vUp based on the target point.
         *
         * @param target the target point
         * @return the Builder instance
         */
        public Builder setDirection(Point target) {
            // Ensure the target point is not the same as the camera position
            if (camera.p0.equals(target)) {
                throw new IllegalArgumentException("Target point cannot be the same as camera position");
            }
            camera.vTo= target.subtract(camera.p0).normalize();
            //Calculate vRight as the cross product of vTo and vUp
            camera.vRight= camera.vTo.crossProduct(camera.vUp).normalize();
            // Calculate vUp as the cross product of vRight and vTo
            camera.vUp = camera.vRight.crossProduct(camera.vTo).normalize();
            return this;
        }

        /**
         * Sets the size of the view plane.
         *
         * @param width the width of the view plane (must be positive)
         * @param height the height of the view plane (must be positive)
         * @return the Builder instance
         * @throws IllegalArgumentException if width or height is not positive
         */
        public Builder setVpSize(double width, double height) {
            // Ensure width and height are positive
            if (alignZero(width) <= 0 || alignZero(height) <= 0) {
                throw new IllegalArgumentException("Width and height must be positive");
            }
            camera.width = width;
            camera.height = height;
            return this;
        }

        /**
         * Sets the distance from the camera to the view plane.
         *
         * @param distance the distance to the view plane (must be positive)
         * @return the Builder instance
         * @throws IllegalArgumentException if the distance is not positive
         */
        public Builder setVpDistance(double distance) {
            // Ensure the distance is positive
            if (alignZero(distance) <= 0) {
                throw new IllegalArgumentException("Distance must be positive");
            }
            camera.distance = distance;
            return this;
        }

        /**
         * Sets the resolution of the view plane.
         *
         * @param nx the number of pixels in the x-direction
         * @param ny the number of pixels in the y-direction
         * @return the Builder instance
         */
        public Builder setResolution(int nx, int ny) {
            //todo
            return  this;
        }

        /**
         * Builds and returns the Camera object.
         *
         * @return the constructed Camera object
         */
        public Camera build() {
            String errorMessage = "Missing render data";
            String className = "Camera";
            if (camera.p0==null){
                throw new MissingResourceException(errorMessage, className, "Missing p0");
            }
            if (camera.vTo==null){
                throw new MissingResourceException(errorMessage, className, "Missing vTo");
            }
            if (camera.vUp==null){
                throw new MissingResourceException(errorMessage, className, "Missing vUp");
            }
            if (camera.vRight==null){
                throw new MissingResourceException(errorMessage, className, "Missing vRight");
            }
            if (camera.distance==0.0){
                throw new MissingResourceException(errorMessage, className, "Missing distance");
            }
            if (camera.width==0.0){
                throw new MissingResourceException(errorMessage, className, "Missing width");
            }
            if (camera.height==0.0){
                throw new MissingResourceException(errorMessage, className, "Missing height");
            }
            // Calculate the center of the view plane
            camera.pc=camera.p0.add(camera.vTo.scale(camera.distance));
            try {
                // Clone the camera to ensure immutability
                return (Camera) camera.clone();
            } catch (CloneNotSupportedException e) {
                return null;
            }
        }

    }
}