package renderer;

import primitives.*;
import scene.Scene;

import java.util.LinkedList;
import java.util.List;
import java.util.MissingResourceException;
import static primitives.Util.*;

/**
 * The Camera class represents a virtual camera in 3D space.
 * It defines the position, orientation, and view plane of the camera.
 * The camera is used to construct rays through pixels on the view plane.
 * This class uses the Builder design pattern for flexible initialization.
 * @author Tehila Shraga and Tova Tretiak
 */
public class Camera implements Cloneable {
    // Camera properties
    /** The position of the camera in 3D space */
    private Point p0 = null;
    /** The forward direction vector of the camera (points towards the view plane) */
    private Vector vTo = null;
    /** The upward direction vector of the camera (points upwards relative to the camera) */
    private Vector vUp = null;
    /** The rightward direction vector of the camera (perpendicular to vTo and vUp) */
    private Vector vRight = null;
    /** The distance from the camera to the view plane */
    private double distance = 0.0;
    /** The width of the view plane */
    private double width = 0.0;
    /** The height of the view plane */
    private double height = 0.0;
    /** The center point of the view plane */
    private Point pc = null;
    /** The image writer used for rendering the scene */
    private ImageWriter imageWriter= null;
    /** The ray tracer used for rendering the scene */
    private RayTracerBase rayTracer= null;
    /** The number of pixels in the x-direction (horizontal resolution) */
    private int nX=1;
    /** The number of pixels in the y-direction (vertical resolution) */
    private int nY=1;

    /** Aperture radius for depth of field effect (0 disables the effect) */
    private double apertureRadius = 0.0;
    /** Distance from camera to the focus plane */
    private double focalDistance = 0.0;
    /** Number of rays per pixel for depth of field */
    private int dofRays = 1;


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
     * Returns a new Builder instance for constructing a Camera object based on an existing camera.
     *
     * @param camera the existing Camera object to copy properties from
     * @return a new Builder instance
     */
    public static Builder getBuilder(Camera camera) {
        return new Builder(camera);
    }

    /**
     * Constructs a ray through a specific pixel on the view plane.
     *
     * @param nX the number of pixels in the x-direction (horizontal resolution)
     * @param nY the number of pixels in the y-direction (vertical resolution)
     * @param j the column index of the pixel (0-based)
     * @param i the row index of the pixel (0-based)
     * @return the constructed ray from the camera to the specified pixel
     */
    public Ray constructRay(int nX, int nY, int j, int i) {
        // Point pij is the center of the pixel, we start with the center of the view plane
        Point pij=pc;
        // Calculate the pixel size in the x and y directions
        double rX = width / nX; // Width of a single pixel
        double rY = height / nY; // Height of a single pixel

        // Calculate the x and y offsets for the pixel
        double xj; // Offset in the x-direction
        double yi; // Offset in the y-direction

        // Calculate the x offset based on whether the number of pixels in the x-direction is even or odd
            xj = (j - (nX - 1) / 2.0) * rX;

        // Calculate the y offset based on whether the number of pixels in the y-direction is even or odd
            yi = -(i - (nY - 1) / 2.0) * rY;

        // Adjust the center point of the pixel (pij) by adding the scaled vRight and vUp vectors
        if (!isZero(xj)) {
            pij = pij.add(vRight.scale(xj));
        }
        if (!isZero(yi)) {
            pij = pij.add(vUp.scale(yi));
        }

        // Calculate the direction vector from the camera position (p0) to the pixel point (pij)
        Vector vector = pij.subtract(p0).normalize();

        // Create and return a new ray from the camera position to the pixel point
        return new Ray(p0, vector);
    }

    /**
     * Constructs a list of rays for depth of field effect.
     * This method generates multiple rays originating from the camera's aperture
     * and passing through a specific pixel on the view plane.
     * @param nX the number of pixels in the x-direction (horizontal resolution)
     * @param nY the number of pixels in the y-direction (vertical resolution)
     * @param j the column index of the pixel (0-based)
     * @param i the row index of the pixel (0-based)
     * @return a list of rays originating from the camera's aperture
     */
    public List<Ray> constructDofRays(int nX, int nY, int j, int i) {
        List<Ray> rays = new LinkedList<>();

        // Construct the center ray passing through the pixel (j, i)
        Ray centerRay = constructRay(nX, nY, j, i);
        // Calculate the focal point at the focal distance along the center ray
        Point focusPoint = centerRay.getPoint(focalDistance);

        for (int k = 0; k < dofRays; k++) {
            // Sample a random radius using sqrt for uniform distribution over the circle area
            double r = Math.sqrt(Math.random()) * apertureRadius;
            // Sample a random angle between 0 and 2*PI radians
            double theta = Math.random() * 2 * Math.PI;

            // Convert polar coordinates (r, theta) to Cartesian (x, y) on the aperture plane
            double x = r * Math.cos(theta);
            double y = r * Math.sin(theta);

            // Calculate the random point on the circular aperture around the camera position p0
            Point aperturePoint = p0
                    .add(vRight.scale(x))  // move right by x units
                    .add(vUp.scale(y));    // move up by y units

            // Calculate the direction from the aperture point to the focal point
            Vector direction = focusPoint.subtract(aperturePoint).normalize();

            // Create a new ray from the aperture point towards the focal point and add it to the list
            rays.add(new Ray(aperturePoint, direction));
        }

        return rays;
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
     * Retrieves the image writer used for rendering the scene.
     *  Iterates through each pixel in the image and casts a ray through it.
     * @return the image writer
     */
    public Camera renderImage(){
        // Iterate through each pixel in the image
        for (int i=0; i<nY; i++){
            for (int j=0; j<nX; j++){
                // Cast a ray through the pixel
                castRay( j, i);
            }
        }
        return this;
    }

    /**
     * Prints a grid on the image.
     *
     * @param interval the interval between grid lines
     * @param color    the color of the grid lines
     * @return the Camera object for method chaining
     */
    public Camera printGrid(int interval, Color color){
        // Check if the interval is valid
        if (alignZero(interval) <= 0) {
            throw new IllegalArgumentException("Interval must be positive");
        }
        // Loop through the pixels in the image
        for (int i = 0; i < nY; i++) {
            for (int j = 0; j < nX; j++) {
                // Check if the pixel is on a grid line
                if (i % interval == 0 || j % interval == 0) {
                    // Write the grid color to the pixel
                    imageWriter.writePixel(j, i, color);
                }
            }
        }
        return this;
    }

    /**
     * Writes the rendered image to a file.
     *
     * @param filePath the path where the image will be saved
     * @return the Camera object for method chaining
     */
    public Camera writeToImage(String filePath){
        // Save the image to a file
        imageWriter.writeToImage(filePath);
        return this;
    }

    /**
     * Casts a ray through a specific pixel on the view plane and writes the color to the image.
     * This method handles both standard ray tracing and depth of field effects.
     * * If the aperture radius is zero or the number of rays per pixel is one,
     * it constructs a single ray through the pixel.
     * * If the aperture radius is greater than zero, it constructs multiple rays
     * and averages their colors for depth of field effect.
     *
     * @param column the column index of the pixel (0-based)
     * @param row the row index of the pixel (0-based)
     */
    private void castRay( int column, int row){
        Color color;
        if (apertureRadius == 0 || dofRays == 1) {
            // Construct a ray through the pixel (column, row)
            Ray ray = constructRay(nX, nY, column, row);
            // Cast the ray and get the color at the intersection point
            color = rayTracer.traceRay(ray);
        } else {
            // Construct multiple rays for depth of field effect
            List<Ray> rays = constructDofRays(nX, nY, column, row);
            color = Color.BLACK;
            // Trace each ray and accumulate the color
            for (Ray ray : rays) {
                color = color.add(rayTracer.traceRay(ray));
            }
            // Average the color over the number of rays
            color = color.reduce(rays.size());
        }
        // Write the color to the image
        imageWriter.writePixel(column, row, color);
    }

    /**
     * The Builder class is used to construct Camera objects.
     * It provides methods for setting the camera's properties and ensures
     * that the camera is properly initialized before use.
     */
    public static class Builder {
        /** The Camera object being constructed */
        private final Camera camera = new Camera();

        /**
         * Constructs a Builder instance with default camera properties.
         */
        public Builder() {
            this.camera.p0 = Point.ZERO; // Default position at the origin
            this.camera.vTo = new Vector(0, 0, -1); // Default forward direction
            this.camera.vUp = Vector.AXIS_Y; // Default upward direction
            this.camera.vRight = Vector.AXIS_X; // Default rightward direction
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
            this.camera.pc = camera.pc;
            this.camera.imageWriter = camera.imageWriter;
            this.camera.rayTracer = camera.rayTracer;
            this.camera.nX = camera.nX;
            this.camera.nY = camera.nY;
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
            camera.vRight = vTo.crossProduct(vUp).normalize();
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
            // Calculate vTo as the normalized vector from camera position to target point
            camera.vTo = target.subtract(camera.p0).normalize();
            // Calculate vRight as the cross product of vTo and vUp
            camera.vRight = camera.vTo.crossProduct(vUp).normalize();
            // Calculate vUp as the cross product of vRight and vTo
            camera.vUp = camera.vRight.crossProduct(camera.vTo).normalize();
            return this;
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
            // Calculate vTo as the normalized vector from camera position to target point
            camera.vTo = target.subtract(camera.p0).normalize();
            // Set default vUp as (0,1,0)
            camera.vUp= Vector.AXIS_Y;
            // Calculate vRight as the cross product of vTo and vUp
            camera.vRight = camera.vTo.crossProduct(camera.vUp).normalize();
            // Recalculate vUp as the cross product of vRight and vTo
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
         * @param nX the number of pixels in the x-direction
         * @param nY the number of pixels in the y-direction
         * @return the Builder instance
         */
        public Builder setResolution(int nX, int nY) {
            camera.nX = nX;
            camera.nY = nY;
            return this;
        }

        /**
         * Sets the ray tracer for the camera.
         *
         * @param scene the scene to be rendered
         * @param rayTracerType the type of ray tracer to use
         * @return the Builder instance
         */
        public Builder setRayTracer(Scene scene, RayTracerType rayTracerType) {
            if (rayTracerType==RayTracerType.SIMPLE) {
                this.camera.rayTracer = new SimpleRayTracer(scene);
            }
            else {
                this.camera.rayTracer = null;
            }
            return this;
        }

        /**
         * Sets the aperture radius for depth of field effect.
         * @param apertureRadius the aperture radius (0 disables the effect)
         * @return the Builder instance
         */
        public Builder setAperture(double apertureRadius) {
            if (alignZero(apertureRadius )< 0) {
                throw new IllegalArgumentException("Aperture radius must be non-negative");
            }
            camera.apertureRadius = apertureRadius;
            return this;
        }

        /**
         * Sets the focal distance for the depth of field effect.
         * @param focalDistance the distance to the focal plane
         * @return the Builder instance
         */
        public Builder setFocalDistance(double focalDistance) {
            if (alignZero(focalDistance) <= 0) {
                throw new IllegalArgumentException("Focal distance must be positive");
            }
            camera.focalDistance = focalDistance;
            return this;
        }

        /**
         * Sets the number of rays per pixel for depth of field effect.
         * @param numRays the number of rays per pixel
         * @return the Builder instance
         */
        public Builder setDofRays(int numRays) {
            if (alignZero(numRays) <= 0) {
                throw new IllegalArgumentException("Number of rays must be positive");
            }
            camera.dofRays = numRays;
            return this;
        }

        /**
         * Moves the camera position by a specified delta vector.
         *
         * @param delta the vector to move the camera position
         * @return the Builder instance
         */
        public Builder moveP0(Vector delta) {
            camera.p0 = camera.p0.add(delta);
            return this;
        }


        /**
         * Rotates the camera around the vTo vector by a specified angle in degrees.
         *
         * @param angleDegrees the angle in degrees to rotate the camera
         * @return the Builder instance
         */
        public Builder rotateAroundVTo(double angleDegrees) {
            // Normalize the angle to the range [0, 360)
            double normalizedAngle = ((angleDegrees % 360) + 360) % 360;

            // Get the current camera vectors
            Vector vUp = camera.vUp;
            Vector vRight = camera.vRight;
            Vector vTo = camera.vTo;

            // Handle special cases for angles 0, 90, 180, and 270 degrees
            if (Math.abs(normalizedAngle - 90) < 1e-6) {
                camera.vUp = vRight;
                camera.vRight = vTo.crossProduct(camera.vUp).normalize();
                return this;
            } else if (Math.abs(normalizedAngle - 180) < 1e-6) {
                camera.vUp = vUp.scale(-1);
                camera.vRight = vRight.scale(-1);
                return this;
            } else if (Math.abs(normalizedAngle - 270) < 1e-6) {
                camera.vUp = vRight.scale(-1);
                camera.vRight = vTo.crossProduct(camera.vUp).normalize();
                return this;
            } else if (Math.abs(normalizedAngle) < 1e-6) {
                return this;
            }

            // Convert the angle to radians
            double angle = Math.toRadians(angleDegrees);

            // Calculate the new vUp and vRight vectors based on the rotation angle
            Vector newVUp = vUp.scale(Math.cos(angle)).add(vRight.scale(Math.sin(angle))).normalize();
            Vector newVRight = vTo.crossProduct(newVUp).normalize();

            // Update the camera vectors
            camera.vUp = newVUp;
            camera.vRight = newVRight;

            return this;
        }

        /**
         * Builds and returns the Camera object.
         *
         * @return the constructed Camera object
         * @throws MissingResourceException if any required property is missing
         */
        public Camera build() {
            //Strings for error messages
            String errorMessage = "Missing render data ";
            String className = "Camera";

            // Check if required properties are set
            if (camera.p0 == null) {
                throw new MissingResourceException(errorMessage, className, "Missing p0");
            }
            if (camera.vTo == null) {
                throw new MissingResourceException(errorMessage, className, "Missing vTo");
            }
            if (camera.vUp == null) {
                throw new MissingResourceException(errorMessage, className, "Missing vUp");
            }
            if (camera.vRight == null) {
                throw new MissingResourceException(errorMessage, className, "Missing vRight");
            }
            if (camera.distance == 0.0) {
                throw new MissingResourceException(errorMessage, className, "Missing distance");
            }
            if (camera.width == 0.0) {
                throw new MissingResourceException(errorMessage, className, "Missing width");
            }
            if (camera.height == 0.0) {
                throw new MissingResourceException(errorMessage, className, "Missing height");
            }

            // Check if the resolution of the view plane is positive
            if (alignZero(camera.nX)<=0 ) {
                throw new IllegalArgumentException("nX must be positive");
            }
            if (alignZero(camera.nY)<=0 ) {
                throw new IllegalArgumentException("nY must be positive");
            }

            // Check if the ray tracer is set, if not, initialize it with a default value
            if (camera.rayTracer == null) {
                camera.rayTracer = new SimpleRayTracer(null);
            }
            // Initialize the image writer with the specified resolution
            camera.imageWriter = new ImageWriter( camera.nX, camera.nY);

            // Calculate the center of the view plane
            camera.pc = camera.p0.add(camera.vTo.scale(camera.distance));
            try {
                // Clone the camera to ensure immutability
                return (Camera) camera.clone();
            } catch (CloneNotSupportedException e) {
                return null;
            }
        }
    }
}
