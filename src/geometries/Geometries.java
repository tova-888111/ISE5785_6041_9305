package geometries;


import primitives.Point;
import primitives.Ray;

import java.util.LinkedList;
import java.util.List;

/**
 * The Geometries class serves as a container for multiple geometric objects.
 * It allows for the management and manipulation of a collection of geometries.
 * @author Tehila Shraga and Tova Tretiak
 */
public class Geometries implements Intersectable {
    /**
     * A list of geometries contained within this Geometries object.
     */
    private List<Intersectable> geometries =new LinkedList<>();

    /**
     * Default constructor for the Geometries class.
     */
    public Geometries(Intersectable... geometries){
       add(geometries);
        }
    }

    @Override
    public List<Point> findIntersections(Ray ray) {
        return null;
    }

    /**
     * Adds one or more geometries to the collection.
     * @param geometries the geometries to be added
     */
    public void add(Intersectable... geometries){
        for (Intersectable geometry:geometries) {
            this.geometries.add(geometry);
        }
    }
}
