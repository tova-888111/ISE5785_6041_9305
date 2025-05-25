package primitives;

/**
 * This class represents the material properties of an object in a 3D scene.
 * It contains the coefficients for ambient, diffuse, and specular reflection.
 * The coefficients are represented as Double3 objects, which allow for RGB color representation.
 * The class provides methods to set the coefficients for ambient reflection (ka), diffuse reflection (kd),
 * specular reflection (ks), reflection (kr), transparency (kt), and shininess (nShininess).
 * It contains the design pattern of chaining setters.
 * It is a PDS (Plain Data Structure) class, meaning it only contains data and no behavior.
 *
 * @author Tehila Shraga and Tova Tretiak
 */
public class Material {

    /** The ambient coefficient of the material. */
    public Double3 ka= Double3.ONE;
    /** The diffuse coefficient of the material. */
    public Double3 kd= Double3.ZERO;
    /** The specular coefficient of the material. */
    public Double3 ks= Double3.ZERO;
    /** The reflection coefficient of the material. */
    public Double3 kr= Double3.ZERO;
    /** The transparency coefficient of the material. */
    public Double3 kt= Double3.ZERO;
    /** The shininess of the material. */
    public int nShininess=0;

    /**
     * Default Constructor for the Material class.
     */
    public Material(){
    }

    /**
     * Sets method for ka- ambient coefficient.
     * @param ka the ambient coefficient of the material
     * @return the Material object itself
     */
    public Material setKa(Double3 ka) {
        this.ka = ka;
        return this;
    }

    /**
     * Sets method for ka- ambient coefficient.
     * @param ka -the ambient coefficient of the material is (kA, kA, kA)
     * @return the Material object itself
     */
    public Material setKa(double ka) {
        this.ka = new Double3(ka);
        return this;
    }

    /**
     * Sets method for kd- diffuse coefficient.
     * @param kd the diffuse coefficient of the material
     * @return the Material object itself
     */
    public Material setKd(Double3 kd) {
        this.kd = kd;
        return this;
    }

    /**
     * Sets method for kd- diffuse coefficient.
     * @param kd -the diffuse coefficient of the material is (kD, kD, kD)
     * @return the Material object itself
     */
    public Material setKd(double kd) {
        this.kd = new Double3(kd);
        return this;
    }

    /**
     * Sets method for ks- specular coefficient.
     * @param ks the specular coefficient of the material
     * @return the Material object itself
     */
    public Material setKs(Double3 ks) {
        this.ks = ks;
        return this;
    }

    /**
     * Sets method for ks- specular coefficient.
     * @param ks -the specular coefficient of the material is (kS, kS, kS)
     * @return the Material object itself
     */
    public Material setKs(double ks) {
        this.ks = new Double3(ks);
        return this;
    }

    /**
     * Sets method for kr- reflection coefficient.
     * @param kr the reflection coefficient of the material
     * @return the Material object itself
     */
    public Material setKr(Double3 kr) {
        this.kr = kr;
        return this;
    }

    /**
     * Sets method for kr- reflection coefficient.
     * @param kr -the reflection coefficient of the material is (kR, kR, kR)
     * @return the Material object itself
     */
    public Material setKr(double kr) {
        this.kr = new Double3(kr);
        return this;
    }

    /**
     * Sets method for kt- transparency coefficient.
     * @param kt the transparency coefficient of the material
     * @return the Material object itself
     */
    public Material setKt(Double3 kt) {
        this.kt = kt;
        return this;
    }

    /**
     * Sets method for kt- transparency coefficient.
     * @param kt -the transparency coefficient of the material is (kT, kT, kT)
     * @return the Material object itself
     */
    public Material setKt(double kt) {
        this.kt = new Double3(kt);
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
