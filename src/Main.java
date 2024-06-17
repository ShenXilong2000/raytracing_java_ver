import entity.*;
import entity.object.HittableList;
import entity.object.Sphere;
import modules.Camera;
import utils.CommonUtils;
import utils.FileUtils;

import java.awt.image.BufferedImage;

public class Main {


    public static void main(String[] args) {
        HittableList world = new HittableList();
        world.add(new Sphere(new Vec3(0, 0, -1), 0.5));
        world.add(new Sphere(new Vec3(0, -100.5, -1), 100));

        Camera camera = new Camera();
        camera.aspectRatio = 16.0 / 9.0;
        camera.imageWidth = 400;
        camera.samplesPerPixel = 10;
        camera.maxDepth = 50;
        camera.render(world);
    }
}