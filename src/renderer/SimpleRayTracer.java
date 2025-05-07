package renderer;

import primitives.*;
import scene.Scene;

public class SimpleRayTracer extends RayTracerBase {

    public SimpleRayTracer(Scene scene) {
        super(scene);
    }
    @Override
    public Color traceRay(Ray ray) {
       throw new UnsupportedOperationException("Not implemented yet");
    }
}
