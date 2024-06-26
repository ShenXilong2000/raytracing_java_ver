import entity.*;
import entity.materials.Dielectric;
import entity.materials.Lambertian;
import entity.materials.Metal;
import entity.object.HittableList;
import entity.object.Sphere;
import modules.Camera;
import utils.CommonUtils;

public class Main {


    public static void main(String[] args) {
        HittableList world = new HittableList();

        Material groundMaterial = new Lambertian(new Color (0.5, 0.5, 0.5));
        world.add(new Sphere(new Vec3(0, -1000, 0), 1000, groundMaterial));
        for (int a = -11; a < 11; a++) {
            for (int b = -11; b < 11; b++) {
                double chooseMat = CommonUtils.randomDouble();
                Vec3 center = new Vec3(a + 0.9 * CommonUtils.randomDouble(), 0.2, b + 0.9 * CommonUtils.randomDouble());

                if (center.subtract(new Vec3(4, 0.2, 0)).length() > 0.9) {
                    Material sphereMaterial;

                    if (chooseMat < 0.8) {
                        // diffuse
                        Color albedo = new Color(Color.random().multiply(Color.random()));
                        sphereMaterial = new Lambertian(albedo);
                        world.add(new Sphere(center, 0.2, sphereMaterial));
                    } else if (chooseMat < 0.95) {
                        // metal
                        Color albedo = new Color(Color.random(0.5, 1));
                        double fuzz = CommonUtils.randomDouble(0, 0.5);
                        sphereMaterial = new Metal(albedo, fuzz);
                        world.add(new Sphere(center, 0.2, sphereMaterial));
                    } else {
                        // glass
                        sphereMaterial = new Dielectric(1.5);
                        world.add(new Sphere(center, 0.2, sphereMaterial));
                    }
                }
            }
        }

        Material material1 = new Dielectric(1.5);
        world.add(new Sphere(new Vec3(0, 1, 0), 1.0, material1));

        Material material2 = new Lambertian(new Color(0.4, 0.2, 0.1));
        world.add(new Sphere(new Vec3(-4, 1, 0), 1.0, material2));

        Material material3 = new Metal(new Color(0.7, 0.6, 0.5), 0.0);
        world.add(new Sphere(new Vec3(4, 1, 0), 1.0, material3));


        Camera camera = new Camera();
        camera.aspectRatio = 16.0 / 9.0;
        camera.imageWidth = 1920;
        camera.samplesPerPixel = 500;
        camera.maxDepth = 50;

        camera.vFov = 20;
        camera.lookFrom = new Vec3(13, 2, 3);
        camera.lookAt = new Vec3(0, 0, 0);
        camera.vUp = new Vec3(0, 1, 0);

        camera.deFocusAngle = 0.6;
        camera.focusDist = 10.0;

        camera.render(world);
    }
}