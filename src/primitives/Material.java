package primitives;

/**
 * This class represents the material properties of an object in a 3D scene.
 * It contains the coefficients for ambient, diffuse, and specular reflection.
 * The coefficients are represented as Double3 objects, which allow for RGB color representation.
 * The class provides methods to set the coefficients for ambient reflection (kA)
 * It contains the design pattern of chaining setters.
 * It is a PDS (Plain Data Structure) class, meaning it only contains data and no behavior.
 *
 * @author Tehila Shraga and Tova Tretiak
 */
public class Material {

    /** The ambient coefficient of the material. */
    public Double3 kA= Double3.ONE;
    /** The diffuse coefficient of the material. */
    public Double3 kD= Double3.ZERO;
    /** The specular coefficient of the material. */
    public Double3 kS= Double3.ZERO;
    /** The shininess of the material. */
    public int nShininess=0;

    /**
     * Default Constructor for the Material class.
     */
    public Material(){
    }

    /**
     * Sets method for kA- ambient coefficient.
     * @param kA the ambient coefficient of the material
     * @return the Material object itself
     */
    public Material setKA(Double3 kA) {
        this.kA = kA;
        return this;
    }

    /**
     * Sets method for kA- ambient coefficient.
     * @param kA -the ambient coefficient of the material is (kA, kA, kA)
     * @return the Material object itself
     */
    public Material setKA(double kA) {
        this.kA = new Double3(kA);
        return this;
    }

    /**
     * Sets method for kD- diffuse coefficient.
     * @param kD the diffuse coefficient of the material
     * @return the Material object itself
     */
    public Material setKD(Double3 kD) {
        this.kD = kD;
        return this;
    }

    /**
     * Sets method for kD- diffuse coefficient.
     * @param kD -the diffuse coefficient of the material is (kD, kD, kD)
     * @return the Material object itself
     */
    public Material setKD(double kD) {
        this.kD = new Double3(kD);
        return this;
    }

    /**
     * Sets method for kS- specular coefficient.
     * @param kS the specular coefficient of the material
     * @return the Material object itself
     */
    public Material setKS(Double3 kS) {
        this.kS = kS;
        return this;
    }

    /**
     * Sets method for kS- specular coefficient.
     * @param kS -the specular coefficient of the material is (kS, kS, kS)
     * @return the Material object itself
     */
    public Material setKS(double kS) {
        this.kS = new Double3(kS);
        return this;
    }

    /**
     * Sets method for nShininess- shininess coefficient.
     * @param nShininess the shininess coefficient of the material
     * @return the Material object itself
     */
    public Material setShininess(int nShininess) {
        this.nShininess = nShininess;
        return this;
    }

}
