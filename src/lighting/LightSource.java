package lighting;

import primitives.Color;
import primitives.*;

/**
 *
 * @author Tehila Shraga and Tova Tretiak
 */
public interface LightSource {

    /**
     *
     * @param p
     * @return
     */
    Color getIntensity(Point p);

    /**
     *
     * @param p
     * @return
     */
    Vector getL(Point p);

}
